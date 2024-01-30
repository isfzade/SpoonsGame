package az.isfan.spoonsgame.Di

import android.content.Context
import az.isfan.spoonsgame.Data.Db.Dao.CardDao
import az.isfan.spoonsgame.Data.Db.Dao.PlayerDao
import az.isfan.spoonsgame.Data.Db.Db
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DbDaoModule {
    @Singleton
    @Provides
    fun provideCardDao(@ApplicationContext context: Context): CardDao = Db.getDB(context).cardDao()

    @Singleton
    @Provides
    fun providePlayerDao(@ApplicationContext context: Context): PlayerDao = Db.getDB(context).playerDao()
}