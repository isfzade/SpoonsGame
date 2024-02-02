package az.isfan.spoonsgame.Data.Models

import az.isfan.spoonsgame.Data.Enums.ChairEnum

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
    fun isSame(other: PlayerData): Boolean {
        return name == other.name
    }
}
