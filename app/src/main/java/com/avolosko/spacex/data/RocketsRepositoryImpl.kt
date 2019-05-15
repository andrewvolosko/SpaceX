package com.avolosko.spacex.data

import com.avolosko.spacex.util.AppExecutors

class RocketsRepositoryImpl(
    private val executors: AppExecutors,
    private val remoteDataSource: RocketsDataSource
) : RocketsRepository {

    override fun getRockets(callback: RocketsRepository.Callback) {
        executors.networkIO().execute {
            val rockets = remoteDataSource.getRockets()

            if (rockets == null) {
                executors.mainThread().execute { callback.onError() }
            } else {
                executors.mainThread().execute { callback.onSuccess(rockets) }
            }
        }
    }
}