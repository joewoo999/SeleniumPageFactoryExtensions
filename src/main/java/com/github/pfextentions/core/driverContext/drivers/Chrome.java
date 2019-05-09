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

package com.github.pfextentions.core.driverContext.drivers;

import com.github.pfextentions.core.BrowserConfig;
import com.github.pfextentions.core.driverContext.AbstractDriver;
import com.google.common.base.Strings;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class Chrome extends AbstractDriver {

    public Chrome(BrowserConfig config) {
        super(config);
    }

    @Override
    public void start() {
        this.setProperty(BrowserConfig.KEYS.CHROME_PROPERTY);

        ChromeOptions chromeOptions = new ChromeOptions()
                .addArguments("--disable-popup-blocking");

        String binary = config.binary(BrowserConfig.KEYS.CHROME_BINARY);
        if (!Strings.isNullOrEmpty(binary)) {
            chromeOptions.setBinary(binary);
        }

        if (!Strings.isNullOrEmpty(downloadDir)) {
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory", downloadDir);
            prefs.put("profile.default_content_settings.popups", 0);
            chromeOptions.setExperimentalOption("prefs", prefs);
        }

        chromeOptions.addArguments("--start-maximized");

        chromeOptions.setHeadless(headless);

        driver = new ChromeDriver(chromeOptions);
    }
}
