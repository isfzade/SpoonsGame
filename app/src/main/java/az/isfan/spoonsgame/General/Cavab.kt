package az.isfan.spoonsgame.General

sealed class Cavab<out T> {
    object StandBy: Cavab<Nothing>() {
        override fun toString(): String {
            return "StandBy"
        }
    }

    object Loading: Cavab<Nothing>() {
        override fun toString(): String {
            return "Loading"
        }
    }

    data class Success<out T>(
        val data: T
    ): Cavab<T>() {
        override fun toString(): String {
            return "Success = $data"
        }
    }
}