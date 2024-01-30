package az.isfan.spoonsgame.Di

import az.isfan.spoonsgame.Data.Db.Dao.CardDao
import az.isfan.spoonsgame.Data.Db.Dao.PlayerDao
import az.isfan.spoonsgame.Data.Db.Repos.GameDbRepo
import az.isfan.spoonsgame.Data.Db.Repos.GameDbRepoInterface
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
    fun provideGameRepo(cardDao: CardDao, playerDao: PlayerDao): GameDbRepoInterface = GameDbRepo(cardDao, playerDao)
}