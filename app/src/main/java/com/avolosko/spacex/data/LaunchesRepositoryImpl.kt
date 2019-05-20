package com.avolosko.spacex.data

import com.avolosko.spacex.db.entity.LaunchEntity
import com.avolosko.spacex.util.AppExecutors

class LaunchesRepositoryImpl(
    private val executors: AppExecutors,
    private val remoteDataSource: LaunchesDataSource,
    private val localDataSource: LaunchesDataSource
) : LaunchesRepository {

    private var cachedAllLaunches: List<LaunchEntity>? = null

    override fun getLaunches(force: Boolean, callback: LaunchesRepository.Callback) {

        if (force) {
            getCloudLaunches(callback)
        } else if (cachedAllLaunches == null) {
            executors.diskIO().execute {
                val launches = localDataSource.getLaunches()

                if (launches == null || launches.isEmpty()) {
                    getCloudLaunches(callback)
                } else {
                    cachedAllLaunches = launches
                    executors.mainThread().execute { callback.onSuccess(cachedAllLaunches!!) }
                }
            }
        } else {
            executors.mainThread().execute { callback.onSuccess(cachedAllLaunches!!) }
        }
    }

    private fun getCloudLaunches(callback: LaunchesRepository.Callback) {
        executors.networkIO().execute {
            val launches = remoteDataSource.getLaunches()

            if (launches == null) {
                executors.mainThread().execute { callback.onError() }
            } else {
                cachedAllLaunches = launches
                localDataSource.saveLaunches(launches)
                executors.mainThread().execute { callback.onSuccess(cachedAllLaunches!!) }
            }
        }
    }
}