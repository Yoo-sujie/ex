package chat.ui;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import chat.user.ui.LoginUI;

public class ProgramUI extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProgramUI frame = new ProgramUI();
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
	public ProgramUI() {
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(700, 250, 700, 700);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		setTitle("Chat 프로그램 사용 방법");
		
		
		JLabel pro = new JLabel();
		pro.setBounds(0,0,684,661);
		contentPane.add(pro);
		pro.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/program_img.jpg")).getImage()
				.getScaledInstance(684, 661, Image.SCALE_SMOOTH)));
		contentPane.setVisible(true);
		
	}

}
