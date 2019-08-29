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

package com.github.pfextentions.page;

import com.github.pfextentions.common.Resources;
import com.github.pfextentions.browser.DriverContext;
import com.github.pfextentions.page.pageObject.expectedCondtion.Be;
import com.github.pfextentions.page.pageObject.functions.Action;
import com.github.pfextentions.page.pageObject.functions.Option;
import com.github.pfextentions.page.pageObject.functions.Radio;
import org.apache.commons.io.FileUtils;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Rectangle;

import java.io.File;

import static org.junit.Assert.*;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PageElementTest extends PageBaseTest {

    @Test
    public void testClear() {
        page.text.clear();
        assertTrue(page.text.getValue().isEmpty());
    }

    @Test
    public void testClick() {
        page.click.click();
        assertTrue(page.clickCheck.isPresent());
    }

    @Test
    public void testClickByJs() {
        page.jsclick.clickByJS();
        assertTrue(page.jsclickCheck.isPresent());
    }

    @Test
    public void testDoubleClick() {
        page.dblclick.doubleClick();
        assertTrue(page.dblclickCheck.isPresent());
    }

    @Test
    public void testFindElement() {
        assertTrue(page.form.findElement(By.id("text")).isPresent());
    }

    @Test
    public void testFindElements() {
        assertFalse(page.form.findElements(By.tagName("input")).isEmpty());
    }

    @Test
    public void testGetAttribute() {
        assertEquals("password", page.password.getAttribute("type"));
    }

    @Test
    public void testGetCssValue() {
        assertEquals("none", page.display.getCssValue("display"));
    }

    @Test
    public void testGetLocation() {
        //chrome
        if (DriverContext.getConfig().isChrome()) {
            assertEquals("(8, 18)", page.form.getLocation().toString());
        }
    }

    @Test
    public void testGetRect() {
        //chrome
        if (DriverContext.getConfig().isChrome()) {
            Rectangle rectangle = page.form.getRect();
            assertEquals("(8, 18)", rectangle.getPoint().toString());
            assertEquals("(767, 223)", rectangle.getDimension().toString());
        }
    }

    @Test
    public void testGetScreenshot() {
        page.form.getScreenshot("form.png");
        File file = new File("form.png");
        assertTrue(FileUtils.waitFor(file, 5));
        FileUtils.deleteQuietly(file);
    }

    @Test
    public void testGetScreenshotAs() {
        String file = page.form.getScreenshotAs(OutputType.BASE64);
        assertFalse(file.isEmpty());
    }

    @Test
    public void testGetSize() {
        //chrome
        if (DriverContext.getConfig().isChrome()) {
            assertEquals("(767, 223)", page.form.getSize().toString());
        }
    }

    @Test
    public void testGetTagName() {
        assertEquals("input", page.password.getTagName());
    }

    @Test
    public void testGetText() {
        assertEquals("show", page.show.getText());
    }

    @Test
    public void testGetValue() {
        assertEquals("Submit", page.submit.getValue());
    }

    @Test
    public void testGetWebElement() {
        assertNotNull(page.submit.getWebElement());
    }

    @Test
    public void testIsDisplayed() {
        assertTrue(page.text.isDisplayed());
        assertFalse(page.display.isDisplayed());
        assertFalse(page.hidden.isDisplayed());
        page.refresh();
        assertFalse(page.clickCheck.isDisplayed());
    }

    @Test
    public void testIsEnabled() {
        assertTrue(page.text.isEnabled());
        assertFalse(page.disabled.isEnabled());
    }

    @Test
    public void testIsPresent() {
        assertTrue(page.disabled.isPresent());
        page.refresh();
        assertFalse(page.clickCheck.isPresent());
    }

    @Test
    public void testIsSelected() {
        assertTrue(page.checkbox1.isSelected());
        assertFalse(page.checkbox2.isSelected());
    }

    @Test
    public void testPerformDoubleClick() {
        page.dblclick.perform(Action.DOUBLE_CLICK);
        assertTrue(page.dblclickCheck.isPresent());
    }

    @Test
    public void testPerformClick() {
        page.click.perform(Action.CLICK);
        assertTrue(page.clickCheck.isPresent());
    }

    @Test
    public void testPerformMoveTo() {
        page.img1.perform(Action.MOVE_TO);
        assertEquals("browser safari", page.img1.getAttribute("class"));
    }

    @Test
    public void testPerformRightClick() {
        page.rightclick.perform(Action.RIGHT_CLICK);
        assertTrue(page.rightclickCheck.isPresent());
    }

    @Test
    public void testScrollToCenter() {
        page.img1.scrollToCenter();
        //chrome
        if (DriverContext.getConfig().isChrome()) {
            assertEquals("(8, 620)", page.img1.getLocation().toString());
        }
    }

    @Test
    public void testSelectOption() {
        page.select.select(Option.byIndex(1));
        assertEquals("Option 2", page.select.select(Option.GET_SELECTED_TEXT));

        page.select.select(Option.byText("Option 3"));
        assertEquals("Option 3", page.select.select(Option.GET_SELECTED_TEXT));

        page.select.select(Option.byValue("1"));
        assertEquals("Option 1", page.select.select(Option.GET_SELECTED_TEXT));
    }

    @Test
    public void testSelectRadio() {
        page.radio.select(Radio.byIndex(1));
        page.radio.select(Radio.byLabelText("radio3"));
        page.radio.select(Radio.byValue("1"));
    }


    @Test
    public void testSendKeys() {
        page.text.sendKeys("sendKeys");
        assertEquals("sendKeys", page.text.getValue());
    }

    @Test
    public void testSetValue() {
        page.disabled.setValue("setValue");
        assertEquals("setValue", page.disabled.getValue());
    }

    @Test
    public void testShould1() {
        page.disabled.should(Be.present);
    }

    @Test(expected = AssertionError.class)
    public void testShould2() {
        page.disabled.should(Be.enabled);
    }

    @Test
    public void testSubmit() {
        page.submit.submit();
    }

    @Test
    public void testUploadFile() {
        page.file.uploadFile(Resources.getPath("test-html/pic/browser.png"));
        assertTrue(page.img4.isDisplayed());
    }

    @Test
    public void testWaitUntil() {
        page.show.click();
        page.showSomething.waitUntil(Be.visible, 5);
    }

    @Test
    public void testWaitWhile() {
        page.hide.click();
        page.hideSomething.waitWhile(Be.visible, 5);
    }

}
