package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.PageElementWait;
import com.github.pfextentions.core.page.pageObject.expectedCondtion.Condtion;
import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class WaitUntil implements CommandFunction<Object> {
    private Condtion<?> func;
    private int timeoutInSec;

    @Override
    public Object apply(ElementLocator locator, Object[] objects) {
        if(!(objects[0] instanceof Condtion && objects[1] instanceof Integer))
            return false;

        func = (Condtion)objects[0];
        timeoutInSec = (int)objects[1];

        return PageElementWait.getInstance(locator).withTimeout(timeoutInSec).until(func);
    }

    @Override
    public String toString() {
        return func.toString();
    }
}
