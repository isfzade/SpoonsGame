package az.isfan.spoonsgame.ViewModels

import android.util.Log
import androidx.compose.animation.core.updateTransition
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.isfan.spoonsgame.Data.Db.Repos.GameDbRepoInterface
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Enums.GameStatusEnum
import az.isfan.spoonsgame.Data.Enums.RankEnum
import az.isfan.spoonsgame.Data.Enums.SuitEnum
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.GameData
import az.isfan.spoonsgame.Data.Models.PlayerData
import az.isfan.spoonsgame.General.Cavab
import az.isfan.spoonsgame.General.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.math.log
import kotlin.random.Random

@HiltViewModel
class GameViewModel @Inject constructor(
    private val repo: GameDbRepoInterface
): ViewModel() {
    private val TAG = "isf_GameViewModel"

    private val DURATION_TO_TAKE_SPOON = 1000L //milliseconds

    private val _isGameReady = MutableStateFlow<Cavab<Boolean>>(Cavab.StandBy)
    val isGameReady = _isGameReady.asStateFlow()

    private val _players = MutableStateFlow<List<PlayerData>>(emptyList())
    val players = _players.asStateFlow()

    private val _discardedDeckCards = MutableStateFlow<List<CardData>>(emptyList())
    val discardedDeckCards = _discardedDeckCards.asStateFlow()

    private val _availableDeckCards = MutableStateFlow<List<CardData>>(emptyList())
    val availableDeckCards = _availableDeckCards.asStateFlow()

    private val _takeSpoonButtonClicked = MutableStateFlow(false)
    val takeSpoonButtonClicked = _takeSpoonButtonClicked.asStateFlow()

    private val _showTakeSpoonButton = MutableStateFlow(false)
    val showTakeSpoonButton = _showTakeSpoonButton.asStateFlow()

    private val _status = MutableStateFlow(GameStatusEnum.NOT_FINISHED)
    val status = _status.asStateFlow()

    private var numberOfPlayers: Int = 0
    private var roundCount: Int = 0

    fun setupNewGame(playerCount: Int) {
        Log.i(TAG, "setupNewGame: playerCount=$playerCount")

        viewModelScope.launch {
            _isGameReady.update { Cavab.Loading }
            numberOfPlayers = playerCount
            setupNewRound()
            playTurn()
            _isGameReady.update { Cavab.Success(true) }
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

        }
    }

    fun localSelectsCard(card: CardData) {
        Log.i(TAG, "localSelectsCard: card = $card")

        viewModelScope.launch {
            discardCard(card)
            if (isRoundFinished()) {
                endRound()
                giveLetter()
                proceedNewRound()
            } else {
                playTurn()
            }
        }
    }

    fun spoonButtonClicked() {
        Log.i(TAG, "setTakeSpoonButtonClicked: ")

        _showTakeSpoonButton.update { false }
        _takeSpoonButtonClicked.update { true }
    }

    private suspend fun proceedNewRound() {
        Log.i(TAG, "proceedNewRound: ")

        kickPlayers()
        val gameStatus = getGameStatus()
        _status.update { gameStatus }
        if (gameStatus == GameStatusEnum.NOT_FINISHED) {
            setupNewRound()
            playTurn()
        }
    }

    private fun setupNewRound() {
        Log.i(TAG, "setupNewRound: ")

        var availablePlayers = emptyList<PlayerData>()
        roundCount += 1
        availablePlayers = if (roundCount == 1) {
            getNewPlayers(numberOfPlayers)
        } else {
            players.value.map { player ->
                player.copy(
                    cards = emptyList(),
                    playTurn = false
                )
            }
        }
        if (roundCount != 1) {
            val oldFirstPlayer = availablePlayers.first{ it.firstPlayer }
            val newFirstPlayer = getNextPlayer(oldFirstPlayer)
            availablePlayers = availablePlayers.map { it.copy(firstPlayer = newFirstPlayer.isSame(it)) }
            availablePlayers = availablePlayers.map { it.copy(firstPlayer = newFirstPlayer.isSame(it)) }
        }

        val generatedCards = getNewCards()
        val playersWithCards = giveFourCardsToPlayers(generatedCards, availablePlayers)
        val availableCards = getAvailableDecCards(generatedCards, playersWithCards)
        _availableDeckCards.update { availableCards }
        _discardedDeckCards.update { emptyList() }
        _players.update { playersWithCards }
    }

    private fun isRoundFinished(): Boolean {
        Log.i(TAG, "isRoundFinished:")

        val player = getTurnPlayer()!!
        return has4EqualCards(player)
    }

    private fun setTurnPlayer() {
        Log.i(TAG, "setTurnPlayer: ")

        val lastTurnPlayer = getTurnPlayer()
        if (lastTurnPlayer != null) {
            if (lastTurnPlayer.cards.size == 4) {
                val nextPlayer = getNextPlayer(lastTurnPlayer)
                setTurnToPlayer(nextPlayer)
            }
        }
        else {
            setTurnToPlayer(getFirstPlayer())
        }
    }

    private suspend fun playTurn() {
        Log.i(TAG, "proceedTurn: ")

        setTurnPlayer()
        pickCardFromDeckIfFirstPlayer()
        if (!isLocalTurn()) {
            discardCardFromBot()
            if (isRoundFinished()) {
                endRound()
                giveLetter()
                proceedNewRound()
            }
            else playTurn()
        }
    }

    private fun pickCardFromDeckIfFirstPlayer() {
        Log.i(TAG, "pickCardFromDeckIfFirstPlayer: ")

        val player = getTurnPlayer()!!
        if (player.firstPlayer && player.cards.size == 4) {
            var card = availableDeckCards.value.firstOrNull()
            if (card == null) {
                generateAvailableCardsFromDiscarded()
                card = availableDeckCards.value.first()
            }
            addCardToPlayer(player, card)
            removeCardFromAvailableSet(card)
        }
    }

    private suspend fun giveLetter() {
        Log.i(TAG, "giveLetter: ")
        val player = getTurnPlayer()!!
        if (player.isLocalUser) {
            giveLetterToRandomBot()
        }
        else {
            if (players.value.size != 2) {
                _showTakeSpoonButton.update { true }
                delay(DURATION_TO_TAKE_SPOON)
                _showTakeSpoonButton.update { false }
                if (takeSpoonButtonClicked.value) {
                    giveLetterToRandomBot(except = player)
                }
                else giveLetterTo(getLocalPlayer())
            }
            else giveLetterTo(getLocalPlayer())
        }
    }

    private fun giveLetterTo(player: PlayerData) {
        Log.i(TAG, "giveLetterTo: player = $player")

        _players.update { oldPlayers ->
            oldPlayers.map { it.copy(
                lettersSize =  if (it.isSame(player)) it.lettersSize+1 else it.lettersSize
            ) }
        }
    }

    private fun discardCardFromBot() {
        Log.i(TAG, "discardCardFromBot: ")

        val player = getTurnPlayer()!!
        val card = getWorstCardFromPlayer(player)
        discardCard(card)
    }

    private fun discardCard(card: CardData) {
        Log.i(TAG, "discardCard: card=$card")

        val fromPlayer = getCardHolder(card)
        if (isLastPlayer(fromPlayer)) {
            discardCardFromLastPlayer(card)
        }
        else {
            passCardToNextPlayer(card)
        }
    }

    private fun getCardHolder(card: CardData): PlayerData {
        Log.i(TAG, "getCardHolder: card = $card")

        return players.value.first { player ->
            player.cards.any {
                it == card
            }
        }
    }

    private fun getWorstCardFromPlayer(player: PlayerData): CardData {
        Log.i(TAG, "getWorstCardFromPlayer: player = $player")

        val cardCount = hashMapOf<SuitEnum, Int>()
        SuitEnum.entries.forEach { suit ->
            val count = player.cards.count { it.suit == suit }
            if (count > 0) {
                cardCount[suit] = count
            }
        }
        return player.cards.first { card ->
            card.suit == cardCount.minBy { it.value }.key
        }
    }

    private fun getGameStatus(): GameStatusEnum {
        Log.i(TAG, "getGameStatus: ")

        players.value.filter { !it.kicked }.firstOrNull{it.isLocalUser} ?: return GameStatusEnum.LOST

        if (players.value.none { !it.kicked && !it.isLocalUser }) return GameStatusEnum.WON
        return GameStatusEnum.NOT_FINISHED
    }

    private fun kickPlayers() {
        Log.i(TAG, "kickPlayers: ")

        _players.update { oldPlayers ->
            oldPlayers.map { it.copy(kicked = it.lettersSize > 4) }
        }
    }

    private fun giveLetterToRandomBot(except: PlayerData? = null) {
        Log.i(TAG, "giveLetterToRandomBot: except=$except")

        players.value.filter {
            !it.kicked && !it.isLocalUser &&
            if (except != null) it.isSame(except) else true}
            .shuffled(random = Random(System.currentTimeMillis())).firstOrNull()?.let{
                giveLetterTo(it)
            }
    }

    private fun endRound() {
        Log.i(TAG, "endRound: ")

        val playerCards = mutableListOf<CardData>()
        _players.update { oldPlayers ->
            oldPlayers.map {
                playerCards.addAll(it.cards)
                it.copy(cards = emptyList())
            }
        }
        _discardedDeckCards.update { discardedCards ->
            discardedCards.map{it.copy()} + availableDeckCards.value.map { it.copy() } + playerCards
        }
        _availableDeckCards.update { emptyList() }
    }

    private fun discardCardFromLastPlayer(card: CardData) {
        Log.i(TAG, "discardCardFromLastPlayer: card=$card")

        val holder = getCardHolder(card)
        removeCardFromPlayer(holder, card)
        addCardToDiscardedSet(card)
    }

    private fun passCardToNextPlayer(card: CardData) {
        Log.i(TAG, "passCardToNextPlayer: card=$card")

        val holder = getCardHolder(card)
        val nextPlayer = getNextPlayer(holder)
        removeCardFromPlayer(holder, card)
        addCardToPlayer(nextPlayer, card)
    }

    private fun getNextPlayer(currentPlayer: PlayerData): PlayerData {
        Log.i(TAG, "getNextPlayer: currentPlayer=$currentPlayer")

        val availablePlayers = players.value.filter{!it.kicked}
        var currentChairId = currentPlayer.chair.chairId
        var nextPlayer: PlayerData? = null
        while (nextPlayer == null) {
            currentChairId += 1
            if (currentChairId > availablePlayers.maxOf { it.chair.chairId }) {
                currentChairId = 0
            }
            nextPlayer = availablePlayers.firstOrNull { currentChairId == it.chair.chairId }
        }
        return nextPlayer
    }

    private fun getPreviousPlayer(currentPlayer: PlayerData): PlayerData {
        Log.i(TAG, "getPreviousPlayer: currentPlayer=$currentPlayer")

        val availablePlayers = players.value.filter{!it.kicked}
        var currentChairId = currentPlayer.chair.chairId
        var prePlayer: PlayerData? = null
        while (prePlayer == null) {
            currentChairId -= 1
            if (currentChairId < 0) {
                currentChairId = availablePlayers.maxOf { it.chair.chairId }
            }
            prePlayer = availablePlayers.firstOrNull { currentChairId == it.chair.chairId }
        }
        return prePlayer
    }

    private fun getNewCards(): List<CardData> {
        Log.i(TAG, "getNewCards: ")

        val generatedCards = mutableListOf<CardData>()
        RankEnum.entries.forEach { rank ->
            SuitEnum.entries.forEach { suit ->
                val newCard = CardData(
                    suit = suit,
                    rank = rank,
                )
                generatedCards.add(newCard)
            }
        }
        return generatedCards.shuffled(random = Random(seed = System.currentTimeMillis()))
    }

    private fun giveFourCardsToPlayers(generatedCards: List<CardData>, generatedPlayers: List<PlayerData>): List<PlayerData> {
        Log.i(TAG, "giveFourCardsToPlayers: ")

        var startIndex = -4
        return generatedPlayers.map { player ->
            startIndex += 4
            val endIndex = startIndex + 4
            player.copy(
                cards = if (player.kicked) emptyList()
                    else {
                        generatedCards.subList(startIndex, endIndex)
                    }
            )
        }
    }

    private fun getAvailableDecCards(generatedCards: List<CardData>, generatedPlayers: List<PlayerData>): List<CardData> {
        Log.i(TAG, "getAvailableDecCards: ")

        val mutableCards = generatedCards.toMutableList()
        generatedPlayers.forEach { player ->
            player.cards.forEach { card ->
                mutableCards.removeIf{it == card}
            }
        }
        return mutableCards
    }

    private fun getNewPlayers(playerCount: Int): List<PlayerData> {
        Log.i(TAG, "getNewPlayers: playerCount=$playerCount")

        val generatedPlayers = mutableListOf<PlayerData>()
        repeat(playerCount) { iteration ->
            val player = PlayerData(
                name = if (iteration == 0) "Me" else "Bot $iteration",
                isLocalUser = iteration == 0,
                chair = ChairEnum.getById(iteration),
                firstPlayer = iteration == 0
            )
            generatedPlayers.add(player)
        }
        return generatedPlayers
    }

    private fun generateAvailableCardsFromDiscarded() {
        Log.i(TAG, "generateAvailableCardsFromDiscarded: ")

        _availableDeckCards.update { discardedDeckCards.value.shuffled(random = Random(seed = System.currentTimeMillis())).map{it.copy()} }
        _discardedDeckCards.update { emptyList() }
    }

    private fun isLastPlayer(player: PlayerData): Boolean {
        Log.i(TAG, "isLastPlayer: ")

        return getPreviousPlayer(getFirstPlayer()).isSame(player)
    }

    private fun addCardToPlayer(player: PlayerData, card:CardData) {
        Log.i(TAG, "addCardToPlayer: ")

        _players.update { oldPlayers ->
            oldPlayers.map { oldPlayer ->
                oldPlayer.copy(
                    cards = if (oldPlayer.isSame(player)) oldPlayer.cards+card
                            else oldPlayer.cards
                )
            }
        }
    }

    private fun removeCardFromPlayer(player: PlayerData, card: CardData) {
        Log.i(TAG, "removeCardFromPlayer: ")

        _players.update { oldPlayers ->
            oldPlayers.map { oldPlayer ->
                oldPlayer.copy(
                    cards = if (oldPlayer.isSame(player)) oldPlayer.cards.filter { it!=card }
                    else oldPlayer.cards
                )
            }
        }
    }

    private fun removeCardFromAvailableSet(card: CardData) {
        Log.i(TAG, "removeCardFromAvailableSet: ")

        _availableDeckCards.update {oldCards ->
            oldCards.filter{it != card}.map { it.copy() }
        }
    }

    private fun addCardToDiscardedSet(card: CardData) {
        Log.i(TAG, "addCardToDiscardedSet: ")

        _discardedDeckCards.update {oldCards ->
            oldCards.map { oldCard ->
                oldCard.copy()
            } + card.copy()
        }
    }

    private fun has4EqualCards(player: PlayerData): Boolean {
        Log.i(TAG, "has4EqualCards: player=$player")

        if (player.cards.size != 4) return false
        return player.cards.count { it.suit == player.cards.first().suit } == 4
    }

    private fun setTurnToPlayer(player: PlayerData) {
        _players.update { oldPlayers ->
            oldPlayers.map {
                it.copy(
                    playTurn = it.isSame(player)
                )
            }
        }
    }

    private fun isLocalTurn(): Boolean {
        return players.value.first { !it.kicked && it.playTurn}.isLocalUser
    }

    private fun getTurnPlayer(): PlayerData? {
        return players.value.firstOrNull { !it.kicked && it.playTurn }
    }

    private fun getFirstPlayer(): PlayerData {
        return players.value.first { !it.kicked && it.firstPlayer }
    }

    private fun getLocalPlayer(): PlayerData {
        return players.value.first {it.isLocalUser}
    }
}