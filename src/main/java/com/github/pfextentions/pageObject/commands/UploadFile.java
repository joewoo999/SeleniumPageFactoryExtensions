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

package com.github.pfextentions.pageObject.commands;

import com.github.pfextentions.command.CommandConsumer;
import com.github.pfextentions.pageObject.PageElementType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class UploadFile implements CommandConsumer<ElementLocator, Object[]> {

    private ElementLocator locator;
    private String file;

    @Override
    public void apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        this.file = (String) objects[0];

        WebElement element = PageElementType.INPUT_FILE.findAndAssertElementType(locator);
        element.clear();
        element.sendKeys(file);
    }

    @Override
    public String toString() {
        return String.format("Click element:%s to upload file: '%s'.",
                file, locator);
    }
}
