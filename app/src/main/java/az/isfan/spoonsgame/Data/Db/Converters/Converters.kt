package az.isfan.spoonsgame.Data.Db.Converters

import androidx.room.TypeConverter
import az.isfan.spoonsgame.Data.Enums.ChairEnum
import az.isfan.spoonsgame.Data.Enums.GameStatusEnum
import az.isfan.spoonsgame.Data.Enums.RankEnum
import az.isfan.spoonsgame.Data.Enums.SuitEnum

class Converters {
    @TypeConverter
    fun toString(suit: SuitEnum): String {
        return suit.name
    }

    @TypeConverter
    fun toString(rank: RankEnum): String {
        return rank.name
    }

    @TypeConverter
    fun toString(chair: ChairEnum): String {
        return chair.name
    }

    @TypeConverter
    fun toString(status: GameStatusEnum): String {
        return status.name
    }

    @TypeConverter
    fun toSuitEnum(suit: String): SuitEnum {
        return SuitEnum.valueOf(suit)
    }

    @TypeConverter
    fun toRankEnum(rank: String): RankEnum {
        return RankEnum.valueOf(rank)
    }

    @TypeConverter
    fun toChairEnum(chair: String): ChairEnum {
        return ChairEnum.valueOf(chair)
    }

    @TypeConverter
    fun toGameStatusEnum(status: String): GameStatusEnum {
        return GameStatusEnum.valueOf(status)
    }
}