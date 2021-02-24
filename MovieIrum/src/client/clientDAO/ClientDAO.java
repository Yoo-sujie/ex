package client.clientDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.regex.Pattern;

import javax.swing.text.StyledEditorKit.BoldAction;


import client.clientVO.ClientVO;
import dBConn.DBConn;

public class ClientDAO {
	
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ClientDAO() throws ClassNotFoundException, SQLException {
		con = new DBConn().getConnection();
	}
	
	public int selectLevel(String id) {
		String sql = "SELECT CLIENT_LEVEL FROM CLIENT WHERE CLIENT_ID=?";
		int level = 1;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				level = rs.getInt("CLIENT_LEVEL");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return level;
	}

	public int selectPoint(String id) {
		String sql = "SELECT CLIENT_POINT FROM CLIENT WHERE CLIENT_ID=?";
		int num = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt("CLIENT_POINT");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}
	
	public void updatePoint(String pm, int point, String id) {
		String sql = "UPDATE CLIENT SET CLIENT_POINT=(SELECT CLIENT_POINT FROM CLIENT WHERE CLIENT_ID = ?)" + pm + point + " WHERE CLIENT_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

		public boolean memberJoin(String id, String pw, String name, String sDate,
				 String email,String tel) { //�쉶�썝媛��엯
			String sql = "INSERT INTO CLIENT VALUES(?, ?, ?, ?, ?, ?,1,SYSDATE,1000)";
			boolean flag = false;
			
			try {
				int year = Integer.parseInt(sDate.substring(0,4))-1900;
		           int month=Integer.parseInt(sDate.substring(4,6))-1;
		           int day = Integer.parseInt(sDate.substring(6,8));
		         Date d = new Date(year,month,day);
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setString(2, pw);
				pstmt.setString(3, name);
				pstmt.setDate(4, d);
				pstmt.setString(5, email);
				pstmt.setString(6, tel);
				
				
				int cnt = pstmt.executeUpdate();
				if(cnt == 1) {
					flag = true;
				} else {
					flag = false;
				}
			} catch(SQLException e) {
				e.printStackTrace();
			} return flag;
		}//�쉶�썝媛��엯-end
		
		public boolean IdCheck(String id) { //以묐났 �븘�씠�뵒 寃��깋
			boolean result = false;
			try {
				String sql = "SELECT CLIENT_ID FROM MI.CLIENT WHERE CLIENT_ID = ?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					result = true ;
				}
			}catch (Exception e) {
			e.printStackTrace();
			}
			return result;
				}
	//以묐났 �븘�씠�뵒 寃��깋-end
		
		
		public String login(String id1) // 濡쒓렇�씤
	    {
	        String x = "";
			String sql = "SELECT Client_PW FROM Client WHERE Client_ID=?";
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setNString(1, id1);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					x = rs.getString("Client_PW");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return x;
	    } // end loginCheck()
	    

		
		public String idSearch(String name, String email) {//�븘�씠�뵒 李얘린
			String sql = "SELECT CLIENT_ID FROM MI.CLIENT WHERE CLIENT_NAME = ? AND  CLIENT_EMAIL = ?";
			String tempId = null;
			
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, name);
				pstmt.setString(2, email);
				rs = pstmt.executeQuery();
				if(rs.next()) 
					tempId = rs.getString("CLIENT_ID");
			}catch (Exception e) {
					e.printStackTrace();
				}
				return tempId;
			}
			public String pwSearch(String id, String name, String email) {//鍮꾨�踰덊샇 李얘린
				String sql = "SELECT CLIENT_PW FROM MI.CLIENT WHERE CLIENT_ID = ? AND  CLIENT_NAME = ? AND  CLIENT_EMAIL = ?";
				String temppw = null;
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, id);
					pstmt.setString(2, name);
					pstmt.setString(3, email);
					rs = pstmt.executeQuery();
					if(rs.next()) 
						temppw = rs.getString("CLIENT_PW");
					}catch (Exception e) {
						e.printStackTrace();
					}
					return temppw;
				}
		public String pwSel(String id) { //鍮꾨�踰덊샇 鍮꾧탳
			String sql = "select client_pw from client where client_id=?";
			String pw = "";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				if(rs.next()) {
					pw=rs.getString("client_pw");
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return pw;
		}
			
		public int pwChange(String pw, String newPw , String id) {//鍮꾨�踰덊샇 蹂�寃�
			String sql = "UPDATE MI.CLIENT SET CLIENT_PW = ? WHERE CLIENT_ID= ? AND CLIENT_PW=?";
			int num = -1;
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, newPw);
				pstmt.setString(2, id);
				pstmt.setString(3, pw);
				
				num = pstmt.executeUpdate();
				
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return num;
		}
		public ClientVO getclient(String id) {//�쉶�썝�닔�젙1
			ClientVO client = new ClientVO();
			String sql = "SELECT * FROM CLIENT WHERE CLIENT_ID = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					client.setClientId(rs.getString("Client_Id"));
					client.setClientPw(rs.getString("Client_Pw"));
					client.setClientName(rs.getString("Client_Name"));
					client.setClientBirth(rs.getDate("Client_Birth"));
					client.setClientEmail(rs.getString("Client_Email"));
					client.setClientTel(rs.getString("Client_Phone"));
					client.setClientPoint(rs.getInt("Client_Point"));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return client;
		}
		public int Update(String name, String email,String tel,String id) {//�쉶�썝�젙蹂� �닔�젙2
			String sql = "UPDATE MI.CLIENT SET CLIENT_NAME = ? , CLIENT_EMAIL = ?,CLIENT_Phone = ? Where Client_id=?";
					try {
						
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, name);
						pstmt.setString(2, email);
						pstmt.setString(3, tel);
						pstmt.setString(4, id);
						return pstmt.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return -1;
		}
		
		
		public boolean ClientDelete(String id) { //�쉶�썝�깉�눜
			boolean flag = false; 
			try {
						String sql1 = "delete from client where CLIENT_ID=?";
						pstmt = con.prepareStatement(sql1);
						pstmt.setString(1, id);
						int num = pstmt.executeUpdate();
						if(num==1) {
							
							flag = true;
					}
					
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return flag;
		}
		
		
}		