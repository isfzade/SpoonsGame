package az.isfan.spoonsgame.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.isfan.spoonsgame.Data.Enums.RankEnum
import az.isfan.spoonsgame.Data.Enums.SuitEnum
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.PlayerData
import az.isfan.spoonsgame.General.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.random.Random

class GameViewModel: ViewModel() {
    private val _players = MutableStateFlow<List<PlayerData>>(emptyList())
    val players = _players.asStateFlow()

    private val _discardedDeckCards = MutableStateFlow<List<CardData>>(emptyList())
    val discardedDeckCards = _discardedDeckCards.asStateFlow()

    private val _availableDeckCards = MutableStateFlow<List<CardData>>(emptyList())
    val availableDeckCards = _availableDeckCards.asStateFlow()

    private val _allCards = MutableStateFlow<List<CardData>>(emptyList())

    fun setupGame(playerCount: Int) {
        viewModelScope.launch(Dispatchers.Default) {
            val generatedPlayers = getPlayers(playerCount-1)
            val generatedCards = getAllCards()
            val deckCards = giveFourCardsToPlayersAndGetRest(generatedCards, generatedPlayers)
            _players.update { generatedPlayers }
            _allCards.update { generatedCards }
            _availableDeckCards.update { deckCards }
        }
    }

    private fun getPlayers(playerCount: Int): List<PlayerData> {
        val generatedPlayers = mutableListOf<PlayerData>()
        repeat(playerCount) { iteration ->
            generatedPlayers.add(
                PlayerData(
                    name = if (iteration == 0) "Me" else "Bot $iteration",
                    isBot = iteration != 0,
                )
            )
        }
        return generatedPlayers
    }

    private fun getAllCards(): List<CardData> {
        val generatedCards = mutableListOf<CardData>()
        RankEnum.entries.forEach { rank ->
            SuitEnum.entries.forEach { suit ->
                generatedCards.add(
                    CardData(
                        suit = suit,
                        rank = rank,
                        cardImageLink = Constants.CARD_IMAGE_LINKS["$rank-$suit"]!!
                    )
                )
            }
        }
        return generatedCards
    }

    private fun giveFourCardsToPlayersAndGetRest(generatedCards: List<CardData>, generatedPlayers: List<PlayerData>): List<CardData> {
        val shuffledCards = generatedCards.shuffled(random = Random(seed = System.currentTimeMillis())).toMutableList()
        generatedPlayers.forEach { player ->
            repeat(4) {
                player.addCard(shuffledCards[0])
                shuffledCards.removeAt(0)
            }
        }
        return shuffledCards
    }
}