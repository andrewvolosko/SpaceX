package com.avolosko.spacex;

import com.avolosko.spacex.api.mapper.RocketMapper;
import com.avolosko.spacex.db.entity.RocketEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class RocketsMapperTest {

    private RocketMapper mapper;

    @Before
    public void setup() {
        mapper = new RocketMapper();
    }

    @Test
    public void shouldMapRockets() {
        List<RocketEntity> rockets = mapper.map(new ArrayList<>());
        assertNotNull(rockets);
    }
}