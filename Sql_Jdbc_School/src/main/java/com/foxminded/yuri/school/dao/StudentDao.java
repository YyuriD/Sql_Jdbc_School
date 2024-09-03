package com.foxminded.yuri.school.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.foxminded.yuri.school.model.Student;

public class StudentDao {

	private static final Logger logger = LogManager.getLogger(StudentDao.class);
	private static final String ADD = "INSERT INTO students(student_id, first_name, last_name, group_id) VALUES (?,?,?,?)";
	private static final String REMOVE = "DELETE FROM students WHERE student_id = ?";
	private static final String GET = "SELECT student_id, first_name, last_name, group_id FROM students WHERE student_id = ?";

	public int add(Student student) throws DAOException {
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(ADD)) {
			logger.debug("Adding new student with id " + student.getId());
			statement.setInt(1, student.getId());
			statement.setString(2, student.getFirstName());
			statement.setString(3, student.getLastName());
			statement.setInt(4, student.getGroupId());
			return statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while adding student with id {}", student.getId());
			throw new DAOException("Can't add student with id " + student.getId(), e);
		}
	}

	public int remove(Integer studentId) throws DAOException {
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(REMOVE)) {
			logger.debug("Removing student with id ", studentId);
			statement.setInt(1, studentId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while removing student with id {}", studentId);
			throw new DAOException("Can't remove student with id " + studentId, e);
		}
	}

	public Student get(Integer studentId) throws DAOException {
		Student student = null;
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET)) {
			logger.debug("Getting student with id ", studentId);
			statement.setInt(1, studentId);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					student = new Student(result.getInt("student_id"), result.getString("first_name"),
							result.getString("last_name"), result.getInt("group_id"));
					logger.debug("Student found: {}", student);
				} else {
					logger.warn("No student found with id {}", studentId);
				}
			}
		} catch (SQLException e) {
			logger.error("Error while getting student with id {}", studentId);
			throw new DAOException("Can't get student with id " + studentId, e);
		}
		return student;
	}
}
