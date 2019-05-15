package com.avolosko.spacex.data

import com.avolosko.spacex.util.AppExecutors

class LaunchesRepositoryImpl(
    private val executors: AppExecutors,
    private val remoteDataSource: LaunchesDataSource
) : LaunchesRepository {

    override fun getLaunches(callback: LaunchesRepository.Callback) {
        executors.networkIO().execute {
            val launches = remoteDataSource.getLaunches()

            if (launches == null) {
                executors.mainThread().execute { callback.onError() }
            } else {
                executors.mainThread().execute { callback.onSuccess(launches) }
            }
        }
    }
}