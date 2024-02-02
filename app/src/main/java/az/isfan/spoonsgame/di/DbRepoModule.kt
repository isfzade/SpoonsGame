package az.isfan.spoonsgame.di

import az.isfan.spoonsgame.data.db.dao.CardDao
import az.isfan.spoonsgame.data.db.dao.GameDao
import az.isfan.spoonsgame.data.db.dao.PlayerDao
import az.isfan.spoonsgame.data.db.repos.GameDbRepo
import az.isfan.spoonsgame.data.db.repos.GameDbRepoInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class DbRepoModule {
    @ViewModelScoped
    @Provides
    fun provideGameRepo(cardDao: CardDao, playerDao: PlayerDao, gameDao: GameDao): GameDbRepoInterface = GameDbRepo(cardDao, playerDao, gameDao)
}