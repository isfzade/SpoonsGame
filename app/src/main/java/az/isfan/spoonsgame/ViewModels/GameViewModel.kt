package az.isfan.spoonsgame.ViewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.isfan.spoonsgame.Data.Db.Repos.GameDbRepoInterface
import az.isfan.spoonsgame.Data.Enums.GameStatusEnum
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.GameData
import az.isfan.spoonsgame.Data.Models.PlayerData
import az.isfan.spoonsgame.General.Cavab
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repo: GameDbRepoInterface
): ViewModel() {
    private val TAG = "isf_GameViewModel"

    private val DURATION_TO_TAKE_SPOON = 10000L //milliseconds

    private val _game = MutableStateFlow<Cavab<GameData>>(Cavab.StandBy)
    val game = _game.asStateFlow()

    private val _takeSpoonButtonClicked = MutableStateFlow(false)
    val takeSpoonButtonClicked = _takeSpoonButtonClicked.asStateFlow()

    private val _showTakeSpoonButton = MutableStateFlow(false)
    val showTakeSpoonButton = _showTakeSpoonButton.asStateFlow()

    private val _showGiveLetterButton = MutableStateFlow(false)
    val showGiveLetterButton = _showGiveLetterButton.asStateFlow()

    private val _status = MutableStateFlow(GameStatusEnum.NOT_FINISHED)
    val status = _status.asStateFlow()

    init {
        Log.i(TAG, "init: ")
        listenPlayerTurn()
    }

    fun setTakeSpoonButtonClicked() {
        Log.i(TAG, "setTakeSpoonButtonClicked: ")

        _takeSpoonButtonClicked.update { true }
    }

    fun setupGame(playerCount: Int) {
        Log.i(TAG, "setupGame: playerCount=$playerCount")

        viewModelScope.launch {
            _game.update { Cavab.Loading }
            val newGame = GameData(playerCount)
            newGame.setupNewRound()
            newGame.play()
            _game.update { Cavab.Success(newGame) }
        }
    }

    fun giveLetterToBot(player: PlayerData) {
        Log.i(TAG, "giveLetterToBot: player = $player")

        viewModelScope.launch {
            player.incLetterCollected()
            _showGiveLetterButton.update { false }
            processNewRound()
        }
    }

    fun localSelectsCard(card: CardData) {
        Log.i(TAG, "localSelectsCard: card = $card")

        viewModelScope.launch {
            discardAndCheckIfAffects(card)
        }
    }

    fun loadLastGame() {
        Log.i(TAG, "loadLastGame: ")

        viewModelScope.launch {

        }
    }

    fun save() {
        Log.i(TAG, "save: ")

        CoroutineScope(Dispatchers.Default).launch {
            if (game.value is Cavab.Success) {
                val currentGame = (game.value as Cavab.Success).data
                if (currentGame.getGameStatus() == GameStatusEnum.NOT_FINISHED) {
                    launch(Dispatchers.IO) {
                        repo.deleteAllPlayers()
                        currentGame.players.value.forEach { player ->
                            repo.insert(player)
                        }
                    }

                    launch(Dispatchers.IO) {
                        currentGame.allCards.value.forEach { card ->
                            repo.insert(card)
                        }
                    }
                }
            }
        }
    }

    private fun processNewRound() {
        Log.i(TAG, "processNewRound: ")

        if (game.value is Cavab.Success) {
            val currentGame = (game.value as Cavab.Success).data
            currentGame.identifyLostPlayers()
            val status = currentGame.getGameStatus()
            _status.update { GameStatusEnum.NOT_FINISHED }
            if (status == GameStatusEnum.NOT_FINISHED) {
                currentGame.setupNewRound()
                currentGame.play()
            }
        }
    }

    private fun listenPlayerTurn() {
        Log.i(TAG, "listenPlayerTurn: ")

        viewModelScope.launch {
            game.collect { cavab ->
                if (cavab is Cavab.Success) {
                    launch {
                        cavab.data.players.collect { players ->
                            players.forEach {  player ->
                                launch {
                                    player.playTurn.collect { playTurn ->
                                        if (playTurn) {
                                            if (!player.isLocalUser) {
                                                handleBotPlayTurn(player)
                                            }
                                            else {
                                                pickCardForLocal(player)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private fun pickCardForLocal(player: PlayerData) {
        Log.i(TAG, "handleBotPlayTurn: player = $player")

        if (game.value is Cavab.Success) {
            val currentGame = (game.value as Cavab.Success).data
            currentGame.pickCardFromDeckIfFirstPlayer(player)
        }
    }

    private suspend fun handleBotPlayTurn(player: PlayerData) {
        Log.i(TAG, "handleBotPlayTurn: player = $player")

        if (game.value is Cavab.Success) {
            val currentGame = (game.value as Cavab.Success).data
            currentGame.pickCardFromDeckIfFirstPlayer(player)
            val card = currentGame.getWorstCard(player)
            discardAndCheckIfAffects(card)
        }
    }

    private suspend fun discardAndCheckIfAffects(card: CardData) {
        Log.i(TAG, "discardAndCheckIfAffects: card=$card")

        if (game.value is Cavab.Success) {
            val currentGame = (game.value as Cavab.Success).data
            val player = currentGame.getCardHolder(card)
            currentGame.discardCard(card)
            if (player.has4EqualCards()) thereIsWinner(player)
            else currentGame.play()
        }
    }

    private suspend fun thereIsWinner(player: PlayerData) {
        Log.i(TAG, "thereIsWinner: player = $player")
        if (game.value is Cavab.Success) {
            val currentGame = (game.value as Cavab.Success).data
            currentGame.endRound()
            if (player.isLocalUser) {
                _showGiveLetterButton.update { true }
            }
            else {
                _showTakeSpoonButton.update { true }
                delay(DURATION_TO_TAKE_SPOON)
                _showTakeSpoonButton.update { false }
                checkTakeSpoonClicked(player)
                processNewRound()
            }
        }
    }

    private fun checkTakeSpoonClicked(winner: PlayerData) {
        Log.i(TAG, "checkTakeSpoonClicked: ")

        if (game.value is Cavab.Success) {
            val currentGame = (game.value as Cavab.Success).data
            if (takeSpoonButtonClicked.value) {
                currentGame.giveLetterToAvailablePlayerFromLocal(winner)
                _takeSpoonButtonClicked.update { false }
            }
            else {
                currentGame.players.value.first { it.isLocalUser }.incLetterCollected()
            }
        }
    }
}