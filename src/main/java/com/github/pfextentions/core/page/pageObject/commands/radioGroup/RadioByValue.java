package com.github.pfextentions.core.page.pageObject.commands.radioGroup;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.List;

public class RadioByValue implements java.util.function.Function<ElementLocator, Boolean> {
    private ElementLocator locator;
    private String value;

    public RadioByValue(String value) {
        this.value = value;
    }

    @Override
    public Boolean apply(ElementLocator locator) {
        this.locator = locator;

        List<WebElement> radios = locator.findElements();
        for (WebElement radio : radios) {
            if (!value.equals(radio.getAttribute("value")))
                continue;

            if (!radio.isSelected())
                radio.click();

            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Select radio with value:'%s' at element:%s.",
                value, locator);
    }
}
