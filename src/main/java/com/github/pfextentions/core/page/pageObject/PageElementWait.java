package com.github.pfextentions.core.page.pageObject;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Sleeper;

import java.time.Clock;
import java.time.Duration;

public class PageElementWait extends FluentWait<ElementLocator> {


    public PageElementWait(ElementLocator locator) {
        super(locator);
    }

    public PageElementWait(ElementLocator locator, Clock clock, Sleeper sleeper) {
        super(locator, clock, sleeper);
    }

    public static PageElementWait getInstance(ElementLocator locator) {
        return new PageElementWait(locator);
    }

    public PageElementWait withTimeout(long second) {
        return (PageElementWait) super.withTimeout(Duration.ofSeconds(second));
    }


}
