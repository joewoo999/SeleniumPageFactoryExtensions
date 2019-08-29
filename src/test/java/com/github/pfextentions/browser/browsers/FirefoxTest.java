package com.github.pfextentions.browser.browsers;

import com.github.pfextentions.browser.Configuration;
import com.github.pfextentions.page.DemoPage;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FirefoxTest {
    private static Firefox firefox;

    @BeforeClass
    public static void before() {
        Configuration config = Configuration.of("firefox", "true", "C:");
        firefox = new Firefox(config);
    }

    @Test
    public void start() {
        firefox.start();
        firefox.getWebDriver().get(DemoPage.URL);
        Assert.assertEquals(firefox.getWebDriver().getTitle(), DemoPage.TITLE);
    }

    @AfterClass
    public static void after() {
        firefox.quit();
    }

}