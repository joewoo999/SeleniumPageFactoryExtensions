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

package com.github.pfextentions.page.pageObject;

import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.util.Objects;

public enum PageElementType {

    INPUT("input", null),
    INPUT_TEXT("input", "text"),
    INPUT_FILE("input", "file"),
    RADIO("input", "radio"),
    CHECKBOX("input", "checkbox"),

    TEXTAREA("textarea", null),
    BUTTON("button", "button"),
    SELECT("select", null),
    LINK("a", null);


    private String tagName;
    private String type;

    PageElementType(String tagName, String type) {
        this.tagName = tagName;
        this.type = type;
    }

    public WebElement findAndAssertElementType(@NotNull ElementLocator locator) {
        StringBuffer b = new StringBuffer();
        WebElement element = locator.findElement();
        String tag = element.getTagName();
        String tp = element.getAttribute("type");

        if (Objects.nonNull(tagName)) {
            if (!tagName.equalsIgnoreCase(tag))
                b.append(String.format("\n\t\ttag name: actual '%s' is not expected '%s'.", tag, tagName));
        }

        if (Objects.nonNull(type)) {
            if (!this.type.equalsIgnoreCase(tp))
                b.append(String.format("\n\t\tattribute type: actual '%s' is not expected '%s'.", tp, type));
        }

        if (b.length() > 0)
            throw new AssertionError(String.format("ELement:%s type is not matched, %s", element, b));

        return element;
    }

}

