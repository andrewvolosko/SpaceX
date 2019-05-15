package com.avolosko.spacex.db.entity

import android.arch.persistence.room.Entity

@Entity(tableName = "launches")
data class LaunchEntity(val id: String)