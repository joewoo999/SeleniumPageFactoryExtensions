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

package com.github.pfextentions.core.page;

import com.github.pfextentions.core.driverContext.DriverContext;
import com.github.pfextentions.core.driverContext.DriverScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class AbstractPage implements Page {
    private Logger log = LoggerFactory.getLogger(Page.class.getSimpleName());

    public AbstractPage() {
        PageObjectFactory.initElements(DriverContext.getDriver(), this);
    }

    @Override
    public WebDriver driver() {
        return DriverContext.getDriver();
    }

    @Override
    public void open() {
        driver().get(url());
        log.debug("Open page url: {}.", url());
    }

    @Override
    public void maximizeWindow() {
        driver().manage().window().maximize();
        log.debug("Browser window maximized.");
    }

    @Override
    public abstract String url();

    @Override
    public abstract String title();

    @Override
    public abstract void assertPageOpened();

    @Override
    public PageTargetLocator switchTo() {
        return new PageTargetLocator();
    }

    @Override
    public void getScreenshot(String filePath) {
        DriverScreenshot.take(filePath);
        log.debug("Get page screenshot as file: {}.", filePath);
    }

    @Override
    public Object executeJS(String script, Object... args) {
        return DriverContext.getJSExecutor().executeScript(script, args);
    }

    @Override
    public void scrollToTop() {
        executeJS("window.scrollTo(0, 0);");
        log.debug("Page scrolled to top.");
    }

    @Override
    public void scrollToBottom() {
        executeJS("window.scrollTo(0, document.body.clientHeight);");
        log.debug("Page scrolled to bottom.");
    }
}
