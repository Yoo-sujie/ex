package chat.user.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

import chat.message.MsgeBox;

public class DBDelete {
	MsgeBox msgbox = new MsgeBox();

	String id = null;

	Statement stmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String sql = null;
	Properties info = null;
	Connection con = null;

	public int InfoDel(String id) {
		int result = 0;
		this.id = id;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			info = new Properties();
			info.setProperty("user", "chat");
			info.setProperty("password", "oracle");
			con = DriverManager.getConnection(url, info);
			stmt = con.createStatement();

			sql = "delete from chat.users where user_id='" + id + "'";
			stmt.executeUpdate(sql);

			sql = "select * from chat.users where user_id='" + id + "'";
			rs = stmt.executeQuery(sql);
			if (rs.next() == true) {
				result = 0;
			} else {
				result = 1;
			}
		} catch (Exception e) {
			System.out.println("Error...");
			e.printStackTrace();
		}

		return result;
	}

}
