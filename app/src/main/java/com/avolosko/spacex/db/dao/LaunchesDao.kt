package com.avolosko.spacex.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import com.avolosko.spacex.db.entity.LaunchEntity

@Dao
interface LaunchesDao{

    fun savelaunches()

    @Query("SELECT * FROM launches")
    fun getAllLaunches(): List<LaunchEntity>

}