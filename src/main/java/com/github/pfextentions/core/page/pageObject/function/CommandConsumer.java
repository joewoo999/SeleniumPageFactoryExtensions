package com.github.pfextentions.core.page.pageObject.function;

import com.github.pfextentions.core.page.pageObject.Command;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.function.BiConsumer;

public interface CommandConsumer extends BiConsumer<ElementLocator, Object[]>, Command {
}
