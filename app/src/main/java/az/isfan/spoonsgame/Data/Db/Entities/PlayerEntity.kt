package az.isfan.spoonsgame.Data.Db.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import az.isfan.spoonsgame.Data.Enums.ChairEnum

@Entity(tableName = "players")
data class PlayerEntity(
    @PrimaryKey(autoGenerate = false)
    val name: String,

    @ColumnInfo(name="is_local_user")
    val isLocalUser: Boolean,

    var chair: ChairEnum? = null,

    @ColumnInfo(name="first_player_in_round")
    var firstPlayerInRound: Boolean = false,

    @ColumnInfo(name="last_player_in_round")
    var lastPlayerInRound: Boolean = false,

    @ColumnInfo(name="is_playing")
    var isPlaying: Boolean = false,

    @ColumnInfo(name="play_turn")
    var playTurn: Boolean = false,
)