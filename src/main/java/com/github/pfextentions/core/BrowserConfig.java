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

package com.github.pfextentions.core;

import org.openqa.selenium.remote.BrowserType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public interface BrowserConfig {

    String type();

    boolean headless();

    String binary(String binaryName);

    int pageLoadTime();

    int implicitlyWaitTime();

    String downloadDir();

    String driverServer(String propertyName);

//    Map<String, String> getMap();

    default boolean isChrome() {
        return KEYS.CHROME_TYPES.contains(type());
    }

    default boolean isFirefox() {
        return KEYS.FIREFOX_TYPES.contains(type());
    }

    default boolean isIE() {
        return KEYS.IE_TYPES.contains(type());
    }

    interface KEYS {
        String TYPE = "type";
        String HEADLESS = "headless";
        String PAGE_LOAD_TIME = "page.load.time";
        String IMPLICITLY_WAIT_TIME = "implicitly.wait.time";
        String DOWNLOAD_DIR = "download.dir";

        List<String> CHROME_TYPES = Arrays.asList(BrowserType.CHROME, BrowserType.GOOGLECHROME);
        List<String> FIREFOX_TYPES = Arrays.asList(BrowserType.FIREFOX, "ff");
        List<String> IE_TYPES = Arrays.asList(BrowserType.IE, BrowserType.IEXPLORE, "ie");

        String CHROME_PROPERTY = "webdriver.chrome.driver";
        String FIREFOX_PROPERTY = "webdriver.gecko.driver";
        String IE_PROPERTY = "webdriver.ie.driver";

        String CHROME_BINARY = "chrome.binary";
        String FIREFOX_BINARY = "firefox.binary";
    }
}
