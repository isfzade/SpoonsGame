package az.isfan.spoonsgame.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Enums.RankEnum
import az.isfan.spoonsgame.Data.Enums.SuitEnum
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.PlayerData
import az.isfan.spoonsgame.General.Cavab
import az.isfan.spoonsgame.General.Constants
import az.isfan.spoonsgame.General.getCardImageResource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel: ViewModel() {
    private val _players = MutableStateFlow<Cavab<List<PlayerData>>>(Cavab.StandBy)
    val players = _players.asStateFlow()

    private val _discardedDeckCards = MutableStateFlow<List<CardData>>(emptyList())
    val discardedDeckCards = _discardedDeckCards.asStateFlow()

    private val _availableDeckCards = MutableStateFlow<List<CardData>>(emptyList())
    val availableDeckCards = _availableDeckCards.asStateFlow()

    private val _allCards = MutableStateFlow<List<CardData>>(emptyList())

    fun setupGame(playerCount: Int) {
        viewModelScope.launch {
            _players.update { Cavab.Loading }
            val generatedPlayers = getPlayers(playerCount)
            val generatedCards = getAllCards()
            val deckCards = giveFourCardsToPlayersAndGetRemainingCards(generatedCards, generatedPlayers)
            _allCards.update { generatedCards }
            _availableDeckCards.update { deckCards }
            _players.update { Cavab.Success(generatedPlayers) }
        }
    }

    fun setupNewRound() {
        viewModelScope.launch {
            val generatedCards = getAllCards()
            val availablePlayers = (players.value as Cavab.Success).data.filter{it.isPlaying.value}
            val deckCards = giveFourCardsToPlayersAndGetRemainingCards(generatedCards, availablePlayers)
            _allCards.update { generatedCards }
            _availableDeckCards.update { deckCards }
            _discardedDeckCards.update { emptyList()}

            val oldFirstPlayer = availablePlayers.first{it.firstPlayerInRound.value}
            oldFirstPlayer.setLastPlayerInRounds(true)
            var nextPlayer: PlayerData? = null
            var currentChairId = availablePlayers.first{it.firstPlayerInRound.value}.chair.chairId
            while (nextPlayer == null) {
                currentChairId += 1
                if (currentChairId > availablePlayers.maxOf { it.chair.chairId }) {
                    currentChairId = 0
                }
                nextPlayer = availablePlayers.firstOrNull { currentChairId == it.chair.chairId }
            }
            nextPlayer.setFirstPlayerInRounds(true)
            nextPlayer.setPlayTurn(true)
        }
    }

    fun discardCard(card: CardData) {
        viewModelScope.launch {
            card.holder.value?.let{ holder ->
                val availablePlayers = (players.value as Cavab.Success).data.filter{it.isPlaying.value}
                val fromPlayer = availablePlayers.first { it.name == holder }
                if (fromPlayer.lastPlayerInRound.value) {
                    fromPlayer.removeCard(card)
                    card.setHolder(Constants.DISCARDED)
                    _discardedDeckCards.update {
                        it + card
                    }
                    if (fromPlayer.has4EqualCards()) {
                        setupNewRound()
                    }
                }
                else {
                    var currentChairId = fromPlayer.chair.chairId
                    var nextPlayer: PlayerData? = null
                    while (nextPlayer == null) {
                        currentChairId += 1
                        if (currentChairId > availablePlayers.maxOf { it.chair.chairId }) {
                            currentChairId = 0
                        }
                        nextPlayer = availablePlayers.firstOrNull { currentChairId == it.chair.chairId }
                    }
                    fromPlayer.removeCard(card)
                    fromPlayer.setPlayTurn(false)
                    if (!fromPlayer.has4EqualCards()) {
                        nextPlayer.addCard(card)
                        nextPlayer.setPlayTurn(true)
                    }
                    else {
                        setupNewRound()
                    }
                }
            }
        }
    }

    fun pickCardFromDeck(card: CardData, toPlayer: PlayerData) {
        viewModelScope.launch {
            toPlayer.addCard(card)
            _availableDeckCards.update { existingCards ->
                existingCards.filter {
                    !(it.rank == card.rank && it.suit == card.suit)
                }
            }
        }
    }

    private fun getPlayers(playerCount: Int): List<PlayerData> {
        val generatedPlayers = mutableListOf<PlayerData>()
        repeat(playerCount) { iteration ->
            val player = PlayerData(
                name = if (iteration == 0) "Me" else "Bot $iteration",
                isLocalUser = iteration == 0,
                chair = ChairEnum.getById(iteration)
            )
            player.setFirstPlayerInRounds(iteration == 0)
            player.setLastPlayerInRounds(iteration == playerCount-1)
            player.setIsPlaying(true)
            player.setPlayTurn(iteration == 0)
            generatedPlayers.add(player)
        }
        return generatedPlayers
    }

    private fun getAllCards(): List<CardData> {
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
        return generatedCards
    }

    private fun giveFourCardsToPlayersAndGetRemainingCards(generatedCards: List<CardData>, generatedPlayers: List<PlayerData>): List<CardData> {
        val shuffledCards = generatedCards.shuffled(random = Random(seed = System.currentTimeMillis())).toMutableList()
        generatedPlayers.forEach { player ->
            if (player.isPlaying.value) {
                repeat(4) {
                    val selectedCard = shuffledCards[0]
                    player.addCard(selectedCard)
                    shuffledCards.removeAt(0)
                }
            }
        }
        return shuffledCards
    }
}