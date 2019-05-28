package com.avolosko.spacex.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.avolosko.spacex.DATABASE_NAME
import com.avolosko.spacex.db.dao.LaunchesDao
import com.avolosko.spacex.db.dao.RocketsDao
import com.avolosko.spacex.db.entity.LaunchEntity
import com.avolosko.spacex.db.entity.RocketEntity


@Database(entities = [LaunchEntity::class, RocketEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun launchesDao(): LaunchesDao
    abstract fun rocketsDao(): RocketsDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
        }
    }
}