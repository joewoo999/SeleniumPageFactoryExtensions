package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class FindElement implements CommandFunction<WebElement> {
    private ElementLocator locator;
    private By by;

    @Override
    public WebElement apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;

        if (!(objects[0] instanceof By))
            return null;
        this.by = (By) objects[0];

        return locator.findElement().findElement(by);
    }

    @Override
    public String toString() {
        return String.format("Find child element: '%s' of parent: %s", by, locator);
    }
}
