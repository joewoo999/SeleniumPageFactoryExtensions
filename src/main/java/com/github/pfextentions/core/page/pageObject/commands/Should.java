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

package com.github.pfextentions.core.page.pageObject.commands;

import com.github.pfextentions.core.page.pageObject.expectedCondtion.Condtion;
import com.github.pfextentions.core.page.pageObject.function.CommandFunction;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class Should implements CommandFunction<Object> {
    private Condtion<?> should;
    private Object result;

    @Override
    public Object apply(ElementLocator locator, Object[] objects) {

        if (!(objects[0] instanceof Condtion)) {
            return null;
        }
        should = (Condtion) objects[0];
        result = should.apply(locator);

        if (null == result || Boolean.FALSE.equals(result))
            throw new AssertionError(toString());

        return result;
    }

    @Override
    public String toString() {
        return String.format("%s : %s.", should, result);
    }
}
