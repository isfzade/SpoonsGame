package az.isfan.spoonsgame.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import az.isfan.spoonsgame.data.db.converters.Converters
import az.isfan.spoonsgame.data.db.dao.CardDao
import az.isfan.spoonsgame.data.db.dao.GameDao
import az.isfan.spoonsgame.data.db.dao.PlayerDao
import az.isfan.spoonsgame.data.db.entities.CardEntity
import az.isfan.spoonsgame.data.db.entities.GameEntity
import az.isfan.spoonsgame.data.db.entities.PlayerEntity

@Database(
    entities = [
        CardEntity::class,
        PlayerEntity::class,
        GameEntity::class,
    ],
    version=5,
    exportSchema = false)
@TypeConverters(Converters::class)
abstract class Db: RoomDatabase() {

    abstract fun cardDao(): CardDao
    abstract fun playerDao(): PlayerDao
    abstract fun gameDao(): GameDao

    companion object{
        @Volatile
        private var INSTANCE: Db? = null

        fun getDB(context: Context): Db {
            val instance = INSTANCE
            if (instance != null){
                return instance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Db::class.java,
                    "spoons_game_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}