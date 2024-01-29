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
    private val _playTurn = MutableStateFlow<Boolean>(false)
    val playTurn = _playTurn.asStateFlow()

    private val _cards = MutableStateFlow<List<CardData>>(emptyList())
    val cards = _cards.asStateFlow()

    private val _firstPlayerInRound = MutableStateFlow(false)
    val firstPlayerInRound = _firstPlayerInRound.asStateFlow()

    private val _lastPlayerInRound = MutableStateFlow(false)
    val lastPlayerInRound = _lastPlayerInRound.asStateFlow()

    private val _isPlaying = MutableStateFlow(true)
    val isPlaying = _isPlaying.asStateFlow()

    private fun setCards(newCards: List<CardData>){
        _cards.update { newCards }
    }

    fun addCard(card: CardData) {
        setCards(cards.value+card)
        card.setHolder(name)
    }

    fun removeCard(card: CardData) {
        val newCards = cards.value.filter { it.rank != card.rank && it.suit != card.suit }
        card.setHolder(null)
        setCards(newCards)
    }

    fun setPlayTurn(isPlayerTurn: Boolean) {
        _playTurn.update { isPlayerTurn }
    }

    fun setFirstPlayerInRounds(isFirst: Boolean) {
        _firstPlayerInRound.update { isFirst }
    }

    fun setLastPlayerInRounds(isLast: Boolean) {
        _lastPlayerInRound.update { isLast }
    }

    fun setIsPlaying(playing: Boolean) {
        _isPlaying.update { playing }
    }

    fun has4EqualCards(): Boolean {
        if (cards.value.size != 4) return false
        return cards.value.count {it.suit == cards.value.first().suit} == 4
    }
}
