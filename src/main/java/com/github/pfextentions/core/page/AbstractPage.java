package com.github.pfextentions.core.page;

import com.github.pfextentions.core.driverContext.DriverContext;
import com.github.pfextentions.core.driverContext.DriverScreenshot;
import com.github.pfextentions.core.factory.PageObjectFactory;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPage implements Page {
    private Logger log = LoggerFactory.getLogger(Page.class.getSimpleName());

    public AbstractPage() {
        PageObjectFactory.initElements(DriverContext.getDriver(), this);
    }

    @Override
    public WebDriver driver() {
        return DriverContext.getDriver();
    }

    @Override
    public void open() {
        driver().get(url());
        log.debug("Open page url: {}.", url());
    }

    @Override
    public void maximizeWindow() {
        driver().manage().window().maximize();
        log.debug("Browser window maximized.");
    }

    @Override
    public abstract String url();

    @Override
    public abstract String title();

    @Override
    public abstract void assertPageOpened();

    @Override
    public PageTargetLocator switchTo() {
        return new PageTargetLocator();
    }

    @Override
    public void getScreenshot(String filePath) {
        DriverScreenshot.take(filePath);
        log.debug("Get page screenshot as file: {}.", filePath);
    }

    @Override
    public Object executeJS(String script, Object... args) {
        return DriverContext.getJSExecutor().executeScript(script, args);
    }

    @Override
    public void scrollToTop() {
        executeJS("window.scrollTo(0, 0);");
        log.debug("Page scrolled to top.");
    }

    @Override
    public void scrollToBottom() {
        executeJS("window.scrollTo(0, document.body.clientHeight);");
        log.debug("Page scrolled to bottom.");
    }
}
