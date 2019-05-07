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
import com.github.pfextentions.core.driverContext.Driver;
import com.google.common.base.Strings;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;

public class Firefox extends AbstractDriver {

    public Firefox(BrowserConfig config) {
        super(config);
    }

    @Override
    public Firefox start() {
        FirefoxOptions ffOptions = new FirefoxOptions()
                .setLogLevel(FirefoxDriverLogLevel.FATAL);

        if (!Strings.isNullOrEmpty(downloadDir)) {
            String fileType = "application/octet-stream,application/vnd.ms-excel,"
                    + "application/zip,application/exe,application/txt";
            ffOptions.addPreference("browser.download.useDownloadDir", true)
                    .addPreference("browser.download.folderList", 2)
                    .addPreference("browser.download.dir", downloadDir)
                    .addPreference("browser.helperApps.neverAsk.saveToDisk", fileType);
        }

        if (!Strings.isNullOrEmpty(binary)) {
            ffOptions.setBinary(binary);
        }

        ffOptions.setHeadless(isHeadless);

        driver = new FirefoxDriver(ffOptions);
        return this;
    }
}
