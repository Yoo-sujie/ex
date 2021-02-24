package chat.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;

import chat.client.Client;
import chat.file.UtilFileIO;
import chat.message.MsgeBox;
import chat.room.Room;
import chat.sound.ChatSound;
import chat.user.db.DBDelete;
import chat.user.db.DBUpdate;
import chat.user.ui.LoginUI;
import chat.user.user.User;

public class WaitRoomUI extends JFrame implements ActionListener {
	
	int c1, c2, c3;
	int c4, c5, c6;
	int c7, c8, c9;
	
	JPanel room, waitroom, user;

	MsgeBox msgbox = new MsgeBox();
	JMenuBar menuBar;
	String temp,id;
	
	public int lastRoomNum = 100;
	public JButton makeRoomBtn, getInRoomBtn, whisperBtn, sendBtn;
	public JTree userTree;
	public JList roomList;
	public JTextField chatField;
	public JTextArea waitRoomArea;
	public JLabel lbid, lbnick, lblNewLabel;
	public JTextField lbip;
	public TitledBorder title1, title2, title3;
	JMenu basicMenus;
	JMenuItem mitSaveChatToFile, mitLoadChatFromFile, updndel, changeInfo, withdrawMem, helpMenus, proInfoItem,
	opMenus, proInfoItem2, proInfoItem3, proInfoItem4;

	ArrayList<String> arrPw;
	
	Client client;
	public ArrayList<User> userArray; // 사용자 목록 배열
	public String currentSelectedTreeNode;
	public DefaultListModel model;
	public DefaultMutableTreeNode level1;		
	public DefaultMutableTreeNode level2;	
	public DefaultMutableTreeNode level3;	
	
	JScrollPane scrollPane;
    LoginUI login;
    private JMenu menu;

	public WaitRoomUI(Client Client) {
		setBackground(new Color(255, 255, 255));
		getContentPane().setBackground(Color.WHITE);
		setTitle("4조 Project");
		userArray = new ArrayList<User>();
		client = Client;
		initialize();
		setLocationRelativeTo(null);
	}

	private void initialize() {
		
		c1 = 255; c2 = 255; c3 = 255;
		c4 = 255; c5 = 250; c6 = 240;
		c7 = 255; c8 = 105; c9 = 180;
		
		setBounds(700, 250, 808, 599);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		menuBar = new JMenuBar();
		menuBar.setBackground(new Color(255, 250, 240));
		setJMenuBar(menuBar);

		basicMenus = new JMenu("파일");
		basicMenus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		basicMenus.setForeground(new Color(255, 105, 180));
		basicMenus.setBackground(new Color(255, 250, 240));
		basicMenus.addActionListener(this);
		menuBar.add(basicMenus);

		mitSaveChatToFile = new JMenuItem("파일로저장");
		mitSaveChatToFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		mitSaveChatToFile.setBackground(new Color(255, 255, 255));
		mitSaveChatToFile.setForeground(new Color(255, 105, 180));
		mitSaveChatToFile.addActionListener(this);
		basicMenus.add(mitSaveChatToFile);
		
		mitLoadChatFromFile = new JMenuItem("파일열기");
		mitLoadChatFromFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		mitLoadChatFromFile.setForeground(new Color(255, 105, 180));
		mitLoadChatFromFile.setBackground(new Color(255, 255, 255));
		mitLoadChatFromFile.addActionListener(this);
		basicMenus.add(mitLoadChatFromFile);

		updndel = new JMenu("회원정보");
		updndel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		updndel.setBackground(new Color(255, 250, 240));
		updndel.setForeground(new Color(255, 105, 180));
		updndel.addActionListener(this);
		menuBar.add(updndel);

		changeInfo = new JMenuItem("회원정보 변경");
		changeInfo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		changeInfo.setForeground(new Color(255, 105, 180));
		changeInfo.setBackground(new Color(255, 255, 255));
		changeInfo.addActionListener(this);
		updndel.add(changeInfo);
		
		withdrawMem = new JMenuItem("회원 탈퇴");
		withdrawMem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		withdrawMem.setBackground(new Color(255, 255, 255));
		withdrawMem.setForeground(new Color(255, 105, 180));
		withdrawMem.addActionListener(this);
		updndel.add(withdrawMem);
		
		opMenus = new JMenu("테마변경");
		opMenus.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				ChatSound.Audio("room.wav");
			}
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		opMenus.setForeground(new Color(255, 105, 180));
		opMenus.setBackground(new Color(255, 250, 240));
		opMenus.addActionListener(this);
		menuBar.add(opMenus);

