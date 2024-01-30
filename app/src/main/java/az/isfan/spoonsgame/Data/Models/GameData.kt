package az.isfan.spoonsgame.Data.Models

import android.app.GameState
import android.util.Log
import az.isfan.spoonsgame.Data.Enums.GameResultEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class GameData(
    val id: String,
    val playerCount: Int,
) {
    private val TAG = "isf_GameData"

    private val _roundCount = MutableStateFlow(0)
    val roundCount = _roundCount.asStateFlow()

    private val _result = MutableStateFlow(GameResultEnum.NOT_FINISHED)
    val result = _result.asStateFlow()

    private val _currentRoundFinished = MutableStateFlow(false)
    val currentRoundFinished = _currentRoundFinished.asStateFlow()

    fun incRoundCount() {
        Log.i(TAG, "incRoundCount:")
        _roundCount.update { it+1 }
    }

    fun setResult(newResult: GameResultEnum) {
        Log.i(TAG, "setResult: newResult=$newResult")
        _result.update { newResult }
    }

    fun setCurrentRoundFinished(finished: Boolean) {
        Log.i(TAG, "setCurrentRoundFinished: finished=$finished")

        _currentRoundFinished.update { finished }
    }
}
