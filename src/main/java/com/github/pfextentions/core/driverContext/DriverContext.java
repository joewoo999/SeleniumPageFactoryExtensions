package com.github.pfextentions.core.driverContext;

import com.github.pfextentions.core.BrowserConfig;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Objects;

public class DriverContext {

    private static ThreadLocal<BrowserConfig> threadConfig = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    private static ThreadLocal<JavascriptExecutor> threadJSExecutor = new ThreadLocal<>();
    private static ThreadLocal<Actions> threadActions = new ThreadLocal<>();
    private final static String ERR_MSG = "Webdriver instance is null, u should run 'set()' first.";

    private DriverContext() {
    }

    public static void set(BrowserConfig config, WebDriver driver) {
        threadConfig.set(config);
        threadDriver.set(driver);
        threadJSExecutor.set((JavascriptExecutor)driver);
        threadActions.set(new Actions(driver));
    }

    public static BrowserConfig getConfig() {
        Objects.requireNonNull(threadDriver.get(), ERR_MSG);
        return threadConfig.get();
    }

    public static WebDriver getDriver() {
        Objects.requireNonNull(threadDriver.get(), ERR_MSG);
        return threadDriver.get();
    }

    public static JavascriptExecutor getJSExecutor() {
        Objects.requireNonNull(threadJSExecutor.get(), ERR_MSG);
        return threadJSExecutor.get();
    }

    public static Actions getActions() {
        Objects.requireNonNull(threadActions.get(), ERR_MSG);
        return threadActions.get();
    }

    public static void removeAll() {
        threadActions.remove();
        threadDriver.remove();
        threadJSExecutor.remove();
    }

}
