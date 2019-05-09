package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.core.Configuration;
import com.github.pfextentions.core.PropertiesConfig;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InternetExplorerTest {
    private static InternetExplorer ie;

    @BeforeClass
    public static void before() {
        ie = new InternetExplorer(Configuration.of());
    }

    @Test
    public void start() {
        ie.start();
        ie.getWebDriver().get("about:blank");
        Assert.assertEquals(ie.getWebDriver().getCurrentUrl(), "about:blank");
    }

    @AfterClass
    public static void after() {
        ie.quit();
    }
}