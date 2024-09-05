package com.foxminded.yuri.school.dao;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DatabaseInitializer {
	public static void initialize() throws DBInitException {
		String sql;
		try {
			sql = new String(Files.readAllBytes(Paths.get("src/main/resources/db/schema.sql")));
		} catch (IOException e) {
			throw new DBInitException("Can't read initialization file. ", e);
		}
		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {
			statement.executeUpdate();
		} catch (Exception e) {
			throw new DBInitException("Can't initialize database", e);
		}
	}
}
