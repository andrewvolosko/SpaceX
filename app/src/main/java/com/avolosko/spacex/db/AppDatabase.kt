package com.avolosko.spacex.db

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.avolosko.spacex.DATABASE_NAME
import com.avolosko.spacex.db.dao.LaunchesDao
import com.avolosko.spacex.db.dao.RocketsDao
import com.avolosko.spacex.ui.Launch
import com.avolosko.spacex.ui.Rocket

/**
 * The Room database for this app
 */
@Database(entities = [Launch::class, Rocket::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun launchesDao(): LaunchesDao
    abstract fun rocketsDao(): RocketsDao

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .build()
        }
    }
}