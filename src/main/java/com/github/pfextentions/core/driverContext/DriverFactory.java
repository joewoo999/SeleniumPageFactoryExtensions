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

package com.github.pfextentions.core.driverContext;

import com.github.pfextentions.core.BrowserConfig;
import com.github.pfextentions.core.PropertiesConfig;
import com.github.pfextentions.core.driverContext.drivers.Chrome;
import com.github.pfextentions.core.driverContext.drivers.Firefox;
import com.github.pfextentions.core.driverContext.drivers.InternetExplorer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DriverFactory {

    private static Driver driver;
    protected static Map<String, Class<? extends Driver>> driverClasses = new HashMap<>();

    static {
        addDriverClass(BrowserConfig.KEYS.CHROME_TYPES, Chrome.class);
        addDriverClass(BrowserConfig.KEYS.FIREFOX_TYPES, Firefox.class);
        addDriverClass(BrowserConfig.KEYS.IE_TYPES, InternetExplorer.class);
    }

    public static void setUp() {
        setUp(new PropertiesConfig());
    }

    public static void setUp(BrowserConfig config) {
        try {
            Constructor constructor = driverClasses.get(config.type()).getConstructor(BrowserConfig.class);
            driver = (Driver) constructor.newInstance(config);
            driver.start();
            if (config.pageLoadTime() > 0)
                driver.managePageLoadTime(config.pageLoadTime());

            if (config.implicitlyWaitTime() > 0)
                driver.manageImplicitlyWaitTime(config.implicitlyWaitTime());

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

    public static void addDriverClass(List<String> typeList, Class<? extends Driver> clazz) {
        typeList.forEach(type -> driverClasses.putIfAbsent(type, clazz));
    }
}
