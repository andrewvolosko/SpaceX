package com.avolosko.spacex.ui

data class Launch(
    val name: String,
    val status: Boolean,
    val startDate: Long,
    val dateLabel: String,
    val launchYear: Int,
    val rocketId: String,
    val imageUrl: String?
)