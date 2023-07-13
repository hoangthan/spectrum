package com.spectrum.libraries.core.utils

import java.text.SimpleDateFormat
import java.util.Locale

object DateTimeUtils {

    const val PATTERN_YMD = "yyyy-mm-dd"
    const val PATTERN_DMY = "dd/MMM/yyyy"

    fun transformFormat(
        dateString: String?,
        oldPattern: String,
        newPattern: String
    ): String? {
        return try {
            dateString ?: return null
            val oldDateFormat = SimpleDateFormat(oldPattern, Locale.getDefault())
            val newDateFormat = SimpleDateFormat(newPattern, Locale.getDefault())
            val dateTime = oldDateFormat.parse(dateString)
            return newDateFormat.format(dateTime)
        } catch (exception: Exception) {
            null
        }
    }
}
