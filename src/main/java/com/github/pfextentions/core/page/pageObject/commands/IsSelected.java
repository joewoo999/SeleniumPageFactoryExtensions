package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandPredicate;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class IsSelected implements CommandPredicate {

    private ElementLocator locator;
    private boolean isSelected;

    @Override
    public boolean test(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        isSelected = locator.findElement().isSelected();
        return isSelected;
    }

    @Override
    public String toString() {
        return String.format("Element:%s is selected: %s.", locator, isSelected);
    }
}
