package az.isfan.spoonsgame.General

import az.isfan.spoonsgame.Data.Enums.RankEnum
import az.isfan.spoonsgame.Data.Enums.SuitEnum

class Constants {
    companion object {
        val CARD_IMAGE_LINKS = hashMapOf<String, String>(
            "back" to "https://github.com/hanhaechi/playing-cards/blob/master/back_dark.png?raw=true",

            "${RankEnum.CLUB}-${SuitEnum.ACE}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_A.png?raw=true",
            "${RankEnum.CLUB}-${SuitEnum.KING}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_K.png?raw=true",
            "${RankEnum.CLUB}-${SuitEnum.QUEEN}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_Q.png?raw=true",
            "${RankEnum.CLUB}-${SuitEnum.JACK}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_J.png?raw=true",
            "${RankEnum.CLUB}-${SuitEnum.TEN}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_10.png?raw=true",
            "${RankEnum.CLUB}-${SuitEnum.NINE}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_9.png?raw=true",
            "${RankEnum.CLUB}-${SuitEnum.EIGHT}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_8.png?raw=true",
            "${RankEnum.CLUB}-${SuitEnum.SEVEN}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_7.png?raw=true",
            "${RankEnum.CLUB}-${SuitEnum.SIX}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_6.png?raw=true",
            "${RankEnum.CLUB}-${SuitEnum.FIVE}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_5.png?raw=true",
            "${RankEnum.CLUB}-${SuitEnum.FOUR}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_4.png?raw=true",
            "${RankEnum.CLUB}-${SuitEnum.THREE}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_3.png?raw=true",
            "${RankEnum.CLUB}-${SuitEnum.TWO}" to "https://github.com/hanhaechi/playing-cards/blob/master/clubs_2.png?raw=true",

            "${RankEnum.DIAMOND}-${SuitEnum.ACE}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_A.png?raw=true",
            "${RankEnum.DIAMOND}-${SuitEnum.KING}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_K.png?raw=true",
            "${RankEnum.DIAMOND}-${SuitEnum.QUEEN}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_Q.png?raw=true",
            "${RankEnum.DIAMOND}-${SuitEnum.JACK}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_J.png?raw=true",
            "${RankEnum.DIAMOND}-${SuitEnum.TEN}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_10.png?raw=true",
            "${RankEnum.DIAMOND}-${SuitEnum.NINE}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_9.png?raw=true",
            "${RankEnum.DIAMOND}-${SuitEnum.EIGHT}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_8.png?raw=true",
            "${RankEnum.DIAMOND}-${SuitEnum.SEVEN}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_7.png?raw=true",
            "${RankEnum.DIAMOND}-${SuitEnum.SIX}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_6.png?raw=true",
            "${RankEnum.DIAMOND}-${SuitEnum.FIVE}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_5.png?raw=true",
            "${RankEnum.DIAMOND}-${SuitEnum.FOUR}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_4.png?raw=true",
            "${RankEnum.DIAMOND}-${SuitEnum.THREE}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_3.png?raw=true",
            "${RankEnum.DIAMOND}-${SuitEnum.TWO}" to "https://github.com/hanhaechi/playing-cards/blob/master/diamonds_2.png?raw=true",

            "${RankEnum.HEART}-${SuitEnum.ACE}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_A.png?raw=true",
            "${RankEnum.HEART}-${SuitEnum.KING}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_K.png?raw=true",
            "${RankEnum.HEART}-${SuitEnum.QUEEN}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_Q.png?raw=true",
            "${RankEnum.HEART}-${SuitEnum.JACK}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_J.png?raw=true",
            "${RankEnum.HEART}-${SuitEnum.TEN}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_10.png?raw=true",
            "${RankEnum.HEART}-${SuitEnum.NINE}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_9.png?raw=true",
            "${RankEnum.HEART}-${SuitEnum.EIGHT}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_8.png?raw=true",
            "${RankEnum.HEART}-${SuitEnum.SEVEN}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_7.png?raw=true",
            "${RankEnum.HEART}-${SuitEnum.SIX}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_6.png?raw=true",
            "${RankEnum.HEART}-${SuitEnum.FIVE}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_5.png?raw=true",
            "${RankEnum.HEART}-${SuitEnum.FOUR}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_4.png?raw=true",
            "${RankEnum.HEART}-${SuitEnum.THREE}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_3.png?raw=true",
            "${RankEnum.HEART}-${SuitEnum.TWO}" to "https://github.com/hanhaechi/playing-cards/blob/master/hearts_2.png?raw=true",

            "${RankEnum.SPADE}-${SuitEnum.ACE}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_A.png?raw=true",
            "${RankEnum.SPADE}-${SuitEnum.KING}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_K.png?raw=true",
            "${RankEnum.SPADE}-${SuitEnum.QUEEN}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_Q.png?raw=true",
            "${RankEnum.SPADE}-${SuitEnum.JACK}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_J.png?raw=true",
            "${RankEnum.SPADE}-${SuitEnum.TEN}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_10.png?raw=true",
            "${RankEnum.SPADE}-${SuitEnum.NINE}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_9.png?raw=true",
            "${RankEnum.SPADE}-${SuitEnum.EIGHT}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_8.png?raw=true",
            "${RankEnum.SPADE}-${SuitEnum.SEVEN}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_7.png?raw=true",
            "${RankEnum.SPADE}-${SuitEnum.SIX}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_6.png?raw=true",
            "${RankEnum.SPADE}-${SuitEnum.FIVE}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_5.png?raw=true",
            "${RankEnum.SPADE}-${SuitEnum.FOUR}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_4.png?raw=true",
            "${RankEnum.SPADE}-${SuitEnum.THREE}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_3.png?raw=true",
            "${RankEnum.SPADE}-${SuitEnum.TWO}" to "https://github.com/hanhaechi/playing-cards/blob/master/spades_2.png?raw=true",
        )
    }
}