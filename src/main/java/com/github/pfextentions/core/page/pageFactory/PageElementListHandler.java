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
import org.openqa.selenium.support.pagefactory.ElementLocator;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class PageElementListHandler implements InvocationHandler {
    private final ElementLocator locator;
    private int index;

    public PageElementListHandler(ElementLocator locator, int index) {
        this.locator = locator;
        this.index = index;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable {
        List<? extends WebElement> elements = locator.findElements();

        try {
            return method.invoke(elements, objects);
        } catch (InvocationTargetException e) {
            // Unwrap the underlying exception
            throw e.getCause();
        }
    }
//
//    private class ProxyList<T extends PageElement> implements List<T>{
//        private ElementLocator locator;
//        private List<WebElement> webElements;
//        private List<T> pageElements = new ArrayList<>();
//
//        public ProxyList(ElementLocator locator) {
//            this.locator = locator;
//            this.webElements = locator.findElements();
//            InvocationHandler handler = new PageElementHandler(locator);
//            for(WebElement ele:webElements){
//
//
//                Object proxy = Proxy.newProxyInstance(
//                        this.getClass().getClassLoader(),
//                        new Class[]{clazz, WrapsElement.class, Locatable.class}, handler);
//
//                pageElements.add(clazz.cast(proxy));
//            }
//        }
//
//        @Override
//        public int size() {
//            return webElements.size();
//        }
//
//        @Override
//        public boolean isEmpty() {
//            return webElements.isEmpty();
//        }
//
//    }
}

