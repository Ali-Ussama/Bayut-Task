package com.bayut.test.model.util

import java.util.*

object LanguageUtil {

    fun isArabicLocale(): Boolean {
        return Locale.getDefault().language.equals("ar", true)
    }

    fun isEnglishLocale(): Boolean {
        return Locale.getDefault().language.equals("en", true)
    }

    fun getLocal(): String {
        return Locale.getDefault().language
    }
}