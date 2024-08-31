package com.foxminded.yuri.school;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.foxminded.yuri.school.cli.commands.Command;

public class SchoolApp {

	public static void main(String[] args) {

		viewMenu();
		processCommands();
	}


	private static void processCommands() {
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

				try {
					String[] parts = input.split(" ", INPUT_PARTS_NUM);
					currentCommand = parseCommand(parts[0]);

					if (Command.EXIT == currentCommand) {
						currentCommand.execute("Good bye!");
						continue;
					}

					if (parts.length != INPUT_PARTS_NUM) {
						throw new IllegalArgumentException("Incorrect number of parameters.");
					}

					currentCommand.execute(parts[1]);
				} catch (Exception e) {
					System.out.println("Error: " + e.getMessage());
				}
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

	private static void viewMenu() {
		List<Command> commands = Arrays.asList(Command.values());
		// TODO get ride of magic numbers
		System.out.println("-".repeat(80));
		System.out.println(String.format("| %-27s | %-48s |", "Command", "Parameter discription"));
		System.out.println("-".repeat(80));
		commands.forEach(c -> System.out.println(String.format("| %-27s | %-48s |", c.name(), c.parameterDiscription)));
		System.out.println("-".repeat(80));
	}
}
