package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.core.Configuration;
import com.github.pfextentions.core.PropertiesConfig;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class FirefoxTest {
    private static Firefox firefox;

    @BeforeClass
    public static void before() {
        firefox = new Firefox(Configuration.of("firefox", "false",""));
    }

    @Test
    public void start() {
        firefox.start();
        firefox.getWebDriver().get("about:config");
        Assert.assertEquals(firefox.getWebDriver().getCurrentUrl(), "about:config");
    }

    @AfterClass
    public static void after() {
        firefox.quit();
    }

}