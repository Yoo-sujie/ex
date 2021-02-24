package pdm.view.ctmmanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import pdm.connection.DBConn;
import pdm.vo.VOCustomer;

public class CtmViewDAO {
	private Connection con;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public CtmViewDAO() throws ClassNotFoundException, SQLException {	
		con = new DBConn().getConnection();

	}

	//거래처 등록 버튼 클릭 시, db에 값 insert해줌
	public int getInsert(String customer_code, String customer_name, String customer_tel, String customer_address, String customer_email, String customer_description) {

		try {
			String sql = "INSERT INTO COMPANY_INFO(company_code, company_name, company_tel, company_address, company_email, company_description) VALUES (?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer_code);
			pstmt.setString(2, customer_name);
			pstmt.setString(3, customer_tel);
			pstmt.setString(4, customer_address);
			pstmt.setString(5, customer_email);
			pstmt.setString(6, customer_description);
			int insertCnt = pstmt.executeUpdate();

			if(insertCnt > 0) {
				pstmt.close();
				return insertCnt;
			}
		} catch(Exception e) {
			e.printStackTrace();
			return -1;
		}
		return -1;

	}

	//거래처 검색 시 정렬 기준 만듦
	public ArrayList<VOCustomer> getSelectedSort(String target, String searchText, String orderBy, String orderValue) {
		ArrayList<VOCustomer> ctmr = new ArrayList<VOCustomer>();
		
		try {
			String sql = "SELECT * FROM COMPANY_INFO WHERE "+ target + " like '%' || ? || '%'"
					+ " ORDER BY " + orderValue + " " + orderBy;
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, searchText);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOCustomer ctr = new VOCustomer();
				ctr.setCustomer_code(rs.getString(1));
				ctr.setCustomer_name(rs.getString(2));
				ctr.setCustomer_email(rs.getString(3));
				ctr.setCustomer_tel(rs.getString(4));
				ctr.setCustomer_address(rs.getString(5));
				ctr.setCustomer_info(rs.getString(6));
				ctmr.add(ctr);
			}
			return ctmr;
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return ctmr;
		
		
	}
	
	//거래처 전체 조회 시 정렬 기준 만듦
	public ArrayList<VOCustomer> getLookUp(String orderValue, String orderBy) {
		ArrayList<VOCustomer> ctm = new ArrayList<VOCustomer>();

		try {
			String sql = "SELECT COMPANY_CODE, COMPANY_NAME, company_email, company_tel, company_address, company_description "
					+ "FROM COMPANY_INFO ORDER BY " + orderValue +" "+ orderBy;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				VOCustomer cu = new VOCustomer();
				cu.setCustomer_code(rs.getString(1));
				cu.setCustomer_name(rs.getString(2));
				cu.setCustomer_email(rs.getString(3));
				cu.setCustomer_tel(rs.getString(4));
				cu.setCustomer_address(rs.getString(5));
				cu.setCustomer_info(rs.getString(6));
				ctm.add(cu);
			}
			return ctm;
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return ctm;

	}

	//거래처 정보 조회해서 수정 버튼 누르면 db에 값 update시켜줌.
	public int getUpdate(String customer_code, String customer_name, String customer_email, String customer_tel, 
			String customer_address, String customer_info) {

		try {
			String sql = "UPDATE COMPANY_INFO SET company_name = ?, company_tel = ?, company_email = ?, company_address = ?, company_description = ?"
					+ " WHERE company_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ""+customer_name);
			pstmt.setString(2, ""+customer_tel);
			pstmt.setString(3, ""+customer_email);
			pstmt.setString(4, ""+customer_address);
			pstmt.setString(5, ""+customer_info);
			pstmt.setString(6, customer_code);
			int updateCnt = pstmt.executeUpdate();
			return updateCnt;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}

	//거래처 정보 조회해서 삭제 버튼 누르면 db에 값 delete시켜줌.
	public int getDelete(String customer_code) {
		try {

			String sql = "DELETE FROM COMPANY_INFO WHERE company_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, customer_code);
			int deleteCnt = pstmt.executeUpdate();
			return deleteCnt;
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}


}
