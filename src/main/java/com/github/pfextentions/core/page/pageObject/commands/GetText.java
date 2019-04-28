package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class GetText implements CommandFunction<String> {

    private ElementLocator locator;
    private String text;

    @Override
    public String apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        text = locator.findElement().getText();
        return text;
    }

    @Override
    public String toString() {
        return String.format("Element:%s innertext is '%s'.",
                locator, text);
    }
}
