package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class GetSize implements CommandFunction<Dimension> {

    private ElementLocator locator;
    private Dimension dimension;

    @Override
    public Dimension apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        dimension = locator.findElement().getSize();
        return dimension;
    }

    @Override
    public String toString() {
        return String.format("Element:%s dimension is '%s'.",
                locator, dimension);
    }
}
