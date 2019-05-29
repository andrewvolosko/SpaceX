package com.avolosko.spacex

import com.avolosko.spacex.api.mapper.RocketMapper
import com.avolosko.spacex.api.pojo.EnginesResponse
import com.avolosko.spacex.api.pojo.RocketResponse
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class RocketMapperTest {

    private lateinit var mapper: RocketMapper

    private val rocketsResponse = mutableListOf<RocketResponse>()

    @Before
    fun setup() {
        for (i in 0..10) {
            rocketsResponse.add(
                RocketResponse(
                    "id",
                    true,
                    "test_name",
                    "test_description",
                    "USA",
                    EnginesResponse(2, "Falcon")
                )
            )
        }

        mapper = RocketMapper()
    }

    @Test
    fun shouldMapRocket() {
        val result = mapper.map(rocketsResponse)
        assert(result.isNotEmpty())

        val rocket = result[0]
        assert(rocket.id == "id")
        assert(rocket.active)
        assert(rocket.name == "test_name")
        assert(rocket.description == "test_description")
        assert(rocket.country == "USA")
    }
}