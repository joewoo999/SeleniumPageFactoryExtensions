package com.github.pfextentions.common.config;


import org.junit.Test;

import static org.junit.Assert.*;

public class ConfigFactoryTest {

    @Test
    public void init() {
        ConfigTest configTest = ConfigFactory.init("test.properties", ConfigTest.class);

        assertEquals("string", configTest._string);
        assertEquals(1, configTest._int);
        assertTrue(configTest._boolean);
        assertEquals(1.2f, configTest._float, 0);
        assertEquals(1.23d, configTest._double, 0);
        assertEquals(100L, configTest._long);
        assertTrue(configTest.empty.isEmpty());
        assertNull(configTest.nullValue);
    }
}