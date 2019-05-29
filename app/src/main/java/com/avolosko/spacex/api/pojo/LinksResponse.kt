package com.avolosko.spacex.api.pojo

import com.google.gson.annotations.SerializedName

data class LinksResponse(
    @SerializedName("mission_patch_small") val missionPatchSmall: String?,
    @SerializedName("mission_patch") val missionPatch: String
)