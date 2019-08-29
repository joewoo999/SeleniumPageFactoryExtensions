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

import com.github.pfextentions.browser.browsers.Chrome;
import com.github.pfextentions.browser.browsers.Firefox;
import com.github.pfextentions.browser.browsers.InternetExplorer;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BrowserFactory {

    private static Browser browser;
    protected static Map<String, Class<? extends Browser>> browserClasses = new HashMap<>();

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
            Constructor constructor = browserClasses.get(config.type()).getConstructor(BrowserConfig.class);
            browser = (Browser) constructor.newInstance(config);
            browser.start();
            if (config.pageLoadTime() > 0)
                browser.managePageLoadTime(config.pageLoadTime());

            if (config.implicitlyWaitTime() > 0)
                browser.manageImplicitlyWaitTime(config.implicitlyWaitTime());

            DriverContext.set(config, browser.getWebDriver());

        } catch (NoSuchMethodException | IllegalAccessException
                | InstantiationException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static void tearDown() {
        browser.quit();
        DriverContext.removeAll();
    }

    public static void addDriverClass(List<String> typeList, Class<? extends Browser> clazz) {
        typeList.forEach(type -> browserClasses.putIfAbsent(type, clazz));
    }
}
