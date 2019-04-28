package com.github.pfextentions.common;

import org.junit.Test;

public class ResourcesTest {

    @Test
    public void getFilePath() {
//        System.out.println(Resources.getFilePath("config.properties"));
//        System.out.println(Resources.getFilePath("C:\\temp"));
//        System.out.println(Resources.getFilePath("config"));
    }

    @Test
    public void getDirPath() {
        System.out.println(Resources.getDirPath("com"));
        System.out.println(Resources.getDirPath("config"));
    }
}