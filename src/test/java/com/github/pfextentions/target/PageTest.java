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

package com.github.pfextentions.target;

import com.github.pfextentions.PageBaseTest;
import com.github.pfextentions.common.Resources;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class PageTest extends PageBaseTest {

    @Test
    public void testDriver() {
        assertNotNull(page.driver());
    }

    @Test
    public void testOpen() {
        page.open();
        page.assertPageOpened();
        page.maximizeWindow();
    }

    @Test
    public void testUrl() {
        assertEquals(Resources.getURL("test-html/index.html").toString(), page.url());
    }

    @Test
    public void testTitle() {
        assertEquals("test page", page.title());
    }

    @Test
    public void testSwitchTo() {
        page.switchTo().windowByTitle("test page");
    }

    @Test
    public void testGetScreenshot() {
        page.getScreenshot("pageshot.png");
        File file = new File("pageshot.png");
        assertTrue(FileUtils.waitFor(file, 5));
        FileUtils.deleteQuietly(file);
    }

    @Test
    public void testRefresh() {
        page.refresh();
    }

    @Test
    public void testScrollToTop() {
        page.scrollToTop();
    }

    @Test
    public void testScrollToBottom() {
        page.scrollToBottom();
    }

}
