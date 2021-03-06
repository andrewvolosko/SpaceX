package com.avolosko.spacex.api.mapper

import com.avolosko.spacex.api.pojo.LaunchResponse
import com.avolosko.spacex.db.entity.LaunchEntity
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class LaunchMapper @Inject constructor(){

    private val DATE_PATTERN = "dd/MM/yyyy HH:mm"

    fun map(launches: List<LaunchResponse>): List<LaunchEntity> {
        return launches.map {
            LaunchEntity(
                it.flightNumber,
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