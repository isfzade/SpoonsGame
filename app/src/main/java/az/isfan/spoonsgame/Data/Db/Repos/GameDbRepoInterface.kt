package az.isfan.spoonsgame.Data.Db.Repos

import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.PlayerData

interface  FootballDbRepoInterface {
    suspend fun insert(card: CardData)

    suspend fun insert(player: PlayerData)

    suspend fun getAllCards(): List<CardData>

    suspend fun getAllPlayers(): List<PlayerData>
}