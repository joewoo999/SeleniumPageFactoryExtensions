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

import com.github.pfextentions.common.Resources;
import com.github.pfextentions.core.page.pageObject.PageElement;
import com.github.pfextentions.core.page.pageObject.expectedCondtion.Be;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DemoPage extends AbstractPage {
    public static String URL = Resources.getURL("test-html/index.html").toString();
    public static String TITLE = "test page";


    @Override
    public String url() {
        return URL;
    }

    @Override
    public String title() {
        return TITLE;
    }

    @Override
    public void assertPageOpened() {
        text.should(Be.present);
    }

    @FindBy(id = "form")
    PageElement form;

    @FindBy(id = "text")
    PageElement text;

    @FindBy(id = "disabled")
    PageElement disabled;

    @FindBy(id = "password")
    PageElement password;

    @FindBy(id = "submit")
    PageElement submit;

    @FindBy(id = "reset")
    PageElement reset;

    @FindBy(id = "click")
    PageElement click;

    @FindBy(id = "rightclick")
    PageElement rightclick;

    @FindBy(id = "jsclick")
    PageElement jsclick;

    @FindBy(id = "dblclick")
    PageElement dblclick;

    @FindBy(css = "label.click")
    PageElement clickCheck;

    @FindBy(css = "label.rightclick")
    PageElement rightclickCheck;

    @FindBy(css = "label.jsclick")
    PageElement jsclickCheck;

    @FindBy(css = "label.dblclick")
    PageElement dblclickCheck;

    @FindBy(id = "file")
    PageElement file;

    @FindBy(id = "checkbox1")
    PageElement checkbox1;

    @FindBy(id = "checkbox2")
    PageElement checkbox2;

    @FindBy(id = "radio")
    PageElement radio;

    @FindBy(id = "image")
    PageElement image;

    @FindBy(name = "hidden")
    PageElement hidden;

    @FindBy(id = "textarea")
    PageElement textarea;

    @FindBy(id = "button")
    PageElement button;

    @FindBy(id = "select")
    PageElement select;

    @FindBy(id = "display")
    PageElement display;

    @FindAll({@FindBy(tagName = "input")})
    List<PageElement> inputs;
}
