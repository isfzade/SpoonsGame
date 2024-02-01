package az.isfan.spoonsgame.Data.Db.Repos

import az.isfan.spoonsgame.Data.Db.Dao.CardDao
import az.isfan.spoonsgame.Data.Db.Dao.GameDao
import az.isfan.spoonsgame.Data.Db.Dao.PlayerDao
import az.isfan.spoonsgame.Data.Mappers.toData
import az.isfan.spoonsgame.Data.Mappers.toEntity
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.GameData
import az.isfan.spoonsgame.Data.Models.PlayerData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GameDbRepo @Inject constructor(
    private val cardDao: CardDao,
    private val playerDao: PlayerDao,
    private val gameDao: GameDao,
): GameDbRepoInterface {
    override fun insert(card: CardData) {
        cardDao.insert(card.toEntity())
    }

    override fun insert(player: PlayerData) {
        playerDao.insert(player.toEntity())
    }

    override fun insert(game: GameData) {
        gameDao.insert(game.toEntity())
    }

    override fun getLatestGame(): GameData? {
        return gameDao.getLatest()?.toData()
    }

    override fun getAllCards(): List<CardData> {
        return cardDao.getAll().map { it.toData() }
    }

    override fun getAllPlayers(): List<PlayerData> {
        return playerDao.getAll().map { it.toData() }
    }

    override fun getAllGames(): List<GameData> {
        return gameDao.getAll().map { it.toData() }
    }

    override fun deleteAllCards() {
        cardDao.deleteAll()
    }

    override fun deleteAllPlayers() {
        playerDao.deleteAll()
    }
}