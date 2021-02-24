package pdm.view.outmanager;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class OutViewInsertDe extends JFrame {
	private JTextField tfDeQuantity;
	private JLabel lblDeQuantity, lblPdInfo, lblDeSelectPd, lblDeSelectCtm;
	private JLabel lblDeSelectedPd, lblDeSelectedCtm;
	private JScrollPane scrollPanePdInfo;
	private JTextArea textAreaPdInfo;
	private JButton btnPdCancel, btnPdInsert;
	private JButton btnDeSet;
	private JLabel lblbox;
	public OutViewInsertDe() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("판매정보등록");
		getContentPane().setLayout(null);

		setBounds(224, 5, 457, 483);
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);

		lblDeQuantity = new JLabel("\uC218\uB7C9");
		lblDeQuantity.setFont(new Font("굴림", Font.BOLD, 20));
		lblDeQuantity.setBounds(30, 128, 88, 29);
		getContentPane().add(lblDeQuantity);

		tfDeQuantity = new JTextField();
		tfDeQuantity.setColumns(10);
		tfDeQuantity.setBounds(157, 127, 193, 30);
		getContentPane().add(tfDeQuantity);

		lblPdInfo = new JLabel("상세 정보");
		lblPdInfo.setFont(new Font("굴림", Font.BOLD, 20));
		lblPdInfo.setBounds(30, 182, 106, 29);
		getContentPane().add(lblPdInfo);

		scrollPanePdInfo = new JScrollPane();
		scrollPanePdInfo.setBounds(30, 221, 395, 137);
		getContentPane().add(scrollPanePdInfo);

		textAreaPdInfo = new JTextArea();
		scrollPanePdInfo.setViewportView(textAreaPdInfo);

		btnPdCancel = new JButton("\uCDE8\uC18C");
		btnPdCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnPdCancel.setBounds(281, 368, 144, 53);
		getContentPane().add(btnPdCancel);

		btnPdInsert = new JButton("\uD655\uC778");
		btnPdInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = 0;
				String pdCode = lblDeSelectedPd.getText();
				String ctmCode = lblDeSelectedCtm.getText();
				int quantity = 0;
				
				if(pdCode.equals("") || ctmCode.equals("")) {
					JOptionPane.showMessageDialog(null, "물품 및 거래처를 선택해 주세요", "물품 및 거래서 선택",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				else if(tfDeQuantity.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "수량을 입력해주세요", "수량 입력",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				try {
					quantity = Integer.parseInt(tfDeQuantity.getText());
				} catch (NumberFormatException numberE) {
					JOptionPane.showMessageDialog(null, "수량은 숫자로 입력해 주세요", "입력 오류",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				
				
				String info = textAreaPdInfo.getText();
				if(info.equals("")) info += " 출고 상세 정보";	// null값 삽입 방지
				
				try {
					result = new OutViewDAO().insertData(pdCode, ctmCode, quantity, info);
					if(result > 0) {
						JOptionPane.showMessageDialog(null, "등록 되엇습니다", "등록",JOptionPane.INFORMATION_MESSAGE);
						clearField();
					}
					else {
						JOptionPane.showMessageDialog(null, "등록에 실패했습니다.", "등록 오류",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
						
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				try {
					result = new OutViewDAO().reflectStock(pdCode, quantity);
					if(result > 0) {
						
					}
					else {
						
					}
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnPdInsert.setBounds(30, 368, 144, 53);
		getContentPane().add(btnPdInsert);

		lblDeSelectPd = new JLabel("\uBB3C\uD488");
		lblDeSelectPd.setFont(new Font("굴림", Font.BOLD, 20));
		lblDeSelectPd.setBounds(30, 26, 88, 29);
		getContentPane().add(lblDeSelectPd);

		lblDeSelectCtm = new JLabel("\uAC70\uB798\uCC98");
		lblDeSelectCtm.setFont(new Font("굴림", Font.BOLD, 20));
		lblDeSelectCtm.setBounds(30, 67, 88, 29);
		getContentPane().add(lblDeSelectCtm);

		btnDeSet = new JButton("\uC870\uD68C");
		btnDeSet.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				OutDeViewSelectPDAndCtm o = new OutDeViewSelectPDAndCtm();
				o.setPdTable();
				o.setCtmTable();
				dispose();
			}
		});

		btnDeSet.setBounds(356, 26, 69, 53);
		getContentPane().add(btnDeSet);

		lblDeSelectedPd = new JLabel("");
		lblDeSelectedPd.setFont(new Font("굴림", Font.PLAIN, 19));
		lblDeSelectedPd.setBounds(157, 26, 88, 29);
		getContentPane().add(lblDeSelectedPd);

		lblDeSelectedCtm = new JLabel("");
		lblDeSelectedCtm.setFont(new Font("굴림", Font.PLAIN, 19));
		lblDeSelectedCtm.setBounds(157, 67, 139, 29);
		getContentPane().add(lblDeSelectedCtm);

		lblbox = new JLabel("(Box)");
		lblbox.setFont(new Font("굴림", Font.BOLD, 18));
		lblbox.setBounds(367, 127, 58, 29);
		getContentPane().add(lblbox);
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	
	public void setLabel(String lblDeSelectedPd, String lblDeSelectedCtm) {
		this.lblDeSelectedPd.setText(lblDeSelectedPd);
		this.lblDeSelectedCtm.setText(lblDeSelectedCtm);
	}
	
	public void clearField() {
		lblDeSelectedPd.setText("");
		lblDeSelectedCtm.setText("");
		tfDeQuantity.setText("");
		textAreaPdInfo.setText("");
	}
}
