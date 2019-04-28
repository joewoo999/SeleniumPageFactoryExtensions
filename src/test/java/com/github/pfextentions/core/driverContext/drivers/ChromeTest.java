package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.core.PropertiesConfig;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;

public class ChromeTest {
    private static Chrome chrome;

    @BeforeClass
    public static void before() {
        PropertiesConfig config = new PropertiesConfig();
        chrome = new Chrome(config);
        chrome.start();
    }

    public void start() {
        chrome.getWebDriver().get("http://localhost/");
        Assert.assertEquals(chrome.getWebDriver().getCurrentUrl(), "http://localhost/");
    }

    @AfterClass
    public static void after() {
        chrome.quit();
    }

}