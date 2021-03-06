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

package com.github.pfextentions.browser.browsers;

import com.github.pfextentions.browser.BrowserConfig;
import com.github.pfextentions.browser.AbstractBrowser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;

public class InternetExplorer extends AbstractBrowser {

    public InternetExplorer(BrowserConfig config) {
        super(config);
    }

    @Override
    public void start() {
        this.setProperty(BrowserConfig.KEYS.IE_PROPERTY);

        InternetExplorerOptions ieOptions = new InternetExplorerOptions();
        driver = new InternetExplorerDriver(ieOptions);
    }

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }


//    @Override
//    public void quit() {
//        super.quit();
//        try {
//            Runtime.getRuntime().exec("taskkill /im iexplore.exe");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
