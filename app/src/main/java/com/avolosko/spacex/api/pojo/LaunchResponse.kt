package com.avolosko.spacex.api.pojo

import com.google.gson.annotations.SerializedName

data class LaunchResponse(
    @SerializedName("flight_number") val flightNumber: Int,
    @SerializedName("mission_name") val name: String,
    @SerializedName("launch_success") val launchStatus: Boolean,
    @SerializedName("launch_date_unix") val startDate: Long,
    @SerializedName("launch_year") val launchYear: String,
    @SerializedName("links")val links: Links,
    @SerializedName("rocket")val rocket: RocketResponse
)