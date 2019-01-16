package com.avolosko.spacex.api.pojo

import com.google.gson.annotations.SerializedName

data class RocketResponse(
    @SerializedName("rocket_id") val id: String,
    @SerializedName("active") val active: Boolean,
    @SerializedName("rocket_name") val name: String,
    @SerializedName("description") val description: String,
    @SerializedName("country") val country: String,
    @SerializedName("engines") val engines: EnginesResponse
)