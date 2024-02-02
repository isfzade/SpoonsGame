package az.isfan.spoonsgame.Data.Models

import android.util.Log
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Enums.GameStatusEnum
import az.isfan.spoonsgame.Data.Enums.RankEnum
import az.isfan.spoonsgame.Data.Enums.SuitEnum
import az.isfan.spoonsgame.General.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlin.random.Random

data class GameData(
    val status: GameStatusEnum,
    val roundCount: Int,
    val playerCount: Int,
    val saveTimestamp: Long = System.currentTimeMillis(),
)
