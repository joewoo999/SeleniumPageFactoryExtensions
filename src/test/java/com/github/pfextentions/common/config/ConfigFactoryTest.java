package com.github.pfextentions.common.config;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ConfigFactoryTest {

    @Test
    public void init() {
        ConfigTest configTest = ConfigFactory.init("config.properties", ConfigTest.class);

        assertEquals("IEDriverServer.exe", configTest.IEDriver);
        assertEquals("chromedriver.exe", configTest.chromeDriver);
        assertEquals("geckodriver.exe", configTest.firefoxDriver);

    }
}