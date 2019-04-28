package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class GetValue implements CommandFunction<String>  {

    private ElementLocator locator;
    private String value;

    @Override
    public String apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        value = locator.findElement().getAttribute("value");
        return value;
    }

    @Override
    public String toString() {
        return String.format("Element:%s value is '%s'.", locator, value);
    }
}
