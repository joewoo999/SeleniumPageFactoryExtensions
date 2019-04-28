package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.core.PropertiesConfig;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

public class FirefoxTest {
    private static Firefox firefox;

    @BeforeClass
    public static void before() {
        PropertiesConfig config = new PropertiesConfig();
        firefox = new Firefox(config);
        firefox.start();
    }

    public void start() {
        firefox.getWebDriver().get("about:config");
        Assert.assertEquals(firefox.getWebDriver().getCurrentUrl(),"about:config");
    }

    @AfterClass
    public static void after() {
        firefox.quit();
    }

}