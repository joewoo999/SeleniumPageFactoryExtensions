package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.driverContext.DriverContext;
import com.github.pfextentions.core.page.pageObject.function.CommandConsumer;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class SetValue implements CommandConsumer {
    private ElementLocator locator;
    private String value;


    @Override
    public void accept(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        this.value = (String) objects[0];

        DriverContext.getJSExecutor()
                .executeScript("arguments[0].value = arguments[1]",
                        locator.findElement(), value);
    }

    @Override
    public String toString() {
        return String.format("Set element: %s value:'%s'", locator, value);
    }
}
