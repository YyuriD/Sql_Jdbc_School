package com.foxminded.yuri.school.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.foxminded.yuri.school.model.Group;

public class GroupDao {

	private static final Logger logger = LogManager.getLogger(GroupDao.class);
	private static final String CREATE = "INSERT INTO groups(group_id, group_name) VALUES (?,?)";
	private static final String REMOVE = "DELETE FROM groups WHERE group_id = ?";
	private static final String GET = "SELECT group_id, group_name FROM groups WHERE group_id = ?";
	private static final String IS_EXISTS = "SELECT EXISTS (SELECT 1 FROM groups WHERE group_id = ?)";

	public int create(Group group) throws DAOException {
		logger.debug("Adding new group with id {}" + group.getId());
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(CREATE)) {
			statement.setInt(1, group.getId());
			statement.setString(2, group.getName());
			return statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while adding group with id {}", group.getId());
			throw new DAOException("Can't add sgroup with id " + group.getId(), e);
		}
	}

	public int remove(Integer groupId) throws DAOException {
		logger.debug("Removing group with id {}", groupId);
		try (PreparedStatement statement = DatabaseConnector.getConnection().prepareStatement(REMOVE)) {
			statement.setInt(1, groupId);
			return statement.executeUpdate();
		} catch (SQLException e) {
			logger.error("Error while removing group with id {}", groupId);
			throw new DAOException("Can't remove group with id " + groupId, e);
		}
	}

}
