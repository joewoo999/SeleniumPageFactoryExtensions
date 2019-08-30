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

package com.github.pfextentions.command;

import com.github.pfextentions.pageObject.commands.Click;
import com.github.pfextentions.pageObject.commands.GetValue;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.UnsupportedCommandException;

public class CommandsCacheTest {
    private static CommandsCache cache;

    @BeforeClass
    public static void before() {
        cache = CommandsCache.fromProperties("test");
    }

    @Test
    public void addFromProperties() {
        CommandsCache cache = new CommandsCache();
        cache.addFromProperties("test");
        Assert.assertTrue(cache.size() > 0);
    }

    @Test
    public void fromProperties() {
        Assert.assertTrue(cache.size() > 0);
    }

    @Test
    public void get() {
        Class<?> clazz = cache.get("getvalue");
        Assert.assertEquals(clazz, GetValue.class);
    }

    @Test(expected = UnsupportedCommandException.class)
    public void get1() {
        Class<?> clazz = cache.get("zxcv");
    }

    @Test(expected = UnsupportedCommandException.class)
    public void get2() {
        Class<?> clazz = cache.get("notcommand");
    }

    @Test(expected = RuntimeException.class)
    public void get3() {
        Class<?> clazz = cache.get("classnotfound");
    }

    @Test
    public void cache() {
        cache.cache("cache", new Click());
        Assert.assertTrue(cache.getCachedCommand("cache") instanceof Click);
    }

    @Test
    public void add() {
        cache.add("add", Click.class);
        Assert.assertEquals(Click.class, cache.get("add"));
    }

    @Test(expected = UnsupportedCommandException.class)
    public void remove() {
        cache.remove("doubleclick");
        Assert.assertNull(cache.get("doubleclick"));
    }

    @Test
    public void removeAll() {
        cache.removeAll();
        Assert.assertEquals(0, cache.size());
    }
}