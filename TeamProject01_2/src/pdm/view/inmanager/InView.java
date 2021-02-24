package pdm.view.inmanager;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import pdm.vo.VOInProduct;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;

public class InView extends JPanel {
	private JTextField tfDeQuantity, textFieldDeSearch;
	private JTable tableDeList;
	private ButtonGroup btngpsort;
	private JPanel paneDelInfo, paneDelList;
	private JLabel lblDeInfo, lblDeCode, lblDeCustomer, lblDeQuantity, labelDeQuantity2;
	private JLabel labelDeCode, lblDeTotal;
	private JScrollPane scrollPaneDeList;
	private JButton btnDeInsert, btnDeUpdate, btnDeDelete, btnDeSearchAll, btnDeSelectIndex, btnDetoday;
	private JComboBox<String> cbDeSeach, cbDeSort;
	private JRadioButton rdbtnDeSortToCode, rdbtnDeSortToTotal, rdbtnDeSortToDate, rdbtnDeSortToQuantity;
	private JLabel lblDeTotal2;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JLabel label_1;
	private JLabel lblDeDate;
	private JTextField tfDeDate;
	private DefaultTableModel tradeListModel ;
	private JLabel lblTotalMoney;
	private JLabel lblDeProductCode;
	private JLabel lblDeCustomerCode;
	private JTextField tfDeProductName;
	private JTextField tfDeCustomerName;
	private int tableselectedRow, rownum;
	private JButton button;
	FileOutputStream fos = null;
	HSSFSheet sheet = null;
	HSSFWorkbook workbook ;
	public InView() {
		setLayout(null);
		btngpsort = new ButtonGroup();
		paneDelInfo = new JPanel();
		paneDelInfo.setBorder(new LineBorder(new Color(0, 0, 0)));
		paneDelInfo.setBounds(43, 25, 406, 479);
		add(paneDelInfo);
		paneDelInfo.setLayout(null);

		lblDeInfo = new JLabel("입고 정보");
		lblDeInfo.setBounds(128, 12, 130, 29);
		paneDelInfo.add(lblDeInfo);
		lblDeInfo.setFont(new Font("굴림", Font.BOLD, 24));

		lblDeCode = new JLabel("입고코드");
		lblDeCode.setFont(new Font("굴림", Font.BOLD, 17));
		lblDeCode.setBounds(14, 54, 97, 29);
		paneDelInfo.add(lblDeCode);

		lblDeCustomer = new JLabel("\uAC70\uB798\uCC98");
		lblDeCustomer.setFont(new Font("굴림", Font.BOLD, 17));
		lblDeCustomer.setBounds(14, 139, 97, 29);
		paneDelInfo.add(lblDeCustomer);

		lblDeQuantity = new JLabel("\uC218\uB7C9");
		lblDeQuantity.setFont(new Font("굴림", Font.BOLD, 17));
		lblDeQuantity.setBounds(14, 178, 97, 29);
		paneDelInfo.add(lblDeQuantity);

		tfDeQuantity = new JTextField();
		tfDeQuantity.setColumns(10);
		tfDeQuantity.setBounds(102, 179, 177, 29);
		paneDelInfo.add(tfDeQuantity);

		labelDeQuantity2 = new JLabel("(Box)");
		labelDeQuantity2.setFont(new Font("굴림", Font.BOLD, 17));
		labelDeQuantity2.setBounds(291, 179, 90, 29);
		paneDelInfo.add(labelDeQuantity2);

		btnDeInsert = new JButton("등록");
		btnDeInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InViewInsertDe();
			}
		});
		btnDeInsert.setBackground(new Color(112, 128, 144));
		btnDeInsert.setFont(new Font("굴림", Font.BOLD, 20));
		btnDeInsert.setBounds(14, 405, 110, 62);
		paneDelInfo.add(btnDeInsert);

		btnDeUpdate = new JButton("\uC218\uC815");
		btnDeUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String inCode = labelDeCode.getText();
				String pdName = tfDeProductName.getText();
				String pdCode = lblDeProductCode.getText();
				String date = tfDeDate.getText();
				String info = textArea.getText();
				int total = Integer.parseInt(lblTotalMoney.getText());
				if(info.equals("")) info += " 입고 상세 정보";	// null값 삽입 방지
				
				int Quantity = 0;
				try {
				Quantity = Integer.parseInt(tfDeQuantity.getText());
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "수량은 숫자로 입력해 주세요", "입력 에러",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				int[] resultAndQuantity = {0,0};
				try {
					resultAndQuantity = new InViewDAO().updateTradeTable(inCode, Quantity, pdCode, pdName, date, info, total);
					if(resultAndQuantity[1] > 0) {
						JOptionPane.showMessageDialog(null, inCode + "의 내용이 수정되었습니다", "수정 완료",JOptionPane.INFORMATION_MESSAGE);
						VOInProduct inPd = new VOInProduct(inCode, lblDeCustomerCode.getText(), pdCode, Quantity, Integer.parseInt(lblTotalMoney.getText()),date, info);
						updateTable(inPd, tableselectedRow);
						clearField();
					}
					else {
						JOptionPane.showMessageDialog(null, inCode + "의 내용이 수정이 실패 했습니다", "수정 에러",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				try {
					int result = new InViewDAO().reflectStockToUpdate(pdCode, Quantity, resultAndQuantity[0]);
					
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 

			}
		});
		btnDeUpdate.setFont(new Font("굴림", Font.BOLD, 20));
		btnDeUpdate.setBackground(new Color(112, 128, 144));
		btnDeUpdate.setBounds(143, 405, 115, 62);
		paneDelInfo.add(btnDeUpdate);

		btnDeDelete = new JButton("\uC0AD\uC81C");
		btnDeDelete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String inCode = labelDeCode.getText();
					int result = 0;
					try {
						result = new InViewDAO().deleteIn(inCode);
					} catch (ClassNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					if(result > 0) {
						JOptionPane.showMessageDialog(null, inCode + "이 삭제되었습니다", "삭제 완료",JOptionPane.INFORMATION_MESSAGE);
						((DefaultTableModel)tableDeList.getModel()).removeRow(tableselectedRow);
						clearField();
					}
					else {
						JOptionPane.showMessageDialog(null, inCode + "의 삭제에 실패했습니다.", "삭제 살패",JOptionPane.INFORMATION_MESSAGE);
					}
					
				}
				
		});
		btnDeDelete.setFont(new Font("굴림", Font.BOLD, 20));
		btnDeDelete.setBackground(new Color(112, 128, 144));
		btnDeDelete.setBounds(277, 404, 115, 62);
		paneDelInfo.add(btnDeDelete);

		labelDeCode = new JLabel("");
		labelDeCode.setFont(new Font("굴림", Font.BOLD, 17));
		labelDeCode.setBounds(102, 54, 151, 29);
		paneDelInfo.add(labelDeCode);

		lblDeTotal = new JLabel("\uCD1D\uC561");
		lblDeTotal.setFont(new Font("굴림", Font.BOLD, 17));
		lblDeTotal.setBounds(14, 217, 97, 29);
		paneDelInfo.add(lblDeTotal);

		lblDeTotal2 = new JLabel("(\uB9CC\uC6D0)");
		lblDeTotal2.setFont(new Font("굴림", Font.BOLD, 17));
		lblDeTotal2.setBounds(291, 217, 90, 29);
		paneDelInfo.add(lblDeTotal2);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(102, 303, 265, 92);
		paneDelInfo.add(scrollPane);

		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);

		label_1 = new JLabel("상세 정보");
		label_1.setFont(new Font("굴림", Font.BOLD, 17));
		label_1.setBounds(14, 296, 76, 74);
		paneDelInfo.add(label_1);

		lblDeDate = new JLabel("\uB0A0\uC9DC");
		lblDeDate.setFont(new Font("굴림", Font.BOLD, 17));
		lblDeDate.setBounds(14, 256, 97, 29);
		paneDelInfo.add(lblDeDate);

		tfDeDate = new JTextField();
		tfDeDate.setColumns(10);
		tfDeDate.setBounds(102, 257, 177, 29);
		paneDelInfo.add(tfDeDate);

		JLabel lblDeProduct = new JLabel("\uBB3C\uD488\uBA85");
		lblDeProduct.setFont(new Font("굴림", Font.BOLD, 17));
		lblDeProduct.setBounds(14, 95, 97, 29);
		paneDelInfo.add(lblDeProduct);

		lblTotalMoney = new JLabel("");
		lblTotalMoney.setFont(new Font("굴림", Font.BOLD, 17));
		lblTotalMoney.setBounds(102, 217, 177, 29);
		paneDelInfo.add(lblTotalMoney);

		lblDeProductCode = new JLabel("");
		lblDeProductCode.setFont(new Font("굴림", Font.BOLD, 16));
		lblDeProductCode.setBounds(249, 98, 132, 29);
		paneDelInfo.add(lblDeProductCode);
		lblDeCustomerCode = new JLabel("");
		lblDeCustomerCode.setFont(new Font("굴림", Font.BOLD, 16));
		lblDeCustomerCode.setBounds(249, 139, 132, 29);
		paneDelInfo.add(lblDeCustomerCode);

		tfDeProductName = new JTextField();
		tfDeProductName.setColumns(10);
		tfDeProductName.setBounds(102, 98, 143, 29);
		paneDelInfo.add(tfDeProductName);

		tfDeCustomerName = new JTextField();
		tfDeCustomerName.setColumns(10);
		tfDeCustomerName.setBounds(102, 140, 143, 29);
		paneDelInfo.add(tfDeCustomerName);

		paneDelList = new JPanel();
		paneDelList.setBorder(new LineBorder(new Color(0, 0, 0)));
		paneDelList.setBounds(500, 25, 429, 479);
		add(paneDelList);
		paneDelList.setLayout(null);

		btnDeSearchAll = new JButton("\uC804\uCCB4 \uC870\uD68C");
		btnDeSearchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 정렬 기준 설정
				String orderBy = cbDeSort.getSelectedItem().toString();
				String orderValue  = getradioButtonText();

				if(orderBy.equals("오름차순")) orderBy = "ASC";
				else orderBy = "DESC";

				if(orderValue.equals("코드")) orderValue = "in_code";
				else if(orderValue.equals("수량")) orderValue = "in_quantity";
				else if(orderValue.equals("총액")) orderValue = "in_amount";
				else orderValue = "output_date";


				try {
					//	전체 조회 쿼리, 판매 목록 리스트 반환
					ArrayList<VOInProduct> inpdlist = new InViewDAO().selectAll(orderBy, orderValue);
					//	테이블 세팅 메서드
					setTable(inpdlist);
					return;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnDeSearchAll.setFont(new Font("굴림", Font.BOLD, 14));
		btnDeSearchAll.setBackground(new Color(112, 128, 144));
		btnDeSearchAll.setBounds(297, 16, 120, 52);
		paneDelList.add(btnDeSearchAll);

		String[][] inrow = new String[][] { };
		String[] incol = new String[] { "입고코드","입고품목","거래처","수량","총액","날짜","정보"};

		tradeListModel = new DefaultTableModel(inrow, incol);
		tableDeList = new JTable(tradeListModel);
		tableDeList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				tableselectedRow = tableDeList.getSelectedRow();
				try {
					String pdName = new InViewDAO().getPdName((String)tableDeList.getValueAt(tableselectedRow, 1));
					String ctmName = new InViewDAO().getCtmName((String)tableDeList.getValueAt(tableselectedRow, 2));
					labelDeCode.setText((String)tableDeList.getValueAt(tableselectedRow, 0));
					tfDeProductName.setText( pdName);
					lblDeProductCode.setText((String) tableDeList.getValueAt(tableselectedRow, 1));
					tfDeCustomerName.setText(ctmName);
					lblDeCustomerCode.setText((String)tableDeList.getValueAt(tableselectedRow, 2));
					tfDeQuantity.setText((String)tableDeList.getValueAt(tableselectedRow, 3));
					lblTotalMoney.setText((String)tableDeList.getValueAt(tableselectedRow, 4));
					tfDeDate.setText((String)tableDeList.getValueAt(tableselectedRow, 5));
					textArea.setText((String)tableDeList.getValueAt(tableselectedRow, 6));
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		scrollPaneDeList = new JScrollPane(tableDeList);
		scrollPaneDeList.setBounds(14, 159, 403, 260);
		paneDelList.add(scrollPaneDeList);

		cbDeSort = new JComboBox<String>();
		cbDeSort.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC624\uB984\uCC28\uC21C", "\uB0B4\uB9BC\uCC28\uC21C"}));
		cbDeSort.setBounds(14, 125, 79, 24);
		paneDelList.add(cbDeSort);

		rdbtnDeSortToCode = new JRadioButton("\uCF54\uB4DC");
		rdbtnDeSortToCode.setBounds(112, 124, 57, 27);
		paneDelList.add(rdbtnDeSortToCode);
		rdbtnDeSortToCode.setSelected(true);
		btngpsort.add(rdbtnDeSortToCode);

		rdbtnDeSortToQuantity = new JRadioButton("\uC218\uB7C9");
		rdbtnDeSortToQuantity.setBounds(186, 124, 57, 27);
		paneDelList.add(rdbtnDeSortToQuantity);
		btngpsort.add(rdbtnDeSortToQuantity);

		rdbtnDeSortToTotal = new JRadioButton("\uCD1D\uC561");
		rdbtnDeSortToTotal.setBounds(265, 124, 57, 27);
		paneDelList.add(rdbtnDeSortToTotal);
		btngpsort.add(rdbtnDeSortToTotal);

		rdbtnDeSortToDate = new JRadioButton("\uB0A0\uC9DC");
		rdbtnDeSortToDate.setBounds(344, 124, 57, 27);
		paneDelList.add(rdbtnDeSortToDate);
		btngpsort.add(rdbtnDeSortToDate);

		btnDeSelectIndex = new JButton("\uAC80\uC0C9");
		btnDeSelectIndex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<VOInProduct> inpdlist;
				String target = cbDeSeach.getSelectedItem().toString();
				String searchText = textFieldDeSearch.getText();
				String orderBy = cbDeSort.getSelectedItem().toString();
				String orderValue  = getradioButtonText();
				
				if(target.equals("물품명")) target = "product_code";
				else target = "company_code";
				
				if(orderBy.equals("오름차순")) orderBy = "ASC";
				else orderBy = "DESC";
				
				if(orderValue.equals("코드")) orderValue = "in_code";
				else if(orderValue.equals("수량")) orderValue = "in_quantity";
				else if(orderValue.equals("총액")) orderValue = "in_amount";
				else orderValue = "output_date";
				
				try {
					inpdlist = new InViewDAO().searchSelected(target, searchText, orderBy, orderValue);
					setTable(inpdlist);
					return;
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					
				}
				
			}
		});
		btnDeSelectIndex.setFont(new Font("굴림", Font.BOLD, 13));
		btnDeSelectIndex.setBackground(new Color(112, 128, 144));
		btnDeSelectIndex.setBounds(342, 78, 70, 30);
		paneDelList.add(btnDeSelectIndex);

		cbDeSeach = new JComboBox<String>();
		cbDeSeach.setFont(new Font("굴림", Font.PLAIN, 12));
		cbDeSeach.setModel(new DefaultComboBoxModel(new String[] {"\uBB3C\uD488\uBA85", "\uAC70\uB798\uCC98"}));
		cbDeSeach.setBounds(14, 78, 74, 30);
		paneDelList.add(cbDeSeach);

		textFieldDeSearch = new JTextField();
		textFieldDeSearch.setBounds(100, 78, 235, 30);
		paneDelList.add(textFieldDeSearch);
		textFieldDeSearch.setColumns(10);

		btnDetoday = new JButton("\uAE08\uC77C \uAC70\uB798\uB0B4\uC5ED");
		btnDetoday.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new InViewToday();
			}
		});
		btnDetoday.setBounds(14, 429, 134, 35);
		paneDelList.add(btnDetoday);
		
		button = new JButton("파일로 저장");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					selectExcelFilePath();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("굴림", Font.PLAIN, 15));
		button.setBounds(291, 429, 126, 35);
		paneDelList.add(button);
		
		setVisible(true);
	}

	//	선택한 라디오 버튼 텍스트 get
	public String getradioButtonText() {
		String order = "";
		Enumeration<AbstractButton> button = btngpsort.getElements();
		while(button.hasMoreElements()) {
			AbstractButton selcetedButton = button.nextElement();
			JRadioButton orderButton = (JRadioButton) selcetedButton;
			if(orderButton.isSelected()) {
				order = orderButton.getText();
				break;
			}
		}
		return order;
	}

	//	 InView 판매목록 테이블 set
	public void setTable(ArrayList<VOInProduct> inpdlist) {
		tradeListModel.setRowCount(0);
		for(int i = 0; i < inpdlist.size(); i++) {
			tradeListModel.addRow(new String[] {inpdlist.get(i).getin_code(), 
					inpdlist.get(i).getProduct_code(), 
					inpdlist.get(i).getCompany_code(), ""+inpdlist.get(i).getin_quantity(),
					""+inpdlist.get(i).getin_amount(), inpdlist.get(i).getOutput_date(), inpdlist.get(i).getin_info()});
		}
	}
	public void updateTable(VOInProduct inPd, int row) {
		tradeListModel.removeRow(row);
		tradeListModel.addRow(new String[] {inPd.getin_code(), 
				inPd.getProduct_code(), inPd.getCompany_code(),""+inPd.getin_quantity(), ""+inPd.getin_amount(), inPd.getOutput_date(), inPd.getin_info()});
	}
	
	public void selectExcelFilePath() throws Exception {
		JFileChooser chooser = new JFileChooser();// 객체 생성
		File savefile = null;
		String savepathname;

		chooser.setCurrentDirectory(new File("C:\\")); // 맨처음경로를 C로 함
		chooser.setAcceptAllFileFilterUsed(false);	// 파일 유형에 모든파일 항목 없애기
		chooser.addChoosableFileFilter(new FileNameExtensionFilter("Excel Files", "xls", "xls"));

		while(true) {
			if (chooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) { //디렉토리를 선택했으면
				savepathname = chooser.getSelectedFile().toString(); //선택된 디렉토리 저장하고
				
				if(!savepathname.toLowerCase().endsWith(".xls")) {
					savepathname += ".xls";
				}
				savefile = new File(savepathname);
				
				if(savefile.exists()) {
					int replace = JOptionPane.showConfirmDialog(null, savefile.getName() +
							"이(가) 이미 존재합니다. 바꾸시겠습니까?", "다른 이름으로 저장 확인", JOptionPane.YES_NO_OPTION);

					if(replace == JOptionPane.NO_OPTION) {
						continue;
					}
					else {
						createExcel(savefile);
						break;
					}
				}
				else {
					createExcel(savefile);
					break;
				}
			}
			else
				break;
		}
		
	}
	
	//엑셀출력메소드
	public void createExcel(File saveFile) throws Exception {
		
		try {
			workbook = new HSSFWorkbook();	//Excel Workbook 생성
			sheet = workbook.createSheet();	//Excel Sheet 생성
			fos =null;
			tableselectedRow = tableDeList.getSelectedRow();
			
			Row label = sheet.createRow(0);		// 테이블 레이블
			for (int colnum = 0; colnum < tableDeList.getColumnCount(); colnum++) {	// table 레이블 엑셀에 출력
				Cell cell = label.createCell(colnum);	// 셀 생성
				cell.setCellValue(tableDeList.getColumnName(colnum));	// 셀 삽입
			}
			for (rownum = 1; rownum < tableDeList.getRowCount()+1; rownum++) {	// 테이블내용 엑셀에  출력
				Row row = sheet.createRow(rownum);	// row 생성

				for (int colnum = 0; colnum < tableDeList.getColumnCount(); colnum++) {	// 테이블 열 카운트 만큼 반복
					Cell cell = row.createCell(colnum);	//셀 생성
					cell.setCellValue(tableDeList.getValueAt(rownum-1, colnum).toString());	// 셀 삽입
					
				}
			}
			fos = new FileOutputStream(saveFile.toString());	// 파일 출력 스트림 생성
			workbook.write(fos);	// 엑셀로 출력
			
		} 
		finally {
			if(fos != null)
				fos.close();	// file resource 반환
			if(workbook != null)
				workbook.close();	// excel resource 반환
		}
	}
	
	public void clearField() {
		labelDeCode.setText("");
		tfDeProductName.setText("");
		tfDeCustomerName.setText("");
		lblDeProductCode.setText("");
		lblDeCustomerCode.setText("");
		tfDeQuantity.setText("");
		lblTotalMoney.setText("");
		tfDeDate.setText("");
		textArea.setText("");
	}
	
}