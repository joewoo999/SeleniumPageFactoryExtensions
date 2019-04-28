package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandPredicate;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class IsDisplayed implements CommandPredicate {

    private ElementLocator locator;
    private boolean isDisplayed;

    @Override
    public boolean test(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        try {
            isDisplayed = locator.findElement().isDisplayed();
        } catch (NoSuchElementException | StaleElementReferenceException ignore) {
            isDisplayed = false;
        }
        return isDisplayed;
    }

    @Override
    public String toString() {
        return String.format("Element:%s is displayed: %s.",
                locator, isDisplayed);
    }
}
