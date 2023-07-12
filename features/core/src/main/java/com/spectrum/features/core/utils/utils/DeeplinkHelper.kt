package com.spectrum.features.core.utils.utils

import android.net.Uri
import androidx.core.net.toUri

object DeeplinkHelper {

    fun toUri(string: String, vararg values: Any): Uri {
        var result = string
        for (value in values) {
            result = result.replaceFirst(Regex("\\{[^}]+\\}"), value.toString())
        }
        return result.toUri()
    }
}
