package com.avolosko.spacex.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "launches")
data class LaunchEntity(
    val name: String,
    val status: Boolean,
    val startDate: Long,
    val dateLabel: String,
    val launchYear: Int,
    val rocketId: String,
    val imageUrl: String?
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}