package chat.user.ui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import chat.client.Client;
import chat.server.ServerAddress;
import chat.sound.ChatSound;
import chat.user.db.DBJoin;
import chat.user.user.User;


public class LoginUI extends JFrame implements KeyListener {

	boolean confirm = false;
	public JTextField idText;
	public JPasswordField pwPf;
	public JButton loginBtn, signUpBtn;
	public JButton ipBtn;
	public Client client;

	DBJoin jdb;
	JScrollPane scrollPane;
    LoginUI login;
	
	// 생성자
	public LoginUI(Client Client) {
		
		setTitle("로그인");
		ServerAddress sd = new ServerAddress(this);
		this.client = Client;
		loginUIInitialize();
	}

	// 메서드
	private void loginUIInitialize() {
		setBounds(700, 250, 400, 600); // 창 크기 조절
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 394, 571); // 로그인 비밀번호 나타내기
		getContentPane().add(panel); // 로그인 화면 나타내기
		
		panel.setLayout(null);

		idText = new JTextField();
		idText.setBounds(129, 307, 203, 21);
		panel.add(idText);
		idText.setColumns(10);

		pwPf = new JPasswordField();
		pwPf.addKeyListener(new KeyAdapter() {

			// 로그인 엔터 버튼
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					msgSummit();
				}
			}
		});
		pwPf.setBounds(129, 377, 203, 21);
		panel.add(pwPf);
		pwPf.setColumns(10);

		idText.addKeyListener(this);
		pwPf.addKeyListener(this);

		loginBtn = new JButton();//로그인
		loginBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});

		loginBtn.addMouseListener(new MouseAdapter() {
			// 클릭시 승인 메서드로 넘어간다.
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatSound.Audio("openroom.wav");
				if (loginBtn.isEnabled() == true) {
					msgSummit();
				}
			}
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ChatSound.Audio("butten.wav");
			}
		});

		loginBtn.setEnabled(false);

		loginBtn.setBounds(65, 440, 267, 41); //로그인버튼
		loginBtn.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/login_btn_img.jpg")).getImage()
				.getScaledInstance(267, 41, Image.SCALE_SMOOTH)));
		panel.add(loginBtn);

		signUpBtn = new JButton();//회원가입버튼
		signUpBtn.setHorizontalAlignment(SwingConstants.CENTER);
		signUpBtn.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/join_btn_img.jpg")).getImage()
				.getScaledInstance(97, 23, Image.SCALE_SMOOTH)));
		signUpBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		signUpBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) { // 회원가입 클릭
				jdb = new DBJoin();
				jdb.JoinDBPanel();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				ChatSound.Audio("butten.wav");
			}
		});
		signUpBtn.setBounds(58, 500, 97, 23);
		panel.add(signUpBtn);

		JLabel jbNewLabe3 = new JLabel("서버 아이피");
		jbNewLabe3.setForeground(new Color(255, 105, 180));
		jbNewLabe3.setBounds(12, 10, 78, 15);
		panel.add(jbNewLabe3);

		ipBtn = new JButton("");
		ipBtn.setBackground(new Color(255, 250, 240));
		ipBtn.setForeground(new Color(255, 105, 180));
		ipBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ServerAddress sd = new ServerAddress(LoginUI.this);
				setVisible(false); // true이면 창이 그냥 닫힌다.
			}
		});
		ipBtn.setBounds(93, 6, 97, 23);
		panel.add(ipBtn);
		
		JLabel lblBack = new JLabel();
		lblBack.setIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/login_back_img.jpg")));
		lblBack.setBounds(0, 0, 394, 571);
		panel.add(lblBack);
	
	}

	// 메시지 승인 메서드
	private void msgSummit() {
		new Thread(new Runnable() {
			public void run() {

				// 소켓생성(로그인 접속이 안된다)
				if (client.serverAccess()) {
					try {
						// 로그인정보(아이디+패스워드) 전송
						client.getDos().writeUTF(User.LOGIN + "/" + idText.getText() + "/" + pwPf.getText());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			} // run() start
		}).start();
	} // msgSummit() end

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (idText.getText().isEmpty() || pwPf.getText().isEmpty()) {
			loginBtn.setEnabled(false);
		} else {
			loginBtn.setEnabled(true);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void textValueChanged(TextEvent e) {
//		if (idText.getText().isEmpty() || pwPf.getText().isEmpty()) {
//			loginBtn.setEnabled(false);
//		} else {
//			loginBtn.setEnabled(true);
//		}
//	}
}
