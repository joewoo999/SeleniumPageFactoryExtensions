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

package com.github.pfextentions.common.config;

import com.github.pfextentions.common.Property;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

public abstract class ConfigFactory {
    private static Logger log = LoggerFactory.getLogger(ConfigFactory.class.getSimpleName());

    public static <T> T init(String fileName, Class<T> classToProxy) {
        try {
            T config = classToProxy.getDeclaredConstructor().newInstance();
            init(fileName, config);
            return config;
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }


    public static void init(String fileName, Object config) {
        proxyFields(load(fileName), config);
    }

    private static void proxyFields(Properties prop, Object config) {
        Field[] fields = config.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (null == field.getAnnotation(ConfigKey.class)) {
                continue;
            }
            String key = field.getAnnotation(ConfigKey.class).value();
            String value = (String)prop.get(key);
            if (null == value) {
                continue;
            }
            try {
                field.setAccessible(true);

                Class<?> type = field.getType();
                if (int.class.equals(type)) {
                    field.set(config, Integer.parseInt(value));
                } else if (double.class.equals(type)) {
                    field.set(config, Double.parseDouble(value));
                } else if (float.class.equals(type)) {
                    field.set(config, Float.parseFloat(value));
                } else if (long.class.equals(type)) {
                    field.set(config, Long.parseLong(value));
                } else if (boolean.class.equals(type)) {
                    field.set(config, Boolean.parseBoolean(value));
                } else {
                    field.set(config, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Properties load(String fileName) {
        return Property.fromFile(fileName).getProperties();
    }
}
