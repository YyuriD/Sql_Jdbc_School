package com.foxminded.yuri.school.commands;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommandFactory {

	private static final Logger logger = LogManager.getLogger(CommandFactory.class);
	Map<String, Command> commands = new HashMap<>();

	public CommandFactory(Map<String, Command> commands) {
		this.commands = commands;
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

		return null;
	}
}
