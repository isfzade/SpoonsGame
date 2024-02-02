package az.isfan.spoonsgame.data.db.repos

import az.isfan.spoonsgame.data.db.entities.CardEntity
import az.isfan.spoonsgame.data.db.entities.PlayerEntity
import az.isfan.spoonsgame.data.models.CardData
import az.isfan.spoonsgame.data.models.GameData
import az.isfan.spoonsgame.data.models.PlayerData

interface  GameDbRepoInterface {
    fun insert(card: CardData, owner: String)

    fun insert(player: PlayerData)

    fun insert(game: GameData)

    fun getLatestGame(): GameData?

    fun getAllCards(): List<CardEntity>

    fun getAllPlayers(): List<PlayerEntity>

    fun getAllFinishedGames(): List<GameData>

    fun deleteAllCards()

    fun deleteAllPlayers()
}