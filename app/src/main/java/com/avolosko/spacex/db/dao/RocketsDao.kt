package com.avolosko.spacex.db.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.avolosko.spacex.db.entity.RocketEntity

@Dao
interface RocketsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAllRockets(rockets: List<RocketEntity>)

    @Query("SELECT * FROM rockets")
    fun getAllRockets(): List<RocketEntity>

}