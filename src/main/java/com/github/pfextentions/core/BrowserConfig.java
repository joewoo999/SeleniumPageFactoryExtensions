package com.github.pfextentions.core;

import com.github.pfextentions.core.driverContext.Driver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.BrowserType;

import java.util.Arrays;

public interface BrowserConfig {

    String CHROME_DRIVER_PROPERTY = ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;

    String FIREFOX_DRIVER_PROPERTY = GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY;

    String IE_DRIVER_PROPERTY = InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY;

    String[] CHROME = {BrowserType.CHROME, BrowserType.GOOGLECHROME};

    String[] FIREFOX = {BrowserType.FIREFOX, "ff"};

    String[] IE = {BrowserType.IE, BrowserType.IEXPLORE, "ie"};

    String browserType();

    boolean isHeadless();

    String browserBinary();

    Class<? extends Driver> driverClass();

    int pageLoadTime();

    int implicitlyWaitTime();

    boolean startMaximized();

    String defaultDownloadDir();

    default boolean isChrome() {
        return Arrays.asList(CHROME).contains(browserType());
    }

    default boolean isFirefox() {
        return Arrays.asList(FIREFOX).contains(browserType());
    }

    default boolean isIE() {
        return Arrays.asList(IE).contains(browserType());
    }
}
