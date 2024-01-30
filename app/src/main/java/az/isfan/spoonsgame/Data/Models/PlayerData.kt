package az.isfan.spoonsgame.Data.Models

import android.util.Log
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PlayerData(
    val name: String,
    val isLocalUser: Boolean,
    val chair: ChairEnum,
) {
    private val TAG = "isf_PlayerData"

    private val _playTurn = MutableStateFlow(false)
    val playTurn = _playTurn.asStateFlow()

    private val _cards = MutableStateFlow<List<CardData>>(emptyList())
    val cards = _cards.asStateFlow()

    private val _firstPlayerInRound = MutableStateFlow(false)
    val firstPlayerInRound = _firstPlayerInRound.asStateFlow()

    private val _lastPlayerInRound = MutableStateFlow(false)
    val lastPlayerInRound = _lastPlayerInRound.asStateFlow()

    private val _isPlaying = MutableStateFlow(true)
    val isPlaying = _isPlaying.asStateFlow()

    fun setCards(newCards: List<CardData>){
        _cards.update { newCards }
    }

    fun addCard(card: CardData) {
        Log.i(TAG, "addCard: card=$card, player=$this")

        setCards(cards.value+card)
        card.setHolder(name)
    }

    fun removeCard(card: CardData) {
        Log.i(TAG, "removeCard: card=$card, player=$this")

        val newCards = cards.value.filter { !(it.rank == card.rank && it.suit == card.suit) }
        card.setHolder(null)
        setCards(newCards)
    }

    fun removeAllCards() {
        Log.i(TAG, "removeAllCards: player=$this")

        _cards.update { emptyList() }
    }

    fun setPlayTurn(isPlayerTurn: Boolean) {
        Log.i(TAG, "setPlayTurn: isPlayerTurn=$isPlayerTurn, player=$this")

        _playTurn.update { isPlayerTurn }
    }

    fun setFirstPlayerInRounds(isFirst: Boolean) {
        Log.i(TAG, "setFirstPlayerInRounds: isFirst=$isFirst, player=$this")

        _firstPlayerInRound.update { isFirst }
    }

    fun setLastPlayerInRounds(isLast: Boolean) {
        Log.i(TAG, "setLastPlayerInRounds: isLast=$isLast, player=$this")

        _lastPlayerInRound.update { isLast }
    }

    fun setIsPlaying(playing: Boolean) {
        Log.i(TAG, "setIsPlaying: playing=$playing, player=$this")

        _isPlaying.update { playing }
    }

    fun has4EqualCards(): Boolean {
        Log.i(TAG, "has4EqualCards: player=$this")

        if (cards.value.size != 4) return false
        return cards.value.count {it.suit == cards.value.first().suit} == 4
    }
}
