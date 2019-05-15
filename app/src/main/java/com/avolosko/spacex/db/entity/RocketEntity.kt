package com.avolosko.spacex.db.entity

import android.arch.persistence.room.Entity

@Entity(tableName = "rockets")
data class RocketEntity(val id: String)