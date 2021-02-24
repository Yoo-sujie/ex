package pdm.view.pdmanager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class PdViewInsertPd extends JFrame {
	private JTextField textFieldPdName, textFieldPdPrice, textFieldPdStock;
	private JLabel lblPdName, lblPdPrice, lblStrock, labelPdType, lblPdInfo;
	private JComboBox<String> comboBoxPdType;
	private JScrollPane scrollPanePdInfo;
	private JTextArea textAreaPdInfo;
	private JButton btnPdCancel, btnPdInsert;
	private JLabel label;
	private JComboBox<String> comboBox;
	private JLabel label_1;
	private JComboBox<String> comboBox_1;
	public PdViewInsertPd() throws NumberFormatException, ClassNotFoundException, SQLException{
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("물품 등록");
		getContentPane().setLayout(null);

		setBounds(224, 5, 457, 465);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);

		lblPdName = new JLabel("\uBB3C\uD488\uBA85");
		lblPdName.setFont(new Font("굴림", Font.BOLD, 20));
		lblPdName.setBounds(33, 26, 88, 29);
		getContentPane().add(lblPdName);

		textFieldPdName = new JTextField();
		textFieldPdName.setColumns(10);
		textFieldPdName.setBounds(133, 26, 193, 30);
		getContentPane().add(textFieldPdName);

		lblPdPrice = new JLabel("\uAC00\uACA9");
		lblPdPrice.setFont(new Font("굴림", Font.BOLD, 20));
		lblPdPrice.setBounds(33, 68, 88, 29);
		getContentPane().add(lblPdPrice);

		textFieldPdPrice = new JTextField();
		textFieldPdPrice.setColumns(10);
		textFieldPdPrice.setBounds(133, 68, 193, 30);
		getContentPane().add(textFieldPdPrice);

		lblStrock = new JLabel("\uC7AC\uACE0");
		lblStrock.setFont(new Font("굴림", Font.BOLD, 20));
		lblStrock.setBounds(33, 110, 88, 29);
		getContentPane().add(lblStrock);

		textFieldPdStock = new JTextField();
		textFieldPdStock.setColumns(10);
		textFieldPdStock.setBounds(133, 110, 193, 30);
		getContentPane().add(textFieldPdStock);

		labelPdType = new JLabel("\uBD84\uB958");
		labelPdType.setFont(new Font("굴림", Font.BOLD, 20));
		labelPdType.setBounds(33, 185, 88, 29);
		getContentPane().add(labelPdType);

		comboBoxPdType = new JComboBox<String>();
		comboBoxPdType.setModel(new DefaultComboBoxModel(new String[] {"감기약", "소화제", "진통제", "마취제", "항균연고", "생리식염수", "위장약", "멀미약", "알러지약", "점이약", "국소진통약", "국소항염약"}));
		comboBoxPdType.setBounds(133, 190, 139, 24);
		getContentPane().add(comboBoxPdType);

		lblPdInfo = new JLabel("상세 정보");
		lblPdInfo.setFont(new Font("굴림", Font.BOLD, 20));
		lblPdInfo.setBounds(33, 224, 106, 29);
		getContentPane().add(lblPdInfo);

		scrollPanePdInfo = new JScrollPane();
		scrollPanePdInfo.setBounds(33, 263, 392, 73);
		getContentPane().add(scrollPanePdInfo);

		textAreaPdInfo = new JTextArea();
		scrollPanePdInfo.setViewportView(textAreaPdInfo);

		btnPdCancel = new JButton("\uCDE8\uC18C");
		btnPdCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnPdCancel.setBounds(281, 346, 144, 53);
		getContentPane().add(btnPdCancel);

		btnPdInsert = new JButton("등록");
		btnPdInsert.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				
				String name = textFieldPdName.getText();
				String info = textAreaPdInfo.getText();
				String large = comboBox.getSelectedItem().toString();
				String medium = comboBox_1.getSelectedItem().toString();
				String small = comboBoxPdType.getSelectedItem().toString();
				int price = 0, stock = 0;
				
				if(name.equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "물품명을 입력해주세요", "물품명 입력",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if(textFieldPdPrice.getText().equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "가격을 입력해주세요", "가격 입력",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if(textFieldPdStock.getText().equals("")) {
					JOptionPane.showMessageDialog(getContentPane(), "재고를 입력해주세요", "입력 오류",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				
				if(info.equals("")) info += " ";	// null값 삽입 방지
				
				try {	// 가격 입력 확인
					price = Integer.parseInt(textFieldPdPrice.getText());
				} catch(NumberFormatException numberE) {
					JOptionPane.showMessageDialog(null, "수량은 숫자로 입력해 주세요", "입력 에러",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try {	// 재고 입력 확인
					stock = Integer.parseInt(textFieldPdStock.getText());
				} catch(NumberFormatException numberE) {
					JOptionPane.showMessageDialog(null, "재고는 숫자로 입력해 주세요", "입력 오류",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				try {
					result = new PdViewDAO().insertproduct(name,large,  medium,  small,  price, info, stock);
					if(result > 0) {
						JOptionPane.showMessageDialog(null, "물품 정보가 등록되었습니다", "등록 완료",JOptionPane.INFORMATION_MESSAGE);
						clearField();
					}
					else {
						JOptionPane.showMessageDialog(null, "등록에 실패했습니다", "등록 실패",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (ClassNotFoundException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
			
		});
		btnPdInsert.setBounds(33, 346, 144, 53);
		getContentPane().add(btnPdInsert);
		
		label = new JLabel("의약외품");
		label.setFont(new Font("굴림", Font.BOLD, 20));
		label.setBounds(33, 151, 88, 29);
		getContentPane().add(label);
		
		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"내용고형제", "주사제", "점안제", "내용액제", "외용액제", "연고제"}));
		comboBox.setBounds(133, 153, 98, 24);
		getContentPane().add(comboBox);
		
		label_1 = new JLabel("제형");
		label_1.setFont(new Font("굴림", Font.BOLD, 20));
		label_1.setBounds(258, 151, 50, 29);
		getContentPane().add(label_1);
		
		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"정제형알약", "캡슐형알약", "주사제", "수액제", "안연고형", "안약형", "시럽제", "경구용액제", "로션제", "점이제", "크림제", "페이스트제"}));
		comboBox_1.setBounds(307, 153, 98, 24);
		getContentPane().add(comboBox_1);
		
		JLabel lblbox = new JLabel("(만원/Box)");
		lblbox.setFont(new Font("굴림", Font.BOLD, 17));
		lblbox.setBounds(338, 68, 90, 29);
		getContentPane().add(lblbox);
		
		JLabel lblbox_1 = new JLabel("(Box)");
		lblbox_1.setFont(new Font("굴림", Font.BOLD, 17));
		lblbox_1.setBounds(338, 110, 90, 29);
		getContentPane().add(lblbox_1);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void clearField() {
		textFieldPdName.setText("");
		textFieldPdPrice.setText("");
		textFieldPdStock.setText("");
		comboBox.setSelectedIndex(0);
		comboBox_1.setSelectedIndex(0);
		comboBoxPdType.setSelectedIndex(0);
		textAreaPdInfo.setText("");
	}
}

