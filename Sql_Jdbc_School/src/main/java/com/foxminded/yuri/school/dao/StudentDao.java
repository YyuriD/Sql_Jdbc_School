package com.foxminded.yuri.school.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.foxminded.yuri.school.model.Student;

public class StudentDao {

	public void add(Student student) throws DAOException {
		String sql = "INSERT INTO students(student_id, first_name, last_name, group_id) VALUES (?,?,?,?)";

		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(sql)) {
			statement.setInt(1, student.getId());
			statement.setString(2, student.getFirstName());
			statement.setString(3, student.getLastName());
			statement.setInt(4, student.getGroupId());
			statement.executeUpdate();
			DatabaseConnector.getConnection().close();
		} catch (SQLException e) {
			throw new DAOException("Can't create student.", e);
		}
	}
}
