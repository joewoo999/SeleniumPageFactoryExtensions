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

import com.github.pfextentions.core.driverContext.DriverFactory;
import com.github.pfextentions.core.page.pageObject.functions.Action;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;

import java.io.File;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PageElementTest {
    private static DemoPage page;

    @BeforeClass
    public static void before() {
        DriverFactory.setUp();
        page = new DemoPage();
        page.open();
        page.maximizeWindow();
        page.assertPageOpened();
    }

    @Test
    public void testClear() {
        page.text.clear();
        Assert.assertTrue(page.text.getValue().isEmpty());
    }

    @Test
    public void testClick() {
        page.click.click();
        Assert.assertTrue(page.clickCheck.isPresent());
    }

    @Test
    public void testClickByJs() {
        page.jsclick.clickByJS();
        Assert.assertTrue(page.jsclickCheck.isPresent());
    }

    @Test
    public void testDoubleClick() {
        page.dblclick.doubleClick();
        Assert.assertTrue(page.dblclickCheck.isPresent());
    }

    @Test
    public void testFindElement() {
        Assert.assertTrue(page.form.findElement(By.id("text")).isPresent());
    }

    @Test
    public void testFindElements() {
        Assert.assertFalse(page.form.findElements(By.tagName("input")).isEmpty());
    }

    @Test
    public void testGetAttribute() {
        Assert.assertEquals("password", page.password.getAttribute("type"));
    }

    @Test
    public void testGetCssValue() {
        Assert.assertEquals("none", page.display.getCssValue("display"));
    }

    @Test
    public void testGetLocation() {
        //chrome
        Assert.assertEquals("(8, 18)", page.form.getLocation().toString());
    }

    @Test
    public void testGetRect() {
        //chrome
        Rectangle rectangle = page.form.getRect();
        Assert.assertEquals("(8, 18)", rectangle.getPoint().toString());
        Assert.assertEquals("(767, 223)", rectangle.getDimension().toString());
    }

    @Test
    public void testGetScreenshot() {
        page.form.getScreenshot("form.png");
        File file = new File("form.png");
        Assert.assertTrue(FileUtils.waitFor(file, 5));
        FileUtils.deleteQuietly(file);
    }

    @Test
    public void testGetScreenshotAs() {
        String file = page.form.getScreenshotAs(OutputType.BASE64);
        Assert.assertFalse(file.isEmpty());
    }

    @Test
    public void testGetSize() {
        //chrome
        Assert.assertEquals("(767, 223)", page.form.getSize().toString());
    }

    @Test
    public void testGetTagName() {
        Assert.assertEquals("input", page.password.getTagName());
    }

    @Test
    public void testGetText() {
        Assert.assertEquals("button", page.button.getText());
    }

    @Test
    public void testGetValue() {
        Assert.assertEquals("Submit", page.submit.getValue());
    }

    @Test
    public void testGetWebElement() {
        Assert.assertNotNull(page.submit.getWebElement());
    }

    @Test
    public void testIsDisplayed() {
        Assert.assertTrue(page.text.isDisplayed());
        Assert.assertFalse(page.display.isDisplayed());
        Assert.assertFalse(page.hidden.isDisplayed());
        Assert.assertFalse(page.clickCheck.isDisplayed());
    }

    @Test
    public void testIsEnabled() {
        Assert.assertTrue(page.text.isEnabled());
        Assert.assertFalse(page.disabled.isEnabled());
    }

    @Test
    public void testIsPresent() {
        Assert.assertTrue(page.disabled.isPresent());
        page.refresh();
        Assert.assertFalse(page.clickCheck.isPresent());
    }

    @Test
    public void testIsSelected() {
        Assert.assertTrue(page.checkbox1.isSelected());
        Assert.assertFalse(page.checkbox2.isSelected());
    }

    @Test
    public void testPerformDoubleClick() {
        page.dblclick.perform(Action.DOUBLE_CLICK);
        Assert.assertTrue(page.dblclickCheck.isPresent());
    }

    @Test
    public void testPerformClick() {
        page.click.perform(Action.CLICK);
        Assert.assertTrue(page.clickCheck.isPresent());
    }

    @Test
    public void testPerformMoveTo() {
        page.text.perform(Action.MOVE_TO);
        Assert.assertEquals("mouse over", page.text.getValue());
    }

    @Test
    public void testPerformRightClick() {
        page.rightclick.perform(Action.RIGHT_CLICK);
        Assert.assertTrue(page.rightclickCheck.isPresent());
    }

    @Test
    public void testScrollToCenter() {
        page.image.scrollToCenter();
        //chrome
        Assert.assertEquals("(8, 620)", page.image.getLocation().toString());

    }

    @AfterClass
    public static void after() {
        DriverFactory.tearDown();
    }
}
