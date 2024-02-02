package az.isfan.spoonsgame.general

import az.isfan.spoonsgame.data.enums.RankEnum
import az.isfan.spoonsgame.data.enums.SuitEnum
import az.isfan.spoonsgame.R

fun getCardImageResource(rank: RankEnum, suitEnum: SuitEnum): Int {
    return when("$rank-$suitEnum") {
        "${RankEnum.CLUB}-${SuitEnum.ACE}" -> R.drawable.clubs_a
        "${RankEnum.CLUB}-${SuitEnum.KING}" -> R.drawable.clubs_k
        "${RankEnum.CLUB}-${SuitEnum.QUEEN}" -> R.drawable.clubs_q
        "${RankEnum.CLUB}-${SuitEnum.JACK}" -> R.drawable.clubs_j
        "${RankEnum.CLUB}-${SuitEnum.TEN}" -> R.drawable.clubs_10
        "${RankEnum.CLUB}-${SuitEnum.NINE}" -> R.drawable.clubs_9
        "${RankEnum.CLUB}-${SuitEnum.EIGHT}" -> R.drawable.clubs_8
        "${RankEnum.CLUB}-${SuitEnum.SEVEN}" -> R.drawable.clubs_7
        "${RankEnum.CLUB}-${SuitEnum.SIX}" -> R.drawable.clubs_6
        "${RankEnum.CLUB}-${SuitEnum.FIVE}" -> R.drawable.clubs_5
        "${RankEnum.CLUB}-${SuitEnum.FOUR}" -> R.drawable.clubs_4
        "${RankEnum.CLUB}-${SuitEnum.THREE}" -> R.drawable.clubs_3
        "${RankEnum.CLUB}-${SuitEnum.TWO}" -> R.drawable.clubs_2

        "${RankEnum.DIAMOND}-${SuitEnum.ACE}" -> R.drawable.diamonds_a
        "${RankEnum.DIAMOND}-${SuitEnum.KING}" -> R.drawable.diamonds_k
        "${RankEnum.DIAMOND}-${SuitEnum.QUEEN}" -> R.drawable.diamonds_q
        "${RankEnum.DIAMOND}-${SuitEnum.JACK}" -> R.drawable.diamonds_j
        "${RankEnum.DIAMOND}-${SuitEnum.TEN}" -> R.drawable.diamonds_10
        "${RankEnum.DIAMOND}-${SuitEnum.NINE}" -> R.drawable.diamonds_9
        "${RankEnum.DIAMOND}-${SuitEnum.EIGHT}" -> R.drawable.diamonds_8
        "${RankEnum.DIAMOND}-${SuitEnum.SEVEN}" -> R.drawable.diamonds_7
        "${RankEnum.DIAMOND}-${SuitEnum.SIX}" -> R.drawable.diamonds_6
        "${RankEnum.DIAMOND}-${SuitEnum.FIVE}" -> R.drawable.diamonds_5
        "${RankEnum.DIAMOND}-${SuitEnum.FOUR}" -> R.drawable.diamonds_4
        "${RankEnum.DIAMOND}-${SuitEnum.THREE}" -> R.drawable.diamonds_3
        "${RankEnum.DIAMOND}-${SuitEnum.TWO}" -> R.drawable.diamonds_2

        "${RankEnum.HEART}-${SuitEnum.ACE}" -> R.drawable.hearts_a
        "${RankEnum.HEART}-${SuitEnum.KING}" -> R.drawable.hearts_k
        "${RankEnum.HEART}-${SuitEnum.QUEEN}" -> R.drawable.hearts_q
        "${RankEnum.HEART}-${SuitEnum.JACK}" -> R.drawable.hearts_j
        "${RankEnum.HEART}-${SuitEnum.TEN}" -> R.drawable.hearts_10
        "${RankEnum.HEART}-${SuitEnum.NINE}" -> R.drawable.hearts_9
        "${RankEnum.HEART}-${SuitEnum.EIGHT}" -> R.drawable.hearts_8
        "${RankEnum.HEART}-${SuitEnum.SEVEN}" -> R.drawable.hearts_7
        "${RankEnum.HEART}-${SuitEnum.SIX}" -> R.drawable.hearts_6
        "${RankEnum.HEART}-${SuitEnum.FIVE}" -> R.drawable.hearts_5
        "${RankEnum.HEART}-${SuitEnum.FOUR}" -> R.drawable.hearts_4
        "${RankEnum.HEART}-${SuitEnum.THREE}" -> R.drawable.hearts_3
        "${RankEnum.HEART}-${SuitEnum.TWO}" -> R.drawable.hearts_2

        "${RankEnum.SPADE}-${SuitEnum.ACE}" -> R.drawable.spades_a
        "${RankEnum.SPADE}-${SuitEnum.KING}" -> R.drawable.spades_k
        "${RankEnum.SPADE}-${SuitEnum.QUEEN}" -> R.drawable.spades_q
        "${RankEnum.SPADE}-${SuitEnum.JACK}" -> R.drawable.spades_j
        "${RankEnum.SPADE}-${SuitEnum.TEN}" -> R.drawable.spades_10
        "${RankEnum.SPADE}-${SuitEnum.NINE}" -> R.drawable.spades_9
        "${RankEnum.SPADE}-${SuitEnum.EIGHT}" -> R.drawable.spades_8
        "${RankEnum.SPADE}-${SuitEnum.SEVEN}" -> R.drawable.spades_7
        "${RankEnum.SPADE}-${SuitEnum.SIX}" -> R.drawable.spades_6
        "${RankEnum.SPADE}-${SuitEnum.FIVE}" -> R.drawable.spades_5
        "${RankEnum.SPADE}-${SuitEnum.FOUR}" -> R.drawable.spades_4
        "${RankEnum.SPADE}-${SuitEnum.THREE}" -> R.drawable.spades_3
        "${RankEnum.SPADE}-${SuitEnum.TWO}" -> R.drawable.spades_2

        else -> R.drawable.back_dark
    }
}