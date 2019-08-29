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

package com.github.pfextentions.browser;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.Objects;

public class DriverContext {

    private static ThreadLocal<BrowserConfig> threadConfig = new ThreadLocal<>();
    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<>();
    private static ThreadLocal<JavascriptExecutor> threadJSExecutor = new ThreadLocal<>();
    private static ThreadLocal<Actions> threadActions = new ThreadLocal<>();
    private final static String ERR_MSG = "Webdriver instance is null, u should run 'set()' first.";

    private DriverContext() {
    }

    public static void set(BrowserConfig config, WebDriver driver) {
        threadConfig.set(config);
        threadDriver.set(driver);
        threadJSExecutor.set((JavascriptExecutor)driver);
        threadActions.set(new Actions(driver));
    }

    public static BrowserConfig getConfig() {
        Objects.requireNonNull(threadDriver.get(), ERR_MSG);
        return threadConfig.get();
    }

    public static WebDriver getDriver() {
        Objects.requireNonNull(threadDriver.get(), ERR_MSG);
        return threadDriver.get();
    }

    public static JavascriptExecutor getJSExecutor() {
        Objects.requireNonNull(threadJSExecutor.get(), ERR_MSG);
        return threadJSExecutor.get();
    }

    public static Actions getActions() {
        Objects.requireNonNull(threadActions.get(), ERR_MSG);
        return threadActions.get();
    }

    public static void removeAll() {
        threadActions.remove();
        threadDriver.remove();
        threadJSExecutor.remove();
    }

}
