package az.isfan.spoonsgame.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import az.isfan.spoonsgame.data.db.entities.CardEntity


@Dao
interface CardDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(card: CardEntity)

    @Query("SELECT * FROM cards")
    fun getAll(): List<CardEntity>

    @Query("DELETE FROM cards")
    fun deleteAll()
}