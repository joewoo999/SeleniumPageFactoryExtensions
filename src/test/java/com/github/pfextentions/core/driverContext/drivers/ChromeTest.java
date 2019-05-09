package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.core.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class ChromeTest {
    private static Chrome chrome;

    @BeforeClass
    public static void before() {
        chrome = new Chrome(Configuration.of());
    }

    @Test
    public void start() {
        chrome.start();
        chrome.getWebDriver().get("http://localhost/");
        Assert.assertEquals(chrome.getWebDriver().getCurrentUrl(), "http://localhost/");
    }

    @AfterClass
    public static void after() {
        chrome.quit();
    }

}