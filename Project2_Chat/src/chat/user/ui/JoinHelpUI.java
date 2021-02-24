package chat.user.ui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Font;
import java.awt.Color;

public class JoinHelpUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JoinHelpUI frame = new JoinHelpUI();
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
	public JoinHelpUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(650, 300, 500, 530);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		
		
		
		JLabel lbl6 = new JLabel("만 가능합니다.");
		lbl6.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lbl6.setBounds(308, 273, 352, 37);
		contentPane.add(lbl6);

		JLabel lbl5 = new JLabel("특수문자 사용 금지, 2 ~ 10글자");
		lbl5.setForeground(Color.RED);
		lbl5.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lbl5.setBounds(117, 273, 352, 37);
		contentPane.add(lbl5);
		
		JLabel lbl4 = new JLabel("▶ 닉네임은 ");
		lbl4.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lbl4.setBounds(43, 273, 352, 37);
		contentPane.add(lbl4);
		
		JLabel lbl3 = new JLabel("만 가능합니다.");
		lbl3.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lbl3.setBounds(63, 239, 352, 37);
		contentPane.add(lbl3);
		
		JLabel lbl2 = new JLabel("영어(대,소문자 구분), 숫자, 특수문자 조합하여 8글자 이상");
		lbl2.setForeground(Color.RED);
		lbl2.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lbl2.setBounds(129, 213, 340, 37);
		contentPane.add(lbl2);
		
		JLabel lbl1 = new JLabel("▶ 비밀번호는 ");
		lbl1.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lbl1.setBounds(43, 213, 100, 37);
		contentPane.add(lbl1);

		JLabel lbl9 = new JLabel("만 가능합니다.");
		lbl9.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lbl9.setBounds(63, 179, 352, 37);
		contentPane.add(lbl9);
		
		JLabel lbl8 = new JLabel("영어(대,소문자 구분)랑 숫자를 혼합하여 6 ~ 12글자 ");
		lbl8.setForeground(Color.RED);
		lbl8.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lbl8.setBounds(117, 153, 332, 37);
		contentPane.add(lbl8);
		
		JLabel lbl7 = new JLabel("▶ 아이디는 ");
		lbl7.setFont(new Font("맑은 고딕", Font.PLAIN, 13));
		lbl7.setBounds(43, 153, 100, 37);
		contentPane.add(lbl7);
		
		JLabel lblHelp = new JLabel();
		lblHelp.setBounds(0,0,500,500);
		contentPane.add(lblHelp);
		lblHelp.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/help_back_img.jpg")).getImage()
				.getScaledInstance(500, 500, Image.SCALE_SMOOTH)));
		contentPane.setVisible(true);
		
	}
}
