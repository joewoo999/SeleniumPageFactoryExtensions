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

package com.github.pfextentions.core.command;

import com.github.pfextentions.common.Property;
import com.github.pfextentions.core.command.CommandExecutor;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.UnsupportedCommandException;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Commands {

    private Map<String, String> commands = new ConcurrentHashMap<>();
    private Map<String, CommandExecutor> cachedCommands = new ConcurrentHashMap<>();

    public static Commands fromProperties(String propertyFile) {
        return new Commands().addFromProperties(propertyFile);
    }

    public Commands addFromProperties(String propertyFile) {
        commands.putAll(Property.fromFile(propertyFile).toMap());
        return this;
    }

    public CommandExecutor get(String methodName) {
        CommandExecutor command = cachedCommands.get(methodName);
        if (null != command)
            return command;

        try {
            String cmdClassName = commands.get(methodName.toLowerCase());

            if (null == cmdClassName) {
                throw new UnsupportedCommandException("Unsupported command: " + methodName);
            }
            Class<?> cmdClass = Class.forName(cmdClassName);
            if (!CommandExecutor.class.isAssignableFrom(cmdClass)) {
                throw new UnsupportedCommandException(cmdClassName + " is not subclass of <Command>.");
            }
            command = (CommandExecutor) cmdClass.getDeclaredConstructor().newInstance();
            cachedCommands.put(methodName, command);
            return command;

        } catch (InstantiationException | InvocationTargetException | IllegalAccessException
                | NoSuchMethodException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void add(@NotNull String methodName, @NotNull Class<? extends CommandExecutor> cmdClass) {
        commands.put(methodName.toLowerCase(), cmdClass.getName());
    }

    public void remove(String methodName) {
        commands.remove(methodName);
        cachedCommands.remove(methodName);
    }

    public void removeAll() {
        commands.clear();
        cachedCommands.clear();
    }
}
