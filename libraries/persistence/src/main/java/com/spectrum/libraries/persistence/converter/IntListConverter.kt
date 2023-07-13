package com.spectrum.libraries.persistence.converter

import androidx.room.TypeConverter

class IntListConverter {
    @TypeConverter
    fun fromIntList(numbers: List<Int>): String {
        return numbers.joinToString(",")
    }

    @TypeConverter
    fun toIntList(numbersString: String): List<Int> {
        return numbersString.split(",").map { it.toInt() }
    }
}
