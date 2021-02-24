package chat.server;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;

import chat.sound.ChatSound;
import chat.user.ui.LoginUI;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;

public class ServerAddress extends JFrame {

	JButton confirmBtn;
	JTextField ipText;
	LoginUI loginUI;

	public ServerAddress(LoginUI loginUI) {
		getContentPane().setBackground(new Color(255, 255, 255));
		this.loginUI = loginUI;
		initialize();
	}

	private void initialize() {
		
		setTitle("Server IP");
		setBounds(700, 250, 306, 95);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(12, 10, 266, 37);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0)); // 텍스트 크기

		ipText = new JTextField();
		ipText.setForeground(new Color(255, 105, 180));
		ipText.setBackground(new Color(255, 255, 255));
		ipText.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginUI.ipBtn.setText(ipText.getText());
					loginUI.setVisible(true);
					dispose();
					loginUI.idText.requestFocus();
				}
			}
		});
		ipText.setText("127.0.0.1");
		panel.add(ipText, BorderLayout.CENTER);
		ipText.setColumns(10);

		confirmBtn = new JButton("확인");
		confirmBtn.setBackground(new Color(255, 105, 180));
		confirmBtn.setForeground(new Color(255, 255, 255));
		confirmBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatSound.Audio("join.wav");
				loginUI.ipBtn.setText(ipText.getText());
				loginUI.setVisible(true);
				dispose();
				loginUI.idText.requestFocus();
			}
		});
		panel.add(confirmBtn, BorderLayout.EAST);
		setVisible(true);
	}

}