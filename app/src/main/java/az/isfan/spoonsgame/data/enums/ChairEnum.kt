package az.isfan.spoonsgame.data.enums

enum class ChairEnum(val chairId: Int) {
    BOTTOM(0),
    RIGHT(1),
    TOP_RIGHT(2),
    TOP_LEFT(3),
    LEFT(4);

    companion object {
        fun getById(chairId: Int) = entries.first { it.chairId == chairId }
    }
}