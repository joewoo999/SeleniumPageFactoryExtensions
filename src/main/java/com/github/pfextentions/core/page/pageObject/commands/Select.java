package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.Command;
import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import com.github.pfextentions.core.page.pageObject.function.OptionFunction;
import com.github.pfextentions.core.page.pageObject.function.RadioGroupFunction;
import com.github.pfextentions.core.page.pageObject.function.RadioGroupSupplier;
import com.github.pfextentions.core.page.pageObject.function.SelectSupplier;
import org.openqa.selenium.support.pagefactory.ElementLocator;


public class Select implements CommandFunction<Object> {
    private Command command;

    @Override
    public Object apply(ElementLocator locator, Object[] objects) {
        Class cls = objects[0].getClass();
        if (SelectSupplier.class.isAssignableFrom(cls)) {
            command = (OptionFunction) ((SelectSupplier) objects[0]).get();
        } else if (RadioGroupSupplier.class.isAssignableFrom(cls)) {
            command = (RadioGroupFunction) ((SelectSupplier) objects[0]).get();
        } else {
            throw new IllegalArgumentException("Unsupported command class type:" + cls.getSimpleName());
        }

        return command.execute(locator, (Object[]) objects[1]);
    }

    @Override
    public String toString() {
        return command.toString();
    }
}
