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
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import chat.message.MsgeBox;
import chat.sound.ChatSound;
import chat.user.ui.JoinHelpUI;
import chat.user.ui.LoginUI;

public class DBJoin implements MouseListener {
	JFrame frame;
	JPanel logPanel2;
	JTextField idTf, nameTf, nickTf, birthTf = null;
	JPasswordField pwPf1, pwPf2;
	JButton joinBtn, checkBtn, btnHelp;
    ImageIcon icon;
    int idClickCount = 0;
	int pwClickCount = 0;
	int nameClickCount = 0;
	int nickClickCount = 0;
	int birthClickCount = 0;
	private boolean idCheck = false;
	private boolean pwCheck1 = false;
	private boolean pwCheck2 = false;
	private boolean nameCheck = false;
	private boolean nicknameCheck = false;
	private boolean nickCheck = false;
	private boolean birthCheck = false;

	MsgeBox msgbox = new MsgeBox();

	/**
	 * @wbp.parser.entryPoint
	 */
	public void JoinDBPanel() {
		
		frame = new JFrame("회원가입");
		logPanel2 = new JPanel();
		logPanel2.setLayout(null);
		
		btnHelp = new JButton(); //도움말
		btnHelp.setBounds(330, 10, 50, 50);
		btnHelp.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/help_btn_img.jpg")).getImage()
				.getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
		btnHelp.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { 
				ChatSound.Audio("Ring.wav");
				new JoinHelpUI().setVisible(true);
			}
		});
		
		idTf = new JTextField("아이디를 입력하세요.");
		idTf.setFont(new Font("굴림",Font.PLAIN,12));
		idTf.setForeground(Color.GRAY);
		idTf.addMouseListener(this);
		idTf.setBounds(120, 193, 150, 25);
		
		pwPf1 = new JPasswordField();
		pwPf1.setFont(new Font("굴림",Font.PLAIN,12));
		pwPf1.setForeground(Color.GRAY);
		pwPf1.addMouseListener(this);
		pwPf1.setBounds(120, 243, 150, 25);
		
		pwPf2 = new JPasswordField(); //비밀번호 확인
		pwPf2.setFont(new Font("굴림",Font.PLAIN,12));
		pwPf2.setForeground(Color.GRAY);
		pwPf2.addMouseListener(this);
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
		
		
		nickTf = new JTextField("닉네임을 입력하세요.");
		nickTf.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) { //닉네임 사용 가능 여부 라벨로 출력하기
				if(nickTf.getText().isEmpty()) {
					lblNickCheck1.setText(""); lblNickCheck2.setText("");
				} else if(nicknameCheck(nickTf.getText()) && duplicateNickCheck(nickTf.getText())) {
					lblNickCheck1.setText("●");
					lblNickCheck1.setForeground(Color.BLUE);
					lblNickCheck2.setText("OK!");
				} else if(!nicknameCheck(nickTf.getText()) || !duplicateNickCheck(nickTf.getText())) {
					lblNickCheck1.setText("●");
					lblNickCheck1.setForeground(Color.RED);	
					lblNickCheck2.setText("NO!");				
				}
			}
		});
		nickTf.setFont(new Font("굴림", Font.PLAIN,12));
		nickTf.setForeground(Color.GRAY);
		nickTf.addMouseListener(this);
		nickTf.setBounds(120, 343, 150, 25);
		
		nameTf = new JTextField("이름을 입력하세요.");
		nameTf.setFont(new Font("굴림",Font.PLAIN,12));
		nameTf.setForeground(Color.GRAY);
		nameTf.addMouseListener(this);
		nameTf.setBounds(120, 393, 150, 25);
		
		birthTf = new JTextField("ex.20200101");
		birthTf.setFont(new Font("굴림",Font.PLAIN,12));
		birthTf.setForeground(Color.GRAY);
		birthTf.addMouseListener(this);
		birthTf.setBounds(120, 443, 150, 25);
		
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

		checkBtn = new JButton();//중복 확인
		checkBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ChatSound.Audio("butten.wav");
			}
		});
		checkBtn.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/idCheck_btn_img.jpg")).getImage()
				.getScaledInstance(69, 26, Image.SCALE_SMOOTH)));
		checkBtn.setBounds(289, 193, 69, 26);
		
		logPanel2.add(checkBtn);
		checkBtn.addMouseListener(this); //addMouseListener 이벤트

		frame.getContentPane().add(logPanel2);

		joinBtn = new JButton();//가입 버튼
		joinBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		//joinBtn.setEnabled(false);
		joinBtn.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/join_btn_img2.jpg")).getImage()
				.getScaledInstance(80, 30, Image.SCALE_SMOOTH)));
		joinBtn.setBounds(100, 500, 80, 30);
		joinBtn.addMouseListener(this); //addMouseListener 이벤트

		JButton cancleBtn = new JButton();//취소 버튼
		cancleBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatSound.Audio("oliw.wav");
			}
		});
		cancleBtn.setBounds(220, 500, 80, 30);
		cancleBtn.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/join_btn_img3.jpg")).getImage()
				.getScaledInstance(80, 30, Image.SCALE_SMOOTH)));
		
		logPanel2.add(joinBtn);
		logPanel2.add(cancleBtn);
		frame.getContentPane().add(logPanel2);
		
		JLabel lblBack = new JLabel();
		lblBack.setIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/join_back_img.jpg")));
		lblBack.setBounds(0, 0, 394, 571);
		logPanel2.add(lblBack);

		//취소 버튼
		cancleBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int memberJoinCancel = JOptionPane.showConfirmDialog(null, "회원가입을 취소하시겠습니까?", 
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

	}//JoinDBPanel-end
		
	Statement stmt = null;
	ResultSet rs = null;
	String url = "jdbc:oracle:thin:@127.0.0.1:1521:xe"; //오라클 포트번호1521/@이후에는 IP주소
	String sql = null;
	Properties info = null;
	Connection con = null;

	@Override
	public void mouseClicked(MouseEvent e) {
		//TextField 클릭 시 예시 삭제하기
		if (e.getSource().equals(idTf)) {
			if(idClickCount==0) {
				idTf.setText("");
				idClickCount = 1;
			}
		} else if (e.getSource().equals(pwPf1)) {
			if(pwClickCount==0) {
				pwPf1.setText("");
				pwClickCount = 1;
			}
		} else if (e.getSource().equals(pwPf2)) {
			if(pwClickCount==0) {
				pwPf2.setText("");
				pwClickCount = 1;
			}
		} else if (e.getSource().equals(nameTf)) {
			if(nameClickCount==0) {
				nameTf.setText("");
				nameClickCount = 1;
			}
		} else if (e.getSource().equals(nickTf)) {
			if(nickClickCount==0) {
				nickTf.setText("");
				nickClickCount = 1;
			}
		} else if (e.getSource().equals(birthTf)) {
			if(birthClickCount==0) {
				birthTf.setText("");
				birthClickCount=1;
			}
		}

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); //conn으로 자동 접속
			info = new Properties();
			info.setProperty("user", "chat");
			info.setProperty("password", "oracle");
			con = DriverManager.getConnection(url, info); //연결할 정보를 가지고 있는 드라이버 매니저를 던진다
			stmt = con.createStatement();
			
			pwCheck1 = pwCheck(pwPf1.getText());
			pwCheck2 = pwPf2.getText().equals(pwPf1.getText());
			nameCheck = nameCheck(nameTf.getText());
			nicknameCheck = nicknameCheck(nickTf.getText());
			nickCheck = duplicateNickCheck(nickTf.getText());
			birthCheck = birthCheck(birthTf.getText());

			//중복 확인 버튼
			if (e.getSource().equals(checkBtn)) {
				boolean duplicateIdCheck = duplicateIdCheck(idTf.getText());
				ChatSound.Audio("Ring.wav");
				if(duplicateIdCheck == false)
					JOptionPane.showMessageDialog(null, "중복된 아이디입니다.",
							"아이디 중복", JOptionPane.WARNING_MESSAGE);
				else if(duplicateIdCheck == true) {
					boolean flag = idCheck(idTf.getText());
					if(flag) {
						
						JOptionPane.showMessageDialog(null, "사용 가능한 아이디입니다.");
						idCheck = true;
					}
					else {
						
						JOptionPane.showMessageDialog(null, "아이디는 영어(대,소문자 구분)와 숫자를 사용하여 6 ~ 12글자로 입력해 주세요.", 
								"아이디 확인", JOptionPane.WARNING_MESSAGE);
						idCheck = false;
					}
				}
			}

			//가입 버튼
			if(e.getSource().equals(joinBtn)) {
				ChatSound.Audio("whisper.wav");
				if(pwPf1.getText().isEmpty() || pwPf2.getText().isEmpty() 
						|| nameTf.getText().equals("") || nameTf.getText().equals("이름을 입력하세요.")
						|| nickTf.getText().equals("") || nickTf.getText().equals("닉네임을 입력하세요.")
						|| birthTf.getText().equals("") || birthTf.getText().equals("ex.20200101"))
					JOptionPane.showMessageDialog(null, "모든 문항에 입력해 주세요.", 
							"공란 확인", JOptionPane.WARNING_MESSAGE);
				else if(!idCheck)
					JOptionPane.showMessageDialog(null, "아이디 중복 확인을 해주세요.", 
							"아이디 중복 확인", JOptionPane.WARNING_MESSAGE);					
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
				else if(!birthCheck)
					JOptionPane.showMessageDialog(null, "생년월일을 정확히 입력해 주세요.", 
							"생년월일 확인", JOptionPane.WARNING_MESSAGE);
				
				if(idCheck && pwCheck1 && pwCheck2 && nameCheck && nicknameCheck && nickCheck 
						&& birthCheck) {
					boolean joinCheck = memberJoin(idTf.getText(), pwPf2.getText(),
							nameTf.getText(), nickTf.getText(), birthTf.getText());
					if(joinCheck) {
						ChatSound.Audio("join.wav");
						JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!");
						frame.dispose(); //창 닫기
						dbClose();
					} else {
						JOptionPane.showMessageDialog(null, "회원가입 실패! 다시 시도해 주세요.", 
								"Error", JOptionPane.WARNING_MESSAGE);
					}
				}
			}
			
		} catch (Exception ee) {
			System.out.println("Error...");
			ee.printStackTrace();
		}
		
	}// mouseClicked 이벤트 end

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

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
	
	public boolean duplicateIdCheck(String userId) { //중복 아이디 검색
		ArrayList<String> arr = new ArrayList<String>();
		String sql = "SELECT USER_ID FROM CHAT.USERS";
		boolean flag = true;
		
		try {
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				String temp = rs.getString("USER_ID");
				arr.add(temp);
			}
			for(String a : arr) {
				if(userId.equals(a)) {
					flag = false;
					break;
				} else flag = true;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} return flag;
	}//중복 아이디 검색-end
	
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
	
	public boolean idCheck(String id) {//ID, 영어(대,소문자 구분)랑 숫자만 6 ~ 12글자
		boolean flag = Pattern.matches("^[a-zA-Z0-9]{6,12}$", id);
		return flag;
	}
	
	public boolean pwCheck(String pw) {//PW, 영어(대,소문자 구분), 숫자, 특수문자 조합하여 8글자 이상
		boolean flag = Pattern.matches("((?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[^a-zA-Z0-9가-힣]).{8,})", pw);
		return flag;
	}
	
	public boolean nameCheck(String name) {//Name, 한글만 입력 가능
		boolean flag = Pattern.matches("^[가-힣]+$", name);
		return flag;
	}
	
	public boolean nicknameCheck(String nickname) {//NickName, 특수문자 사용 금지, 2 ~ 10글자
		boolean flag = Pattern.matches("^[a-zA-Z0-9가-힣]{2,10}$", nickname);
		return flag;
	}	
	
	public boolean birthCheck(String birth) { //8자리 숫자, ex) 20000330, 1920년 ~ 2099년까지 입력 가능
		boolean flag = Pattern.matches("^19[2-9][0-9]0[1-9][0-9][0-9]|19[2-9][0-9]1[0-2][0-9][0-9]"
				+ "|20[0-9][0-9]0[1-9][0-9][0-9]|20[0-9][0-9]1[0-2][0-9][0-9]{8,8}$", birth);
		return flag;
	}
	
	public boolean memberJoin(String id, String pw, String name, String nickname, String birth) 
			throws ParseException { //회원가입
		String sql = "insert into chat.users(user_id, user_pw, user_name, user_nickname, user_birth) "
				+ "values('" + id + "','" + pw + "','"
				+ name + "','" + nickname + "','" + birth + "')";
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
	}//회원가입-end

}// class end
