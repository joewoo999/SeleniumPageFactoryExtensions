package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.core.BrowserConfig;
import com.github.pfextentions.core.driverContext.AbstractDriver;
import com.github.pfextentions.core.driverContext.Driver;
import com.google.common.base.Strings;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Firefox  extends AbstractDriver implements Driver {

    public Firefox(BrowserConfig config) {
        super(config);
    }

    @Override
    public Firefox start() {
        FirefoxOptions ffOptions = new FirefoxOptions()
                .setLogLevel(FirefoxDriverLogLevel.FATAL);

        if (!Strings.isNullOrEmpty(downloadDir)) {
            String fileType = "application/octet-stream,application/vnd.ms-excel,"
                    + "application/zip,application/exe,application/txt";
            ffOptions.addPreference("browser.download.useDownloadDir", true)
                    .addPreference("browser.download.folderList", 2)
                    .addPreference("browser.download.dir", downloadDir)
                    .addPreference("browser.helperApps.neverAsk.saveToDisk", fileType);
        }

        if (!Strings.isNullOrEmpty(binary)) {
            ffOptions.setBinary(binary);
        }

        ffOptions.setHeadless(isHeadless);

        driver = new FirefoxDriver(ffOptions);
        return this;
    }
}
