package az.isfan.spoonsgame.data.models

import az.isfan.spoonsgame.data.enums.RankEnum
import az.isfan.spoonsgame.data.enums.SuitEnum

data class CardData(
    val suit: SuitEnum,
    val rank: RankEnum,
)
