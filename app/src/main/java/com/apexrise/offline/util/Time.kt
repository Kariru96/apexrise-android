package com.apexrise.offline.util

import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter

object Time {
    private val isoDate: DateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE

    fun todayIso(): String = LocalDate.now().format(isoDate)

    fun currentMonthIso(): String = YearMonth.now().toString()

    fun monthStartEnd(monthIso: String): Pair<String, String> {
        val ym = YearMonth.parse(monthIso)
        val start = ym.atDay(1).format(isoDate)
        val end = ym.plusMonths(1).atDay(1).format(isoDate)
        return start to end
    }
}

