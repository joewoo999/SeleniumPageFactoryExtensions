package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.BaseTest;
import com.github.pfextentions.core.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FirefoxTest extends BaseTest {
    private static Firefox firefox;

    @BeforeClass
    public static void before() {
        Configuration config = Configuration.of("firefox", "false", "C:");
        firefox = new Firefox(config);
    }

    @Test
    public void start() {
        firefox.start();
        firefox.getWebDriver().get(TEST_URL);
        Assert.assertEquals(firefox.getWebDriver().getTitle(), TEST_TITLE);
    }

    @AfterClass
    public static void after() {
        firefox.quit();
    }

}