package com.apexrise.offline.util

import java.text.NumberFormat
import java.util.Locale
import kotlin.math.roundToLong

object Formatters {
    private val numberFormatter = NumberFormat.getNumberInstance(Locale.getDefault())

    fun litres(value: Double): String {
        return numberFormatter.format((value * 100).roundToLong() / 100.0)
    }

    fun money(value: Double): String {
        return numberFormatter.format((value * 100).roundToLong() / 100.0)
    }
}

