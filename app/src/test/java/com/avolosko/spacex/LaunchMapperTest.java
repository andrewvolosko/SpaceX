package com.avolosko.spacex;

import com.avolosko.spacex.api.mapper.LaunchMapper;
import com.avolosko.spacex.db.entity.LaunchEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNotNull;

@RunWith(JUnit4.class)
public class LaunchMapperTest {

    private LaunchMapper mapper;

    @Before
    public void setup() {
        mapper = new LaunchMapper();
    }

    @Test
    public void shouldMapLaunches() {
        List<LaunchEntity> launches = mapper.map(new ArrayList<>());
        assertNotNull(launches);
    }
}