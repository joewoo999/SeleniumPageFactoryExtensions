package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.driverContext.DriverContext;
import com.github.pfextentions.core.page.pageObject.function.CommandConsumer;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class ScrollToCenter implements CommandConsumer {
    private ElementLocator locator;

    @Override
    public void accept(ElementLocator locator, Object[] objects) {
        this.locator = locator;

        DriverContext.getJSExecutor()
                .executeScript("window.scrollTo(0,arguments[0].offsetTop - window.screen.height/2)",
                        locator.findElement());
    }

    @Override
    public String toString() {
        return String.format("page scrolled to element:[%s]", locator);
    }
}
