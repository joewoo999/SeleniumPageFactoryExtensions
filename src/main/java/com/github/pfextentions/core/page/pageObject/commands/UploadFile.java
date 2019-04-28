package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.PageElementType;
import com.github.pfextentions.core.page.pageObject.function.CommandConsumer;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class UploadFile implements CommandConsumer {

    private ElementLocator locator;
    private String file;

    @Override
    public void accept(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        this.file = (String) objects[0];

        WebElement element = PageElementType.INPUT_FILE.findAndAssertElementType(locator);
        element.clear();
        element.sendKeys(file);
    }

    @Override
    public String toString() {
        return String.format("Input file:%s into element:%s.",
                file, locator);
    }
}
