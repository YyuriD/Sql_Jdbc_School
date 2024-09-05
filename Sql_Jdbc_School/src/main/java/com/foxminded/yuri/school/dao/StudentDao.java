package com.foxminded.yuri.school.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.foxminded.yuri.school.model.Student;

public class StudentDao {

	private static final Logger logger = LogManager.getLogger(StudentDao.class);
	private static final String CREATE = "INSERT INTO students(student_id, first_name, last_name, group_id) VALUES (?,?,?,?)";
	private static final String REMOVE = "DELETE FROM students WHERE student_id = ?";
	private static final String FIND_BY_ID = "SELECT student_id, first_name, last_name, group_id FROM students WHERE student_id = ?";
	private static final String IS_EXISTS = "SELECT EXISTS (SELECT 1 FROM students WHERE student_id = ?)";
	private static final String FIND_BY_COURSE = """
			SELECT s.student_id, s.first_name, s.last_name, s.group_id
			FROM students s
			JOIN students_courses sc ON s.student_id = sc.student_id
			WHERE sc.course_id = ?
			""";

	public int create(Student student) throws DAOException {
		logger.debug("Adding new student with id {}" + student.getId());
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(CREATE)) {
			statement.setInt(1, student.getId());
			statement.setString(2, student.getFirstName());
			statement.setString(3, student.getLastName());
			statement.setInt(4, student.getGroupId());
			return statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while adding student with id {}", student.getId(), e);
			throw new DAOException("Can't add student with id " + student.getId(), e);
		}
	}

	public int remove(Integer studentId) throws DAOException {
		logger.debug("Removing student with id {}", studentId);
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(REMOVE)) {
			statement.setInt(1, studentId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while removing student with id {}", studentId, e);
			throw new DAOException("Can't remove student with id " + studentId, e);
		}
	}

	public Student findById(Integer studentId) throws DAOException {
		logger.debug("Getting student with id {}", studentId);
		Student student = null;
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(FIND_BY_ID)) {
			statement.setInt(1, studentId);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					student = new Student(result.getInt("student_id"), result.getString("first_name"),
							result.getString("last_name"), result.getInt("group_id"));
					logger.debug("Student found: {}", student);
				} else {
					logger.debug("No student found with id {}", studentId);
				}
			}
		} catch (SQLException e) {
			logger.error("Error while getting student with id {}", studentId, e);
			throw new DAOException("Can't get student with id " + studentId, e);
		}
		return student;
	}

	public boolean isExists(Integer studentId) throws DAOException {
		logger.debug("Checking is student exists with id {}", studentId);
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(IS_EXISTS)) {
			statement.setInt(1, studentId);
			try (ResultSet result = statement.executeQuery()) {
				if (result.next()) {
					return result.getBoolean(1);
				}
			}
		} catch (SQLException e) {
			logger.error("Error while checking is student exists with id {}", studentId, e);
			throw new DAOException("Can't check is student exists with id " + studentId, e);
		}
		return false;
	}

	public List<Student> findByCourse(Integer courseId) throws DAOException {
		logger.debug("Finding students by course id {}", courseId);
		List<Student> students = new ArrayList<>();
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(FIND_BY_COURSE)) {
			statement.setInt(1, courseId);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					Student student = new Student(result.getInt("student_id"), result.getString("first_name"),
							result.getString("last_name"), result.getInt("group_id"));
					students.add(student);
					logger.debug("Student found: {}", student);
				}
			}
		} catch (SQLException e) {
			logger.error("Error while finding students by course id {}", courseId, e);
			throw new DAOException("Can't find students by course id " + courseId, e);
		}
		return students;
	}

}
