package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandConsumer;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class Submit implements CommandConsumer {

    private ElementLocator locator;

    @Override
    public void accept(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        locator.findElement().submit();
    }

    @Override
    public String toString() {
        return String.format("Submit element:%s", locator);
    }
}
