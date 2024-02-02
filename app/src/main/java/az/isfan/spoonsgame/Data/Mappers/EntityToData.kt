package az.isfan.spoonsgame.Data.Mappers

import az.isfan.spoonsgame.Data.Db.Entities.CardEntity
import az.isfan.spoonsgame.Data.Db.Entities.GameEntity
import az.isfan.spoonsgame.Data.Db.Entities.PlayerEntity
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.GameData
import az.isfan.spoonsgame.Data.Models.PlayerData
import az.isfan.spoonsgame.General.Constants

fun CardEntity.toData(): CardData {
    return CardData(
        suit = suit,
        rank = rank
    )
}

fun PlayerEntity.toData(cards: List<CardData>): PlayerData {
    return PlayerData(
        name = name,
        isLocalUser = isLocalUser,
        chair = chair!!,
        playTurn = playTurn,
        firstPlayer = firstPlayerInRound,
        kicked = kicked,
        cards = cards,
        lettersSize = lettersCollected
    )
}

fun GameEntity.toData(): GameData {
    return GameData(
        status = status,
        roundCount = roundCount,
        playerCount = playerCount,
        saveTimestamp = saveTimestamp
    )
}