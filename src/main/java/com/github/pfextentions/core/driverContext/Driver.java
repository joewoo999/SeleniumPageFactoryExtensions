package com.github.pfextentions.core.driverContext;

import org.openqa.selenium.WebDriver;

public interface Driver {

    Driver start();

    WebDriver getWebDriver();

    Driver managePageLoadTime(int timeInSecond);

    Driver manageImplicitlyWaitTime(int timeInSecond);

    void quit();

}
