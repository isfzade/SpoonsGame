package az.isfan.spoonsgame.data.db.entities

import androidx.room.Entity
import az.isfan.spoonsgame.data.enums.RankEnum
import az.isfan.spoonsgame.data.enums.SuitEnum

@Entity(tableName = "cards", primaryKeys = [
    "suit",
    "rank",
])
data class CardEntity(
    val suit: SuitEnum,
    val rank: RankEnum,
    val owner: String,
)
