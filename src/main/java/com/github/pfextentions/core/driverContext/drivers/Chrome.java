package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.core.BrowserConfig;
import com.github.pfextentions.core.driverContext.AbstractDriver;
import com.github.pfextentions.core.driverContext.Driver;
import com.google.common.base.Strings;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Chrome extends AbstractDriver implements Driver {

    public Chrome(BrowserConfig config) {
        super(config);
    }

    @Override
    public Chrome start() {
        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--disable-popup-blocking");

        if (!Strings.isNullOrEmpty(binary)) {
            chromeOptions.setBinary(binary);
        }

        if (!Strings.isNullOrEmpty(downloadDir)) {
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory", downloadDir);
            prefs.put("profile.default_content_settings.popups", 0);
            chromeOptions.setExperimentalOption("prefs", prefs);
        }

        if (startMaximized)
            chromeOptions.addArguments("--start-maximized");

        chromeOptions.setHeadless(isHeadless);

        driver = new ChromeDriver(chromeOptions);
        return this;
    }
}
