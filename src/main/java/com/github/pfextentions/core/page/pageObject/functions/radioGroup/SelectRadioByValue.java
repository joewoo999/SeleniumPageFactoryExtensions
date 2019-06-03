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

package com.github.pfextentions.core.page.pageObject.functions.radioGroup;

import com.github.pfextentions.core.page.pageObject.CommandExecuteException;
import com.github.pfextentions.core.page.pageObject.function.RadioGroupFunction;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.List;

public class SelectRadioByValue implements RadioGroupFunction<WebElement> {
    private ElementLocator locator;
    private String value;

    public SelectRadioByValue(String value) {
        this.value = value;
    }

    @Override
    public WebElement apply(@NotNull ElementLocator locator) {
        this.locator = locator;

        List<WebElement> radios = locator.findElements();
        for (WebElement radio : radios) {
            if (!value.equals(radio.getAttribute("value")))
                continue;

            if (!radio.isSelected())
                radio.click();

            return radio;
        }
        throw new CommandExecuteException(String.format("Can not find radio element by value:'%s'", value));
    }

    @Override
    public String toString() {
        return String.format("Select radio with value:'%s' at element:%s.",
                value, locator);
    }
}
