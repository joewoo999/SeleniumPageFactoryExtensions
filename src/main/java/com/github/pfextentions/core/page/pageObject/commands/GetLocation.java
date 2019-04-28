package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.Point;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class GetLocation implements CommandFunction<Point> {

    private ElementLocator locator;
    private Point point;

    @Override
    public Point apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        point = locator.findElement().getLocation();
        return point;
    }

    @Override
    public String toString() {
        return String.format("Element:%s location is '%s'.", locator, point);
    }
}
