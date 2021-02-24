package pdm.view.ctmmanager;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class CtmViewInsertCtm extends JFrame {
	private JTextField textFieldCtmName, textFieldCtmTel, tfCtmAddress;
	private JLabel lbCtmName, lblCtmTel, lblCtmaddress, lblCtmInfo;
	private JScrollPane scrollPanePdInfo;
	private JTextArea textAreaCtmInfo;
	private JButton btnPdCancel, btnPdInsert;
	private JTextField textField;
	public CtmViewInsertCtm() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("\uAC70\uB798\uCC98 \uB4F1\uB85D");
		getContentPane().setLayout(null);

		setBounds(224, 5, 457, 465);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);

		lbCtmName = new JLabel("\uAC70\uB798\uCC98\uBA85");
		lbCtmName.setFont(new Font("굴림", Font.BOLD, 20));
		lbCtmName.setBounds(33, 26, 88, 29);
		getContentPane().add(lbCtmName);

		textFieldCtmName = new JTextField();
		textFieldCtmName.setColumns(10);
		textFieldCtmName.setBounds(160, 25, 193, 30);
		getContentPane().add(textFieldCtmName);

		lblCtmTel = new JLabel("\uB300\uD45C\uBC88\uD638");
		lblCtmTel.setFont(new Font("굴림", Font.BOLD, 20));
		lblCtmTel.setBounds(33, 68, 88, 29);
		getContentPane().add(lblCtmTel);

		textFieldCtmTel = new JTextField();
		textFieldCtmTel.setColumns(10);
		textFieldCtmTel.setBounds(160, 67, 193, 30);
		getContentPane().add(textFieldCtmTel);

		lblCtmaddress = new JLabel("\uC8FC\uC18C");
		lblCtmaddress.setFont(new Font("굴림", Font.BOLD, 20));
		lblCtmaddress.setBounds(33, 110, 88, 29);
		getContentPane().add(lblCtmaddress);

		tfCtmAddress = new JTextField();
		tfCtmAddress.setColumns(10);
		tfCtmAddress.setBounds(160, 109, 193, 30);
		getContentPane().add(tfCtmAddress);

		lblCtmInfo = new JLabel("상세 정보");
		lblCtmInfo.setFont(new Font("굴림", Font.BOLD, 20));
		lblCtmInfo.setBounds(33, 197, 106, 29);
		getContentPane().add(lblCtmInfo);

		scrollPanePdInfo = new JScrollPane();
		scrollPanePdInfo.setBounds(33, 238, 396, 96);
		getContentPane().add(scrollPanePdInfo);
		
				textAreaCtmInfo = new JTextArea();
				scrollPanePdInfo.setViewportView(textAreaCtmInfo);

		btnPdCancel = new JButton("\uCDE8\uC18C");
		btnPdCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
			}
		});
		btnPdCancel.setBounds(281, 344, 144, 53);
		getContentPane().add(btnPdCancel);

		btnPdInsert = new JButton("\uD655\uC778");
		btnPdInsert.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SimpleDateFormat dataformat = new SimpleDateFormat("yyMMddHHmmss");
				int result;
				
				String code = "C" + dataformat.format(new Date());
				String name = textFieldCtmName.getText();
				String tel = textFieldCtmTel.getText();
				String email = textField.getText();
				String address = tfCtmAddress.getText();
				String description = textAreaCtmInfo.getText();
				if(description.equals("")) description += " 거래처 상세 정보";	// null값 삽입 방지
				
				if(name.equals("")) {
					JOptionPane.showMessageDialog(null, "거래처명을 입력해 주세요", "거래처명 입력",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if(tel.equals("")) {
					JOptionPane.showMessageDialog(null, "대표번호를 입력해 주세요", "대표번호 입력",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if(email.equals("")) {
					JOptionPane.showMessageDialog(null, "이메일을 입력해 주세요", "이메일 입력",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if(address.equals("")) {
					JOptionPane.showMessageDialog(null, "소재지를 입력해 주세요", "소재지 입력",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				try {
					result = new CtmViewDAO().getInsert(code, name, tel, address, email, description);
					if(result > 0) {
						JOptionPane.showMessageDialog(null, "등록 되엇습니다", "등록",JOptionPane.INFORMATION_MESSAGE);
						clearField();
					}
					else {
						JOptionPane.showMessageDialog(null, "등록에 실패했습니다.", "등록 오류",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
				
			}
		});
		btnPdInsert.setBounds(33, 344, 144, 53);
		getContentPane().add(btnPdInsert);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(160, 151, 193, 30);
		getContentPane().add(textField);
		
		JLabel lblEmail = new JLabel("email");
		lblEmail.setFont(new Font("굴림", Font.BOLD, 20));
		lblEmail.setBounds(33, 152, 88, 29);
		getContentPane().add(lblEmail);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public void clearField() {
		textFieldCtmName.setText("");
		textFieldCtmTel.setText("");
		tfCtmAddress.setText("");
		textField.setText("");
		textAreaCtmInfo.setText("");
	}
}
