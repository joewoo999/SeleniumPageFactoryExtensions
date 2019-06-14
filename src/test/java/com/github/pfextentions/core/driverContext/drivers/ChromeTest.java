package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.core.Configuration;
import com.github.pfextentions.core.page.DemoPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ChromeTest {
    private static Chrome chrome;

    @BeforeClass
    public static void before() {
        chrome = new Chrome(Configuration.of("chrome", "true", "."));
    }

    @Test
    public void start() {
        chrome.start();
        chrome.getWebDriver().get(DemoPage.URL);
        Assert.assertEquals(chrome.getWebDriver().getTitle(), DemoPage.TITLE);
    }

    @AfterClass
    public static void after() {
        chrome.quit();
    }

}