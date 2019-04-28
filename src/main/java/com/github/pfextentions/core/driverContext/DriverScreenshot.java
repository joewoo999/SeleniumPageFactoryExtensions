package com.github.pfextentions.core.driverContext;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

public class DriverScreenshot {

    public static void take(String savedFileName) {
        take((TakesScreenshot) DriverContext.getDriver(), savedFileName);
    }

    public static void take(TakesScreenshot screenshot, String savedFileName) {
        try {
            File file = screenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File(savedFileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
