package az.isfan.spoonsgame.Data.Db.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.isfan.spoonsgame.Data.Db.Entities.CardEntity
import az.isfan.spoonsgame.Data.Db.Entities.GameEntity


@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(game: GameEntity)

    @Query("SELECT *, MAX(save_timestamp) FROM games")
    fun getLatest(): GameEntity?

    @Query("SELECT * FROM games")
    fun getAll(): List<GameEntity>
}