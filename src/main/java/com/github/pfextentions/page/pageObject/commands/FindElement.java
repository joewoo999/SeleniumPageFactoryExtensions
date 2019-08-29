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

package com.github.pfextentions.page.pageObject.commands;

import com.github.pfextentions.command.CommandFunction;
import com.github.pfextentions.page.factory.PageElementHandler;
import com.github.pfextentions.page.factory.PageElementLocator;
import com.github.pfextentions.page.pageObject.PageElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class FindElement implements CommandFunction<ElementLocator, Object[], PageElement> {
    private ElementLocator locator;
    private By by;

    @Override
    public PageElement apply(ElementLocator locator, Object[] objects) {
        this.locator = locator;
        this.by = (By) objects[0];

        ElementLocator childLocator = new PageElementLocator(locator.findElement(), false, by);
        InvocationHandler invocationHandler = new PageElementHandler(childLocator);

        return (PageElement) Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{PageElement.class}, invocationHandler);
    }

    @Override
    public String toString() {
        return String.format("Find child element: '%s' of parent: %s", by, locator);
    }
}
