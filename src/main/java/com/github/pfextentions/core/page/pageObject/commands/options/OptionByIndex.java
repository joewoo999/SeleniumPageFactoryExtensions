package com.github.pfextentions.core.page.pageObject.commands.options;

import com.github.pfextentions.core.page.pageObject.PageElementType;
import com.github.pfextentions.core.page.pageObject.function.OptionFunction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.Select;

public class OptionByIndex implements OptionFunction<WebElement> {
    private ElementLocator locator;
    private int index;

    @Override
    public WebElement apply(ElementLocator locator,Object[] objects) {
        this.locator = locator;
        this.index = (int)objects[0];

        WebElement element = PageElementType.SELECT.findAndAssertElementType(locator);
        new Select(element).selectByIndex(index);
        return element;
    }

    @Override
    public String toString() {
        return String.format("Select option with index:'%s' at element:%s.",
                index, locator);
    }
}
