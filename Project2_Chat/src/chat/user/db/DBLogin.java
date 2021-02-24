package chat.user.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.swing.JOptionPane;

import chat.user.user.User;

public class DBLogin {

	String id = null;
	String pw = null;

	Statement stmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String sql = null;
	Properties info = null;
	Connection con = null;

	public int checkIDPW(String id, String pw) {
		this.id = id;
		this.pw = pw;
		int result = 1;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			info = new Properties();
			info.setProperty("user", "chat");
			info.setProperty("password", "oracle");
			con = DriverManager.getConnection(url, info); // 연결할 정보를 가지고있는 드라이버매니저를 던진다
			stmt = con.createStatement();

			sql = "select * from chat.users where user_id='" + id + "'";
			rs = stmt.executeQuery(sql); // 읽어오는거라 다르다 비교해	//리턴타입이 ResultSet

			if (rs.next() == false || (id.isEmpty()) == true) { // id가 존재x
				result = 1;
			} else {
				sql = "select * from (select * from chat.users where user_id='" + id + "')";
				rs = stmt.executeQuery(sql);
				while (rs.next() == true) { 		// 다음값의
					if (rs.getString(2).equals(pw)) { // pw와 같은지 비교
						result = 0; 		// 같으면 로그인 성공
					} else {				// 아이디는같고 pw가 다른경우
						result = 1;
					}
				}
			}
		} catch (Exception ee) {
			System.out.println("Error...");
			ee.printStackTrace();
		}
		return result;
	}//checkIDPW-end
	
	////////////////////////////////////////////////////////////////////////////////////////
//	public int loginCheck(UserVO vo) {//로그인 체크
//		String tempId = null;
//		String tempPw = null;
//		int tempLv;
//		int tempSt;
//		int a = -1;
//		String sql = "SELECT * FROM CHAT.USERS WHERE USER_ID = ?";
//
//		try {
//			stmt = con.prepareStatement(sql);
//			stmt.setString(1, vo.getUserID());
//			rs = stmt.executeQuery(sql);
//			while(rs.next()) {
//				tempId = rs.getString("USER_ID");
//				tempPw = rs.getString("USER_PW");
//				tempLv = Integer.parseInt(rs.getString("USER_LEVEL"));
//				tempSt = Integer.parseInt(rs.getString("USER_STATUS"));
//				if(tempId.equals(vo.getUserID())) {
//					if(tempPw.equals(vo.getUserPW())) {
//						if(tempLv == 0) {
//							a = 2; //관리자 로그인 인증 성공
//						} else if(tempLv == 1) {
//							if(tempSt == 0) {
//								a = 1; //일반 회원 인증 성공
//							} else if(tempSt == 1) {
//								a = 3; //일반 회원 정지
//							}
//						}
//					} else {
//						a = 0; //인증 실패 (비밀번호 불일치)
//					}
//				} else {
//					a = -1; //인증 실패 (아이디 없음)
//				}
//			}//while-end 
//		} catch(SQLException e) {
//			e.printStackTrace();
//		} return a;
//	}//로그인 체크-end
//	////////////////////////////////////////////////////////////////////////////////////////
//	try {
//		UserVO vo = new UserVO();
//		UserDAO dao = new UserDAO();
//		vo.setUserID(tf_id.getText());
//		vo.setUserPW(tf_pw.getText());
//		int num = dao.loginCheck(vo);
//		if(tf_id.getText().equals("")) {
//			JOptionPane.showMessageDialog(null, "아이디를 입력해 주세요.", 
//					"아이디 확인", JOptionPane.WARNING_MESSAGE);
//		} else if(tf_pw.getText().equals("")) {
//			JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요.", 
//					"비밀번호 확인", JOptionPane.WARNING_MESSAGE);
//		} else if(num == -1) {
//			JOptionPane.showMessageDialog(null, "존재하지 않는 아이디입니다.",
//					"아이디 확인", JOptionPane.WARNING_MESSAGE);
//		} else if(num == 0) {
//			JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.",
//					"비밀번호 확인", JOptionPane.WARNING_MESSAGE);
//		} else if(num == 3) {
//			JOptionPane.showMessageDialog(null, "이 계정을 사용할 수 없습니다.\n고객센터로 문의해 주세요.", 
//					"사용할 수 없는 계정", JOptionPane.WARNING_MESSAGE);
//		} else if(num == 2) {
//			JOptionPane.showMessageDialog(null, "관리자 계정으로 로그인 되었습니다.");
//			dispose();
//			setVisible(false);
////			new manager.managerView.ManagerMain(vo.getUserID(), 0);
//			new manager.managerView.ManagerMain(vo.getUserID()).setVisible(true);
//		} else if(num == 1) {
//			vo = new UserVO();
////			BoardDAO bDao = new BoardDAO(); //일반 회원 로그인 DAO
//			vo.setUserID(tf_id.getText());
//			vo.setUserPW(tf_pw.getText());
//			dispose();
//			setVisible(false);
////			new UserMemberUpdate(nickname, vo.getUserId()); //일반 회원 화면으로 넘어가는 내용 추가
//		}
//	} catch (ClassNotFoundException | SQLException e1) {
//		e1.printStackTrace();
//	}
	////////////////////////////////////////////////////////////////////////////////////////
	
	String selectNick(String id) {
		this.id = id;
		String nick = "";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			info = new Properties();
			info.setProperty("user", "chat");
			info.setProperty("password", "oracle");
			con = DriverManager.getConnection(url, info);
			stmt = con.createStatement();

			sql = "SELECT USER_NICKNAME FROM CHAT.USERS WHERE USER_ID='" + id + "'";
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) {
				User vo = new User();
				vo.setNickName(rs.getString("USER_NICKNAME"));
				nick = vo.getNickName();
			}
			
		} catch (Exception ee) {
			System.out.println("Error...");
			ee.printStackTrace();
		}
		return nick;
	}
	
}
