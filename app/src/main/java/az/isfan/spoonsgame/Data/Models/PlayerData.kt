package az.isfan.spoonsgame.Data.Models

import android.util.Log
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PlayerData(
    val name: String,
    val chair: ChairEnum,
    val cards: List<CardData> = emptyList(),
    val isLocalUser: Boolean = false,
    val firstPlayer: Boolean = false,
    val kicked: Boolean = false,
    val lettersSize: Int = 0,
) {
    private val TAG = "isf_PlayerData"

    fun has4EqualCards(): Boolean {
        Log.i(TAG, "has4EqualCards: name=$name")

        if (cards.size != 4) return false
        return cards.count { it.suit == cards.first().suit } == 4
    }
}
