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

package com.github.pfextentions.pageObject.functions;

import com.github.pfextentions.pageObject.function.RadioGroupFunction;
import com.github.pfextentions.pageObject.functions.radioGroup.SelectRadioByIndex;
import com.github.pfextentions.pageObject.functions.radioGroup.SelectRadioByLabelText;
import com.github.pfextentions.pageObject.functions.radioGroup.SelectRadioByValue;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

public class Radio {

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public static RadioGroupFunction<WebElement> byIndex(int index) {
        return new SelectRadioByIndex(index);
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public static RadioGroupFunction<WebElement> byLabelText(String text) {
        return new SelectRadioByLabelText(text);
    }

    @NotNull
    @Contract(value = "_ -> new", pure = true)
    public static RadioGroupFunction<WebElement> byValue(String value) {
        return new SelectRadioByValue(value);
    }
}
