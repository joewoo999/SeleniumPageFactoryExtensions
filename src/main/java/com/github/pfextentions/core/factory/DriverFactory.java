package com.github.pfextentions.core.factory;

import com.github.pfextentions.core.BrowserConfig;
import com.github.pfextentions.core.driverContext.Driver;
import com.github.pfextentions.core.driverContext.DriverContext;
import com.github.pfextentions.core.PropertiesConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DriverFactory {

    private static Driver driver;

    public static void setUp() {
        setUp(new PropertiesConfig());
    }

    public static void setUp(BrowserConfig config) {
        try {
            Constructor constructor = config.driverClass().getConstructor(BrowserConfig.class);
            driver = (Driver) constructor.newInstance(config);

            driver.start().managePageLoadTime(config.pageLoadTime());

            DriverContext.set(config, driver.getWebDriver());

        } catch (NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void tearDown() {
        driver.quit();
        DriverContext.removeAll();
    }
}
