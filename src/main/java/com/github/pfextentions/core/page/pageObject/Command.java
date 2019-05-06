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

package com.github.pfextentions.core.page.pageObject;

import com.github.pfextentions.core.page.pageObject.function.*;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public interface Command {

    default Object execute(ElementLocator locator, Object[] objects) {
        try {
            if (CommandConsumer.class.isAssignableFrom(this.getClass())) {
                ((CommandConsumer) this).accept(locator, objects);
                return null;

            } else if (CommandPredicate.class.isAssignableFrom(this.getClass())) {
                return ((CommandPredicate) this).test(locator, objects);

            } else if (CommandFunction.class.isAssignableFrom(this.getClass())) {
                return ((CommandFunction<?>) this).apply(locator, objects);

            } else {
                throw new UnsupportedCommandException("Unsupported command class:"
                        + this.getClass().getSimpleName());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException("The method of command:'" + this.getClass().getSimpleName()
                    + "' needs more parameters", e);
        }
    }
}
