package az.isfan.spoonsgame.data.models

import az.isfan.spoonsgame.data.enums.GameStatusEnum

data class GameData(
    val status: GameStatusEnum,
    val roundCount: Int,
    val playerCount: Int,
    val dateTime: String? = null,
)
