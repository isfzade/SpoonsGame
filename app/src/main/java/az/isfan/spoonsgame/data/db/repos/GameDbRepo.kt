package az.isfan.spoonsgame.data.db.repos

import az.isfan.spoonsgame.data.db.dao.CardDao
import az.isfan.spoonsgame.data.db.dao.GameDao
import az.isfan.spoonsgame.data.db.dao.PlayerDao
import az.isfan.spoonsgame.data.db.entities.CardEntity
import az.isfan.spoonsgame.data.db.entities.PlayerEntity
import az.isfan.spoonsgame.data.enums.GameStatusEnum
import az.isfan.spoonsgame.data.mappers.toData
import az.isfan.spoonsgame.data.mappers.toEntity
import az.isfan.spoonsgame.data.models.CardData
import az.isfan.spoonsgame.data.models.GameData
import az.isfan.spoonsgame.data.models.PlayerData
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