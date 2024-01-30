package az.isfan.spoonsgame.Data.Db.Repos

import az.isfan.spoonsgame.Data.Db.Dao.CardDao
import az.isfan.spoonsgame.Data.Db.Dao.PlayerDao
import az.isfan.spoonsgame.Data.Db.Entities.CardEntity
import az.isfan.spoonsgame.Data.Mappers.toData
import az.isfan.spoonsgame.Data.Mappers.toEntity
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.PlayerData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameDbRepo @Inject constructor(
    private val cardDao: CardDao,
    private val playerDao: PlayerDao,
): FootballDbRepoInterface {
    override suspend fun insert(card: CardData) {
        cardDao.insert(card.toEntity())
    }

    override suspend fun insert(player: PlayerData) {
        playerDao.insert(player.toEntity())
    }

    override suspend fun getAllCards(): List<CardData> {
        return cardDao.getAll().map { it.toData() }
    }

    override suspend fun getAllPlayers(): List<PlayerData> {
        return playerDao.getAll().map { it.toData() }
    }
}