package com.bayut.test.model.util

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    const val dayMonthDateFormat: String = "d MMM"
    const val dashLongDateTimeFormatWithMs: String = "yyyy-MM-dd hh:mm:ss.SSS"

    @JvmStatic
    fun formatSelectedDate(
        currentDateFormat: String,
        newDateFormat: String,
        dateString: String
    ): String? {
        val df = SimpleDateFormat(currentDateFormat, Locale.US)
        val newFormat = SimpleDateFormat(newDateFormat, Locale.US)

        try {
            val date: Date? = df.parse(dateString)
            if (date != null)
                return newFormat.format(date)
        } catch (e: Exception) {
        }
        return null
    }


}