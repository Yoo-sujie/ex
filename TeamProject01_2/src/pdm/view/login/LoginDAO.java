package pdm.view.login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import pdm.connection.DBConn;

public class LoginDAO {

	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public LoginDAO() throws ClassNotFoundException, SQLException {	
		con = new DBConn().getConnection();
	}
	
	public boolean getLogin(String serialNum) throws ClassNotFoundException, SQLException {
		
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
        	con = new DBConn().getConnection();
        	
        	String sql = "SELECT * FROM SERIAL_NUM";
        	pstmt = con.prepareStatement(sql);
        	rs = pstmt.executeQuery();
        	
        	rs.next();
        	if (rs.getString(1).equals(serialNum)) {
				return true;
			}
        	else {
        		
        		return false;
        	}
        
        } catch(Exception e) {
        	
        }
        return false;
	}


}
