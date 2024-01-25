package az.isfan.spoonsgame.Data.Models

import az.isfan.spoonsgame.Data.Enums.RankEnum
import az.isfan.spoonsgame.Data.Enums.SuitEnum

data class CardData(
    val suit: SuitEnum,
    val rank: RankEnum,
    val cardImageLink: String,
)
