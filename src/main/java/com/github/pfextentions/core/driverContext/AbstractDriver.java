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

import com.github.pfextentions.common.Resources;
import com.github.pfextentions.core.BrowserConfig;
import com.google.common.base.Strings;
import org.openqa.selenium.WebDriver;

import java.util.Objects;

import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class AbstractDriver implements Driver {

    protected WebDriver driver;
    protected BrowserConfig config;
    protected String downloadDir;
    protected boolean headless;

    public AbstractDriver(BrowserConfig config) {
        this.config = config;
        this.downloadDir = Resources.getPath(config.downloadDir());
        this.headless = config.headless();
    }

    @Override
    public void setProperty(String propertyName) {
        String propertyFile = Resources.getPath(config.driverProperty(propertyName));
        if (!Strings.isNullOrEmpty(propertyFile)) {
            System.setProperty(propertyName, propertyFile);
        }
    }

    @Override
    public WebDriver getWebDriver() {
        Objects.requireNonNull(driver, "Webdriver instance is null,u should run 'start()' first.");
        return driver;
    }

    @Override
    public void managePageLoadTime(int timeInSecond) {
        getWebDriver().manage().timeouts().pageLoadTimeout(timeInSecond, SECONDS);
    }

    @Override
    public void manageImplicitlyWaitTime(int timeInSecond) {
        getWebDriver().manage().timeouts().implicitlyWait(timeInSecond, SECONDS);
    }

    @Override
    public void quit() {
        getWebDriver().quit();
    }
}
