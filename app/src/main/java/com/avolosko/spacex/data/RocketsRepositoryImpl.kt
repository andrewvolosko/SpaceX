package com.avolosko.spacex.data

import com.avolosko.spacex.util.AppExecutors

class RocketsRepositoryImpl(
    private val executors: AppExecutors,
    private val remoteDataSource: RocketsDataSource,
    private val localDataSource: RocketsDataSource
) : RocketsRepository {

    override fun getRockets(force: Boolean, callback: RocketsRepository.Callback) {

        if (force) {
            getCloudRockets(callback)
        } else {
            executors.diskIO().execute {
                val rockets = localDataSource.getRockets()

                if (rockets == null || rockets.isEmpty()) {
                    getCloudRockets(callback)
                } else {
                    executors.mainThread().execute { callback.onSuccess(rockets) }
                }
            }
        }
    }

    private fun getCloudRockets(callback: RocketsRepository.Callback) {
        executors.networkIO().execute {
            val rockets = remoteDataSource.getRockets()

            if (rockets == null) {
                executors.mainThread().execute { callback.onError() }
            } else {
                localDataSource.saveRockets(rockets)
                executors.mainThread().execute { callback.onSuccess(rockets) }
            }
        }
    }
}