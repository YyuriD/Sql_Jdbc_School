package com.foxminded.yuri.school.utils;

import java.util.List;

import com.foxminded.yuri.school.model.Student;

public class DataConverter {

	public static Integer convertToInt(String input) throws DataConvertException {
		try {
			return Integer.parseInt(input);
		} catch (NumberFormatException e) {
			throw new DataConvertException("Error: parameter must be number.", e);
		}
	}

	public static Student convertStudent(String input) throws DataConvertException {
		String[] parts = convertToStringArray(input);

		try {
			if (parts.length != Student.FIELDS_QUANTITY || parts[1].isBlank() || parts[2].isBlank()) {
				throw new IllegalArgumentException("Wrong parameters.");
			}
			Integer id = convertToInt(parts[0]);
			String firstName = parts[1];
			String lastName = parts[2];
			Integer groupId = convertToInt(parts[3]);
			return new Student(id, firstName, lastName, groupId);
		} catch (IllegalArgumentException e) {
			throw new DataConvertException("Error: fail to convert to student object.", e);
		}
	}

	public static String[] convertToStringArray(String input) throws DataConvertException {
		if (input == null) {
			throw new DataConvertException("Error: fail to convert to string array.");
		}
		final String DELIMETER = ",";
		String[] parts = input.split(DELIMETER);
		for (String part : parts) {
			part = part.trim();
		}
		return parts;
	}

	public static Integer[] convertToIntegerArray(String input) throws DataConvertException {
		if (input == null) {
			throw new DataConvertException("Error: fail to convert to integer array.");
		}
		String[] stringParts = convertToStringArray(input);
		Integer[] intArray = new Integer[stringParts.length];

		for (int i = 0; i < stringParts.length; i++) {
			intArray[i] = convertToInt(stringParts[i]);
		}
		return intArray;
	}

	public static <E> String convertFromListToString(List<E> inputList) throws DataConvertException {
		if (inputList == null) {
			throw new DataConvertException("Error: fail to convert to integer array.");
		}
		StringBuilder result = new StringBuilder();
		for (E item : inputList) {
			result.append(item.toString()).append("\n");
		}
		return result.toString();
	}

}
