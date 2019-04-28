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
