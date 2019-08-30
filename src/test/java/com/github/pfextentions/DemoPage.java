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

package com.github.pfextentions;

import com.github.pfextentions.common.Resources;
import com.github.pfextentions.pageObject.PageElement;
import com.github.pfextentions.pageObject.expectedCondtion.Be;
import com.github.pfextentions.target.AbstractPage;
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
    public PageElement form;

    @FindBy(id = "text")
    public PageElement text;

    @FindBy(id = "disabled")
    public PageElement disabled;

    @FindBy(id = "password")
    public PageElement password;

    @FindBy(id = "submit")
    public PageElement submit;

    @FindBy(id = "reset")
    public PageElement reset;

    @FindBy(id = "click")
    public PageElement click;

    @FindBy(id = "rightclick")
    public PageElement rightclick;

    @FindBy(id = "jsclick")
    public PageElement jsclick;

    @FindBy(id = "dblclick")
    public PageElement dblclick;

    @FindBy(css = "label.click")
    public PageElement clickCheck;

    @FindBy(css = "label.rightclick")
    public PageElement rightclickCheck;

    @FindBy(css = "label.jsclick")
    public PageElement jsclickCheck;

    @FindBy(css = "label.dblclick")
    public PageElement dblclickCheck;

    @FindBy(id = "file")
    public PageElement file;

    @FindBy(id = "checkbox1")
    public PageElement checkbox1;

    @FindBy(id = "checkbox2")
    public PageElement checkbox2;

    @FindBy(name = "radio")
    public PageElement radio;

    @FindBy(id = "img1")
    public PageElement img1;

    @FindBy(id = "img4")
    public PageElement img4;

    @FindBy(name = "hidden")
    public PageElement hidden;

    @FindBy(id = "textarea")
    public PageElement textarea;

    @FindBy(id = "select")
    public PageElement select;

    @FindBy(id = "display")
    public PageElement display;

    @FindBy(id = "show")
    public PageElement show;

    @FindBy(id = "hide")
    public PageElement hide;

    @FindBy(id = "show-something")
    public PageElement showSomething;

    @FindBy(id = "hide-something")
    public PageElement hideSomething;

    @FindAll({@FindBy(name = "radio")})
    public List<PageElement> radios;
}
