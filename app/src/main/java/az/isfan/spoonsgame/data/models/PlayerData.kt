package az.isfan.spoonsgame.data.models

import az.isfan.spoonsgame.data.enums.ChairEnum
import az.isfan.spoonsgame.data.enums.MoodEnum

data class PlayerData(
    val name: String,
    val chair: ChairEnum,
    val cards: List<CardData> = emptyList(),
    val playTurn: Boolean = false,
    val isLocalUser: Boolean = false,
    val firstPlayer: Boolean = false,
    val kicked: Boolean = false,
    val lettersSize: Int = 0,
    val mood: MoodEnum = MoodEnum.NORMAL
) {
    fun isSame(other: PlayerData): Boolean {
        return name == other.name
    }
}
