package com.github.pfextentions.common.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
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
            if (null == field.getAnnotation(ConfigKey.class))
                continue;

            String key = field.getAnnotation(ConfigKey.class).value();
            String value = (String) prop.get(key);
            if (null == value || 0 == value.trim().length())
                continue;

            try {
                field.setAccessible(true);

                Class<?> type = field.getType();
                if (int.class.equals(type)) {
                    field.set(config, Integer.valueOf(value));
                } else if (double.class.equals(type)) {
                    field.set(config, Double.valueOf(value));
                } else if (float.class.equals(type)) {
                    field.set(config, Float.valueOf(value));
                } else if (long.class.equals(type)) {
                    field.set(config, Long.valueOf(value));
                } else if (boolean.class.equals(type)) {
                    field.set(config, Boolean.valueOf(value));
                } else {
                    field.set(config, value);
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Properties load(String fileName) {
        Properties prop = new Properties();
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
        log.debug("Properties file: {} loaded.", fileName);
        return prop;
    }
}
