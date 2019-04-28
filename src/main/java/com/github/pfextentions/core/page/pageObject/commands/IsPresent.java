package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandPredicate;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class IsPresent implements CommandPredicate {

    private ElementLocator locator;
    private boolean isPresent;

    @Override
    public boolean test(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        try {
            isPresent = locator.findElement() != null;
        }catch(NoSuchElementException| StaleElementReferenceException ignore){
            isPresent = false;
        }
        return isPresent;
    }

    @Override
    public String toString() {
        return String.format("Element '%s' is present: %s.", locator, isPresent);
    }
}
