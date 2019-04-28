package com.github.pfextentions.core.page.pageObject.commands.actions;

import com.github.pfextentions.core.driverContext.DriverContext;
import com.github.pfextentions.core.page.pageObject.function.ActionFunction;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class TypeText implements ActionFunction {
    private ElementLocator locator;
    private String text;

    public TypeText(String text) {
        this.text = text;
    }

    @Override
    public void accept(ElementLocator locator) {
        this.locator = locator;

        DriverContext.getActions().sendKeys(locator.findElement(), text).perform();
    }

    @Override
    public String toString() {
        return String.format("(ActionFunction)Type text:%s into element:%s.", text, locator);
    }
}
