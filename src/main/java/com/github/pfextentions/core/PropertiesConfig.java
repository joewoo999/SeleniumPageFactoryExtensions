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

import com.github.pfextentions.common.Property;
import com.github.pfextentions.common.Resources;
import com.github.pfextentions.core.driverContext.Driver;
import com.github.pfextentions.core.driverContext.drivers.Chrome;
import com.github.pfextentions.core.driverContext.drivers.Firefox;
import com.github.pfextentions.core.driverContext.drivers.InternetExplorer;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;


public class PropertiesConfig implements BrowserConfig {
    private static Logger log = LoggerFactory.getLogger(BrowserConfig.class.getSimpleName());

    private String browserType;
    private String defaultDownloadDir;
    private boolean isHeadless;
    private boolean startMaximized;
    private int pageLoadTime;
    private int implicitlyWaitTime;

    private String chromeDriver, firefoxDriver, IEDriver;
    private String browserBinary, chromeBinary, firefoxBinary;
    private Class<? extends Driver> driverClass;

    public PropertiesConfig() {
        this(Property.loadFromFile("config.properties").toMap());
    }

    public PropertiesConfig(Map<String, String> configMap) {
        this.browserType = configMap.getOrDefault("browserType", BrowserType.CHROME);
        this.isHeadless = Boolean.parseBoolean(configMap.getOrDefault("isHeadless", "false"));
        this.startMaximized = Boolean.parseBoolean(configMap.getOrDefault("startMaximized", "true"));
        this.pageLoadTime = Integer.parseInt(configMap.getOrDefault("pageLoadTime", "60"));
        this.implicitlyWaitTime = Integer.parseInt(configMap.get("implicitlyWaitTime"));
        this.defaultDownloadDir = configMap.get("defaultDownloadDir");

        if (isChrome()) {
            setProperty(CHROME_DRIVER, chromeDriver, chromeBinary, Chrome.class);
        } else if (isFirefox()) {
            setProperty(FIREFOX_DRIVER, firefoxDriver, firefoxBinary, Firefox.class);
        } else if (isIE()) {
            setProperty(IE_DRIVER, IEDriver, null, InternetExplorer.class);
        } else {
            throw new IllegalArgumentException("Unsupported browser type: " + browserType);
        }
    }

    @Override
    public String browserType() {
        return browserType;
    }

    @Override
    public boolean isHeadless() {
        return isHeadless;
    }

    @Override
    public boolean startMaximized() {
        return startMaximized;
    }

    @Override
    public int pageLoadTime() {
        return pageLoadTime;
    }

    @Override
    public int implicitlyWaitTime() {
        return implicitlyWaitTime;
    }

    @Override
    public String defaultDownloadDir() {
        return defaultDownloadDir;
    }

    @Override
    public String browserBinary() {
        return browserBinary;
    }

    @Override
    public Class<? extends Driver> driverClass() {
        return driverClass;
    }

    private void setProperty(String browserProperty, String browserDriver,
                             String browserBinary, Class<? extends Driver> driverClass) {

        System.setProperty(browserProperty, Resources.getFilePath(browserDriver));
        this.browserBinary = Resources.getFilePath(browserBinary);
        this.defaultDownloadDir = Resources.getDirPath(defaultDownloadDir);
        this.driverClass = driverClass;
    }

}
