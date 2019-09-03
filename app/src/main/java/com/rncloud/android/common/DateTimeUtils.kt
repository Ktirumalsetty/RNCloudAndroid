package com.rncloud.android.common

import java.text.SimpleDateFormat

object DateTimeUtils {

    val SERVER_DATE_TIME_FORMAAT ="yyyy-MM-dd'T'HH:mm:ss"
//    val CLIENT_DATE_TIME_FORMAAT ="MMM-dd-yyyy"  //Jun-17-2015
    val CLIENT_DATE_TIME_FORMAAT_2 ="MMM dd, yyyy"  //Mar 06, 2007

    fun formatToDate(date:String):String{
        val dateFormatParser = SimpleDateFormat(SERVER_DATE_TIME_FORMAAT)
        val formatter = SimpleDateFormat(CLIENT_DATE_TIME_FORMAAT_2)
        val formattedDate = dateFormatParser.parse(date)
        return formatter.format(formattedDate)
    }

    fun formatToEduCompletedAt(date:String):String{
        val dateFormatParser = SimpleDateFormat(SERVER_DATE_TIME_FORMAAT)
        val formatter = SimpleDateFormat(CLIENT_DATE_TIME_FORMAAT_2)
        val formattedDate = dateFormatParser.parse(date)
        return formatter.format(formattedDate)
    }
}