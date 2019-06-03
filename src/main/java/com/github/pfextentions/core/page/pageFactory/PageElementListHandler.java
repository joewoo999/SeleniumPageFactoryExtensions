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

package com.github.pfextentions.core.page.pageFactory;

import com.github.pfextentions.core.page.pageObject.PageElement;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;


public class PageElementListHandler<T extends PageElement> implements InvocationHandler {
    private final Class<T> clazz;
    private final ClassLoader loader;
    private final ElementLocator locator;
    private List<T> cachedElements = new ArrayList<>();


    public PageElementListHandler(Class<T> clazz, ClassLoader loader, ElementLocator locator) {
        this.locator = locator;
        this.clazz = clazz;
        this.loader = loader;
    }

    @Override
    public Object invoke(Object object, @NotNull Method method, Object[] objects) throws Throwable {
        if ("tostring".equalsIgnoreCase(method.getName())) {
            return "Proxy element for: " + locator;
        }
        if ("clear".equalsIgnoreCase(method.getName())) {
            if (!cachedElements.isEmpty()) {
                cachedElements.clear();
            }
            return cachedElements;
        }

        List<WebElement> elements = locator.findElements();
        int size = locator.findElements().size();
        InvocationHandler handler = new PageElementHandler(locator);

        if (cachedElements.isEmpty()) {
            for (int index = 0; index < size; index++) {
                ((PageElementLocator) locator).setIndex(index);

                cachedElements.add(index, clazz.cast(Proxy.newProxyInstance(
                        loader, new Class[]{clazz}, handler)));
            }
        }

        try {
            return method.invoke(cachedElements, objects);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

}

