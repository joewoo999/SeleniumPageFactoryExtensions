package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandPredicate;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class IsEnabled implements CommandPredicate  {

    private ElementLocator locator;
    private boolean isEnabled;

    @Override
    public boolean test(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        isEnabled = locator.findElement().isEnabled();
        return isEnabled;
    }

    @Override
    public String toString() {
        return String.format("Element:%s is enabled: %s.", locator, isEnabled);
    }
}
