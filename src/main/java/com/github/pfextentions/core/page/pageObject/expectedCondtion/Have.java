package com.github.pfextentions.core.page.pageObject.expectedCondtion;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class Have {
    /**
     * An expectation for checking WebElement has attribute with a specific value
     *
     * @param attr     used to define html attribute
     * @param expected used as expected attribute value
     * @return Boolean true when element has html attribute with the value
     */
    public static Condtion<Boolean> attribute(final String attr, final String expected) {
        return new Condtion<>() {
            private ElementLocator locator;
            private String actual;

            @Override
            public Boolean apply(ElementLocator locator) {
                this.locator = locator;
                try {
                    actual = locator.findElement().getAttribute(attr);
                    return expected.equalsIgnoreCase(actual);
                } catch (NoSuchElementException ignore) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String.format("ELement %s should Have %s: actual is '%s',expected is '%s'",
                        locator, attr, actual, expected);
            }
        };
    }

    /**
     * An expectation for checking WebElement has specific text
     *
     * @param expected used as expected attribute value
     * @return Boolean true when element has text value equal to @expected
     */
    public static Condtion<Boolean> text(final String expected) {
        return new Condtion<>() {
            private ElementLocator locator;
            private String actual;

            @Override
            public Boolean apply(ElementLocator locator) {
                this.locator = locator;
                try {
                    actual = locator.findElement().getText();
                    return expected.equalsIgnoreCase(actual);
                } catch (NoSuchElementException ignore) {
                    return false;
                }
            }

            @Override
            public String toString() {
                return String.format("ELement %s should Have text: actual is '%s',expected is '%s'",
                        locator, actual, expected);
            }
        };
    }



}
