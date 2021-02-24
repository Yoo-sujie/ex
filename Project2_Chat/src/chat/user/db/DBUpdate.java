package chat.user.db;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Properties;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import chat.client.Client;
import chat.user.db.DBJoin;
import chat.message.MsgeBox;
import chat.sound.ChatSound;
import chat.user.ui.JoinHelpUI;
import chat.user.ui.LoginUI;

public class DBUpdate {
	
	private String id, pw, name, nick, sBirth = null;
	private Date birth;
	
	JFrame frame;
	JPanel logPanel2;
	JTextField idTf, nameTf, nickTf, birthTf = null;
	JPasswordField pwPf1, pwPf2;
	JButton okBtn, btnHelp;
    ImageIcon icon;
	private boolean pwCheck1 = false;
	private boolean pwCheck2 = false;
	private boolean nameCheck = false;
	private boolean nicknameCheck = false;
	private boolean nickCheck = false;
	private Client client;
	private DBJoin dbj;
	
	Statement stmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe";
	String sql = null;
	Properties info = null;
	Connection con = null;

	MsgeBox msgbox = new MsgeBox();

	/**
	 * @wbp.parser.entryPoint
	 */
	public void UpdateDB(String userID) {
		
		this.id = userID;
		dbj = new DBJoin();

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); // 알아서 conn으로 연결
			info = new Properties();
			info.setProperty("user", "chat");
			info.setProperty("password", "oracle");
			con = DriverManager.getConnection(url, info); // 연결할 정보를 가지고 있는 드라이버 매니저를 던진다
			stmt = con.createStatement();

			sql = "select * from chat.users where user_id = '" + userID + "'";
			rs = stmt.executeQuery(sql);

