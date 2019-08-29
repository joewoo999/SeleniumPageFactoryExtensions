/*
 * Licensed to the Software Freedom Conservancy (SFC) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The SFC licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.github.pfextentions.page.pageObject.expectedCondtion;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class Have {
    /**
     * An expectation for checking WebElement has attribute with a specific value
     *
     * @param attr     used to define test-html attribute
     * @param expected used as expected attribute value
     * @return Boolean true when element has test-html attribute with the value
     */
    @NotNull
    @Contract(value = "_, _ -> new", pure = true)
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
    @NotNull
    @Contract(value = "_ -> new", pure = true)
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
