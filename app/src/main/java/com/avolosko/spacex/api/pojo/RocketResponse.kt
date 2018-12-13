package com.avolosko.spacex.api.pojo

import com.google.gson.annotations.SerializedName

data class RocketResponse(
    @SerializedName("rocket_id") val id: String,
    val active: Boolean,
    @SerializedName("rocket_name") val name: String,
    val description: String,
    val country: String,
    val engines: EnginesResponse
)