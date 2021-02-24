package pdm.view.pdmanager;

import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import pdm.vo.VOProduct;

public class PdView extends JPanel{

	private JTextField textFieldPdName, textFieldPdPrice, textFieldPdStock, textFieldPdSearch;
	private JTable tablePdList;
	private ButtonGroup btngpsort;
	private JPanel panelInfo, panelPdList;
	private JLabel lblInfo, lblPdCode, labelPdName, labePdPrice, lblPdPrice2, labelPdStock, labelPdStock2;
	private JLabel labelPdCode, lblPdType;
	private JScrollPane scrollPanePdInfo, scrollPanePdList;
	private JTextArea txtrInfo;
	private JButton btnPdInsert, btnPdUpdate, btnPdDelete, btnPdSearchAll, btnPdSelectIndex;
	private JComboBox<String> comboBox_1 ,comboBox, cbPdType, cbPdSeach, cbPdSort;
	private JRadioButton rdbtnPdSortToCode, rdbtnPdSortToPrice, rdbtnPdSortToStock;
	private JLabel lblPdInfo;
	private DefaultTableModel defaultmodel;
	private ArrayList<VOProduct> pd;
	private int tableselectedRow;
	private FileOutputStream fos = null;
	private HSSFSheet sheet = null;
	private HSSFWorkbook workbook ;
	public PdView() {
		setLayout(null);
		btngpsort = new ButtonGroup();
		panelInfo = new JPanel();
		panelInfo.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelInfo.setBounds(43, 25, 406, 479);
		panelInfo.setLayout(null);

		lblInfo = new JLabel("\uBB3C\uD488 \uC815\uBCF4");
		lblInfo.setBounds(128, 12, 130, 29);
		panelInfo.add(lblInfo);
		lblInfo.setFont(new Font("굴림", Font.BOLD, 24));

		lblPdCode = new JLabel("\uBB3C\uD488 \uCF54\uB4DC");
		lblPdCode.setFont(new Font("굴림", Font.BOLD, 17));
		lblPdCode.setBounds(14, 70, 97, 29);
		panelInfo.add(lblPdCode);

		labelPdName = new JLabel("\uBB3C\uD488\uBA85");
		labelPdName.setFont(new Font("굴림", Font.BOLD, 17));
		labelPdName.setBounds(14, 109, 97, 29);
		panelInfo.add(labelPdName);

		textFieldPdName = new JTextField();
		textFieldPdName.setColumns(10);
		textFieldPdName.setBounds(104, 110, 175, 29);
		panelInfo.add(textFieldPdName);

		labePdPrice = new JLabel("\uBB3C\uD488 \uAC00\uACA9");
		labePdPrice.setFont(new Font("굴림", Font.BOLD, 17));
		labePdPrice.setBounds(14, 151, 97, 29);
		panelInfo.add(labePdPrice);

		textFieldPdPrice = new JTextField();
		textFieldPdPrice.setColumns(10);
		textFieldPdPrice.setBounds(104, 151, 175, 29);
		panelInfo.add(textFieldPdPrice);

		labelPdStock = new JLabel("\uBB3C\uD488 \uC7AC\uACE0");
		labelPdStock.setFont(new Font("굴림", Font.BOLD, 17));
		labelPdStock.setBounds(14, 192, 97, 29);
		panelInfo.add(labelPdStock);

		textFieldPdStock = new JTextField();
		textFieldPdStock.setColumns(10);
		textFieldPdStock.setBounds(104, 190, 175, 29);
		panelInfo.add(textFieldPdStock);

		lblPdPrice2 = new JLabel("(만원/Box)");
		lblPdPrice2.setFont(new Font("굴림", Font.BOLD, 17));
		lblPdPrice2.setBounds(291, 151, 90, 29);
		panelInfo.add(lblPdPrice2);

		labelPdStock2 = new JLabel("(Box)");
		labelPdStock2.setFont(new Font("굴림", Font.BOLD, 17));
		labelPdStock2.setBounds(291, 192, 90, 29);
		panelInfo.add(labelPdStock2);

		scrollPanePdInfo = new JScrollPane();
		scrollPanePdInfo.setBounds(104, 309, 267, 84);
		panelInfo.add(scrollPanePdInfo);

		txtrInfo = new JTextArea();
		scrollPanePdInfo.setViewportView(txtrInfo);

		btnPdInsert = new JButton("\uBB3C\uD488\uB4F1\uB85D");
		btnPdInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					new PdViewInsertPd();
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
			}
		});
		btnPdInsert.setBackground(new Color(112, 128, 144));
		btnPdInsert.setFont(new Font("굴림", Font.BOLD, 17));
		btnPdInsert.setBounds(14, 405, 110, 62);
		panelInfo.add(btnPdInsert);

		btnPdUpdate = new JButton("\uC218\uC815");
		btnPdUpdate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String code = labelPdCode.getText();
				String name = textFieldPdName.getText();
				String info = txtrInfo.getText();
				
				if(info.equals("")) info += " 물품 상세 정보";	// null값 삽입 방지
				
				if(name.equals("")) {
					JOptionPane.showMessageDialog(null, "물품명을 입력해 주세요", "물품명 입력",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				int price = 0, stock = 0;
				try {	// 가격 입력 확인
					price = Integer.parseInt(textFieldPdPrice.getText());
				} catch(NumberFormatException numberE) {
					JOptionPane.showMessageDialog(null, "수량은 숫자로 입력해 주세요", "입력 에러",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				try {	// 재고 입력 확인
					stock = Integer.parseInt(textFieldPdStock.getText());
				} catch(NumberFormatException numberE) {
					JOptionPane.showMessageDialog(null, "재고는 숫자로 입력해 주세요", "입력 에러",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				
				VOProduct pd = new VOProduct(name, code, comboBox_1.getSelectedItem().toString(), 
						comboBox.getSelectedItem().toString(), cbPdType.getSelectedItem().toString(), Integer.parseInt(textFieldPdPrice.getText()), info, Integer.parseInt(textFieldPdStock.getText()));
				
				
				int update = 0;
				try {
					update = new PdViewDAO().getUpdate(code,name,price,stock,info);
					if(update > 0) {
						JOptionPane.showMessageDialog(null,"수정완료","알림",JOptionPane.INFORMATION_MESSAGE );
						updateTable(pd, tableselectedRow);
						clearField();
					}
					else{JOptionPane.showMessageDialog(null,"수정실패","오류",JOptionPane.INFORMATION_MESSAGE);
					}

				} catch (Exception e2) {
					
				}
				


			}
		});

		btnPdUpdate.setFont(new Font("굴림", Font.BOLD, 20));
		btnPdUpdate.setBackground(new Color(112, 128, 144));
		btnPdUpdate.setBounds(143, 405, 115, 62);
		panelInfo.add(btnPdUpdate);

		btnPdDelete = new JButton("\uC0AD\uC81C");
		btnPdDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnPdDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int result = 0;
				try {
					result = new PdViewDAO().getdelete(labelPdCode.getText());
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (result > 0 ) {
					JOptionPane.showMessageDialog(null,"삭제완료","알림",JOptionPane.INFORMATION_MESSAGE );
					((DefaultTableModel)tablePdList.getModel()).removeRow(tableselectedRow);
					clearField();
				}
				else{JOptionPane.showMessageDialog(null,"삭제실패","오류",JOptionPane.INFORMATION_MESSAGE);
				}
			}
		});
		btnPdDelete.setFont(new Font("굴림", Font.BOLD, 20));
		btnPdDelete.setBackground(new Color(112, 128, 144));
		btnPdDelete.setBounds(277, 404, 115, 62);
		panelInfo.add(btnPdDelete);

		labelPdCode = new JLabel("");
		labelPdCode.setFont(new Font("굴림", Font.BOLD, 17));
		labelPdCode.setBounds(104, 70, 175, 29);
		panelInfo.add(labelPdCode);

		lblPdType = new JLabel("분류");
		lblPdType.setFont(new Font("굴림", Font.BOLD, 17));
		lblPdType.setBounds(14, 270, 97, 29);
		panelInfo.add(lblPdType);

		cbPdType = new JComboBox<String>();
		cbPdType.setModel(new DefaultComboBoxModel(new String[] {"감기약", "소화제", "진통제", "마취제", "항균연고", "생리식염수", "위장약", "멀미약", "알러지약", "점이약", "국소진통약", "국소항염약"}));
		cbPdType.setBounds(104, 273, 105, 24);
		panelInfo.add(cbPdType);

		panelPdList = new JPanel();
		panelPdList.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelPdList.setBounds(500, 25, 429, 479);
		panelPdList.setLayout(null);

		btnPdSearchAll = new JButton("\uC804\uCCB4 \uC870\uD68C");
		btnPdSearchAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// 정렬 기준 설정
				String orderBy = cbPdSort.getSelectedItem().toString();
				String orderValue  = getradioButtonText();

				if(orderBy.equals("오름차순")) orderBy = "ASC";
				else orderBy = "DESC";

				if(orderValue.equals("코드")) orderValue = "product_code";
				else if(orderValue.equals("가격")) orderValue = "product_price";
				else if(orderValue.equals("재고")) orderValue = "product_stock";



				try {
					ArrayList<VOProduct> pdlist = new PdViewDAO().selectAll(orderBy, orderValue);
					setTable(pdlist);
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

		btnPdSearchAll.setFont(new Font("굴림", Font.BOLD, 14));
		btnPdSearchAll.setBackground(new Color(112, 128, 144));
		btnPdSearchAll.setBounds(297, 16, 120, 52);
		panelPdList.add(btnPdSearchAll);

		String[][] row = new String[][] { };
		String[] col = new String[] { "코드", "물품명", "가격", "재고", "의약외품","제형","분류","정보"};
		defaultmodel = new DefaultTableModel(row, col);
		tablePdList = new JTable(defaultmodel);
		tablePdList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				ArrayList<VOProduct> pd1;
				tableselectedRow = tablePdList.getSelectedRow();

				int [] typeCode = convertCodeToName((String)tablePdList.getValueAt(tableselectedRow, 4), 
						(String)tablePdList.getValueAt(tableselectedRow, 5), (String)tablePdList.getValueAt(tableselectedRow, 6));
				labelPdCode.setText((String)tablePdList.getValueAt(tableselectedRow, 0));
				textFieldPdName.setText((String)tablePdList.getValueAt(tableselectedRow, 1));
				textFieldPdPrice.setText((String)tablePdList.getValueAt(tableselectedRow, 2));
				textFieldPdStock.setText((String)tablePdList.getValueAt(tableselectedRow, 3));
				txtrInfo.setText((String)tablePdList.getValueAt(tableselectedRow, 7));
				comboBox_1.setSelectedIndex(typeCode[0]);
				comboBox.setSelectedIndex(typeCode[1]);
				cbPdType.setSelectedIndex(typeCode[2]);




			}
		});


		scrollPanePdList = new JScrollPane(tablePdList);
		scrollPanePdList.setBounds(14, 159, 403, 260);
		panelPdList.add(scrollPanePdList);

		cbPdSort = new JComboBox<String>();
		cbPdSort.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC624\uB984\uCC28\uC21C", "\uB0B4\uB9BC\uCC28\uC21C"}));
		cbPdSort.setBounds(14, 125, 79, 24);
		panelPdList.add(cbPdSort);

		rdbtnPdSortToCode = new JRadioButton("\uCF54\uB4DC");
		rdbtnPdSortToCode.setBounds(111, 124, 57, 27);
		panelPdList.add(rdbtnPdSortToCode);
		rdbtnPdSortToCode.setSelected(true);
		btngpsort.add(rdbtnPdSortToCode);

		rdbtnPdSortToPrice = new JRadioButton("\uAC00\uACA9");
		rdbtnPdSortToPrice.setBounds(225, 125, 57, 27);
		panelPdList.add(rdbtnPdSortToPrice);
		btngpsort.add(rdbtnPdSortToPrice);

		rdbtnPdSortToStock = new JRadioButton("\uC7AC\uACE0");
		rdbtnPdSortToStock.setBounds(346, 124, 57, 27);
		panelPdList.add(rdbtnPdSortToStock);
		btngpsort.add(rdbtnPdSortToStock);

		btnPdSelectIndex = new JButton("\uAC80\uC0C9");
		btnPdSelectIndex.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String target = cbPdSeach.getSelectedItem().toString();
				String searchText = textFieldPdSearch.getText();
				String orderBy = cbPdSort.getSelectedItem().toString();
				String orderValue  = getradioButtonText();

				if(target.equals("물품명")) target = "product_name";
				else if(target.equals("분류")) target = "Small_code";

				if(orderBy.equals("오름차순")) orderBy = "ASC";
				else orderBy = "DESC";

				if(orderValue.equals("코드")) orderValue = "product_code";
				else if(orderValue.equals("가격")) orderValue = "product_price";
				else if(orderValue.equals("재고")) orderValue = "product_stock";



				try {
					ArrayList<VOProduct> pdlist = new PdViewDAO().selectTarget(target,searchText,orderBy, orderValue);
					setTable(pdlist);
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
		btnPdSelectIndex.setFont(new Font("굴림", Font.BOLD, 13));
		btnPdSelectIndex.setBackground(new Color(112, 128, 144));
		btnPdSelectIndex.setBounds(342, 78, 70, 30);
		panelPdList.add(btnPdSelectIndex);

		cbPdSeach = new JComboBox<String>();
		cbPdSeach.setFont(new Font("굴림", Font.PLAIN, 12));
		cbPdSeach.setModel(new DefaultComboBoxModel(new String[] {"물품명", "분류"}));
		cbPdSeach.setBounds(14, 78, 74, 30);
		panelPdList.add(cbPdSeach);

		textFieldPdSearch = new JTextField();
		textFieldPdSearch.setBounds(100, 78, 235, 30);
		panelPdList.add(textFieldPdSearch);
		textFieldPdSearch.setColumns(10);
		add(panelInfo);

		lblPdInfo = new JLabel("상세 정보");
		lblPdInfo.setFont(new Font("굴림", Font.BOLD, 17));
		lblPdInfo.setBounds(14, 306, 97, 29);
		panelInfo.add(lblPdInfo);

		JLabel label = new JLabel("제형");
		label.setFont(new Font("굴림", Font.BOLD, 17));
		label.setBounds(229, 231, 50, 29);
		panelInfo.add(label);

		comboBox = new JComboBox<String>();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"정제형알약", "캡슐형알약", "주사제", "수액제", "안연고형", "안약형", "시럽제", "경구용액제", "로션제", "점이제", "크림제", "페이스트제"}));
		comboBox.setBounds(277, 234, 105, 24);
		panelInfo.add(comboBox);

		JLabel label_1 = new JLabel("의약외품");
		label_1.setFont(new Font("굴림", Font.BOLD, 17));
		label_1.setBounds(14, 231, 97, 29);
		panelInfo.add(label_1);

		comboBox_1 = new JComboBox<String>();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"내용고형제", "주사제", "점안제", "내용액제", "외용액제", "연고제"}));
		comboBox_1.setBounds(104, 234, 105, 24);
		panelInfo.add(comboBox_1);
		add(panelPdList);

		JButton button = new JButton("파일로 저장");
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
		panelPdList.add(button);

		setVisible(true);
	}public String getradioButtonText() {
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

	//	 OutView 판매목록 테이블 set
	public void setTable(ArrayList<VOProduct> pdlist) {
		defaultmodel.setRowCount(0);
		for(int i = 0; i < pdlist.size(); i++) {
			defaultmodel.addRow(new String[] {
					pdlist.get(i).getProduct_code(), pdlist.get(i).getProduct_name(), 
					""+pdlist.get(i).getProduct_price(), ""+pdlist.get(i).getProduct_stock(),
					""+pdlist.get(i).getLarge_code(), pdlist.get(i).getMedium_code(), 
					pdlist.get(i).getSmall_code(),pdlist.get(i).getProduct_explain()});
		}
	}
	
	public void updateTable(VOProduct pd, int row) {
		defaultmodel.removeRow(row);
		defaultmodel.addRow(new String[] {
				pd.getProduct_code(), pd.getProduct_name(), 
				""+pd.getProduct_price(), ""+pd.getProduct_stock(),
				""+pd.getLarge_code(), pd.getMedium_code(), 
				pd.getSmall_code(), pd.getProduct_explain()});
		}

	public int[] convertCodeToName(String large, String mideum, String small) {
		int[] typecode = new int[3];

		if(large.equals("A")) typecode[0] = 0;
		else if(large.equals("B")) typecode[0] = 1;
		else if(large.equals("C")) typecode[0] = 2;
		else if(large.equals("D")) typecode[0] = 3;
		else if(large.equals("E")) typecode[0] = 4;
		else if(large.equals("F")) typecode[0] = 5;

		if(mideum.equals("a1")) typecode[1] = 0;
		else if(mideum.equals("a2")) typecode[1] = 1;
		else if(mideum.equals("b1")) typecode[1] = 2;
		else if(mideum.equals("b2")) typecode[1] = 3;
		else if(mideum.equals("c1")) typecode[1] = 4;
		else if(mideum.equals("c2")) typecode[1] = 5;
		else if(mideum.equals("d1")) typecode[1] = 6;
		else if(mideum.equals("d2")) typecode[1] = 7;
		else if(mideum.equals("e1")) typecode[1] = 8;
		else if(mideum.equals("e2")) typecode[1] = 9;
		else if(mideum.equals("f1")) typecode[1] = 10;
		else if(mideum.equals("f2")) typecode[1] = 11;

		if(small.equals("A")) typecode[2] = 0;
		else if(small.equals("B")) typecode[2] = 1;
		else if(small.equals("C")) typecode[2] = 2;
		else if(small.equals("D")) typecode[2] = 3;
		else if(small.equals("E")) typecode[2] = 4;
		else if(small.equals("F")) typecode[2] = 5;
		else if(small.equals("G")) typecode[2] = 6;
		else if(small.equals("H")) typecode[2] = 7;
		else if(small.equals("I")) typecode[2] = 8;
		else if(small.equals("J")) typecode[2] = 9;
		else if(small.equals("K")) typecode[2] = 10;
		else if(small.equals("L")) typecode[2] = 11;



		return typecode;
	}
	public JTable getP1table() {
		// TODO Auto-generated method stub
		return null;
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


			Row label = sheet.createRow(0);		// 테이블 레이블
			for (int colnum = 0; colnum < tablePdList.getColumnCount(); colnum++) {	// table 레이블 엑셀에 출력
				Cell cell = label.createCell(colnum);	// 셀 생성
				cell.setCellValue(tablePdList.getColumnName(colnum));	// 셀 삽입
			}
			for (int rownum = 1; rownum < tablePdList.getRowCount()+1; rownum++) {	// 테이블내용 엑셀에  출력
				Row row = sheet.createRow(rownum);	// row 생성

				for (int colnum = 0; colnum < tablePdList.getColumnCount(); colnum++) {	// 테이블 열 카운트 만큼 반복
					Cell cell = row.createCell(colnum);	//셀 생성
					cell.setCellValue(tablePdList.getValueAt(rownum-1, colnum).toString());	// 셀 삽입

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
		labelPdCode.setText("");
		textFieldPdName.setText("");
		textFieldPdPrice.setText("");
		textFieldPdStock.setText("");
		comboBox_1.setSelectedIndex(0);
		comboBox.setSelectedIndex(0);
		cbPdType.setSelectedIndex(0);
		txtrInfo.setText("");
		
	}

}
