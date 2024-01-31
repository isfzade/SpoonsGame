package az.isfan.spoonsgame.Data.Mappers

import az.isfan.spoonsgame.Data.Db.Entities.CardEntity
import az.isfan.spoonsgame.Data.Db.Entities.GameEntity
import az.isfan.spoonsgame.Data.Db.Entities.PlayerEntity
import az.isfan.spoonsgame.Data.Models.CardData
import az.isfan.spoonsgame.Data.Models.GameData
import az.isfan.spoonsgame.Data.Models.PlayerData
import az.isfan.spoonsgame.General.Constants

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

fun GameEntity.toData(players: List<PlayerData>, cards: List<CardData>): GameData {
    val game = GameData(
        playerCount = playerCount
    )
    game.setPlayers(players)
    players.forEach { player->
        player.setCards(cards.filter { it.holder.value == player.name })
    }
    game.setAllCards(cards)
    game.setAvailableDeckCards(cards.filter{it.holder.value == Constants.AVAILABLE})
    game.setDiscardedDeckCards(cards.filter{it.holder.value == Constants.DISCARDED})
    game.setRoundCount(roundCount)
    return game
}

fun GameEntity.toData(): GameData {
    val game = GameData(
        playerCount = playerCount
    )
    game.setRoundCount(roundCount)
    return game
}