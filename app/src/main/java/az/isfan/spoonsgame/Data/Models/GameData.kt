package az.isfan.spoonsgame.Data.Models

import az.isfan.spoonsgame.Data.Enums.GameStatusEnum

data class GameData(
    val status: GameStatusEnum,
    val roundCount: Int,
    val playerCount: Int,
    val dateTime: String? = null,
)
