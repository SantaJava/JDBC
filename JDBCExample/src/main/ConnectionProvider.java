package main;

import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionProvider {
	public static Connection conn;
	
	public static Properties getDatabaseProperty() throws Exception{
		Properties properties = new Properties();
		String path = ConnectionProvider.class.getResource("database.properties").getPath();
		properties.load(new FileReader(path));
		
		return properties;		
	}

	static {
		try {
			Properties properties = getDatabaseProperty();
			String url = properties.getProperty("url");
			String username = properties.getProperty("username");
			String password = properties.getProperty("password");
			conn = (Connection) DriverManager.getConnection(url, "iot", "1234");
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static Connection getConnection() {
		return conn;
	}

	public static void close() {
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (Exception se) {
		}
	}
}
