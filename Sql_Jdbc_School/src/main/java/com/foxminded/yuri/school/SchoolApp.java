package com.foxminded.yuri.school;

import java.util.Collection;
import java.util.List;
import java.util.Scanner;

import com.foxminded.yuri.school.commands.Command;
import com.foxminded.yuri.school.commands.CommandAddStudent;
import com.foxminded.yuri.school.commands.CommandFindGroupByMaxStudent;
import com.foxminded.yuri.school.commands.CommandFindStudentsByCourseName;
import com.foxminded.yuri.school.commands.CommandProvider;
import com.foxminded.yuri.school.parameters.Parameter;
import com.foxminded.yuri.school.service.SchoolService;

public class SchoolApp {

	public static void main(String[] args) {
		// TODO application initialiser
		CommandAddStudent commandAddStudent = new CommandAddStudent();
		CommandFindGroupByMaxStudent commandFindGroupByMaxStudent = new CommandFindGroupByMaxStudent();
		CommandFindStudentsByCourseName commandFindStudentsByCourseName = new CommandFindStudentsByCourseName();
		CommandProvider commandProvider = new CommandProvider();
		commandProvider.addCommand(commandAddStudent);
		commandProvider.addCommand(commandFindGroupByMaxStudent);
		commandProvider.addCommand(commandFindStudentsByCourseName);

//***************************************************************
//			DatabaseInitializer.initialize();
//			TestDataGenerator.generate();
//***************************************************************

		System.out.println(viewCommands(commandProvider.getCommands()));
		consoleInteraction(commandProvider);
	}

	private static void consoleInteraction(CommandProvider commandProvider) {
		try (Scanner scanner = new Scanner(System.in)) {
			String input = "";
			while (true) {
				System.out.print("enter command-> ");
				input = scanner.nextLine().trim().toUpperCase();
				if ("exit".equalsIgnoreCase(input)) {
					break;
				}
				if (input.isEmpty()) {
					System.out.println("Input cannot be empty. Please enter a command.");
					continue;
				}
				try {
					Command command = commandProvider.createCommand(input);
					List<Parameter> parameters = command.getParameters();
					String paramValue = null;
					for (Parameter parameter : parameters) {
						System.out.println(parameter.getPrompt());
						paramValue = scanner.nextLine().trim();
						parameter.setValue(paramValue);
					}
					String message = command.execute(parameters, new SchoolService());
					System.out.println(message);
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}

	public static String viewCommands(Collection<Command> commands) {
		StringBuilder description = new StringBuilder();

		// TODO get ride of magic numbers(calcCommandsMaxLength(),calcParamsMaxLength())
		description.append("-".repeat(86)).append("\n");
		description.append(String.format("| %-30s | %-50s |", "Command", "Description")).append("\n");
		description.append("-".repeat(86)).append("\n");
		commands.forEach(c -> description.append(String.format("| %-30s | %-50s |", c.getName(), c.getDescription()))
				.append("\n"));
		description.append("-".repeat(86)).append("\n");
		return description.toString();
	}
}
