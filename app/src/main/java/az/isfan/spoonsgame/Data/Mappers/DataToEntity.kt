package az.isfan.spoonsgame.Data.Mappers

import az.isfan.spoonsgame.Data.Db.Entities.CardEntity
import az.isfan.spoonsgame.Data.Db.Entities.PlayerEntity
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.PlayerData

fun CardData.toEntity(): CardEntity {
    return CardEntity(
        rank = rank,
        suit = suit,
        holder = holder.value
    )
}

fun PlayerData.toEntity(): PlayerEntity {
    return PlayerEntity(
        name = name,
        isLocalUser = isLocalUser,
        chair = chair,
        isPlaying = isPlaying.value,
        firstPlayerInRound = firstPlayerInRound.value,
        lastPlayerInRound = lastPlayerInRound.value,
        playTurn = playTurn.value,
    )
}