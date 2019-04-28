package com.github.pfextentions.core.page.pageObject.commands.actions;

import com.github.pfextentions.core.driverContext.DriverContext;
import com.github.pfextentions.core.page.pageObject.function.ActionFunction;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class DoubleLeftClick implements ActionFunction {
    private ElementLocator locator;
    
    @Override
    public void accept(ElementLocator locator) {
        this.locator = locator;

        DriverContext.getActions().doubleClick(locator.findElement()).perform();
    }

    @Override
    public String toString() {
        return String.format("(ActionFunction)Double click element:%s.", locator);
    }
}
