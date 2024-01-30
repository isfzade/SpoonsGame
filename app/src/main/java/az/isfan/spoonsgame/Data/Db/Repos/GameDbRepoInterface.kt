package az.isfan.spoonsgame.Data.Db.Repos

import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.PlayerData

interface  GameDbRepoInterface {
    fun insert(card: CardData)

    fun insert(player: PlayerData)

    fun getAllCards(): List<CardData>

    fun getAllPlayers(): List<PlayerData>

    fun deleteAllCards()

    fun deleteAllPlayers()
}