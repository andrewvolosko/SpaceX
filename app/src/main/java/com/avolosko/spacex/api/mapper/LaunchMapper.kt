package com.avolosko.spacex.api.mapper

import android.util.Log
import com.avolosko.spacex.api.pojo.LaunchResponse
import com.avolosko.spacex.ui.Launch
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class LaunchMapper {

    private val DATE_PATTERN = "dd/MM/yyyy HH:mm"

    fun map(launches: List<LaunchResponse>): List<Launch> {
        return launches.map {
            Launch(
                it.name,
                it.launchStatus,
                it.startDate,
                prepareDateLabel(it.startDate),
                it.launchYear.toInt(),
                it.rocket.id,
                it.links.missionPatchSmall
            )
        }
    }

    private fun prepareDateLabel(unixTimestamp: Long): String {
        var dateLabel = ""
        val inFormatter = SimpleDateFormat(DATE_PATTERN, Locale.getDefault())
        try {
            dateLabel = inFormatter.format(Date(unixTimestamp * 1000))//to milli
        } catch (e: ParseException) {
            e.printStackTrace()
        } catch (e: NullPointerException) {
            e.printStackTrace()
        }
        return dateLabel
    }
}