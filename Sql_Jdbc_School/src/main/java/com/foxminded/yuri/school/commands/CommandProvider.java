package com.foxminded.yuri.school.commands;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandProvider {

	private static final Logger logger = LogManager.getLogger(CommandProvider.class);
	private Map<String, Command> commands;

	public CommandProvider() {
		commands = new HashMap<>();
	}

	public Command createCommand(String commandName) {
		Command command = commands.get(commandName);
		if (command == null) {
			logger.error("Command not found.");
			throw new IllegalArgumentException("Command not found.");
		}
		return command;
	}

	public Collection<Command> getCommands() {
		return commands.values();
	}

	public Command addCommand(Command command) {
		return commands.put(command.getName(), command);
	}
}
