package az.isfan.spoonsgame.Data.Enums

enum class ChairEnum(val chairId: Int) {
    BOTTOM(0),
    LEFT(1),
    RIGHT(2),
    TOP_LEFT(3),
    TOP_RIGHT(4);

    companion object {
        fun getById(chairId: Int) = ChairEnum.values().first { it.chairId == chairId }
    }
}