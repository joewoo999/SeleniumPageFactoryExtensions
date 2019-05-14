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
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.AbstractList;

public class PageElementProxyList<T extends PageElement> extends AbstractList<T> {
    private int index;
    private int size;
    private InvocationHandler handler, list;
    private Class<?> clazz;

    public PageElementProxyList(T input, ElementLocator locator) {
        this.clazz = input.getClass();
        this.size = locator.findElements().size();
        this.handler = new PageElementHandler(locator);
    }

    @Override
    public T get(int index) {
        Object proxy = Proxy.newProxyInstance(this.getClass().getClassLoader(),
                new Class[]{clazz}, this.handler);


        return null;
    }

    @Override
    public int size() {
        return size;
    }
}
