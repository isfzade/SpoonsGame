package az.isfan.spoonsgame.Data.Models

import android.util.Log
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Enums.GameStatusEnum
import az.isfan.spoonsgame.Data.Enums.RankEnum
import az.isfan.spoonsgame.Data.Enums.SuitEnum
import az.isfan.spoonsgame.General.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

data class GameData(
    val playerCount: Int,
) {
    private val TAG = "isf_GameData"

    private val _players = MutableStateFlow<List<PlayerData>>(emptyList())
    val players = _players.asStateFlow()

    private val _discardedDeckCards = MutableStateFlow<List<CardData>>(emptyList())
    val discardedDeckCards = _discardedDeckCards.asStateFlow()

    private val _availableDeckCards = MutableStateFlow<List<CardData>>(emptyList())
    val availableDeckCards = _availableDeckCards.asStateFlow()

    private val _roundCount = MutableStateFlow(0)
    val roundCount = _roundCount.asStateFlow()

    private val _allCards = MutableStateFlow<List<CardData>>(emptyList())
    val allCards = _allCards.asStateFlow()

    fun incRoundCount() {
        Log.i(TAG, "incRoundCount:")
        _roundCount.update { it+1 }
    }

    fun setupNewRound() {
        Log.i(TAG, "setupNewRound: ")

        incRoundCount()
        if (roundCount.value == 1) {
            val generatedPlayers = getNewPlayers(playerCount)
            _players.update { generatedPlayers }
        }
        else {
            players.value.forEach {
                it.removeAllCards()
                it.setPlayTurn(false)
            }
        }
        val availablePlayers = players.value.filter{it.isPlaying.value}
        if (roundCount.value == 1) {
            val localPlayer = availablePlayers.first {it.isLocalUser}
            val prePlayer = getPreviousPlayer(localPlayer)
            localPlayer.setFirstPlayerInRounds(true)
            prePlayer.setLastPlayerInRounds(true)
        }
        else {
            val oldFirstPlayer = players.value.first{ it.firstPlayerInRound.value }
            availablePlayers.forEach { it.setFirstPlayerInRounds(false) }
            availablePlayers.forEach { it.setLastPlayerInRounds(false) }
            val newFirstPlayer = getNextPlayer(oldFirstPlayer)
            val newLastPlayer = getPreviousPlayer(newFirstPlayer)
            newFirstPlayer.setFirstPlayerInRounds(true)
            newLastPlayer.setLastPlayerInRounds(true)
        }

        val generatedCards = getNewCards()
        val deckCards = giveFourCardsToPlayersAndGetRemainingCards(generatedCards, availablePlayers)
        _availableDeckCards.update { deckCards }
        _discardedDeckCards.update { emptyList() }
        _allCards.update { generatedCards }
    }

    fun play() {
        Log.i(TAG, "play: ")

        val availablePlayers = players.value.filter { it.isPlaying.value }
        val lastTurnPlayer = availablePlayers.firstOrNull { it.playTurn.value }
        if (lastTurnPlayer != null) {
            if (lastTurnPlayer.cards.value.size == 4) {
                val nextPlayer = getNextPlayer(lastTurnPlayer)
                lastTurnPlayer.setPlayTurn(false)
                nextPlayer.setPlayTurn(true)
            }
        }
        else {
            val firstPlayer = availablePlayers.first { it.firstPlayerInRound.value }
            firstPlayer.setPlayTurn(true)
        }
    }

    fun pickCardFromDeckIfFirstPlayer(player: PlayerData) {
        Log.i(TAG, "pickCardFromDeck: player=$player")

        if (player.firstPlayerInRound.value && player.cards.value.size == 4) {
            var card = availableDeckCards.value.firstOrNull()
            if (card == null) {
                generateAvailableCardsFromDiscarded()
                card = availableDeckCards.value.first()
            }
            player.addCard(card)
            _availableDeckCards.update { existingCards ->
                existingCards.filter {
                    !(it.rank == card.rank && it.suit == card.suit)
                }
            }
        }
    }

    fun discardCard(card: CardData) {
        Log.i(TAG, "discardCard: card=$card")

        val fromPlayer = getCardHolder(card)
        if (fromPlayer.lastPlayerInRound.value) {
            discardCardFromLastPlayer(card)
        }
        else {
            passCardToNextPlayer(card)
        }
    }

    fun getCardHolder(card: CardData): PlayerData {
        Log.i(TAG, "getCardHolder: card = $card")

        return players.value.first { it.name == card.holder.value }
    }

    fun getWorstCard(player: PlayerData): CardData {
        Log.i(TAG, "getWorstCard: player = $player")

        val cardCount = hashMapOf<SuitEnum, Int>()
        SuitEnum.entries.forEach { suit ->
            val count = player.cards.value.count { it.suit == suit }
            if (count > 0) {
                cardCount[suit] = count
            }
        }
        return player.cards.value.first { card ->
            card.suit == cardCount.minBy { it.value }.key
        }
    }

    fun getGameStatus(): GameStatusEnum {
        Log.i(TAG, "checkGameStatus: ")

        players.value.filter { it.isPlaying.value }.firstOrNull{it.isLocalUser} ?: return GameStatusEnum.LOST

        if (players.value.none { it.isPlaying.value && !it.isLocalUser }) return GameStatusEnum.WON
        return GameStatusEnum.NOT_FINISHED
    }

    fun identifyLostPlayers() {
        Log.i(TAG, "identifyLostPlayers: ")

        players.value.forEach { player ->
            if (player.lettersCollected.value >= 5) {
                player.setIsPlaying(false)
            }
        }
    }

    fun giveLetterToAvailablePlayerFromLocal(winner: PlayerData) {
        Log.i(TAG, "giveLetterToAvailablePlayerFromLocal: winner=$winner")

        players.value.firstOrNull{it.isPlaying.value && !it.isLocalUser && it.name!=winner.name}
            ?.incLetterCollected()
    }

    fun endRound() {
        Log.i(TAG, "endRound: ")

        players.value.forEach { player ->
            player.setCards(emptyList())
        }
        _availableDeckCards.update { emptyList() }
        _allCards.value.forEach { it.setHolder(Constants.DISCARDED) }
        _discardedDeckCards.update { allCards.value }
    }

    private fun discardCardFromLastPlayer(card: CardData) {
        Log.i(TAG, "discardCardFromLastPlayer: card=$card")

        val player = getCardHolder(card)
        player.removeCard(card)
        card.setHolder(Constants.DISCARDED)
        _discardedDeckCards.update {
            it + card
        }
    }

    private fun passCardToNextPlayer(card: CardData) {
        Log.i(TAG, "passCardToNextPlayer: card=$card")

        val player = getCardHolder(card)
        val nextPlayer = getNextPlayer(player)
        player.removeCard(card)
        nextPlayer.addCard(card)
    }

    private fun passTurnToNextPlayer(fromPlayer: PlayerData) {
        Log.i(TAG, "passTurnToNextPlayer: fromPlayer=$fromPlayer")
        
        val nextPlayer = getNextPlayer(fromPlayer)
        fromPlayer.setPlayTurn(false)
        nextPlayer.setPlayTurn(true)
    }
    
    private fun getNextPlayer(currentPlayer: PlayerData): PlayerData {
        Log.i(TAG, "getNextPlayer: currentPlayer=$currentPlayer")

        val availablePlayers = players.value.filter{it.isPlaying.value}
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

        val availablePlayers = players.value.filter{it.isPlaying.value}
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
                newCard.setHolder(Constants.AVAILABLE)
                generatedCards.add(newCard)
            }
        }
        return generatedCards.shuffled(random = Random(seed = System.currentTimeMillis())).toMutableList()
    }

    private fun giveFourCardsToPlayersAndGetRemainingCards(generatedCards: List<CardData>, generatedPlayers: List<PlayerData>): List<CardData> {
        Log.i(TAG, "giveFourCardsToPlayersAndGetRemainingCards: ")

        val mutableCards = generatedCards.toMutableList()
        generatedPlayers.forEach { player ->
            if (player.isPlaying.value) {
                repeat(4) {
                    val selectedCard = mutableCards[0]
                    player.addCard(selectedCard)
                    mutableCards.removeAt(0)
                }
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
                chair = ChairEnum.getById(iteration)
            )
            generatedPlayers.add(player)
        }
        return generatedPlayers
    }

    private fun generateAvailableCardsFromDiscarded() {
        Log.i(TAG, "generateAvailableCardsFromDiscarded: ")

        discardedDeckCards.value.forEach {
            it.setHolder(Constants.AVAILABLE)
        }
        _availableDeckCards.update { discardedDeckCards.value.shuffled(random = Random(seed = System.currentTimeMillis())) }
        _discardedDeckCards.update { emptyList() }
    }
}
