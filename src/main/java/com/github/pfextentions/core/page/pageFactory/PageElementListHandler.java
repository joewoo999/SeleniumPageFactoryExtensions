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

import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.List;

/**
 * dev...
 */
public class PageElementListHandler implements InvocationHandler {
    private final Class clazz;
    private final ClassLoader loader;
    private final ElementLocator locator;


    public PageElementListHandler(Class clazz, ClassLoader loader, ElementLocator locator) {
        this.locator = locator;
        this.clazz = clazz;
        this.loader = loader;
    }

    @Override
    public Object invoke(Object object, Method method, Object[] objects) {
        List<? extends WebElement> elements = locator.findElements();

        switch (method.getName().toLowerCase()) {
            case "size":
                return elements.size();
            case "isempty":
                return elements.isEmpty();
            case "get":
                ((PageElementLocator) locator).setIndex((int) objects[0]);
                InvocationHandler handler = new PageElementHandler(locator);

                Object proxy = Proxy.newProxyInstance(
                        loader, new Class[]{clazz}, handler);

                return clazz.cast(proxy);
            default:
                throw new UnsupportedOperationException(method.getName());

        }
    }

}

