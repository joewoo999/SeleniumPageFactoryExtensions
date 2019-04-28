package com.github.pfextentions.core.page.pageObject.commands.options;

import com.github.pfextentions.core.page.pageObject.PageElementType;
import com.github.pfextentions.core.page.pageObject.function.OptionFunction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.Select;

public class GetSelectedOptionText implements OptionFunction<String> {
    private ElementLocator locator;
    private String selectedText;

    @Override
    public String apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;

        WebElement element = PageElementType.SELECT.findAndAssertElementType(locator);
        selectedText = new Select(element).getFirstSelectedOption().getText();
        return selectedText;
    }

    @Override
    public String toString() {
        return String.format("The selected option text of element:%s is '%s' .",
                locator, selectedText);
    }
}
