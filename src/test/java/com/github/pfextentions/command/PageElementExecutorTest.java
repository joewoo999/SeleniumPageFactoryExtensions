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

import com.github.pfextentions.browser.Configuration;
import com.github.pfextentions.browser.browsers.Chrome;
import com.github.pfextentions.page.DemoPage;
import com.github.pfextentions.page.factory.PageElementLocator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.support.pagefactory.ElementLocator;

public class PageElementExecutorTest {
    private static Chrome chrome;
    private static PageElementExecutor executor;


    @BeforeClass
    public static void before() {
        executor = new PageElementExecutor(CommandsCache.fromProperties("test"));

        Configuration config = Configuration.of("chrome", "true", "");
        chrome = new Chrome(Configuration.of("chrome", "true", "."));
        chrome.start();
        chrome.getWebDriver().get(DemoPage.URL);
    }

    @AfterClass
    public static void after() {
        chrome.quit();
    }

    @Test
    public void execute() throws Throwable {
        ElementLocator locator = new PageElementLocator(chrome.getWebDriver(), DemoPage.class.getField("text"));
        Object rt = executor.execute("getvalue", locator, new Object[]{});
        Assert.assertEquals("text", rt);
    }

    @Test
    public void getCache() {
        Assert.assertTrue(executor.getCache().size() > 0);
    }

    @Test
    public void getInstance() {
        Assert.assertTrue(PageElementExecutor.getInstance().getCache().size() > 0);
    }
}