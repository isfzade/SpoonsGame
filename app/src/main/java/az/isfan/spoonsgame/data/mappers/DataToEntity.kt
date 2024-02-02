package az.isfan.spoonsgame.data.mappers

import az.isfan.spoonsgame.data.db.entities.CardEntity
import az.isfan.spoonsgame.data.db.entities.GameEntity
import az.isfan.spoonsgame.data.db.entities.PlayerEntity
import az.isfan.spoonsgame.data.models.CardData
import az.isfan.spoonsgame.data.models.GameData
import az.isfan.spoonsgame.data.models.PlayerData

fun CardData.toEntity(owner: String): CardEntity {
    return CardEntity(
        rank = rank,
        suit = suit,
        owner = owner,
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