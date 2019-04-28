package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandConsumer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.Arrays;

public class Sendkeys implements CommandConsumer {
    private ElementLocator locator;
    private CharSequence[] text;

    @Override
    public void accept(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        this.text = (CharSequence[])objects[0];

        WebElement element = locator.findElement();
        element.clear();
        element.sendKeys(text);
    }

    @Override
    public String toString() {
        return String.format("Input text:%s into element:%s.",
                Arrays.toString(text), locator);
    }
}
