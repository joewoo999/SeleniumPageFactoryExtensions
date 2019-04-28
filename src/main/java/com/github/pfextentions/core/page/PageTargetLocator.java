package com.github.pfextentions.core.page;

import com.github.pfextentions.core.driverContext.DriverContext;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class PageTargetLocator implements WebDriver.TargetLocator {

    private Logger log = LoggerFactory.getLogger(WebDriver.TargetLocator.class.getSimpleName());
    private WebDriver driver = DriverContext.getDriver();

    public WebDriver frame(int index) {
        driver.switchTo().frame(index);
        log.debug("Target switch to frame: '{}'.", index);
        return driver;
    }

    public WebDriver frame(String nameOrId) {
        driver.switchTo().frame(nameOrId);
        log.debug("Target switch to frame: '{}'.", nameOrId);
        return driver;
    }

    public WebDriver frame(WebElement frameElement) {
        driver.switchTo().frame(frameElement);
        log.debug("Target switch to frame: '{}'.", frameElement);
        return driver;
    }

    public WebDriver parentFrame() {
        driver.switchTo().parentFrame();
        log.debug("Target switch to parent frame.");
        return driver;
    }

    public WebDriver window(String handle) {
        driver.switchTo().window(handle);
        log.debug("Target switch to window with handle: '{}'.", handle);
        return driver;
    }

    public WebDriver defaultContent() {
        driver.switchTo().defaultContent();
        log.debug("Target switch to default content.");
        return driver;
    }

    @Override
    public WebElement activeElement() {
        WebElement element = driver.switchTo().activeElement();
        log.debug("Target switch to active element.");
        return element;
    }

    public Alert alert() {
        return driver.switchTo().alert();
    }

    public WebDriver windowByTitle(String title) {
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            driver.switchTo().window(handle);
            if (title.equals(driver.getTitle()))
                break;
        }
        log.debug("Target switch to window: '{}'.", title);
        return driver;
    }

    public WebDriver newWindow() {
        Object[] handles = driver.getWindowHandles().toArray();
        driver.switchTo().window((String) handles[handles.length - 1]);
        log.debug("Target switch to new window.");
        return driver;
    }
}
