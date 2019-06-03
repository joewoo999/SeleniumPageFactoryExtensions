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

package com.github.pfextentions.core.page.pageObject.functions;

import com.github.pfextentions.core.page.pageObject.function.ActionConsumer;
import com.github.pfextentions.core.page.pageObject.functions.actions.DoubleLeftClick;
import com.github.pfextentions.core.page.pageObject.functions.actions.TypeText;

public class Action {

    public static ActionConsumer DOUBLE_CLICK = new DoubleLeftClick();

    public static ActionConsumer CLICK = new DoubleLeftClick();

    public static ActionConsumer MOVE_TO = new DoubleLeftClick();

    public static ActionConsumer RIGHT_CLICK = new DoubleLeftClick();

    public static ActionConsumer type_text(String text) {
        return new TypeText(text);
    }

}