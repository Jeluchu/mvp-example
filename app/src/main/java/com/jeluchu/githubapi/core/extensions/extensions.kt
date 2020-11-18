package com.jeluchu.githubapi.core.extensions

import com.squareup.moshi.Moshi
import java.text.SimpleDateFormat
import java.util.*

fun String.formatDate(): String {
    val initialFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'", Locale.ENGLISH)
    val format = SimpleDateFormat("dd/M/yyyy", Locale.US)
    val date = initialFormat.parse(this)
    return format.format(date!!)
}

inline fun <reified T> Moshi.fromJson(json: String): T = this.adapter(T::class.java).fromJson(json)

