package com.github.pfextentions.core.driverContext;

import com.github.pfextentions.core.factory.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;

public class DriverScreenshotTest {
    private String fileName = "shot.png";

    @BeforeClass
    public static void before() {
        DriverFactory.setUp();
    }

    @Test
    public void take() {
        DriverScreenshot.take(fileName);
        Assert.assertTrue(FileUtils.waitFor(new File(fileName), 5));
    }

    @Test
    public void take1() {
        DriverScreenshot.take((TakesScreenshot) DriverContext.getDriver(), fileName);
        Assert.assertTrue(FileUtils.waitFor(new File(fileName), 5));
    }

    @AfterClass
    public static void after() {
        DriverFactory.tearDown();
    }
}