package az.isfan.spoonsgame.Data.Models

import az.isfan.spoonsgame.Data.Enums.RankEnum
import az.isfan.spoonsgame.Data.Enums.SuitEnum
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class CardData(
    val suit: SuitEnum,
    val rank: RankEnum,
) {
    private val _holder = MutableStateFlow<String?>(null)
    val holder = _holder.asStateFlow()

    fun setHolder(name: String?) {
        _holder.update { name }
    }
}
