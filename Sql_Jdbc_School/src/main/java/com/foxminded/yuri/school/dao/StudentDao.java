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
	private static final String GET = "SELECT 1 FROM students WHERE student_id = ?";

	public void add(Student student) throws DAOException {
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(ADD)) {
			logger.debug("Adding new student with id " + student.getId());
			statement.setInt(1, student.getId());
			statement.setString(2, student.getFirstName());
			statement.setString(3, student.getLastName());
			statement.setInt(4, student.getGroupId());
			statement.executeUpdate();
			logger.debug("Student was added!");
		} catch (SQLException e) {
			logger.warn("Can't add student.");
			throw new DAOException("Can't add student.", e);
		}
	}

	public boolean remove(Integer studentId) throws DAOException {
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(REMOVE)) {
			logger.debug("Removing student with id ", studentId);
			statement.setInt(1, studentId);
			int rowsAffected = statement.executeUpdate();
			if (rowsAffected > 0) {
				logger.debug("Student was removed!");
				return true;
			} else {
				logger.debug("Student don't exists!");
				return false;
			}
		} catch (SQLException e) {
			logger.warn("Can't remove student.");
			throw new DAOException("Can't remove student.", e);
		}
	}

	public Student get(Integer studentId) throws DAOException {
		Student student = null;
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(GET)) {
			logger.debug("Trying to get student with id ", studentId);
			statement.setInt(1, studentId);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					student = new Student(result.getInt("student_id"), result.getString("first_name"),
							result.getString("last_name"), result.getInt("group_id"));
				}
			} catch (SQLException e) {
				throw new DAOException("Can't check is student exists.", e);
			}
		} catch (SQLException e) {
			throw new DAOException("Can't check is student exists.", e);
		}
		return student;
	}
}
