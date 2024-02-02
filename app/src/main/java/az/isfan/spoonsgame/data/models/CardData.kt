package az.isfan.spoonsgame.data.models

import az.isfan.spoonsgame.data.enums.RankEnum
import az.isfan.spoonsgame.data.enums.SuitEnum

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
