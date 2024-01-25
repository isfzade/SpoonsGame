package az.isfan.spoonsgame.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import az.isfan.spoonsgame.Data.Models.PlayerData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class GameViewModel: ViewModel() {
    fun generatePlayers() {
        viewModelScope.launch(Dispatchers.Default) {
            val players = getPlayers(3)
        }
    }

    private fun getPlayers(botCount: Int): List<PlayerData> {
        val players = mutableListOf<PlayerData>()
        players.add(
            PlayerData(
                name = "Local User",
                isBot = false,
            )
        )

        repeat(botCount) {
            players.add(
                PlayerData(
                    name = it.toString(),
                    isBot = true,
                )
            )
        }

        return players
    }

    private fun splitInitialCardsToPlayers(players: List<PlayerData>) {

    }
}