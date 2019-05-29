package com.avolosko.spacex

import com.avolosko.spacex.api.mapper.LaunchMapper
import com.avolosko.spacex.api.pojo.EnginesResponse
import com.avolosko.spacex.api.pojo.LaunchResponse
import com.avolosko.spacex.api.pojo.LinksResponse
import com.avolosko.spacex.api.pojo.RocketResponse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class LaunchMapperTest {

    private lateinit var mapper: LaunchMapper

    private val launches = mutableListOf<LaunchResponse>()

    @Before
    fun setup() {
        for (i in 0..10) {
            launches.add(
                LaunchResponse(
                    1,
                    "test_launch",
                    true,
                    1607731200,
                    "2019",
                    LinksResponse("test_patch_small", "test_patch"),
                    RocketResponse(
                        "rocket_id", true, "rocket_name", "rocket_description", "rocket_country",
                        EnginesResponse(2, "Falcon")
                    )
                )
            )
        }

        mapper = LaunchMapper()
    }

    @Test
    fun shouldMapLaunch() {
        val result = mapper.map(launches)
        assert(result.isNotEmpty())

        val launch = result[0]
        assert(launch.name == "test_launch")
        assert(launch.rocketId == "rocket_id")
    }
}