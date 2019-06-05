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

package com.github.pfextentions.core.page.pageObject.expectedCondtion;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class Be {
    /**
     * An expectation for checking that an element is present on the DOM of a page.png. This does not
     * necessarily mean that the element is visible.
     */
    public static Condtion<Boolean> present = new Condtion<>() {
        private ElementLocator locator;

        @NotNull
        @Override
        public Boolean apply(ElementLocator locator) {
            this.locator = locator;

            WebElement element = findElement(locator);
            return null != element;
        }

        @Override
        public String toString() {
            return String.format("ELement %s should be present", locator);
        }
    };

    /**
     * An expectation for checking that an element is present on the DOM of a page.png and visible.
     * Visibility means that the element is not only displayed but also has a height and width that is
     * greater than 0.
     */
    public static Condtion<Boolean> visible = new Condtion<>() {
        private ElementLocator locator;

        @NotNull
        @Override
        public Boolean apply(ElementLocator locator) {
            this.locator = locator;

            WebElement element = findElement(locator);
            return null != element && element.isDisplayed();
        }

        @Override
        public String toString() {
            return String.format("ELement %s should be visible", locator);
        }
    };

    /**
     * An expectation for checking that an element is present on the DOM of a page.png and enabled.
     */
    public static Condtion<Boolean> enabled = new Condtion<>() {
        private ElementLocator locator;

        @NotNull
        @Override
        public Boolean apply(ElementLocator locator) {
            this.locator = locator;

            WebElement element = findElement(locator);
            return null != element && element.isEnabled();
        }

        @Override
        public String toString() {
            return String.format("ELement %s should be enabled", locator);
        }
    };

    /**
     * An expectation for checking that an element is present on the DOM of a page.png and selected.
     */
    public static Condtion<Boolean> selected = new Condtion<>() {
        private ElementLocator locator;

        @NotNull
        @Override
        public Boolean apply(ElementLocator locator) {
            this.locator = locator;

            WebElement element = findElement(locator);
            return null != element && element.isSelected();
        }

        @Override
        public String toString() {
            return String.format("ELement %s should be selected", locator);
        }
    };


    @Nullable
    private static WebElement findElement(ElementLocator locator) {
        try {
            return locator.findElement();
        } catch (NoSuchElementException ignore) {
            return null;
        }
    }
}

