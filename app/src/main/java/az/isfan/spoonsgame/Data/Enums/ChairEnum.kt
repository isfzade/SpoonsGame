package az.isfan.spoonsgame.Data.Enums

enum class ChairEnum(val chairId: Int) {
    BOTTOM(0),
    RIGHT(1),
    TOP_RIGHT(2),
    TOP_LEFT(3),
    LEFT(4);

    companion object {
        fun getById(chairId: Int) = ChairEnum.values().first { it.chairId == chairId }
    }
}