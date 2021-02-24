package store.storeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dBConn.DBConn;

public class StoreDAO {
	
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public StoreDAO() throws ClassNotFoundException, SQLException {
		con = new DBConn().getConnection();
	}
	
}
