package com.avolosko.spacex.db.entity

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "rockets")
data class RocketEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String,
    val country: String,
    val enginesCount: Int,
    val active: Boolean
)