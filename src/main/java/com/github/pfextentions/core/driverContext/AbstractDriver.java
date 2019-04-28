package com.github.pfextentions.core.driverContext;

import com.github.pfextentions.core.BrowserConfig;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class AbstractDriver implements Driver {

    protected WebDriver driver;
    protected String binary, downloadDir;
    protected boolean startMaximized, isHeadless;

    public AbstractDriver(BrowserConfig config) {
        this.binary = config.browserBinary();
        this.downloadDir = config.defaultDownloadDir();
        this.startMaximized = config.startMaximized();
        this.isHeadless = config.isHeadless();
    }

    @Override
    public abstract AbstractDriver start();

    @Override
    public WebDriver getWebDriver() {
        Objects.requireNonNull(driver, "Webdriver instance is null,u should run 'start()' first.");
        return driver;
    }

    @Override
    public AbstractDriver managePageLoadTime(int timeInSecond) {
        getWebDriver().manage().timeouts().pageLoadTimeout(timeInSecond, SECONDS);
        return this;
    }

    @Override
    public AbstractDriver manageImplicitlyWaitTime(int timeInSecond) {
        getWebDriver().manage().timeouts().implicitlyWait(timeInSecond, SECONDS);
        return this;
    }

    @Override
    public void quit() {
        getWebDriver().quit();
    }
}
