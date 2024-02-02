package az.isfan.spoonsgame.General

import java.text.SimpleDateFormat
import java.util.Date

fun getDateTimeFromTimestamp(timestamp: Long): String? {
    val date = Date()
    date.time = timestamp
    return SimpleDateFormat("dd.MM.yyyy HH:mm").format(date)
}