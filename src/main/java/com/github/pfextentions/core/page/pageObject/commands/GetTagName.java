package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class GetTagName implements CommandFunction<String> {

    private ElementLocator locator;
    private String tagName;

    @Override
    public String apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        tagName = locator.findElement().getTagName();
        return tagName;
    }

    @Override
    public String toString() {
        return String.format("Element:%s tag name is '%s'.",
                locator, tagName);
    }
}
