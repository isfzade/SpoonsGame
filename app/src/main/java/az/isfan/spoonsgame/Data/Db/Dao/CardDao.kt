package az.isfan.spoonsgame.Data.Db.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.isfan.spoonsgame.Data.Db.Entities.CardEntity


@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(card: CardEntity)

    @Query("SELECT * FROM cards")
    fun getAll(): List<CardEntity>
}