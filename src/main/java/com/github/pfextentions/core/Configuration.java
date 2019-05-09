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

import java.util.Map;

public class Configuration implements BrowserConfig {
    protected Map<String, String> map;
    protected String type;
    protected boolean headless;
    protected int pageLoadTime;
    protected int implicitlyWaitTime;
    protected String downloadDir;

    public Configuration(Map<String, String> map) {
        this.map = map;
        this.type = map.getOrDefault(KEYS.TYPE, BrowserType.CHROME);
        this.headless = "true".equalsIgnoreCase(map.get(KEYS.HEADLESS));
        this.downloadDir = map.get(KEYS.DOWNLOAD_DIR);

        this.pageLoadTime = Integer.parseInt(map.getOrDefault(KEYS.PAGE_LOAD_TIME, "60"));
        this.implicitlyWaitTime = Integer.parseInt(map.getOrDefault(KEYS.IMPLICITLY_WAIT_TIME, "0"));

    }

    public static Configuration of() {
        return new Configuration(Map.of());
    }

    public static Configuration of(String type, String headless, String downloadDir) {

        return new Configuration(Map.of(
                KEYS.TYPE, type,
                KEYS.HEADLESS, headless,
                KEYS.DOWNLOAD_DIR, downloadDir
        ));
    }

    @Override
    public String type() {
        return type;
    }

    @Override
    public boolean headless() {
        return headless;
    }

    @Override
    public String binary(String binaryName) {
        return map.get(binaryName);
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
    public String downloadDir() {
        return downloadDir;
    }

    public String driverProperty(String propertyName) {
        return map.get(propertyName);
    }

    @Override
    public Map<String, String> getMap() {
        return map;
    }
}
