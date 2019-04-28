package com.github.pfextentions.core.page.pageFactory;

import com.github.pfextentions.core.page.pageObject.Command;
import com.github.pfextentions.core.page.pageObject.Commands;
import com.github.pfextentions.core.page.pageObject.PageElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.function.Supplier;

public class PageElementHandler implements InvocationHandler {

    private Logger log = LoggerFactory.getLogger(PageElement.class.getSimpleName());
    private final ElementLocator locator;

    public PageElementHandler(ElementLocator locator) {
        this.locator = locator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) {

        if ("toString".equals(method.getName())) {
            return "Proxy element for: " + locator;
        }

        Command command;

        if ("execute".equals(method.getName())) {
            command = (Command) ((Supplier) objects[0]).get();
            objects = (Object[]) objects[1];
        } else {
            command = Commands.getInstance().get(method.getName());
        }

        Object result = command.execute(locator, objects);

        log.debug(command.toString());
//        log.debug(String.valueOf(command.hashCode()));
        return result;
    }

}
