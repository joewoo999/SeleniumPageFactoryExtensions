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

package com.github.pfextentions.core.page.pageObject;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Sleeper;

import java.time.Clock;
import java.time.Duration;

public class PageElementWait extends FluentWait<ElementLocator> {


    public PageElementWait(ElementLocator locator) {
        super(locator);
    }

    public PageElementWait(ElementLocator locator, Clock clock, Sleeper sleeper) {
        super(locator, clock, sleeper);
    }

    @NotNull
    @Contract("_ -> new")
    public static PageElementWait getInstance(ElementLocator locator) {
        return new PageElementWait(locator);
    }

    public PageElementWait withTimeout(long second) {
        return (PageElementWait) super.withTimeout(Duration.ofSeconds(second));
    }


}
