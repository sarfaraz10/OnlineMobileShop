package databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataBaseConnection {
	public static Connection createConnection() {
		Connection connection = null;
		String url = "jdbc:mysql://localhost:3306/onlinemobileshop"; // MySQL URL followed by the database name
		String username = "root"; // MySQL username
		String password = "9363221141"; // MySQL password

		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); // loading MySQL drivers. This differs for database servers
			connection = DriverManager.getConnection(url, username, password); // attempting to connect to MySQL database
		} catch (Exception exception) {
			System.out.println(exception);
		}
		return connection;
	}
}
