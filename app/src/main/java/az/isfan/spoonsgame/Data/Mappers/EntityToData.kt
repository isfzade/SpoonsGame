package az.isfan.spoonsgame.Data.Mappers

import az.isfan.spoonsgame.Data.Db.Entities.CardEntity
import az.isfan.spoonsgame.Data.Db.Entities.PlayerEntity
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.PlayerData

fun CardEntity.toData(): CardData {
    val card = CardData(
        suit = suit,
        rank = rank
    )
    card.setHolder(holder)
    return card
}

fun PlayerEntity.toData(): PlayerData {
    val player = PlayerData(
        name = name,
        isLocalUser = isLocalUser,
        chair = chair!!,
    )
    player.setIsPlaying(isPlaying)
    player.setFirstPlayerInRounds(firstPlayerInRound)
    player.setLastPlayerInRounds(lastPlayerInRound)
    player.setPlayTurn(playTurn)
    player.setLettersCollected(lettersCollected)
    return player
}