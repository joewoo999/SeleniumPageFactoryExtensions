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

import com.github.pfextentions.core.driverContext.Driver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.remote.BrowserType;

import java.util.Arrays;

public interface BrowserConfig {

    String browserType();

    boolean isHeadless();

    int pageLoadTime();

    int implicitlyWaitTime();

    boolean startMaximized();

    String defaultDownloadDir();

    String browserBinary();

    Class<? extends Driver> driverClass();

    default boolean isChrome() {
        return Arrays.asList(CHROME).contains(browserType());
    }

    default boolean isFirefox() {
        return Arrays.asList(FIREFOX).contains(browserType());
    }

    default boolean isIE() {
        return Arrays.asList(IE).contains(browserType());
    }

    String CHROME_DRIVER = ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY;

    String FIREFOX_DRIVER = GeckoDriverService.GECKO_DRIVER_EXE_PROPERTY;

    String IE_DRIVER = InternetExplorerDriverService.IE_DRIVER_EXE_PROPERTY;

    String[] CHROME = {BrowserType.CHROME, BrowserType.GOOGLECHROME};

    String[] FIREFOX = {BrowserType.FIREFOX, "ff"};

    String[] IE = {BrowserType.IE, BrowserType.IEXPLORE, "ie"};
}
