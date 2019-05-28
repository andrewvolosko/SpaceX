package com.avolosko.spacex.data

import com.avolosko.spacex.util.AppExecutors
import javax.inject.Inject
import javax.inject.Named

class RocketsRepositoryImpl @Inject constructor(
    private val executors: AppExecutors,

    @Named("cloud")
    private val remoteDataSource: RocketsDataSource,

    @Named("local")
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