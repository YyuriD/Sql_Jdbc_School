package com.foxminded.yuri.school;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.foxminded.yuri.school.commands.Command;

public class SchoolApp {

	public static void main(String[] args) {

		System.out.println(getMenuView());
		processMenu();
	}

	private static void processMenu() {
		final int INPUT_PARTS_NUM = 2;

		try (Scanner scanner = new Scanner(System.in)) {
			Command currentCommand = null;

			while (Command.EXIT != currentCommand) {
				System.out.print("enter command-> ");
				String input = scanner.nextLine().trim();

				if (input.isEmpty()) {
					System.out.println("Input cannot be empty. Please enter a command.");
					continue;
				}

					String[] parts = input.split(" ", INPUT_PARTS_NUM);
					currentCommand = parseCommand(parts[0]);

					if (Command.EXIT == currentCommand) {
						System.out.println(currentCommand.execute("Good bye!"));
						continue;
					}

					String message = parts.length < INPUT_PARTS_NUM ? currentCommand.execute("")
							: currentCommand.execute(parts[1]);
					System.out.println(message);
			}
		}
	}

	private static Command parseCommand(String command) {
		try {
			return Command.valueOf(command.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Unknown command \"" + command + "\".");
		}
	}

	public static String getMenuView() {
		List<Command> commands = Arrays.asList(Command.values());
		StringBuilder menuBuilder = new StringBuilder();

		// TODO get ride of magic numbers(calcCommandsMaxLength, calcParamsMaxLength)
		menuBuilder.append("-".repeat(80)).append("\n");
		menuBuilder.append(String.format("| %-27s | %-48s |", "Command", "Parameters description")).append("\n");
		menuBuilder.append("-".repeat(80)).append("\n");

		commands.forEach(c -> menuBuilder.append(String.format("| %-27s | %-48s |", c.name(), c.parameterDiscription))
				.append("\n"));

		menuBuilder.append("-".repeat(80)).append("\n");
		return menuBuilder.toString();
	}
}
