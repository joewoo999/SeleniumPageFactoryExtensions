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
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.pagefactory.*;
import org.openqa.selenium.support.pagefactory.internal.LocatingElementListHandler;

import java.lang.reflect.*;
import java.util.List;

public class PageFieldDecorator implements FieldDecorator {

    protected final WebDriver driver;

    public PageFieldDecorator(WebDriver driver) {
        this.driver = driver;
    }


    @Override
    public Object decorate(ClassLoader loader, Field field) {
        if (!(PageElement.class.isAssignableFrom(field.getType())
                || isDecoratableList(field))) {
            return null;
        }

        ElementLocator locator = new DefaultElementLocator(driver, field) {
            @Override
            public String toString() {
                return super.toString().replace(this.getClass().getSimpleName(), "").trim();
            }
        };

        if (PageElement.class.isAssignableFrom(field.getType())) {
            return proxyForLocator(loader, locator);
        } else if (List.class.isAssignableFrom(field.getType())) {
            return proxyForListLocator(loader, locator);
        } else {
            return null;
        }
    }

    protected boolean isDecoratableList(Field field) {
        if (!List.class.isAssignableFrom(field.getType()))
            return false;

        Type genericType = field.getGenericType();
        if (!(genericType instanceof ParameterizedType))
            return false;

        Type listType = ((ParameterizedType) genericType).getActualTypeArguments()[0];

        if (!PageElement.class.equals(listType))
            return false;

        return field.getAnnotation(FindBy.class) != null ||
                field.getAnnotation(FindBys.class) != null ||
                field.getAnnotation(FindAll.class) != null;
    }

    protected PageElement proxyForLocator(ClassLoader loader, ElementLocator locator) {
        InvocationHandler handler = new PageElementHandler(locator);

        PageElement proxy;
        proxy = (PageElement) Proxy.newProxyInstance(
                loader, new Class[]{PageElement.class, WrapsElement.class, Locatable.class}, handler);
        return proxy;
    }

    @SuppressWarnings("unchecked")
    protected List<PageElement> proxyForListLocator(ClassLoader loader, ElementLocator locator) {
        InvocationHandler handler = new LocatingElementListHandler(locator);

        List<PageElement> proxy;
        proxy = (List<PageElement>) Proxy.newProxyInstance(
                loader, new Class[]{List.class}, handler);
        return proxy;
    }
}
