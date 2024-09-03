package com.foxminded.yuri.school.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.foxminded.yuri.school.model.Student;

public class StudentDao {

	private static final String ADD = "INSERT INTO students(student_id, first_name, last_name, group_id) VALUES (?,?,?,?)";

	public void add(Student student) throws DAOException {

		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(ADD)) {
			statement.setInt(1, student.getId());
			statement.setString(2, student.getFirstName());
			statement.setString(3, student.getLastName());
			statement.setInt(4, student.getGroupId());
			statement.executeUpdate();
			DatabaseConnector.getConnection().close();
		} catch (SQLException e) {
			throw new DAOException("Can't add student.", e);
		}
	}
}
