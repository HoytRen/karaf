/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.apache.felix.karaf.gshell.core;

import java.util.Map;

import org.apache.geronimo.gshell.command.Command;
import org.apache.geronimo.gshell.command.Link;
import org.apache.geronimo.gshell.registry.CommandRegistry;
import org.apache.geronimo.gshell.wisdom.command.LinkCommand;
import org.apache.geronimo.gshell.wisdom.registry.CommandLocationImpl;

public class OsgiCommandRegistry {

    public static final String NAME = "name";
    public static final String TARGET = "target";

    private CommandRegistry commandRegistry;

    public OsgiCommandRegistry(CommandRegistry commandRegistry) {
        this.commandRegistry = commandRegistry;
    }

    public synchronized void register(final Command command, Map props) throws Exception {
        commandRegistry.registerCommand(command);
    }

    public synchronized void unregister(final Command command, Map props) throws Exception {
        commandRegistry.removeCommand(command);
    }

    public synchronized void register(final Link link, Map props) throws Exception {
        LinkCommand cmd = new LinkCommand(commandRegistry, link.getTarget());
        cmd.setLocation(new CommandLocationImpl(link.getName()));
        commandRegistry.registerCommand(cmd);
    }

    public synchronized void unregister(final Link link, Map props) throws Exception {
        commandRegistry.removeCommand(commandRegistry.getCommand(link.getName()));
    }

}
