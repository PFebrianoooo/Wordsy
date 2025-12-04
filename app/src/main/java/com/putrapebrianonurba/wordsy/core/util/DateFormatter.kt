package com.putrapebrianonurba.wordsy.core.util

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

fun formatTimeBasedTimeStamp(timestamp: Long, format: String): String {
    val formatter = DateTimeFormatter.ofPattern(format, Locale.forLanguageTag("id-ID"))

    return Instant.ofEpochMilli(timestamp)
        .atZone(ZoneId.systemDefault())
        .toLocalDate()
        .format(formatter)
}