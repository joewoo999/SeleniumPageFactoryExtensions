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

package com.github.pfextentions.core.page;

import org.junit.Test;

import static org.junit.Assert.*;

public class PageTargetLocatorTest extends PageBaseTest{

    @Test
    public void frame() {
        page.switchTo().frame(0);
        assertEquals("", page.title());
    }

    @Test
    public void frame1() {
        page.switchTo().frame("");
        assertEquals("", page.title());
    }

    @Test
    public void frame2() {
        page.switchTo().frame(page.click);
    }

    @Test
    public void parentFrame() {
        page.switchTo().parentFrame();
    }

    @Test
    public void window() {
        page.switchTo().window("");
    }

    @Test
    public void defaultContent() {
        page.switchTo().defaultContent();
    }

    @Test
    public void activeElement() {
        page.switchTo().activeElement();
    }

    @Test
    public void alert() {
        page.switchTo().alert();
    }

    @Test
    public void windowByTitle() {
        page.switchTo().windowByTitle("");
    }

    @Test
    public void newWindow() {
        page.switchTo().newWindow();
    }
}