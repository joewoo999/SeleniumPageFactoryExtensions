package com.github.pfextentions.common;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;

public class ResourcesTest {

    @Test
    public void getPath() {
        File f = new File(Resources.getPath("config.properties"));
        Assert.assertTrue(f.exists());

        f = new File(Resources.getPath("C:\\Windows\\notepad.exe"));
        Assert.assertTrue(f.exists());

        f = new File(Resources.getPath("com/github/pfextentions/common/Resources.class"));
        Assert.assertTrue(f.exists());
    }

    @Test(expected = RuntimeException.class)
    public void getPath1() {
        Resources.getPath("com/github/pfextentions/common/Resources.java");
    }
}