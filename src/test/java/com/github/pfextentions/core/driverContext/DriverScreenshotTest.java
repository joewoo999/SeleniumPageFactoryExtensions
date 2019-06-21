package com.github.pfextentions.core.driverContext;

import com.github.pfextentions.core.page.DemoPage;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;

public class DriverScreenshotTest {

    @BeforeClass
    public static void before() {
        DriverFactory.setUp();
        DriverContext.getDriver().get(DemoPage.URL);
    }

    @Test
    public void take() {
        String fileName = "shot.png";
        DriverScreenshot.take(fileName);
        File file = new File(fileName);
        Assert.assertTrue(FileUtils.waitFor(file, 5));
        FileUtils.deleteQuietly(file);
    }

    @AfterClass
    public static void after() {
        DriverFactory.tearDown();
    }
}