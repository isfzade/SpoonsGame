package az.isfan.spoonsgame.Data.Db.Repos

import az.isfan.spoonsgame.Data.Db.Dao.CardDao
import az.isfan.spoonsgame.Data.Db.Dao.GameDao
import az.isfan.spoonsgame.Data.Db.Dao.PlayerDao
import az.isfan.spoonsgame.Data.Db.Entities.CardEntity
import az.isfan.spoonsgame.Data.Db.Entities.PlayerEntity
import az.isfan.spoonsgame.Data.Enums.GameStatusEnum
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
    override fun insert(card: CardData, owner: String) {
        cardDao.insert(card.toEntity(owner))
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

    override fun getAllCards(): List<CardEntity> {
        return cardDao.getAll()
    }

    override fun getAllPlayers(): List<PlayerEntity> {
        return playerDao.getAll()
    }

    override fun getAllFinishedGames(): List<GameData> {
        return gameDao.getAll(GameStatusEnum.NOT_FINISHED).map { it.toData() }
    }

    override fun deleteAllCards() {
        cardDao.deleteAll()
    }

    override fun deleteAllPlayers() {
        playerDao.deleteAll()
    }
}