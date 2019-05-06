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

package com.github.pfextentions.core.factory;

import com.github.pfextentions.core.BrowserConfig;
import com.github.pfextentions.core.driverContext.Driver;
import com.github.pfextentions.core.driverContext.DriverContext;
import com.github.pfextentions.core.PropertiesConfig;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class DriverFactory {

    private static Driver driver;

    public static void setUp() {
        setUp(new PropertiesConfig());
    }

    public static void setUp(BrowserConfig config) {
        try {
            Constructor constructor = config.driverClass().getConstructor(BrowserConfig.class);
            driver = (Driver) constructor.newInstance(config);

            driver.start().managePageLoadTime(config.pageLoadTime());

            DriverContext.set(config, driver.getWebDriver());

        } catch (NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void tearDown() {
        driver.quit();
        DriverContext.removeAll();
    }
}
