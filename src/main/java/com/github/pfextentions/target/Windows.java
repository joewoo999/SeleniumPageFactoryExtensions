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

package com.github.pfextentions.target;

import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;

import java.util.Set;

public class Windows {


    public static TargetLocator<WebDriver> byTitle(final String title) {
        return new TargetLocator<>() {
            @Override
            public WebDriver apply(WebDriver webDriver) {
                Set<String> handles = webDriver.getWindowHandles();
                for (String handle : handles) {
                    webDriver.switchTo().window(handle);
                    if (title.equals(webDriver.getTitle()))
                        return webDriver;
                }
                throw new NoSuchWindowException("can not find window by title: " + title);
            }

            @Override
            public String toString() {
                return "switch to window by title: " + title;
            }
        };
    }

    public static TargetLocator<WebDriver> newWindow() {
        return new TargetLocator<>() {
            @Override
            public WebDriver apply(WebDriver webDriver) {
                Object[] handles = webDriver.getWindowHandles().toArray();
                webDriver.switchTo().window((String) handles[handles.length - 1]);
                return webDriver;
            }

            @Override
            public String toString() {
                return "switch to new window";
            }
        };
    }
}
