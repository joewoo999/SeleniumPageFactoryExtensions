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

import com.github.pfextentions.core.driverContext.DriverContext;
import com.github.pfextentions.core.page.pageObject.function.CommandConsumer;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class ScrollToCenter implements CommandConsumer {
    private ElementLocator locator;

    @Override
    public void accept(ElementLocator locator, Object[] objects) {
        this.locator = locator;

        DriverContext.getJSExecutor()
                .executeScript("window.scrollTo(0,arguments[0].offsetTop - window.screen.height/2)",
                        locator.findElement());
    }

    @Override
    public String toString() {
        return String.format("page scrolled to element:[%s]", locator);
    }
}
