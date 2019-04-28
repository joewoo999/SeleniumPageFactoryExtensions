package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class GetAttribute implements CommandFunction<String> {

    private ElementLocator locator;
    private String attr, value;

    @Override
    public String apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        attr = String.valueOf(objects[0]);
        value = locator.findElement().getAttribute(attr);
        return value;
    }

    @Override
    public String toString() {
        return String.format("Element:%s attribute '%s' value is '%s'.",
                locator, attr, value);
    }
}
