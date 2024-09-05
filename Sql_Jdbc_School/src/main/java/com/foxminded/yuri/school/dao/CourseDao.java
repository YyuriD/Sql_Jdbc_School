package com.foxminded.yuri.school.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CourseDao {

	private static final Logger logger = LogManager.getLogger(CourseDao.class);
	private static final String ADD_STUDENT_TO_COURSE = """
			INSERT INTO students_courses (student_id, course_id) VALUES (?, ?)
			""";
	private static final String REMOVE_STUDENT_FROM_COURSE = """
			DELETE FROM students_courses
			WHERE student_id = ? AND course_id = ?
			""";

	public int addStudentToCourse(Integer studentId, Integer courseId) throws DAOException {
		logger.debug("Adding student with id {0} to course with id {1} " + studentId, courseId);
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(ADD_STUDENT_TO_COURSE)) {
			statement.setInt(1, studentId);
			statement.setInt(2, courseId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while adding student with id {} to course with id {} ", studentId, courseId, e);
			throw new DAOException("Can't add student with id " + studentId + " to course with id " + courseId, e);
		}
	}

	public int removeStudentFromCourse(Integer studentId, Integer courseId) throws DAOException {
		logger.debug("Removing student with id {} from course with id {}", studentId, courseId);
		try (PreparedStatement statement = DatabaseConnector.getConnection()
				.prepareStatement(REMOVE_STUDENT_FROM_COURSE)) {
			statement.setInt(1, studentId);
			statement.setInt(2, courseId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while removing student with id {} from course with id {}", studentId, courseId, e);
			throw new DAOException("Can't remove student with id " + studentId + " from course with id " + courseId, e);
		}
	}

}
