package com.avolosko.spacex.data

import com.avolosko.spacex.util.AppExecutors

class LaunchesRepositoryImpl(
    private val executors: AppExecutors,
    private val remoteDataSource: LaunchesDataSource,
    private val localDataSource: LaunchesDataSource
) : LaunchesRepository {

    override fun getLaunches(force: Boolean, callback: LaunchesRepository.Callback) {

        if (force) {
            getCloudLaunches(callback)
        } else {
            executors.diskIO().execute {
                val launches = localDataSource.getLaunches()

                if (launches == null) {
                    getCloudLaunches(callback)
                } else {
                    executors.mainThread().execute { callback.onSuccess(launches) }
                }
            }
        }
    }

    private fun getCloudLaunches(callback: LaunchesRepository.Callback) {
        executors.networkIO().execute {
            val launches = remoteDataSource.getLaunches()

            if (launches == null) {
                executors.mainThread().execute { callback.onError() }
            } else {
                localDataSource.saveLaunches(launches)
                executors.mainThread().execute { callback.onSuccess(launches) }
            }
        }
    }
}