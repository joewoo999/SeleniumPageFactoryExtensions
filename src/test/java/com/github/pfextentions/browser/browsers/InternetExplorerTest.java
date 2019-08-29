package com.github.pfextentions.browser.browsers;

import com.github.pfextentions.browser.Configuration;
import com.github.pfextentions.page.DemoPage;
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
        ie.getWebDriver().get(DemoPage.URL);
        Assert.assertEquals(ie.getWebDriver().getTitle(), DemoPage.TITLE);
    }

    @AfterClass
    public static void after() {
        ie.quit();
    }
}