		proInfoItem2 = new JMenuItem("테마1");
		proInfoItem2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		proInfoItem2.setForeground(new Color(255, 105, 180));
		proInfoItem2.setBackground(new Color(255, 255, 255));
		proInfoItem2.addActionListener(this);
		opMenus.add(proInfoItem2);

		proInfoItem3 = new JMenuItem("테마2");
		proInfoItem3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		proInfoItem3.setForeground(new Color(255, 105, 180));
		proInfoItem3.setBackground(new Color(255, 255, 255));
		proInfoItem3.addActionListener(this);
		opMenus.add(proInfoItem3);

		proInfoItem4 = new JMenuItem("테마3");
		proInfoItem4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				ChatSound.Audio("room.wav");
			}
		});
		proInfoItem4.setForeground(new Color(255, 105, 180));
		proInfoItem4.setBackground(new Color(255, 255, 255));
		proInfoItem4.addActionListener(this);
		opMenus.add(proInfoItem4);
				
				helpMenus = new JMenu("도움말");
				helpMenus.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						ChatSound.Audio("room.wav");
					}
				});
				helpMenus.setForeground(new Color(255, 105, 180));
				helpMenus.setBackground(new Color(255, 250, 240));
				helpMenus.addActionListener(this);
				
				menu = new JMenu("                                                                                                                                                                                             ");
				menu.setEnabled(false);
				menu.setForeground(new Color(255, 105, 180));
				menu.setBackground(new Color(255, 250, 240));
				menuBar.add(menu);
				menuBar.add(helpMenus);
				
						proInfoItem = new JMenuItem("프로그램 정보");
						proInfoItem.setForeground(new Color(255, 105, 180));
						proInfoItem.setBackground(new Color(255, 255, 255));
						proInfoItem.addActionListener(this);
						helpMenus.add(proInfoItem);
						proInfoItem.addMouseListener(new MouseAdapter() {
							@Override
							public void mouseEntered(MouseEvent e) {
								ChatSound.Audio("room.wav");
							}
						});
		
		getContentPane().setLayout(null);
		
		lblNewLabel = new JLabel();
		lblNewLabel.setBounds(307, 10, 149, 88);
		lblNewLabel.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/chat_img.png")).getImage()
				.getScaledInstance(149, 88, Image.SCALE_SMOOTH)));
		getContentPane().add(lblNewLabel);

		room = new JPanel();
		room.setBackground(Color.WHITE);
		title1 = new TitledBorder(new LineBorder(new Color(255, 105, 180)), "             채 팅 방             ", TitledBorder.CENTER, TitledBorder.TOP, null, null);
		title1.setTitleColor(new Color(255, 105, 180));
		room.setBorder(title1);
		room.setBounds(22, 108, 571, 215);
		getContentPane().add(room);
		room.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane = new JScrollPane();
		room.add(scrollPane, BorderLayout.CENTER);

		// 리스트 객체와 모델 생성
		roomList = new JList(new DefaultListModel());
		roomList.setBackground(new Color(255, 250, 240));
		roomList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i = roomList.getFirstVisibleIndex();
				// System.out.println(">>>>>>>>>>>" + i);
				if (i != -1) {
					// 채팅방 목록 중 하나를 선택한 경우,
					// 선택한 방의 방번호를 전송
					String temp = (String) roomList.getSelectedValue();
					if(temp.equals(null)){
						return;
					}

					try {
						client.getUser().getDos().writeUTF(User.UPDATE_SELECTEDROOM_USERLIST + "/" + temp.substring(0, 3));
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		model = (DefaultListModel) roomList.getModel();
		scrollPane.setViewportView(roomList);

		JPanel panel2 = new JPanel();
		room.add(panel2, BorderLayout.SOUTH);

		makeRoomBtn = new JButton("방 만들기");
		makeRoomBtn.setForeground(new Color(255, 255, 255));
		makeRoomBtn.setBackground(new Color(255, 105, 180));
		makeRoomBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			}
		});
		makeRoomBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ChatSound.Audio("Ring.wav");
				// 방만들기 버튼 클릭
				createRoom();
			}
		});
		panel2.setLayout(new GridLayout(0, 2, 0, 0));
		panel2.add(makeRoomBtn);

		getInRoomBtn = new JButton("방 입장하기");
		getInRoomBtn.setBackground(new Color(255, 255, 255));
		getInRoomBtn.setForeground(new Color(255, 105, 180));
		getInRoomBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ChatSound.Audio("room.wav");
				// 방 들어가기
				
				try {
					
					String selectedRoom = (String) roomList.getSelectedValue();
					StringTokenizer token = new StringTokenizer(selectedRoom, "/"); // 토큰 생성
					String rNum = token.nextToken();
					String rName = token.nextToken();
					System.out.println("방 이름 : " + rName);
					System.out.println("방 번호" + rNum);
					getIn();
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
		});
		panel2.add(getInRoomBtn);
		
		user = new JPanel();
		user.setBackground(Color.WHITE);
		title3 = new TitledBorder(new LineBorder(new Color(255, 105, 180)), "             사용자 목록             ", TitledBorder.CENTER, TitledBorder.TOP, null, null);
		title3.setTitleColor(new Color(255, 105, 180));
		user.setBorder(title3);
		user.setBounds(605, 108, 171, 409);
		getContentPane().add(user);
		user.setLayout(new BorderLayout(0, 0));

		JScrollPane scrollPane1 = new JScrollPane();
		user.add(scrollPane1, BorderLayout.CENTER);

		// 사용자목록, 트리구조
		userTree = new JTree();
		userTree.setForeground(new Color(255, 105, 180));
		userTree.setBackground(new Color(255, 250, 240));
		userTree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				currentSelectedTreeNode = e.getPath().getLastPathComponent().toString();
			}
		});
		level1 = new DefaultMutableTreeNode("참여자");
		level2 = new DefaultMutableTreeNode("채팅방");
		level3 = new DefaultMutableTreeNode("대기실");
		level1.add(level2);
		level1.add(level3);
		DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();

		userTree.setCellRenderer(renderer);
		userTree.setEditable(false);

		DefaultTreeModel model = new DefaultTreeModel(level1);
		userTree.setModel(model);

		scrollPane1.setViewportView(userTree);

		JPanel panel1 = new JPanel();
		user.add(panel1, BorderLayout.SOUTH);
		panel1.setLayout(new GridLayout(1, 0, 0, 0));

		whisperBtn = new JButton("귓속말");
		whisperBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ChatSound.Audio("whisper.wav");
			}
		});
		whisperBtn.setBackground(new Color(255, 255, 255));
		whisperBtn.setForeground(new Color(255, 105, 180));
		
		whisperBtn.addActionListener(this);
		panel1.add(whisperBtn);

		waitroom = new JPanel();
		waitroom.setBackground(Color.WHITE);
		title2 = new TitledBorder(new LineBorder(new Color(255, 105, 180)), "             전 체 채 팅             ", TitledBorder.CENTER, TitledBorder.TOP, null, null);
		title2.setTitleColor(new Color(255, 105, 180));
		waitroom.setBorder(title2);
		waitroom.setBounds(22, 332, 571, 185);
		getContentPane().add(waitroom);
		waitroom.setLayout(new BorderLayout(0, 0));

		JPanel panel = new JPanel();
		waitroom.add(panel, BorderLayout.SOUTH);
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

		JScrollPane scrollPane4 = new JScrollPane();
		panel.add(scrollPane4);

		chatField = new JTextField();

		chatField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
		
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					msgSummit();
					ChatSound.Audio("butten.wav");
				}
			}

		});
		scrollPane4.setViewportView(chatField);
		chatField.setColumns(10);

		sendBtn = new JButton("전송");
		sendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChatSound.Audio("butten.wav");
			}
		});
		sendBtn.setForeground(new Color(255, 255, 255));
		sendBtn.setBackground(new Color(255, 105, 180));
		sendBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				msgSummit();
				chatField.requestFocus();
			}
		});
		panel.add(sendBtn);

		JScrollPane scrollPane2 = new JScrollPane();
		waitroom.add(scrollPane2, BorderLayout.CENTER);

		waitRoomArea = new JTextArea();
		waitRoomArea.setBackground(new Color(255, 250, 240));
		waitRoomArea.setEditable(false);
		scrollPane2.setViewportView(waitRoomArea);

		JPanel info = new JPanel();
		lbid = new JLabel("-");
		info.add(lbid);
		lbnick = new JLabel("-");
		info.add(lbnick);
		lbip = new JTextField();
		lbip.setEditable(false);
		info.add(lbip);
		lbip.setColumns(10);

		chatField.requestFocus();
		setVisible(true);
		chatField.requestFocus();

		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				exit01();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		id = client.user.getId();
		switch (e.getActionCommand()) {
		case "귓속말":
			StringTokenizer token = new StringTokenizer(currentSelectedTreeNode, "("); // 토큰 생성
			temp = token.nextToken(); // 토큰으로 분리된 스트링
			temp = token.nextToken();
			String imsiId = id + ")";
			if(imsiId.equals(temp)) {
				JOptionPane.showMessageDialog(null, "귓속말 상대가 잘못되었습니다!");
				break;
			} else {
				id = "/" + temp.substring(0, temp.length() - 1) + " ";
				chatField.setText(id);
				chatField.requestFocus();				
			}
			break;
		// 메뉴1 파일 메뉴
		case "회원정보 변경":
			   DBUpdate dbu = new DBUpdate();
			   dbu.UpdateDB(id);
			   break;
		case "회원 탈퇴":
			DBDelete delDB = new DBDelete();

			int ans = JOptionPane.showConfirmDialog(this, "정말 탈퇴 하시겠습니까?", "탈퇴확인", JOptionPane.OK_CANCEL_OPTION);

			if (ans == 0) {
				int i = 0;
				i = delDB.InfoDel(id);
				if (i == 0) {
				} else {
					msgbox.messageBox(this, "탈퇴 되었습니다!");
					exit01();
				}
			}
			break;
		case "종료하기":
			int ans1 = JOptionPane.showConfirmDialog(this, "종료 하시겠습니까?", 
					"종료하기", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (ans1 == 0) {
				// System.exit(0); // 강제 종료
				try {
					client.getUser().getDos().writeUTF(User.LOGOUT);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				
				dispose();
				//new LoginUI(client).setVisible(true);
			}
			break;
		case "파일로저장":
			String filename = UtilFileIO.saveFile(waitRoomArea);
			JOptionPane.showMessageDialog(waitRoomArea.getParent(), "채팅내용을 파일명(" + filename + ")으로 저장하였습니다", "채팅백업", JOptionPane.INFORMATION_MESSAGE);
			break;
		case "파일열기":
			filename = UtilFileIO.getFilenameFromFileOpenDialog("./");
			if (filename == "") break;
			String text = UtilFileIO.loadFile(filename);
			TextViewUI textview = new TextViewUI(text);
			break;
		case "프로그램 정보":
			new ProgramUI().setVisible(true);
			break;
		case "테마1":
			title1.setTitleColor(new Color(0, 0, 0));
			title2.setTitleColor(new Color(0, 0, 0));
			title3.setTitleColor(new Color(0, 0, 0));
			backColor(211, 211, 211);
			chatColor(245, 245, 245);
			fontColor(0, 0, 0);
			lblNewLabel.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/chat_img3.png")).getImage()
					.getScaledInstance(149, 88, Image.SCALE_SMOOTH)));
			getContentPane().add(lblNewLabel);
			break;
		case "테마2":
			title1.setTitleColor(new Color(0, 191, 255));
			title2.setTitleColor(new Color(0, 191, 255));
			title3.setTitleColor(new Color(0, 191, 255));
			
			backColor(176, 224, 230);
			chatColor(245, 255, 250) ;
			fontColor(0, 191, 255);
			lblNewLabel.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/chat_img2.png")).getImage()
					.getScaledInstance(149, 88, Image.SCALE_SMOOTH)));
			getContentPane().add(lblNewLabel);
			
			break;
		case "테마3":
			title1.setTitleColor(new Color(255, 105, 180));
			title2.setTitleColor(new Color(255, 105, 180));
			title3.setTitleColor(new Color(255, 105, 180));
			
			backColor(255, 255, 255);
			chatColor(255, 250, 240);
			fontColor(255, 105, 180);
			lblNewLabel.setIcon(new ImageIcon(new ImageIcon(LoginUI.class.getResource("/chat/img/chat_img.png")).getImage()
					.getScaledInstance(149, 88, Image.SCALE_SMOOTH)));
			getContentPane().add(lblNewLabel);
			break;
		}

	}
	
	private void msgSummit() {
	      String string = chatField.getText();// 메시지전송
	      if (!string.equals("")) {
	         if (string.substring(0, 1).equals("/")) {
	            
	            StringTokenizer token = new StringTokenizer(string, " "); // 토큰 생성
	            String id = token.nextToken(); // 토큰으로 분리된 스트링
	            String msg = token.nextToken();
	            
	            try {
	               client.getDos().writeUTF(User.WHISPER + id + "/" + msg);
	               waitRoomArea.append(id + "님에게 귓속말 : " + msg + "\n");
	               //@@@귓속말시 스크롤바 밑으로
	               int pos =  waitRoomArea.getText().length();
	               waitRoomArea.setCaretPosition(pos);

	            } catch (IOException e) {
	               e.printStackTrace();
	            }
	            chatField.setText("");
	         } else {

	            try {
	               // 대기실에 메시지 보냄
	               client.getDos().writeUTF(User.ECHO01 + "/" + string);
	            } catch (IOException e) {
	               e.printStackTrace();
	            }
	            chatField.setText("");
	         }
	      }
	   }

	private void exit01() {
		try {
			client.getUser().getDos().writeUTF(User.LOGOUT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void createRoom() {
		String roomname = JOptionPane.showInputDialog("채팅방 이름을 입력하세요.");////////////
		
		if(roomname==null) {	// 취소 버튼
			
		} else {
			try {
		
				getContentPane().setEnabled(false);
				Room newRoom = new Room(roomname);	// 방 객체 생성
				newRoom.setRoomNum(lastRoomNum);
				newRoom.setrUI(new RoomUI(client, newRoom, c1,c2,c3,c4,c5,c6,c7,c8,c9));
				
				// 클라이언트가 접속한 방 목록에 추가
				client.getUser().getRoomArray().add(newRoom);
				
				client.getDos().writeUTF(User.CREATE_ROOM + "/" + newRoom.toProtocol());
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		}
	
	}

	private void getIn() {
		// 선택한 방 정보
		String selectedRoom = (String) roomList.getSelectedValue();
		StringTokenizer token = new StringTokenizer(selectedRoom, "/"); // 토큰 생성
		String rNum = token.nextToken();
		String rName = token.nextToken();
		System.out.println("방 이름 : " + rName);

		Room theRoom = new Room(rName); // 방 객체 생성
		theRoom.setRoomNum(Integer.parseInt(rNum)); // 방번호 설정
		theRoom.setrUI(new RoomUI(client, theRoom, c1,c2,c3,c4,c5,c6,c7,c8,c9)); // UI

		// 클라이언트가 접속한 방 목록에 추가
		client.getUser().getRoomArray().add(theRoom);

		try {
			client.getDos().writeUTF(User.GETIN_ROOM + "/" + theRoom.getRoomNum());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void backColor(int r, int g, int b) {
		c1 = r; c2 = g; c3 = b;
		getContentPane().setBackground(new Color(c1,c2,c3));
		room.setBackground(new Color(c1,c2,c3));
		waitroom.setBackground(new Color(c1,c2,c3));
		user.setBackground(new Color(c1,c2,c3));		
	}
	
	public void chatColor(int r, int g, int b) {
		c4 = r; c5 = g; c6 = b;
		roomList.setBackground(new Color(c4, c5, c6));
		waitRoomArea.setBackground(new Color(c4, c5, c6));
		userTree.setBackground(new Color(c4, c5, c6));
		menuBar.setBackground(new Color(c4, c5, c6));
	}
	
	public void fontColor(int r, int g, int b) {
		c7 = r; c8 = g; c9 = b;
		makeRoomBtn.setBackground(new Color(c7, c8, c9));
		getInRoomBtn.setForeground(new Color(c7, c8, c9));
		whisperBtn.setForeground(new Color(c7, c8, c9));
		basicMenus.setForeground(new Color(c7, c8, c9));
		mitSaveChatToFile.setForeground(new Color(c7, c8, c9));
		mitLoadChatFromFile.setForeground(new Color(c7, c8, c9));
		updndel.setForeground(new Color(c7, c8, c9));
		changeInfo.setForeground(new Color(c7, c8, c9));
		withdrawMem.setForeground(new Color(c7, c8, c9));
		helpMenus.setForeground(new Color(c7, c8, c9));
		proInfoItem.setForeground(new Color(c7, c8, c9));
		opMenus.setForeground(new Color(c7, c8, c9));
		proInfoItem2.setForeground(new Color(c7, c8, c9));
		proInfoItem3.setForeground(new Color(c7, c8, c9));
		proInfoItem4.setForeground(new Color(c7, c8, c9));
		sendBtn.setBackground(new Color(c7, c8, c9));
	}
}


class Maker extends JPanel {
	public Maker() {
		super();
		initialize();
	}

	private void initialize() {
		this.setLayout(new GridLayout(3, 1));

	
	}
}