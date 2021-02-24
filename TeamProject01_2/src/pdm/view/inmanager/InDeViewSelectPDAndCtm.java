package pdm.view.inmanager;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import pdm.vo.VOCustomer;
import pdm.vo.VOProduct;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class InDeViewSelectPDAndCtm extends JFrame {
	private JPanel panelDeListPd;
	private JTextField tfListPd;
	private JTable tableListPd, tableListCtm;
	private JButton btnListPd;
	private JLabel lblListPd;
	private JScrollPane scrollPaneListPd;
	private JPanel panelDeListCtm;
	private JLabel lblListCtm;
	private JTextField tfListCtm;
	private JButton btnListCtm;
	private JScrollPane scrollPaneListCtm;
	private DefaultTableModel pdmodel, ctmmodel;
	public InDeViewSelectPDAndCtm() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("거래 대상 조회");
		getContentPane().setLayout(null);
		setBounds(14, 80, 450, 390);
		panelDeListPd = new JPanel();
		panelDeListPd.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDeListPd.setBounds(18, 10, 181, 287);
		getContentPane().add(panelDeListPd);
		panelDeListPd.setLayout(null);
		
		lblListPd = new JLabel("\uD488\uBAA9");
		lblListPd.setFont(new Font("굴림", Font.BOLD, 17));
		lblListPd.setBounds(65, 31, 41, 18);
		panelDeListPd.add(lblListPd);
		
		tfListPd = new JTextField();
		tfListPd.setBounds(14, 61, 92, 24);
		panelDeListPd.add(tfListPd);
		tfListPd.setColumns(10);
		
		btnListPd = new JButton("\uAC80\uC0C9");
		btnListPd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pdmodel.setNumRows(0);
				String searchPd = tfListPd.getText();
				try {
					ArrayList<VOProduct> listpd = new InViewDAO().getProductList(searchPd);
					for(int i = 0; i < listpd.size(); i++) {
						pdmodel.addRow(new String[] {listpd.get(i).getProduct_code(), listpd.get(i).getProduct_name()});
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
		btnListPd.setFont(new Font("굴림", Font.BOLD, 10));
		btnListPd.setBounds(106, 61, 61, 24);
		panelDeListPd.add(btnListPd);
		
		String[][] pdrow = new String[][] { };
		String[] pdcol = new String[] { "물품코드", "물품명"};
		
		pdmodel = new DefaultTableModel(pdrow,pdcol);
		
		tableListPd = new JTable(pdmodel);
		
		scrollPaneListPd = new JScrollPane(tableListPd);
		scrollPaneListPd.setBounds(14, 97, 153, 166);
		panelDeListPd.add(scrollPaneListPd);
		
		panelDeListCtm = new JPanel();
		panelDeListCtm.setLayout(null);
		panelDeListCtm.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelDeListCtm.setBounds(226, 10, 196, 287);
		getContentPane().add(panelDeListCtm);
		
		lblListCtm = new JLabel("\uAC70\uB798\uCC98");
		lblListCtm.setFont(new Font("굴림", Font.BOLD, 17));
		lblListCtm.setBounds(65, 31, 54, 18);
		panelDeListCtm.add(lblListCtm);
		
		tfListCtm = new JTextField();
		tfListCtm.setColumns(10);
		tfListCtm.setBounds(14, 61, 108, 24);
		panelDeListCtm.add(tfListCtm);
		
		btnListCtm = new JButton("\uAC80\uC0C9");
		btnListCtm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ctmmodel.setNumRows(0);
				String searchCtm = tfListCtm.getText();
				try {
					ArrayList<VOCustomer> listctm = new InViewDAO().getCustomerList(searchCtm);
					for(int i = 0; i < listctm.size(); i++) {
						ctmmodel.addRow(new String[] {listctm.get(i).getCustomer_code(), listctm.get(i).getCustomer_name()});
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
		btnListCtm.setFont(new Font("굴림", Font.BOLD, 10));
		btnListCtm.setBounds(121, 61, 61, 24);
		panelDeListCtm.add(btnListCtm);
		
		String[][] ctmrow = new String[][] { };
		String[] ctmcol = new String[] { "거래처코드", "거래처명"};
		ctmmodel = new DefaultTableModel(ctmrow, ctmcol);
		tableListCtm = new JTable(ctmmodel);
		
		scrollPaneListCtm = new JScrollPane(tableListCtm);
		scrollPaneListCtm.setBounds(14, 97, 168, 166);
		panelDeListCtm.add(scrollPaneListCtm);
		
		JButton btnInsert = new JButton("확인");
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int pdrow = tableListPd.getSelectedRow();
				int ctmrow = tableListCtm.getSelectedRow();
				new InViewInsertDe().setLabel((String)tableListPd.getValueAt(pdrow, 0), (String)tableListCtm.getValueAt(ctmrow, 0));
			
				dispose();
			}
		});
		btnInsert.setFont(new Font("굴림", Font.BOLD, 18));
		btnInsert.setBounds(317, 297, 105, 34);
		getContentPane().add(btnInsert);
		
		setVisible(true);
		setLocationRelativeTo(null);
	}
	public void settable() {
		pdmodel.setNumRows(0);
		String searchPd = tfListPd.getText();
		try {
			ArrayList<VOProduct> listpd = new InViewDAO().getProductList(searchPd);
			for(int i = 0; i < listpd.size(); i++) {
				pdmodel.addRow(new String[] {listpd.get(i).getProduct_code(), listpd.get(i).getProduct_name()});
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ctmmodel.setNumRows(0);
		String searchCtm = tfListCtm.getText();
		try {
			ArrayList<VOCustomer> listctm = new InViewDAO().getCustomerList(searchCtm);
			for(int i = 0; i < listctm.size(); i++) {
				ctmmodel.addRow(new String[] {listctm.get(i).getCustomer_code(), listctm.get(i).getCustomer_name()});
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
}
