package az.isfan.spoonsgame.general

sealed class Cavab<out T> {
    data object StandBy: Cavab<Nothing>()

    data object Loading: Cavab<Nothing>()

    data class Success<out T>(
        val data: T
    ): Cavab<T>()
}