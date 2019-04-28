package com.github.pfextentions.core.page.pageObject.expectedCondtion.conditions;

import com.github.pfextentions.core.page.pageObject.expectedCondtion.Condtion;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class Not implements Condtion<Object> {
    private Condtion<?> func;

    public Not(Condtion<?> func) {
        this.func = func;
    }

    @Override
    public Object apply(ElementLocator locator) {
        Object value = func.apply(locator);
        if (null == value || value.equals(Boolean.FALSE))
            return true;
        else
            return null;
    }

    @Override
    public String toString() {
        return "Not{" + func + '}';
    }
}
