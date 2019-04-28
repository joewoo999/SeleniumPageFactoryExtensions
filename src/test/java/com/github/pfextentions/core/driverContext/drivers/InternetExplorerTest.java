package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.core.PropertiesConfig;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class InternetExplorerTest{
    private static InternetExplorer ie;
    @BeforeClass
    public static void before() {
        PropertiesConfig config = new PropertiesConfig();
        ie = new InternetExplorer(config);
        ie.start();
    }

    @Test
    public void start() {
        ie.getWebDriver().get("about:blank");
        Assert.assertEquals(ie.getWebDriver().getCurrentUrl(),"about:blank");
    }

    @AfterClass
    public static void after() {
        ie.quit();
    }
}