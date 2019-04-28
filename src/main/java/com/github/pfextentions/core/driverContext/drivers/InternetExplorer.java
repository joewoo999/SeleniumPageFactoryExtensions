package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.core.BrowserConfig;
import com.github.pfextentions.core.driverContext.AbstractDriver;
import com.github.pfextentions.core.driverContext.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class InternetExplorer extends AbstractDriver implements Driver {

    public InternetExplorer(BrowserConfig config) {
        super(config);
    }

    @Override
    public InternetExplorer start() {
        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        driver = new InternetExplorerDriver(ieOptions);
        return this;
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }


//    @Override
//    public void quit() {
//        super.quit();
//        try {
//            Runtime.getRuntime().exec("taskkill /im iexplore.exe");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
