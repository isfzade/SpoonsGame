package az.isfan.spoonsgame.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.isfan.spoonsgame.data.db.entities.PlayerEntity


@Dao
interface PlayerDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(player: PlayerEntity)

    @Query("SELECT * FROM players")
    fun getAll(): List<PlayerEntity>

    @Query("DELETE FROM players")
    fun deleteAll()
}