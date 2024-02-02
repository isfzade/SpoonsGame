package az.isfan.spoonsgame.general

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