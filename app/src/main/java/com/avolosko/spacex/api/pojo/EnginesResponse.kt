package com.avolosko.spacex.api.pojo

import com.google.gson.annotations.SerializedName

data class EnginesResponse(@SerializedName("number")val number: Int,
                           @SerializedName("type") val type: String)