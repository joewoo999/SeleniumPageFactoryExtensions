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

package com.github.pfextentions.pageObject.functions.radioGroup;

import com.github.pfextentions.pageObject.function.RadioGroupFunction;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class SelectRadioByIndex implements RadioGroupFunction<WebElement> {
    private ElementLocator locator;
    private int index;

    public SelectRadioByIndex(int index) {
        this.index = index;
    }

    @Override
    public WebElement apply(@NotNull ElementLocator locator) {
        this.locator = locator;

        WebElement radio = locator.findElements().get(index);

        if (!radio.isSelected()) {
            radio.click();
        }
        return radio;
    }

    @Override
    public String toString() {
        return String.format("Select radio with index:%d at element:%s.",
                index, locator);
    }
}
