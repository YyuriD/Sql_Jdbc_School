package com.foxminded.yuri.school.dbinit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.foxminded.yuri.school.dao.DatabaseConnector;

public class DatabaseInitializer {
	private static final Logger logger = LogManager.getLogger(DatabaseInitializer.class);

	public static void initialize() {
		logger.debug("Initializing database...");
		String sql = null;
		try {
			sql = new String(Files.readAllBytes(Paths.get("src/main/resources/db/schema.sql")));
		} catch (IOException e) {
			logger.error("Can't read initialization file.", e);
		}
		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.executeUpdate();
		} catch (Exception e) {
			logger.error("Can't initialize database. ", e);
		}
	}
}
