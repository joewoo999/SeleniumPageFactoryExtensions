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

import com.github.pfextentions.common.Property;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.UnsupportedCommandException;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CommandsCache {

    private Map<String, String> commands = new ConcurrentHashMap<>();
    private Map<String, Command> cachedCommands = new ConcurrentHashMap<>();

    public static CommandsCache fromProperties(String propertyFile) {
        return new CommandsCache().addFromProperties(propertyFile);
    }

    public CommandsCache addFromProperties(String propertyFile) {
        commands.putAll(Property.fromFile(propertyFile).toMap());
        return this;
    }

    public Class<?> get(String commandName) {
        String cmdClassName = commands.get(commandName.toLowerCase());
        if (null == cmdClassName) {
            throw new UnsupportedCommandException("Unsupported command: " + commandName);
        }

        try {
            Class<?> cmdClass = Class.forName(cmdClassName);
            if (!Command.class.isAssignableFrom(cmdClass)) {
                throw new UnsupportedCommandException(cmdClassName + " is not subclass of <Command>.");
            }
            return cmdClass;
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Command getCachedCommand(String commandName) {
        return cachedCommands.get(commandName);
    }

    public void add(@NotNull String methodName, @NotNull Class<? extends Command> cmdClass) {
        commands.put(methodName.toLowerCase(), cmdClass.getName());
    }

    public void cache(@NotNull String methodName, @NotNull Command instance) {
        cachedCommands.put(methodName.toLowerCase(), instance);
    }

    public int size() {
        return commands.size();
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
