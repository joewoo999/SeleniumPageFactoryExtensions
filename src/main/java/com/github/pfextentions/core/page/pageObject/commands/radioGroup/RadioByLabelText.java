package com.github.pfextentions.core.page.pageObject.commands.radioGroup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.List;
import java.util.NoSuchElementException;

public class RadioByLabelText implements java.util.function.Function<ElementLocator, Boolean> {
    private ElementLocator locator;
    private String text;

    public RadioByLabelText(String text) {
        this.text = text;
    }

    @Override
    public Boolean apply(ElementLocator locator) {
        this.locator = locator;

        List<WebElement> radios = locator.findElements();
        for (WebElement radio : radios) {
            WebElement label;
            try {
                label = radio.findElement(By.xpath("./following-sibling::*[1]"));
            } catch (NoSuchElementException ignore) {
                try {
                    label = radio.findElement(By.xpath(".."));
                } catch (NoSuchElementException e) {
                    continue;
                }
            }

            if (!text.equals(label.getText()))
                continue;

            if (!radio.isSelected())
                radio.click();
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("Select radio with label text:'%s' at element:%s.",
                text, locator);
    }
}
