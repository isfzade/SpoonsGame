package az.isfan.spoonsgame.data.mappers

import az.isfan.spoonsgame.data.db.entities.CardEntity
import az.isfan.spoonsgame.data.db.entities.GameEntity
import az.isfan.spoonsgame.data.db.entities.PlayerEntity
import az.isfan.spoonsgame.data.models.CardData
import az.isfan.spoonsgame.data.models.GameData
import az.isfan.spoonsgame.data.models.PlayerData
import az.isfan.spoonsgame.general.getDateTimeFromTimestamp

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
        dateTime = getDateTimeFromTimestamp(saveTimestamp)
    )
}