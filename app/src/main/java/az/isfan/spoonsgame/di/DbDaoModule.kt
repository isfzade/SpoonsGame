package az.isfan.spoonsgame.di

import android.content.Context
import az.isfan.spoonsgame.data.db.Db
import az.isfan.spoonsgame.data.db.dao.CardDao
import az.isfan.spoonsgame.data.db.dao.GameDao
import az.isfan.spoonsgame.data.db.dao.PlayerDao
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

    @Singleton
    @Provides
    fun provideGameDao(@ApplicationContext context: Context): GameDao = Db.getDB(context).gameDao()
}