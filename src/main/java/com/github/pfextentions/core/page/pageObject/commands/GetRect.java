package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class GetRect implements CommandFunction<Rectangle> {

    private ElementLocator locator;
    private Rectangle rect;

    @Override
    public Rectangle apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        rect = locator.findElement().getRect();
        return rect;
    }

    @Override
    public String toString() {
        return String.format("Element:%s rectangle is '%s,%s'.",
                locator, rect.getPoint(), rect.getDimension());
    }
}
