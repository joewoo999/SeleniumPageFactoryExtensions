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

import com.github.pfextentions.core.page.pageObject.function.RadioGroupFunction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.List;
import java.util.NoSuchElementException;

public class SelectRadioByLabelText implements RadioGroupFunction<WebElement> {
    private ElementLocator locator;
    private String text;

    public SelectRadioByLabelText(String text) {
        this.text = text;
    }

    @Override
    public WebElement apply(ElementLocator locator) {
        this.locator = locator;

        List<WebElement> radios = locator.findElements();
        for (WebElement radio : radios) {
            WebElement label;
            try {
                label = radio.findElement(By.xpath("./following-sibling::*[1]"));
            } catch (NoSuchElementException ignore) {
                try {
                    label = radio.findElement(By.xpath(".."));
                } catch (NoSuchElementException e) {
                    continue;
                }
            }

            if (!text.equals(label.getText()))
                continue;

            if (!radio.isSelected())
                radio.click();
            return radio;
        }
        return null;
    }

    @Override
    public String toString() {
        return String.format("Select radio with label text:'%s' at element:%s.",
                text, locator);
    }
}
