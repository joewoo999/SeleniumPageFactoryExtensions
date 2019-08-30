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

package com.github.pfextentions.pageObject.commands;

import com.github.pfextentions.command.CommandFunction;
import com.github.pfextentions.pageObject.function.OptionFunction;
import com.github.pfextentions.pageObject.function.RadioGroupFunction;
import org.openqa.selenium.support.pagefactory.ElementLocator;


public class Select implements CommandFunction<ElementLocator, Object[], Object> {
    private String toString;

    @Override
    public Object apply(ElementLocator locator, Object[] objects) {
        Object object = null;
        Class cls = objects[0].getClass();

        if (OptionFunction.class.isAssignableFrom(cls)) {
            OptionFunction<?> command = (OptionFunction) objects[0];
            object = command.apply(locator);
            toString = command.toString();

        } else if (RadioGroupFunction.class.isAssignableFrom(cls)) {
            RadioGroupFunction<?> command = (RadioGroupFunction) objects[0];
            object = command.apply(locator);
            toString = command.toString();
        }
        return object;
    }

    @Override
    public String toString() {
        return toString;
    }
}
