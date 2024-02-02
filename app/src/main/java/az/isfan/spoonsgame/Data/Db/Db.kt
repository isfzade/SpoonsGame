package az.isfan.spoonsgame.Data.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import az.isfan.spoonsgame.Data.Db.Converters.Converters
import az.isfan.spoonsgame.Data.Db.Dao.CardDao
import az.isfan.spoonsgame.Data.Db.Dao.GameDao
import az.isfan.spoonsgame.Data.Db.Dao.PlayerDao
import az.isfan.spoonsgame.Data.Db.Entities.CardEntity
import az.isfan.spoonsgame.Data.Db.Entities.GameEntity
import az.isfan.spoonsgame.Data.Db.Entities.PlayerEntity

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