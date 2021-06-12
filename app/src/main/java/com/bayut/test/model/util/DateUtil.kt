package com.bayut.test.model.util

import android.text.format.DateUtils
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateUtil {
    const val MILLIS_IN_SECOND: Long = 1000
    const val MILLIS_IN_MINUTE: Long = 60 * MILLIS_IN_SECOND
    const val MILLIS_IN_HOUR: Long = 60 * MILLIS_IN_MINUTE
    const val MILLIS_IN_DAY: Long = 24 * MILLIS_IN_HOUR

    const val timeFormatAM_PM: String = "hh:mm aaa"
    const val dayMonthDateFormat: String = "d MMM"
    const val monthDayDateFormat: String = "MMM d"
    const val SlashShortDateFormat: String = "dd/MM/yyyy"
    const val timeDateFormat: String = "HH:mm"
    const val timeDateFormatWithMs: String = "HH:mm:ss"
    const val dashLongDateFormatWithMs: String = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    const val dashLongDateFormat: String = "yyyy-MM-dd'T'HH:mm:ss'Z'"
    const val dashLongDateTimeFormat: String = "dd/MM/yyyy hh:mm aaa"
    const val dashLongDateTimeFormatWithMs: String = "yyyy-MM-dd hh:mm:ss.SSS"
    const val fullDateFormat: String = "EEEE, d MMM 'at' hh:mm aaa"
    const val MonthYearDateFormat: String = "MM/yy"
    const val YearMonthDateFormat: String = "yy/MM"

    fun getDateString(dateString: String, dateFormat: String): String {
        return formatSelectedDate(dateFormat, dateString)
    }

    fun formatSelectedDate(dateFormat: String, dateString: String): String {
        val df = SimpleDateFormat(dateFormat, Locale.US)
        val newFormat = SimpleDateFormat(SlashShortDateFormat, Locale.US)
        return newFormat.format(df.parse(dateString)!!)
    }

    fun formatCalendar(calendar: Calendar, format: String): String {
        val simpleDateFormat =
            SimpleDateFormat(format, Locale.US)

        return simpleDateFormat.format(calendar.time)
    }

    private fun getStringFromLongIfNot0(long: Long, value: String): String {
        return if (long > 0)
            long.toString() + value
        else ""
    }

    fun getCurrentCalendar(): Calendar {
        return Calendar.getInstance()
    }

    fun addYearsToCurrentDate(years: Int): Date {
        return getCurrentCalendar().apply { add(Calendar.YEAR, years) }.time
    }

    fun formatDate(date: Date?, format: String): String {

        val calendar = Calendar.getInstance()
        date?.let {
            calendar.time = date
        }
        if (date != null)
            return formatCalendar(calendar, format)
        return ""
    }

    fun getAPIFormat(date: Date?): String {
        return formatDate(date, dashLongDateFormatWithMs)
    }

    fun getDateFromString(dateString: String?, dateFormat: String): Date {
        return try {
            SimpleDateFormat(dateFormat, Locale.ENGLISH).parse(dateString.orEmpty()) ?: Date()
        } catch (e: java.lang.Exception) {
            Date()
        }

    }

    fun getDateFromServerFormat(dateString: String?): Date {
        return getDateFromString(dateString, dashLongDateFormatWithMs)
    }

    fun getDateFromMilliseconds(milliseconds: Long?): String {
        milliseconds?.let {
            val dateCalendar = getCurrentCalendar()
            dateCalendar.timeInMillis = it
            return formatCalendar(dateCalendar, SlashShortDateFormat)
        }
        return ""
    }

    fun getCurrentDateTime(): Date? {
        val dateTime = Date(System.currentTimeMillis())
        dateTime.seconds = 0
        return dateTime
    }

    fun formatSelectedDate(
        currentDateFormat: String,
        newDateFormat: String,
        dateString: String
    ): String {
        val df = SimpleDateFormat(currentDateFormat, Locale.US)
        val newFormat = SimpleDateFormat(newDateFormat, Locale.US)
        var date = Date()
        try {
            date = df.parse(dateString)
        } catch (e: Exception) {
        }
        return newFormat.format(date)
    }

    fun getDurationFromTime(value: Long, day: String, hour: String): String {
        var mutableValue = value
        val days = TimeUnit.MILLISECONDS.toDays(mutableValue)
        mutableValue %= DateUtils.DAY_IN_MILLIS
        val hours = TimeUnit.MILLISECONDS.toHours(mutableValue)
        return getStringFromLongIfNot0(days, " $day ") + getStringFromLongIfNot0(hours, " $hour")
    }

    fun getMonthYearDateFormat(stringDate: String?): String? {
        return stringDate?.let {
            formatSelectedDate(
                dashLongDateFormatWithMs, MonthYearDateFormat,
                it
            )
        }
    }

    fun getDaysDifference(startDate: Long?, endDate: Long?): Long {
        if (startDate != null && endDate != null)
            return TimeUnit.MILLISECONDS.toDays((endDate - startDate))
        return 0
    }

    fun getHoursDifference(startDate: Long?, endDate: Long?): Long {
        if (startDate != null && endDate != null)
            return TimeUnit.MILLISECONDS.toHours((endDate - startDate))
        return 0
    }

    fun getCalendarFromDate(value: Date?): Calendar? {
        value?.let {
            val calendar = Calendar.getInstance()
            calendar.time = value
            return calendar
        }
        return null
    }

}