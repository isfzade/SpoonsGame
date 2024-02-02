package az.isfan.spoonsgame.Data.Db.Repos

import az.isfan.spoonsgame.Data.Db.Entities.CardEntity
import az.isfan.spoonsgame.Data.Db.Entities.PlayerEntity
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.GameData
import az.isfan.spoonsgame.Data.Models.PlayerData

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