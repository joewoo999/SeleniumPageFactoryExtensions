package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.BaseTest;
import com.github.pfextentions.core.Configuration;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

public class ChromeTest extends BaseTest {
    private static Chrome chrome;

    @BeforeClass
    public static void before() {
        chrome = new Chrome(Configuration.of("chrome", "true", "."));
    }

    @Test
    public void start() {
        chrome.start();
        chrome.getWebDriver().get(TEST_URL);
        Assert.assertEquals(chrome.getWebDriver().getTitle(), TEST_TITLE);
    }

    @AfterClass
    public static void after() {
        chrome.quit();
    }

}