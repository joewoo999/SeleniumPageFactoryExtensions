package com.github.pfextentions.core.page;

import org.openqa.selenium.WebDriver;

public interface Page {

    String url();

    String title();

    void open();

    void maximizeWindow();

    void assertPageOpened();

    WebDriver driver();

    PageTargetLocator switchTo();

    void getScreenshot(String filePath);

    Object executeJS(String script, Object... args);

    void scrollToTop();

    void scrollToBottom();

}
