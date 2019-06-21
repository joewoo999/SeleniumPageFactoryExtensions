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

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Resources {

    public static String getPath(String fileName) {
        if (null == fileName || fileName.isBlank())
            return fileName;
        URL u = getURL(fileName);
        String path = getURL(fileName).getPath();
        return path.startsWith("/") ? path.substring(1) : path;
    }

    public static URL getURL(String fileName) {
        Objects.requireNonNull(fileName);
        try {
            File file = new File(fileName);
            if (file.isFile() || file.isDirectory()) {
                return file.toURI().toURL();
            }
        } catch (MalformedURLException ignore) {
//            ignore.printStackTrace();
        }
        return ClassLoader.getSystemResource(fileName);
    }
}
