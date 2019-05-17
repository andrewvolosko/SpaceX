package com.avolosko.spacex.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.avolosko.spacex.db.entity.LaunchEntity

@Dao
interface LaunchesDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveLaunches(launches: List<LaunchEntity>)

    @Query("SELECT * FROM launches")
    fun getAllLaunches(): List<LaunchEntity>

}