package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class GetScreenshotAs implements CommandFunction<Object> {
    private ElementLocator locator;

    @Override
    public Object apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;

        WebElement element = locator.findElement();
        OutputType<?> outputType = (OutputType) objects[0];

        return element.getScreenshotAs(outputType);
    }

    @Override
    public String toString() {
        return String.format("Get element: %s screenshot.", locator);
    }
}
