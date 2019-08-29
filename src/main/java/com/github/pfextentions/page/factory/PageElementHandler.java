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

package com.github.pfextentions.page.factory;

import com.github.pfextentions.command.PageElementExecutor;
import com.github.pfextentions.page.pageObject.PageElement;
import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PageElementHandler implements InvocationHandler {

    private Logger log = LoggerFactory.getLogger(PageElement.class.getSimpleName());
    private final ElementLocator locator;

    public PageElementHandler(ElementLocator locator) {
        this.locator = locator;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] objects) throws Throwable{
        String methodName = method.getName();
        if ("toString".equals(methodName)) {
            return "Proxy element for: " + locator;
        }

        return PageElementExecutor.getInstance().execute(methodName, locator, objects);
    }

}
