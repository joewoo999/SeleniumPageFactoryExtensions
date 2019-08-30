package com.github.pfextentions.browser;

import com.github.pfextentions.DemoPage;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

public class DriverScreenshotTest {
    private static File file;

    @BeforeClass
    public static void before() {
        BrowserFactory.setUp();
        DriverContext.getDriver().get(DemoPage.URL);
    }

    @Test
    public void take() {
        String fileName = "shot.png";
        DriverScreenshot.take(fileName);
        file = new File(fileName);
        Assert.assertTrue(FileUtils.waitFor(file, 5));

    }

    @AfterClass
    public static void after() {
        BrowserFactory.tearDown();
        FileUtils.deleteQuietly(file);
    }
}