package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.driverContext.DriverContext;
import com.github.pfextentions.core.page.pageObject.function.CommandConsumer;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class ClickByJS implements CommandConsumer {
    private ElementLocator locator;

    @Override
    public void accept(ElementLocator locator, Object[] objects) {
        this.locator = locator;

        DriverContext.getJSExecutor()
                .executeScript("arguments[0].click();",
                        locator.findElement());
    }

    @Override
    public String toString() {
        return String.format("Click element:[%s] by javascript.", locator);
    }
}
