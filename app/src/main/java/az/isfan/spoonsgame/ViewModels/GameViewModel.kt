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
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repo: GameDbRepoInterface
): ViewModel() {
    private val TAG = "isf_GameViewModel"

    private val DURATION_TO_TAKE_SPOON = 1000L //milliseconds

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

    fun spoonButtonClicked() {
        Log.i(TAG, "setTakeSpoonButtonClicked: ")

        _showTakeSpoonButton.update { false }
        _takeSpoonButtonClicked.update { true }
    }

    fun setupGame(playerCount: Int) {
        Log.i(TAG, "setupGame: playerCount=$playerCount")

        viewModelScope.launch {
            _game.update { Cavab.Loading }
            val newGame = GameData(playerCount)
            newGame.setupNewRound()
            _game.update { Cavab.Success(newGame) }
            nextPlayerTurn()
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
            _game.update { Cavab.Loading }
            val latestGame = withContext(Dispatchers.IO) { repo.getLatestGame() }
            if (latestGame != null && latestGame.getGameStatus() == GameStatusEnum.NOT_FINISHED) {
                val players = withContext(Dispatchers.IO) { repo.getAllPlayers() }
                val cards = withContext(Dispatchers.IO) { repo.getAllCards() }
                latestGame.load(players, cards)
                _game.update { Cavab.Success(latestGame) }
                nextPlayerTurn()
            }
            else {
                _game.update { Cavab.StandBy }
            }
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
                else {
                    launch(Dispatchers.IO) {
                        repo.deleteAllPlayers()
                    }

                    launch(Dispatchers.IO) {
                        repo.deleteAllCards()
                    }
                }
                launch(Dispatchers.IO) {
                    repo.insert(currentGame)
                }
            }
        }
    }

    private fun nextPlayerTurn() {
        Log.i(TAG, "nextPlayerTurn: ")

        viewModelScope.launch {
            if (game.value is Cavab.Success) {
                val currentGame = (game.value as Cavab.Success).data
                val turnPlayer = currentGame.nextPlayerTurn()
                if (!turnPlayer.isLocalUser) {
                    handleBotPlayTurn(turnPlayer)
                }
                else {
                    pickCardForLocal(turnPlayer)
                }
            }
        }
    }

    private fun processNewRound() {
        Log.i(TAG, "processNewRound: ")

        if (game.value is Cavab.Success) {
            val currentGame = (game.value as Cavab.Success).data
            currentGame.identifyLostPlayers()
            val gameStatus = currentGame.getGameStatus()
            _status.update { gameStatus }
            if (gameStatus == GameStatusEnum.NOT_FINISHED) {
                currentGame.setupNewRound()
                nextPlayerTurn()
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
            else nextPlayerTurn()
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