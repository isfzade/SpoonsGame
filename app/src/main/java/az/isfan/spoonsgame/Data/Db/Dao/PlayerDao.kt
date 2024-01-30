package az.isfan.spoonsgame.Data.Db.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.isfan.spoonsgame.Data.Db.Entities.PlayerEntity


@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(player: PlayerEntity)

    @Query("SELECT * FROM players")
    fun getAll(): List<PlayerEntity>

    @Query("DELETE FROM cards")
    fun deleteAll()
}