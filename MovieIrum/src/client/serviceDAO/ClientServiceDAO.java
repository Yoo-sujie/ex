package client.serviceDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;

import client.clientVO.ClientVO;
import client.serviceVO.ClientCategoryVO;
import client.serviceVO.ClientServiceVO;
import dBConn.DBConn;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dBConn.DBConn;

public class ClientServiceDAO {
	
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	public ClientServiceDAO() throws ClassNotFoundException, SQLException {
		con = new DBConn().getConnection();
	}
	
	public void deleteService(String id) {
		String sql = "DELETE FROM CLIENT_SERVICE WHERE CLIENT_ID=?";
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//고객센터 글쓰기 버튼 누르기 전 미리 카테고리 조회
		public ArrayList<ClientCategoryVO> searchCategory() throws SQLException {
			String sql = "SELECT category_name FROM CATEGORY";

			ArrayList<ClientCategoryVO> carray = new ArrayList<ClientCategoryVO>();
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				String categoryName = rs.getString("category_name");
				ClientCategoryVO cvo = new ClientCategoryVO(categoryName);
				carray.add(cvo);
			}
			return carray;

		}

		//고객 센터 클릭 시 글 목록 가져오기(ClientListForm.jsp로 )
		public ArrayList<ClientServiceVO> getServiceList() {
			String sql = "select s.client_id, s.claim_num, s.client_title, s.client_content, s.client_claim_date, s.claim_pw, c.category_name" + 
					" from client_service s join category c on (s.category_num = c.category_num)"
					+ " order by claim_num desc";
			//여기서 client_id는 세션에 담긴 아이디를 넣어줘야 한다
			//ClientController에서 로그인 유저 id를 ClientServiceVO로 보내줬음
			ArrayList<ClientServiceVO> serviceList = new ArrayList<>();
			
			try {
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				while(rs.next()) { //select 한 값을 가져오는것
					ClientServiceVO scvo = new ClientServiceVO(
							rs.getString("client_id"),
							rs.getInt("claim_num"),
							rs.getString("category_name"),
							rs.getString("client_title"),
							rs.getString("client_claim_date"),
							rs.getString("claim_pw")
					);
					serviceList.add(scvo);
					
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			return serviceList;
		}

		
		//clientWrittenboard.jsp 에서 내작성글 가져오기
		public HashMap<String, Object> getServiceForm(int param) {
			// TODO Auto-generated method stub
			
			HashMap<String, Object> result = null;
			String sql = "SELECT s.client_id, s.claim_num, c.category_name, s.client_title, s.client_content, s.client_claim_date, s.claim_pw "
					+ " FROM CLIENT_SERVICE S JOIN CATEGORY C ON (s.category_num = c.category_num) WHERE s.claim_num = ?";
			try {
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, param);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					result = new HashMap<String,Object>();
					result.put("client_id", rs.getString("client_id"));
					result.put("claim_num", "" + rs.getInt("claim_num"));
					result.put("category_name", rs.getString("category_name"));
					result.put("client_title", rs.getString("client_title"));
					result.put("client_content", rs.getString("client_content"));
					result.put("client_claim_date", rs.getString("client_claim_date"));
					result.put("claim_pw", rs.getString("claim_pw"));
				}
			} catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
			return result;
		}

		//고객센터 글쓰기 버튼 클릭 시 insert
		public void insertClaim(Map<String, Object> map) throws SQLException {
			
			String sql = "insert into client_service values(?,(select nvl(max(claim_num)+1,1)from client_service),"
					+ " (select category_num from category where category_name = ?),?,?,sysdate,?,null)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,(String)map.get("id"));
			pstmt.setString(2,(String)map.get("category"));
			pstmt.setString(3,(String)map.get("title"));
			pstmt.setString(4,(String)map.get("content"));
			pstmt.setString(5,(String)map.get("pw"));
			
			pstmt.executeUpdate();
			
		}

		//고객센터 비밀번호 가져옴
			public Map<String, Object> getClaimpw(Map<String, Object> param1) {
				Map<String, Object> result = new HashMap<String,Object>();
				String sql = "select claim_pw from client_service where claim_num = ? ";
				
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, Integer.parseInt((String)param1.get("num")));
					rs = pstmt.executeQuery();
					
					while(rs.next()) {
						
						result.put("claim_pw", rs.getString("claim_pw"));
					}
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				
				return result;
			}
		
			//고객센터 글 삭제
			public int getRemoveClaim(int claim_num2) {
				String sql = "DELETE FROM client_service WHERE claim_num = ?";
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, claim_num2);
					int deleteCnt = pstmt.executeUpdate();
					return deleteCnt;
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				return 0;
			}

			//글 삭제 시 전체 글 번호 새로 정렬
			public int getUpdateClaimNum(int claim_num2) {
				String sql = "UPDATE CLIENT_SERVICE SET CLAIM_NUM=CLAIM_NUM-1 WHERE CLAIM_NUM > ?";
				try {
					pstmt = con.prepareStatement(sql);
					pstmt.setInt(1, claim_num2);
					int updateCnt = pstmt.executeUpdate();
					return updateCnt;
				} catch(Exception e) {
					System.out.println(e.getMessage());
				}
				return 0;
			}


		//update
		public void updateClaim(Map<String, Object> map) throws SQLException {
			
			String sql = "update client_service set category_num=(select category_num from category c  where c.category_name = ?), "
					+ " client_title=?, client_content=?, claim_pw=? where claim_num=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setString(1,(String)map.get("category"));
			pstmt.setString(2,(String)map.get("title"));
			pstmt.setString(3,(String)map.get("content"));
			pstmt.setString(4,(String)map.get("pw"));
			pstmt.setInt(5,Integer.parseInt((String)map.get("num")));
			
			pstmt.executeUpdate();
		}

	}