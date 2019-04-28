package com.github.pfextentions.core.page.pageObject.function;

@FunctionalInterface
public interface ActionSupplier<R extends ActionFunction> {
    R get(Object... input);
}
