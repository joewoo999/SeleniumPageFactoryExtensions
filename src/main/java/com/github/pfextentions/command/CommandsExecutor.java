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


import java.lang.reflect.InvocationTargetException;


public abstract class CommandsExecutor<U, V, R> {
    private CommandsCache cache;

    public CommandsExecutor(CommandsCache cache) {
        this.cache = cache;
    }

    public abstract R execute(String cmdName, U u, V v) throws Throwable;

    public CommandsCache getCache() {
        return cache;
    }

    protected Command buildCommand(String cmdName) {
        Command command = cache.getCachedCommand(cmdName);
        if (null != command) {
            return command;
        }
        try {
            Class<?> cmdClass = cache.get(cmdName);
            command = (Command) cmdClass.getDeclaredConstructor().newInstance();
            cache.cache(cmdName, command);
            return command;

        } catch (InstantiationException | InvocationTargetException | IllegalAccessException
                | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
