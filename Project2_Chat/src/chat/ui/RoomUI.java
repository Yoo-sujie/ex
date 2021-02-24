package chat.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import chat.client.Client;
import chat.file.UtilFileIO;
import chat.room.Room;
import chat.sound.ChatSound;
import chat.user.user.User;

public class RoomUI extends JFrame implements ActionListener {

	Client client;
	Room room;
	String roompw;

	public JTextArea chatArea;
	public JTextField chatField;
	public JList uList;
	public DefaultListModel model;
	int c1,c2,c3,c4,c5,c6,c7,c8,c9;
	

	public RoomUI(Client client, Room room, int c1, int c2, int c3, int c4, int c5, int c6, int c7, int c8, int c9) {
		this.client = client;
		this.room = room;
		this.roompw = roompw;
		this.c1=c1;this.c2=c2;this.c3=c3;
		this.c4=c4;this.c5=c5;this.c6=c6;
		this.c7=c7;this.c8=c8;this.c9=c9;
		getContentPane().setBackground(new Color(c1, c2, c3));
		setTitle("ChatRoom : " + room.toProtocol());
		initialize();
	}

	private void initialize() {
		
		System.out.println(c7 +" " + c8 + " " + c9);
		
		setBounds(700, 250, 502, 481);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(null);
		setResizable(false);

		// 채팅 패널
		final JPanel panel = new JPanel();
		panel.setBounds(12, 10, 300, 358);
		getContentPane().add(panel);
		panel.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		panel.add(scrollPane, BorderLayout.CENTER);

		chatArea = new JTextArea();
		chatArea.setBackground(new Color(c4, c5, c6));
		chatArea.setEditable(false); // 수정불가
		scrollPane.setViewportView(chatArea); // 화면 보임
		chatArea.append("새로운 채팅 방을 개설했습니다.\n");

		JPanel panel1 = new JPanel();
		// 글쓰는 패널
		panel1.setBounds(12, 378, 300, 34);
		getContentPane().add(panel1);
		panel1.setLayout(new BorderLayout(0, 0));

		chatField = new JTextField();
		chatField.setBackground(new Color(255, 255, 255));
		panel1.add(chatField);
		chatField.setColumns(10);
		chatField.addKeyListener(new KeyAdapter() {
			// 엔터 버튼 이벤트
			@Override
			public void keyPressed(KeyEvent e){
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					msgSummit();
					ChatSound.Audio("butten.wav");
				}
			}

		});

		// 참여자 패널
		JPanel panel2 = new JPanel();
		// 참여자 패널
		panel2.setBounds(324, 10, 150, 358);
		getContentPane().add(panel2);
		panel2.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane1 = new JScrollPane();
		panel2.add(scrollPane1, BorderLayout.CENTER);

		uList = new JList(new DefaultListModel());
		uList.setForeground(new Color(c7, c8, c9));
		uList.setBackground(new Color(c4, c5, c6));
		model = (DefaultListModel) uList.getModel();
		scrollPane1.setViewportView(uList);

		// send button
		JButton roomSendBtn = new JButton("전송");
		roomSendBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatSound.Audio("butten.wav");
			}
		});
		roomSendBtn.setForeground(new Color(255, 255, 255));
		roomSendBtn.setBackground(new Color(c7, c8, c9));
		roomSendBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				msgSummit();
				chatField.requestFocus();
			}
		});
		roomSendBtn.setBounds(324, 378, 75, 34);
		getContentPane().add(roomSendBtn);

		JButton roomExitBtn = new JButton("나가기");
		roomExitBtn.setBackground(new Color(255, 255, 255));
		roomExitBtn.setForeground(new Color(c7, c8, c9));
		roomExitBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatSound.Audio("out.wav");
				int ans = JOptionPane.showConfirmDialog(panel, "방을 나가시겠습니까?", 
						"삭제", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (ans == 0) {
					try {
						client.getUser().getDos().writeUTF(User.GETOUT_ROOM + "/" + room.getRoomNum());
						for (int i = 0; i < client.getUser().getRoomArray().size(); i++) {
							if (client.getUser().getRoomArray().get(i).getRoomNum() == room.getRoomNum()) {
								client.getUser().getRoomArray().remove(i);
							}
						}
						msgSummit();
						setVisible(false);
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		roomExitBtn.setBounds(400, 378, 75, 34);
		getContentPane().add(roomExitBtn);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBackground(new Color(c4, c5, c6));
		setJMenuBar(menuBar);
		setVisible(true);
		
		//===============================================================
		// ------- 채팅내용 저장 및 가져오기 메뉴  -------
		JMenu mnuSaveChat = new JMenu("채팅저장");
		mnuSaveChat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		mnuSaveChat.setForeground(new Color(c7, c8, c9));
		mnuSaveChat.setBackground(new Color(255, 250, 240));
		mnuSaveChat.addActionListener(this);
		menuBar.add(mnuSaveChat);
		JMenuItem mitSaveChatToFile = new JMenuItem("파일로저장");
		mitSaveChatToFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		mitSaveChatToFile.setBackground(new Color(255, 255, 255));
		mitSaveChatToFile.setForeground(new Color(c7, c8, c9));
		mitSaveChatToFile.addActionListener(this);
		mnuSaveChat.add(mitSaveChatToFile);
		
		JMenu mnuLoadChat = new JMenu("저장채팅확인");
		mnuLoadChat.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		mnuLoadChat.setForeground(new Color(c7, c8, c9));
		mnuLoadChat.setBackground(new Color(255, 250, 240));
		mnuLoadChat.addActionListener(this);
		menuBar.add(mnuLoadChat);
		JMenuItem mitLoadChatFromFile = new JMenuItem("파일열기");
		mitLoadChatFromFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		mitLoadChatFromFile.setForeground(new Color(c7, c8, c9));
		mitLoadChatFromFile.setBackground(new Color(255, 255, 255));
		mitLoadChatFromFile.addActionListener(this);
		mnuLoadChat.add(mitLoadChatFromFile);
		
		//===============================================================
		
		chatField.requestFocus();
		this.addWindowListener(new WindowAdapter() {	// 윈도우 나가기
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					client.getUser().getDos().writeUTF(User.GETOUT_ROOM + "/" + room.getRoomNum());
					for (int i = 0; i < client.getUser().getRoomArray().size(); i++) {
						if (client.getUser().getRoomArray().get(i).getRoomNum() == room.getRoomNum()) {
							client.getUser().getRoomArray().remove(i);
						}
					}
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	private void msgSummit() {
		String string = chatField.getText();
		if (!string.equals("")) {
			try {
				// 채팅방에 메시지 보냄
				client.getDos()
						.writeUTF(User.ECHO02 + "/" + room.getRoomNum() + "/" + client.getUser().toString() + string);
				chatField.setText("");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
			case "파일로저장":
				String filename = UtilFileIO.saveFile(chatArea);
				JOptionPane.showMessageDialog(chatArea.getParent(), 
						"채팅내용을 파일명(" + filename + ")으로 저장하였습니다", 
						"채팅백업", JOptionPane.INFORMATION_MESSAGE);
				break;
			case "파일열기":
				filename = UtilFileIO.getFilenameFromFileOpenDialog("./");
				String text = UtilFileIO.loadFile(filename);
				TextViewUI textview = new TextViewUI(text);
				break;
		}
	}
}
	