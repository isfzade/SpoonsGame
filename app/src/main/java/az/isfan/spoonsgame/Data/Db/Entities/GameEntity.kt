package az.isfan.spoonsgame.Data.Db.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import az.isfan.spoonsgame.Data.Enums.GameStatusEnum

@Entity(tableName="games")
class GameEntity(
    val status: GameStatusEnum,

    @ColumnInfo(name = "player_count")
    val playerCount: Int,

    @ColumnInfo(name = "round_count")
    val roundCount: Int,
)