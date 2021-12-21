package Helper;

import java.sql.*;

public class DBConnection {

	Connection c = null;

	public DBConnection() {

	}

	public Connection ConnDb() {
		try {
			this.c = DriverManager.getConnection("jdbc:mariadb://localhost:2532/hospital?user=root&password=syso9697");
			return c;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
}
