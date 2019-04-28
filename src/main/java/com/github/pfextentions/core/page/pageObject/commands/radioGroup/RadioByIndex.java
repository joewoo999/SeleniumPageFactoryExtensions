package com.github.pfextentions.core.page.pageObject.commands.radioGroup;

import com.github.pfextentions.core.page.pageObject.function.RadioGroupFunction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class RadioByIndex implements RadioGroupFunction<WebElement> {
    private ElementLocator locator;
    private int index;


    @Override
    public WebElement apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        this.index = (int) objects[0];

        WebElement radio = locator.findElements().get(index);

        if (!radio.isSelected()) {
            radio.click();
        }
        return radio;
    }

    @Override
    public String toString() {
        return String.format("Select radio with index:%d at element:%s.",
                index, locator);
    }
}
