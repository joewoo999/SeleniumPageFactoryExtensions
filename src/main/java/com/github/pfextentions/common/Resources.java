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
import java.net.URL;

public class Resources {

    public static String getFilePath(String fileName) {
        if (null == fileName || fileName.trim().isEmpty() || new File(fileName).isFile())
            return fileName;
        URL u = ClassLoader.getSystemResource(fileName);
        if (null == u)
            throw new RuntimeException("Can not find given file: " + fileName);
        return u.getPath();
    }

    public static String getDirPath(String dir) {
        if (null == dir || dir.trim().isEmpty() || new File(dir).isDirectory())
            return dir;
        URL u = ClassLoader.getSystemResource(dir);
        if (null == u || !new File(u.getPath()).isDirectory())
            throw new RuntimeException("Can not find directory: " + dir);
        return u.getPath();
    }
}
