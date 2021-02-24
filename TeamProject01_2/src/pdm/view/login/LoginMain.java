package pdm.view.login;


import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pdm.view.main.MainView;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginMain extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginMain frame = new LoginMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginMain() {
		setTitle("관리자 로그인");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 438, 223);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		

		JButton btnNewButton = new JButton("로그인");
		btnNewButton.setFont(new Font("굴림", Font.BOLD, 15));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("deprecation")
				String SN = passwordField.getText().trim();
				boolean login;
				if(SN.length()==0 ) {
					JOptionPane.showMessageDialog(null, "시리얼 넘버를 입력해주세요", "오류", JOptionPane.DEFAULT_OPTION);
					return;
				}
				try {
					login = new LoginDAO().getLogin(SN);
					if(login) {
						JOptionPane.showMessageDialog(null, "로그인 성공", "Login", JOptionPane.DEFAULT_OPTION);
						new MainView();
						dispose();
					}
						
					else {
						JOptionPane.showMessageDialog(null, "시리얼 넘버가 틀립니다", "오류", JOptionPane.DEFAULT_OPTION);
						return;
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}

		});

		btnNewButton.setBounds(100, 83, 242, 53);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBounds(62, 31, 301, 41);
		contentPane.add(panel);
		panel.setLayout(null);

		passwordField = new JPasswordField();
		passwordField.setBounds(130, 7, 159, 28);
		panel.add(passwordField);

		JLabel lblPassword = new JLabel("Serial number");
		lblPassword.setFont(new Font("굴림", Font.BOLD, 14));
		lblPassword.setBounds(12, 0, 106, 41);
		panel.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);

		setLocationRelativeTo(null);
		setResizable(false);
	}
}