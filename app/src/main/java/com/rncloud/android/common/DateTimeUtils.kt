package com.rncloud.android.common

import java.text.SimpleDateFormat

object DateTimeUtils {

    val SERVER_DATE_TIME_FORMAAT ="yyyy-MM-dd'T'HH:mm:ss"
    val CLIENT_DATE_TIME_FORMAAT ="yyyy-MM-dd"

    fun formatToDate(date:String):String{
        val dateFormatParser = SimpleDateFormat(SERVER_DATE_TIME_FORMAAT)
        val formatter = SimpleDateFormat(CLIENT_DATE_TIME_FORMAAT)
        val formattedDate = dateFormatParser.parse(date)
        return formatter.format(formattedDate)
    }
}