package az.isfan.spoonsgame.Data.Models

import az.isfan.spoonsgame.Data.Enums.RankEnum
import az.isfan.spoonsgame.Data.Enums.SuitEnum

data class CardData(
    val suit: SuitEnum,
    val rank: RankEnum,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is CardData) return false

        return suit == other.suit && rank == other.rank
    }
}
