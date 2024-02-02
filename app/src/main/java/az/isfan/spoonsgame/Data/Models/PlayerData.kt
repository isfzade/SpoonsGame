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
    val playTurn: Boolean = false,
    val isLocalUser: Boolean = false,
    val firstPlayer: Boolean = false,
    val kicked: Boolean = false,
    val lettersSize: Int = 0,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is PlayerData) return false

        return name == other.name
    }
}
