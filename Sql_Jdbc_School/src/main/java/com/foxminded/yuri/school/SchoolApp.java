package com.foxminded.yuri.school;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.foxminded.yuri.school.commands.Command;
import com.foxminded.yuri.school.commands.CommandAddStudent;
import com.foxminded.yuri.school.commands.CommandFactory;
import com.foxminded.yuri.school.parameters.ParamGroupId;
import com.foxminded.yuri.school.parameters.ParamStudentFirstName;
import com.foxminded.yuri.school.parameters.ParamStudentId;
import com.foxminded.yuri.school.parameters.ParamStudentLastName;
import com.foxminded.yuri.school.parameters.Parameter;
import com.foxminded.yuri.school.service.SchoolService;

public class SchoolApp {




	public static void main(String[] args) {

		ParamStudentId paramStudentId = new ParamStudentId("Enter student Id");
		ParamStudentFirstName paramStudentFirstName = new ParamStudentFirstName("Enter student first name");
		ParamStudentLastName paramStudentLastName = new ParamStudentLastName("Enter student last name");
		ParamGroupId paramGroupId = new ParamGroupId("Enter group Id");

		List<Parameter> addStudentParameters = Arrays.asList(paramStudentId, paramStudentFirstName,
				paramStudentLastName, paramGroupId);
		CommandAddStudent commandAddStudent = new CommandAddStudent("ADD_STUDENT", addStudentParameters);
		Map<String, Command> commands = Map.of(commandAddStudent.getName(), commandAddStudent);
		CommandFactory commandFactory = new CommandFactory(commands);

//			DatabaseInitializer.initialize();
//			TestDataGenerator.generate();

		System.out.println(viewCommands(commands.values()));
		consoleInteraction(commandFactory);
	}

	private static void consoleInteraction(CommandFactory commandFactory) {

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
					Command command = commandFactory.createCommand(input);
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

//	static Command parseCommand(String command) {
//		try {
//			return Command.valueOf(command.toUpperCase());
//		} catch (IllegalArgumentException e) {
//			throw new IllegalArgumentException("Unknown command \"" + command + "\".");
//		}
//	}

	public static String viewCommands(Collection<Command> commands) {

		StringBuilder description = new StringBuilder();

		// TODO get ride of magic numbers(calcCommandsMaxLength, calcParamsMaxLength)
		description.append("-".repeat(80)).append("\n");
		description.append(String.format("| %-27s | %-48s |", "Command", "Description")).append("\n");
		description.append("-".repeat(80)).append("\n");

		commands.forEach(c -> description.append(String.format("| %-27s | %-48s |", c.getName(), c.getDescription()))
				.append("\n"));
		description.append("-".repeat(80)).append("\n");
		return description.toString();
	}
}
