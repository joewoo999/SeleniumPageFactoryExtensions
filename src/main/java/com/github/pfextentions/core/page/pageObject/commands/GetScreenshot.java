package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.driverContext.DriverScreenshot;
import com.github.pfextentions.core.page.pageObject.function.CommandConsumer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class GetScreenshot implements CommandConsumer {
    private ElementLocator locator;
    private String pathName;

    @Override
    public void accept(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        this.pathName = (String) objects[0];

        WebElement element = locator.findElement();

        DriverScreenshot.take(element, pathName);
    }

    @Override
    public String toString() {
        return String.format("Get element: %s screenshot and save as: %s.",
                locator, pathName);
    }
}
