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

package com.github.pfextentions.command;

import org.openqa.selenium.support.pagefactory.ElementLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class PageElementExecutor extends CommandsExecutor<ElementLocator, Object[], Object> {
    private Logger log = LoggerFactory.getLogger(PageElementExecutor.class.getSimpleName());

    public PageElementExecutor(CommandsCache cache) {
        super(cache);
    }

    public Object execute(Command command, ElementLocator elementLocator, Object[] objects) throws Throwable{
        try {
            Method method = command.getClass().getDeclaredMethod("apply", ElementLocator.class, Object[].class);
            return method.invoke(command, elementLocator, objects);

        } catch (NoSuchMethodException | IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } finally {
            log.debug(command.toString());
        }
    }

    public static PageElementExecutor getInstance() {
        return StaticExecutor.INSTANCE;
    }

    private static class StaticExecutor {
        public static PageElementExecutor INSTANCE
                = new PageElementExecutor(CommandsCache.fromProperties("page.element"));
    }
}
