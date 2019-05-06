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
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class AbstractDriver implements Driver {

    protected WebDriver driver;
    protected String binary, downloadDir;
    protected boolean startMaximized, isHeadless;

    public AbstractDriver(BrowserConfig config) {
        this.binary = config.browserBinary();
        this.downloadDir = config.defaultDownloadDir();
        this.startMaximized = config.startMaximized();
        this.isHeadless = config.isHeadless();
    }

    @Override
    public abstract AbstractDriver start();

    @Override
    public WebDriver getWebDriver() {
        Objects.requireNonNull(driver, "Webdriver instance is null,u should run 'start()' first.");
        return driver;
    }

    @Override
    public AbstractDriver managePageLoadTime(int timeInSecond) {
        getWebDriver().manage().timeouts().pageLoadTimeout(timeInSecond, SECONDS);
        return this;
    }

    @Override
    public AbstractDriver manageImplicitlyWaitTime(int timeInSecond) {
        getWebDriver().manage().timeouts().implicitlyWait(timeInSecond, SECONDS);
        return this;
    }

    @Override
    public void quit() {
        getWebDriver().quit();
    }
}
