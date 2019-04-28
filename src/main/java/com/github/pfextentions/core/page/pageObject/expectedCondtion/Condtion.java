package com.github.pfextentions.core.page.pageObject.expectedCondtion;

import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.function.Function;

public interface Condtion<V> extends Function<ElementLocator,V> {
}
