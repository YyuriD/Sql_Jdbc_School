package com.foxminded.yuri.school.dbinit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.foxminded.yuri.school.dao.DatabaseConnector;

public class TestDataGenerator {

	private static final Logger logger = LogManager.getLogger(TestDataGenerator.class);
	private static final String CREATE_GROUP = "INSERT INTO groups(group_name) VALUES(?)";
	private static final String CREATE_COURS = "INSERT INTO courses(course_name, course_description) VALUES(?,?)";
	private static final String CREATE_STUDENT = "INSERT INTO students(first_name, last_name, group_id) VALUES(?,?,?)";
	private static final String ADD_STUDENT_TO_COURSE = """
			INSERT INTO students_courses(student_id, course_id) VALUES (?, ?)
			ON CONFLICT (student_id, course_id) DO NOTHING
			""";

	private static Random random = new Random();

	public static void generate() {
		logger.debug("Generating test data, filling the database...");
		try (Connection connection = DatabaseConnector.getConnection()) {
			generateGroups(connection);
			generateCourses(connection);
			generateStudents(connection);
			addStudentsToCourses(connection);
			System.out.println("Successful!");
		} catch (SQLException e) {
			logger.error(" Can't generate test data and fill the database. ", e);
		}

	}

	private static void generateGroups(Connection connection) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(CREATE_GROUP)) {
			for (int i = 0; i < 10; i++) {
				statement.setString(1, "GR-" + (char) (65 + i) + "-" + (random.nextInt(90) + 10));
				statement.executeUpdate();
			}
		}
	}

	private static void generateCourses(Connection connection) throws SQLException {
		String[] courses = { "Math", "Biology", "Chemistry", "Physics", "History", "Geography", "English",
				"Computer Science", "Music", "Art" };
		try (PreparedStatement statement = connection.prepareStatement(CREATE_COURS)) {
			for (String course : courses) {
				statement.setString(1, course);
				statement.setString(2, course + " description.");
				statement.executeUpdate();
			}
		}
	}

	private static void generateStudents(Connection connection) throws SQLException {
		String[] firstNames = { "John", "Jane", "James", "Jill", "Jack", "Judy", "Joe", "Joan", "Jeff", "Julia" };
		String[] lastNames = { "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
				"Rodriguez", "Martinez" };
		try (PreparedStatement statement = connection.prepareStatement(CREATE_STUDENT)) {
			for (int i = 0; i < 200; i++) {
				statement.setString(1, firstNames[random.nextInt(firstNames.length)]);
				statement.setString(2, lastNames[random.nextInt(lastNames.length)]);
				statement.setInt(3, random.nextInt(10) + 1);
				statement.executeUpdate();
			}
		}
	}

	private static void addStudentsToCourses(Connection connection) throws SQLException {
		try (PreparedStatement statement = connection.prepareStatement(ADD_STUDENT_TO_COURSE)) {
			for (int i = 1; i <= 200; i++) {
				int numberOfCourses = random.nextInt(3) + 1;
				for (int j = 0; j < numberOfCourses; j++) {
					int randomCourseId = random.nextInt(10) + 1;
					statement.setInt(1, i);
					statement.setInt(2, randomCourseId);
					statement.executeUpdate();
				}
			}
		}
	}

}
