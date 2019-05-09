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

package com.github.pfextentions.common;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Properties;

public class Property {
    private Logger log = LoggerFactory.getLogger(Property.class.getSimpleName());
    private Properties prop = new Properties();

    public static Property fromFile(String fileName) {
        return new Property().load(fileName);
    }

    public Map<String, String> toMap() {
        return Maps.fromProperties(prop);
    }

    public Properties getProperties() {
        return prop;
    }

    private Property load(String fileName) {
        fileName = fileName.endsWith(".properties") ? fileName : fileName.concat(".properties");
        InputStream is;
        try {
            is = new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            log.debug("Can not find file: {} at given path.", fileName);
            is = ClassLoader.getSystemResourceAsStream(fileName);
            if (null == is) {
                throw new RuntimeException("Can not find file: " + fileName);
            }
        }
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
            prop.load(br);
            br.close();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("Properties file: {} loaded.", fileName);
        return this;
    }
}
