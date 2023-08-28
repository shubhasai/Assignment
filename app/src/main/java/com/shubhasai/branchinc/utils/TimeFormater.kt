package com.shubhasai.branchinc.utils

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

object TimeFormater {
    fun convertToSocialFormat(timestamp: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC")

        val outputFormat = SimpleDateFormat("dd MMMM yyyy, HH:mm:ss", Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault()

        val date = inputFormat.parse(timestamp)
        return outputFormat.format(date)
    }
}