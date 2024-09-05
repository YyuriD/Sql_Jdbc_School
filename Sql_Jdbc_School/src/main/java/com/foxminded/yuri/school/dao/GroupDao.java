package com.foxminded.yuri.school.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.foxminded.yuri.school.model.Group;

public class GroupDao {

	private static final Logger logger = LogManager.getLogger(GroupDao.class);
	private static final String CREATE = "INSERT INTO groups(group_id, group_name) VALUES (?,?)";
	private static final String REMOVE = "DELETE FROM groups WHERE group_id = ?";
	private static final String FIND_BY_MAX_STUDENT = """
			SELECT g.group_id, g.group_name
			FROM groups g
			LEFT JOIN students s ON g.group_id = s.group_id
			GROUP BY g.group_id, g.group_name
			HAVING COUNT(s.student_id) <= ?
			""";

	public int create(Group group) throws DAOException {
		logger.debug("Adding new group with id {}" + group.getId());
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(CREATE)) {
			statement.setInt(1, group.getId());
			statement.setString(2, group.getName());
			return statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while adding group with id {}", group.getId(), e);
			throw new DAOException("Can't add sgroup with id " + group.getId(), e);
		}
	}

	public int remove(Integer groupId) throws DAOException {
		logger.debug("Removing group with id {}", groupId);
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(REMOVE)) {
			statement.setInt(1, groupId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while removing group with id {}", groupId, e);
			throw new DAOException("Can't remove group with id " + groupId, e);
		}
	}

	public List<Group> findByMaxStudent(Integer maxStudent) throws DAOException {
		logger.debug("Finding group with max student {}", maxStudent);
		List<Group> groups = new ArrayList<>();
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(FIND_BY_MAX_STUDENT)) {
			statement.setInt(1, maxStudent);
			try (ResultSet result = statement.executeQuery()) {
				while (result.next()) {
					Group group = new Group(result.getInt("group_id"), result.getString("group_name"));
					groups.add(group);
				}
			}
		} catch (SQLException e) {
			logger.error("Error while finding group with max student {}", maxStudent, e);
			throw new DAOException("Can't find group with max student " + maxStudent, e);
		}
		return groups;
	}
}
