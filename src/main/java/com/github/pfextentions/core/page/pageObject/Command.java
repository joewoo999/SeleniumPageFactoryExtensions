package com.github.pfextentions.core.page.pageObject;

import com.github.pfextentions.core.page.pageObject.function.*;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public interface Command {

    default Object execute(ElementLocator locator, Object[] objects) {
        try {
            if (CommandConsumer.class.isAssignableFrom(this.getClass())) {
                ((CommandConsumer) this).accept(locator, objects);
                return null;

            } else if (CommandPredicate.class.isAssignableFrom(this.getClass())) {
                return ((CommandPredicate) this).test(locator, objects);

            } else if (CommandFunction.class.isAssignableFrom(this.getClass())) {
                return ((CommandFunction<?>) this).apply(locator, objects);

            } else {
                throw new UnsupportedCommandException("Unsupported command class:"
                        + this.getClass().getSimpleName());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("The method of command:'" + this.getClass().getSimpleName()
                    + "' needs more parameters", e);
        }
    }
}
