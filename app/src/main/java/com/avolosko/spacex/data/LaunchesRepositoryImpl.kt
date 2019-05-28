package com.avolosko.spacex.data

import com.avolosko.spacex.db.entity.LaunchEntity
import com.avolosko.spacex.util.AppExecutors
import javax.inject.Inject
import javax.inject.Named

class LaunchesRepositoryImpl @Inject constructor(
    private val executors: AppExecutors,

    @Named("cloud")
    private val remoteDataSource: LaunchesDataSource,

    @Named("local")
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