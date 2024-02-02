package az.isfan.spoonsgame.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import az.isfan.spoonsgame.data.enums.GameStatusEnum

@Entity(tableName="games")
class GameEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    val status: GameStatusEnum,

    @ColumnInfo(name = "player_count")
    val playerCount: Int,

    @ColumnInfo(name = "round_count")
    val roundCount: Int,

    @ColumnInfo(name = "save_timestamp")
    val saveTimestamp: Long,
)