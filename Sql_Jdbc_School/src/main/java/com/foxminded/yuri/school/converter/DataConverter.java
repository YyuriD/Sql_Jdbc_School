package com.foxminded.yuri.school.converter;

import java.util.Arrays;
import java.util.List;

import com.foxminded.yuri.school.model.Student;

public class DataConverter {

	public static Integer convertToInt(String input) {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Student convertStudent(String input) {
		String[] parts = convertToStringArray(input);
		if (parts.length != Student.FIELDS_QUANTITY) {
			return null;
		}
		Integer id = convertToInt(parts[0]);
		String firstName = parts[1];
		String lastName = parts[2];
		Integer groupId = convertToInt(parts[3]);
		return new Student(id, firstName, lastName, groupId);
	}

	public static String[] convertToStringArray(String input) {
		String[] parts = input.split(",");
		for (String part : parts) {
			part = part.trim();
		}
		return parts;
	}

	public static Integer[] convertToIntegerArray(String input) {
		String[] stringParts = convertToStringArray(input);
		return Arrays.stream(stringParts).map(p -> convertToInt(p)).toArray(Integer[]::new);
	}

	public static <E> String convertFromListToString(List<E> list) {
		StringBuilder result = new StringBuilder();
		for (E item : list) {
			result.append(item.toString()).append("\n");
		}
		return result.toString();
	}

}
