package pdm.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConn {
	
	private Connection con;
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String user = "hr1";
	private String pwd = "hr1";
	
	public Connection getConnection() {	
		return con;
	}

	public DBConn() throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		con = DriverManager.getConnection(url, user, pwd);
	}
}
