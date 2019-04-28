package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.expectedCondtion.Condtion;
import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class Should implements CommandFunction<Object> {
    private Condtion<?> should;
    private Object result;

    @Override
    public Object apply(ElementLocator locator, Object[] objects) {

        if (!(objects[0] instanceof Condtion)) {
            return null;
        }
        should = (Condtion) objects[0];
        result = should.apply(locator);

        if (null == result || Boolean.FALSE.equals(result))
            throw new AssertionError(toString());

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s : %s.", should, result);
    }
}
