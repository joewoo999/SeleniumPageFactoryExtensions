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

package com.github.pfextentions.core.page.pageObject.functions.options;

import com.github.pfextentions.core.page.pageObject.PageElementType;
import com.github.pfextentions.core.page.pageObject.function.OptionFunction;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.Select;

public class GetSelectedOptionText implements OptionFunction<String> {
    private ElementLocator locator;
    private String selectedText;

    @Override
    public String apply(ElementLocator locator) {
        this.locator = locator;

        WebElement element = PageElementType.SELECT.findAndAssertElementType(locator);
        selectedText = new Select(element).getFirstSelectedOption().getText();
        return selectedText;
    }

    @Override
    public String toString() {
        return String.format("The selected option text of element:%s is '%s' .",
                locator, selectedText);
    }
}
