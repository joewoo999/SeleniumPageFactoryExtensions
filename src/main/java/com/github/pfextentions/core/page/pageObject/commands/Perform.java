package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.ActionFunction;
import com.github.pfextentions.core.page.pageObject.function.ActionSupplier;
import com.github.pfextentions.core.page.pageObject.function.CommandConsumer;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class Perform implements CommandConsumer {
    private ActionFunction action;

    @Override
    public void accept(ElementLocator locator, Object[] objects) {
        ActionSupplier supplier = (ActionSupplier)objects[0];
        this.action = supplier.get(objects[1]);

        action.accept(locator);
    }

    @Override
    public String toString() {
        return action.toString();
    }
}
