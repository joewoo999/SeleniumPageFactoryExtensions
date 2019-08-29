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

package com.github.pfextentions.page.pageObject.commands;

import com.github.pfextentions.command.CommandConsumer;
import com.github.pfextentions.page.pageObject.function.ActionConsumer;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class Perform implements CommandConsumer<ElementLocator, Object[]> {
    private ActionConsumer consumer;

    @Override
    public void apply(ElementLocator locator, Object[] objects) {
        consumer = (ActionConsumer) objects[0];

        consumer.accept(locator);
    }

    @Override
    public String toString() {
        return consumer.toString();
    }
}
