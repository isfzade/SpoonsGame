package az.isfan.spoonsgame.Data.Models

import az.isfan.spoonsgame.Data.Enums.ChairEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PlayerData(
    val name: String,
    val isLocalUser: Boolean,
    val chair: ChairEnum,
) {
    private val _cards = MutableStateFlow<List<CardData>>(emptyList())
    val cards = _cards.asStateFlow()

    private fun setCards(newCards: List<CardData>){
        _cards.update { newCards }
    }

    fun addCard(card: CardData) {
        setCards(cards.value+card)
    }

    fun removeCard(card: CardData) {
        val newCards = cards.value.filter { it.rank != card.rank && it.suit != card.suit }
        setCards(newCards)
    }
}
