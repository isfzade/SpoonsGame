package az.isfan.spoonsgame.Data.Db.Entities

import androidx.room.Entity
import az.isfan.spoonsgame.Data.Enums.RankEnum
import az.isfan.spoonsgame.Data.Enums.SuitEnum

@Entity(tableName = "cards", primaryKeys = [
    "suit",
    "rank",
])
data class CardEntity(
    val suit: SuitEnum,
    val rank: RankEnum,
)