			while (rs.next() == true) { // 다음값의
				pw = rs.getString("user_pw");
				name = rs.getString("user_name");
				nick = rs.getString("user_nickname");
				birth = rs.getDate("user_birth");
			}
		} catch (Exception ee) {
			System.out.println("Error...");
			ee.printStackTrace();
		}
		
		frame = new JFrame("회원 정보 수정");
		logPanel2 = new JPanel();
		logPanel2.setLayout(null);
		
		btnHelp = new JButton(); //도움말
		btnHelp.setBounds(330, 10, 50, 50);
		btnHelp.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/help_btn_img.jpg")).getImage()
				.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { //도움말 클릭
				ChatSound.Audio("Ring.wav");
				new JoinHelpUI().setVisible(true);
			}
		});
		
		idTf = new JTextField(userID);
		idTf.setFont(new Font("굴림",Font.PLAIN,12));
		idTf.setForeground(Color.GRAY);
		idTf.setBounds(120, 193, 150, 25);
		idTf.setEditable(false);
		
		pwPf1 = new JPasswordField();
		pwPf1.setFont(new Font("굴림",Font.PLAIN,12));
		pwPf1.setForeground(Color.GRAY);
		pwPf1.setBounds(120, 243, 150, 25);
		
		pwPf2 = new JPasswordField(); //비밀번호 확인
		pwPf2.setFont(new Font("굴림",Font.PLAIN,12));
		pwPf2.setForeground(Color.GRAY);
		pwPf2.setBounds(120, 293, 150, 25);
		
		JLabel lblPwCheck1 = new JLabel(); //비밀번호 일치 & 불일치
		JLabel lblPwCheck2 = new JLabel(); //비밀번호 일치 & 불일치
		lblPwCheck1.setBounds(275, 293, 26, 26);
		lblPwCheck2.setBounds(295, 293, 60, 26);
		lblPwCheck1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPwCheck2.setHorizontalAlignment(SwingConstants.CENTER);
		
		JLabel lblNickCheck1 = new JLabel(); //닉네임 사용 가능 & 불가능
		JLabel lblNickCheck2 = new JLabel(); //닉네임 사용 가능 & 불가능
		lblNickCheck1.setBounds(275, 343, 26, 26);
		lblNickCheck2.setBounds(295, 343, 60, 26);
		lblNickCheck1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNickCheck2.setHorizontalAlignment(SwingConstants.CENTER);
		
		pwPf1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { //비밀번호 일치 확인(pw1)
				if(pwPf1.getText().equals("") && pwPf2.getText().equals("")) {
					lblPwCheck1.setText(""); lblPwCheck2.setText("");
				} else if(pwPf1.getText().equals(pwPf2.getText())) {
					lblPwCheck1.setText("●");
					lblPwCheck1.setForeground(Color.BLUE);
					lblPwCheck2.setText("success");
				} else if(!pwPf1.getText().equals(pwPf2.getText())) {
					lblPwCheck1.setText("●");
					lblPwCheck1.setForeground(Color.RED);	
					lblPwCheck2.setText("fail");				
				}
			}
		});
		
		pwPf2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { //비밀번호 일치 확인(pw2)
				if(pwPf1.getText().equals("") && pwPf2.getText().equals("")) {
					lblPwCheck1.setText(""); lblPwCheck2.setText("");
				} else if(pwPf1.getText().equals(pwPf2.getText())) {
					lblPwCheck1.setForeground(Color.BLUE);
					lblPwCheck2.setText("success");
				} else if(!pwPf1.getText().equals(pwPf2.getText())) {
					lblPwCheck1.setForeground(Color.RED);	
					lblPwCheck2.setText("fail");				
				}	
			}
		});
		
		
		nickTf = new JTextField(nick);
		nickTf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { //닉네임 사용 가능 여부 라벨로 출력하기
				if(nickTf.getText().isEmpty()) {
					lblNickCheck1.setText(""); lblNickCheck2.setText("");
				} else if(dbj.nicknameCheck(nickTf.getText()) && duplicateNickCheck(nickTf.getText())) {
					lblNickCheck1.setText("●");
					lblNickCheck1.setForeground(Color.BLUE);
					lblNickCheck2.setText("OK!");
				} else if(!dbj.nicknameCheck(nickTf.getText()) || !duplicateNickCheck(nickTf.getText())) {
					lblNickCheck1.setText("●");
					lblNickCheck1.setForeground(Color.RED);	
					lblNickCheck2.setText("NO!");				
				}
			}
		});
		nickTf.setFont(new Font("굴림", Font.PLAIN,12));
		nickTf.setForeground(Color.GRAY);
		nickTf.setBounds(120, 343, 150, 25);
		
		nameTf = new JTextField(name);
		nameTf.setFont(new Font("굴림",Font.PLAIN,12));
		nameTf.setForeground(Color.GRAY);
		nameTf.setBounds(120, 393, 150, 25);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일");
		sBirth = sdf.format(birth);
		birthTf = new JTextField(sBirth);
		birthTf.setFont(new Font("굴림",Font.PLAIN,12));
		birthTf.setForeground(Color.GRAY);
		birthTf.setBounds(120, 443, 150, 25);
		birthTf.setEditable(false);
		
		logPanel2.add(idTf);
		logPanel2.add(pwPf1);
		logPanel2.add(pwPf2);
		logPanel2.add(nameTf);
		logPanel2.add(nickTf);
		logPanel2.add(birthTf);
		logPanel2.add(lblPwCheck1);
		logPanel2.add(lblPwCheck2);
		logPanel2.add(lblNickCheck1);
		logPanel2.add(lblNickCheck2);
		logPanel2.add(btnHelp);

		frame.getContentPane().add(logPanel2);

		okBtn = new JButton();//수정 버튼
		okBtn.setText("OK");
		okBtn.setBackground(new Color(255, 105, 180));
		okBtn.setForeground(new Color(255, 255, 255));
		okBtn.setFont(new Font("굴림", Font.BOLD, 12));
		okBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				pwCheck1 = dbj.pwCheck(pwPf1.getText());
				pwCheck2 = pwPf2.getText().equals(pwPf1.getText());
				nameCheck = dbj.nameCheck(nameTf.getText());
				nicknameCheck = dbj.nicknameCheck(nickTf.getText());
				nickCheck = duplicateNickCheck(nickTf.getText());
				
				//확인 버튼
				if (arg0.getSource().equals(okBtn)) {
					ChatSound.Audio("whisper.wav");
					if(pwPf1.getText().isEmpty() || pwPf2.getText().isEmpty() || nameTf.getText().equals("") 
							|| nickTf.getText().equals(""))
						JOptionPane.showMessageDialog(null, "모든 문항에 입력해 주세요.", 
								"공란 확인", JOptionPane.WARNING_MESSAGE);
					else if(!pwCheck1)
						JOptionPane.showMessageDialog(null, "비밀번호는 영문 대문자, 숫자, 특수문자를 포함하여 최소 8자리 이상 입력해 주세요.", 
								"비밀번호 확인", JOptionPane.WARNING_MESSAGE);
					else if(!pwCheck2)
						JOptionPane.showMessageDialog(null, "비밀번호가 일치하지 않습니다.", 
								"비밀번호 확인", JOptionPane.WARNING_MESSAGE);
					else if(!nameCheck)
						JOptionPane.showMessageDialog(null, "이름은 한글만 입력이 가능합니다.", 
								"이름 확인", JOptionPane.WARNING_MESSAGE);
					else if(!nicknameCheck)
						JOptionPane.showMessageDialog(null, "사용할 수 없는 닉네임입니다.", 
								"닉네임 확인", JOptionPane.WARNING_MESSAGE);
					else if(!nickCheck)
						JOptionPane.showMessageDialog(null, "이미 사용 중인 닉네임입니다.", 
								"닉네임 중복 확인", JOptionPane.WARNING_MESSAGE);
					
					if(pwCheck1 && pwCheck2 && nameCheck && nicknameCheck && nickCheck) {
						boolean UpdateCheck = false;
						try {
							UpdateCheck = memberUpdate(userID, pwPf2.getText(),
									nameTf.getText(), nickTf.getText());
						} catch (ParseException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						if(UpdateCheck) {
							ChatSound.Audio("last.wav");
							JOptionPane.showMessageDialog(null, "회원 정보 수정이 완료되었습니다!");
							frame.dispose(); //창 닫기
							dbClose();
						} else {
							ChatSound.Audio("Slap.wav");
							JOptionPane.showMessageDialog(null, "회원 정보 수정 실패! 다시 시도해 주세요.", 
									"Error", JOptionPane.WARNING_MESSAGE);
						}
					}
				}
			}
		});
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//okBtn.setEnabled(false);
		
		okBtn.setBounds(100, 500, 80, 30);

		JButton cancleBtn = new JButton();//취소 버튼
		cancleBtn.setForeground(new Color(255, 105, 180));
		cancleBtn.setBackground(new Color(255, 255, 255));
		cancleBtn.setFont(new Font("굴림", Font.BOLD, 12));
		cancleBtn.setText("Cancel");
		cancleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatSound.Audio("oliw.wav");
			}
		});
		cancleBtn.setBounds(220, 500, 80, 30);
		
		logPanel2.add(okBtn);
		logPanel2.add(cancleBtn);
		frame.getContentPane().add(logPanel2);
		
		JLabel lblBack = new JLabel();
		lblBack.setIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/userinfo_back_img.jpg")));
		lblBack.setBounds(0, 0, 394, 571);
		logPanel2.add(lblBack);

		//취소 버튼
		cancleBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int memberJoinCancel = JOptionPane.showConfirmDialog(null, "회원 정보 수정을 취소하시겠습니까?", 
						"회원가입 취소", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if(memberJoinCancel == 0) {
					frame.dispose();
					dbClose();
				}
			}
		});
		
		frame.setBounds(700, 250, 400, 600);
		frame.setResizable(false);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //로그인 창도 함께 사라짐

	}//UpdateDBPanel-end

	public void dbClose() {
		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			if (con != null)
				con.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public boolean duplicateNickCheck(String nick) { //중복 닉네임 검색
		ArrayList<String> arr = new ArrayList<String>();
		String sql = "SELECT USER_NICKNAME FROM CHAT.USERS";
		boolean flag = true;
		
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String temp = rs.getString("USER_NICKNAME");
				arr.add(temp);
			}
			for(String a : arr) {
				if(nick.equals(a)) {
					flag = false;
					break;
				} else flag = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} return flag;
	}//중복 닉네임 검색-end
	
	public boolean memberUpdate(String id, String pw, String name, String nickname) throws ParseException { //회원 정보 수정
		String sql = "update chat.users set user_pw = '" + pw + "',user_name='" + name + "',user_nickname='" 
				+ nickname +  "' where user_id='" + id + "'";
		boolean flag = false;
		
		try {			
			int cnt = stmt.executeUpdate(sql);
			if(cnt == 1) {
				flag = true;
			} else {
				flag = false;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} return flag;
	}//Update-end

}// class end
