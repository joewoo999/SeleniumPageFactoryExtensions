package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class GetWebElement implements CommandFunction<WebElement> {
    private ElementLocator locator;

    @Override
    public WebElement apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        return locator.findElement();
    }

    @Override
    public String toString() {
        return String.format("Get wrapped webelement '%s'.", locator);
    }
}
