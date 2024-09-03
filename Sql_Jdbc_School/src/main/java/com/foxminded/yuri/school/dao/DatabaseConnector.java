package com.foxminded.yuri.school.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

	private static final String DATABASE_URL = "jdbc:postgresql://ruby.db.elephantsql.com:5432/tdskpstb";
	private static final String USERNAME = "tdskpstb";
	private static final String PASSWORD = "ImhEnUOsGpwCyNj_2Vsax1YR7sIXOL12";
	private static Connection DATABASE_CONNECTION;

	public DatabaseConnector() {
	}

	public static Connection getConnection() throws SQLException {
		try {
			if (DATABASE_CONNECTION == null || DATABASE_CONNECTION.isClosed()) {
				DATABASE_CONNECTION = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
				System.out.println("Connection established.");
			}

		} catch (SQLException e) {
			System.err.println("Connection failed: " + e.getMessage());
			throw e;
		}
		return DATABASE_CONNECTION;
	}
}
