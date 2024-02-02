package az.isfan.spoonsgame.Data.Mappers

import az.isfan.spoonsgame.Data.Db.Entities.CardEntity
import az.isfan.spoonsgame.Data.Db.Entities.GameEntity
import az.isfan.spoonsgame.Data.Db.Entities.PlayerEntity
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.GameData
import az.isfan.spoonsgame.Data.Models.PlayerData
import kotlin.math.round

fun CardData.toEntity(): CardEntity {
    return CardEntity(
        rank = rank,
        suit = suit,
    )
}

fun PlayerData.toEntity(): PlayerEntity {
    return PlayerEntity(
        name = name,
        isLocalUser = isLocalUser,
        chair = chair,
        kicked = kicked,
        firstPlayerInRound = firstPlayer,
        playTurn = playTurn,
        lettersCollected = lettersSize
    )
}

fun GameData.toEntity(): GameEntity {
    return GameEntity(
        status = status,
        roundCount = roundCount,
        playerCount = playerCount,
        saveTimestamp = System.currentTimeMillis()
    )
}