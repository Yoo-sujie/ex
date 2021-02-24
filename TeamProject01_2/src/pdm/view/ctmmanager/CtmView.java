package pdm.view.ctmmanager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import pdm.vo.VOCustomer;
import javax.swing.JTextArea;

public class CtmView extends JPanel {
	private JTextField tfCtmName, tfCtmEmail, tfctmTel, tfCtmSearch;
	private JTable tableCtmList;
	private ButtonGroup btngpsort;
	private JPanel panelCtmInfo, panelCtmList;
	private JLabel lblCtmInfoTitle, lblCtmCode, lblCtmName, lblCtmRegistrationDate, lblCtmTel;
	private JLabel labelCtmCode, lblCtmAddress, lblCtmInfo;
	private JScrollPane scrollPaneCtmList, scrollPane;
	private JButton btnPdInsert, btnPdUpdate, btnPdDelete, btnCtmSearchAll, btnCtmSelectIndex;
	private JComboBox<String> cbCtmSeach, cbCtmSort;
	private JRadioButton rdbtnCtmSortToCode, rdbtnCtmSortToDate;
	private JTextField tfCtmAddress;
	private DefaultTableModel ctmmodel;
	private int tableselectedRow;
	private JButton button_1;
	private JTextArea textAreaCtmInfo;
	private FileOutputStream fos = null;
	private HSSFSheet sheet = null;
	private HSSFWorkbook workbook ;
	private int rownum;
	public CtmView() {
		setLayout(null);
		btngpsort = new ButtonGroup();
		panelCtmInfo = new JPanel();
		panelCtmInfo.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCtmInfo.setBounds(43, 25, 406, 479);
		panelCtmInfo.setLayout(null);

		lblCtmInfoTitle = new JLabel("\uAC70\uB798\uCC98 \uC815\uBCF4");
		lblCtmInfoTitle.setBounds(128, 12, 151, 29);
		panelCtmInfo.add(lblCtmInfoTitle);
		lblCtmInfoTitle.setFont(new Font("굴림", Font.BOLD, 24));

		lblCtmCode = new JLabel("거래처코드");
		lblCtmCode.setFont(new Font("굴림", Font.BOLD, 17));
		lblCtmCode.setBounds(14, 70, 97, 29);
		panelCtmInfo.add(lblCtmCode);

		lblCtmName = new JLabel("\uAC70\uB798\uCC98\uBA85");
		lblCtmName.setFont(new Font("굴림", Font.BOLD, 17));
		lblCtmName.setBounds(14, 109, 97, 29);
		panelCtmInfo.add(lblCtmName);

		tfCtmName = new JTextField();
		tfCtmName.setColumns(10);
		tfCtmName.setBounds(104, 110, 175, 29);
		panelCtmInfo.add(tfCtmName);

		lblCtmRegistrationDate = new JLabel("이메일");
		lblCtmRegistrationDate.setFont(new Font("굴림", Font.BOLD, 17));
		lblCtmRegistrationDate.setBounds(14, 192, 97, 29);
		panelCtmInfo.add(lblCtmRegistrationDate);

		tfCtmEmail = new JTextField();
		tfCtmEmail.setColumns(10);
		tfCtmEmail.setBounds(104, 190, 175, 29);
		panelCtmInfo.add(tfCtmEmail);

		btnPdInsert = new JButton("\uAC70\uB798\uCC98\uB4F1\uB85D");
		btnPdInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CtmViewInsertCtm();
			}
		});
		btnPdInsert.setBackground(new Color(112, 128, 144));
		btnPdInsert.setFont(new Font("굴림", Font.BOLD, 17));
		btnPdInsert.setBounds(14, 405, 119, 62);
		panelCtmInfo.add(btnPdInsert);

		btnPdUpdate = new JButton("\uC218\uC815");
		btnPdUpdate.addMouseListener(new MouseAdapter() {
		//@@@@@@@ 거래처 정보 조회 해서 수정하는 이벤트
			@Override
			public void mouseClicked(MouseEvent e) {
				
				String code = labelCtmCode.getText();
				String name = tfCtmName.getText();
				String email = tfCtmEmail.getText();
				String tel = tfctmTel.getText();
				String address = tfCtmAddress.getText();
				String info = textAreaCtmInfo.getText();
				
				if(info.equals("")) info += " 거래처 상세 정보";	// null값 삽입 방지
				
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
				
				VOCustomer ctm = new VOCustomer(code, name, email, tel, address, info);
				
				int update = 0;

				try {
					update = new CtmViewDAO().getUpdate(code, name, email, tel, address, info);
					if(update > 0) {
						JOptionPane.showMessageDialog(null, "수정되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
						clearField();
						updateTable(ctm, tableselectedRow);
					} else {
						JOptionPane.showMessageDialog(null, code + "의 수정에 실패 했습니다", "에러", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}

			}
		});
		btnPdUpdate.setFont(new Font("굴림", Font.BOLD, 17));
		btnPdUpdate.setBackground(new Color(112, 128, 144));
		btnPdUpdate.setBounds(145, 405, 115, 62);
		panelCtmInfo.add(btnPdUpdate);

		String[][] ctmrow = new String[][] { };
		String[] ctmcol = new String[] { "거래처코드","거래처명","이메일","대표번호","주소", "상세정보"};
		ctmmodel = new DefaultTableModel(ctmrow,ctmcol);
		tableCtmList = new JTable(ctmmodel);
		tableCtmList.addMouseListener(new MouseAdapter() {
			
			//@@@@@@@ 거래처 정보 조회 해서 JTable에 나타내는 이벤트
			@Override
			public void mouseClicked(MouseEvent e) {
				
				tableselectedRow = tableCtmList.getSelectedRow();
				labelCtmCode.setText((String)tableCtmList.getValueAt(tableselectedRow, 0));
				tfCtmName.setText((String)tableCtmList.getValueAt(tableselectedRow, 1));
				tfctmTel.setText((String)tableCtmList.getValueAt(tableselectedRow, 3));
				tfCtmEmail.setText((String)tableCtmList.getValueAt(tableselectedRow, 2));
				tfCtmAddress.setText((String)tableCtmList.getValueAt(tableselectedRow, 4));
				textAreaCtmInfo.setText((String)tableCtmList.getValueAt(tableselectedRow, 5));
			}

		});
		scrollPaneCtmList = new JScrollPane(tableCtmList);
		scrollPaneCtmList.setSize(500,200);





		btnPdDelete = new JButton("\uC0AD\uC81C");
		btnPdDelete.addMouseListener(new MouseAdapter() {
			
			//@@@@@@@ 거래처 정보 조회 해서 삭제하는 이벤트
			@Override
			public void mouseClicked(MouseEvent e) {
				String code = labelCtmCode.getText();
				int delete = 0;

				try {
					delete = new CtmViewDAO().getDelete(code);
					if(delete > 0) {
						JOptionPane.showMessageDialog(null, "삭제되었습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
						((DefaultTableModel)tableCtmList.getModel()).removeRow(tableselectedRow);
						clearField();
					} else {
						JOptionPane.showMessageDialog(null, code + "의 삭제에 실패 했습니다", "에러", JOptionPane.INFORMATION_MESSAGE);
					}
				} catch (Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnPdDelete.setFont(new Font("굴림", Font.BOLD, 17));
		btnPdDelete.setBackground(new Color(112, 128, 144));
		btnPdDelete.setBounds(277, 404, 115, 62);
		panelCtmInfo.add(btnPdDelete);

		labelCtmCode = new JLabel("");
		labelCtmCode.setFont(new Font("굴림", Font.BOLD, 17));
		labelCtmCode.setBounds(104, 70, 151, 29);
		panelCtmInfo.add(labelCtmCode);

		lblCtmAddress = new JLabel("\uC18C\uC7AC\uC9C0");
		lblCtmAddress.setFont(new Font("굴림", Font.BOLD, 17));
		lblCtmAddress.setBounds(14, 231, 97, 29);
		panelCtmInfo.add(lblCtmAddress);

		panelCtmList = new JPanel();
		panelCtmList.setBorder(new LineBorder(new Color(0, 0, 0)));
		panelCtmList.setBounds(500, 25, 429, 479);
		panelCtmList.setLayout(null);

		btnCtmSearchAll = new JButton("\uC804\uCCB4 \uC870\uD68C");
		btnCtmSearchAll.addMouseListener(new MouseAdapter() {
			
			//@@@@@@@ 거래처 정보 조회 할 때 정렬순으로 조회 & 정렬기준으로 조회 하는 이벤트
			@Override
			public void mouseClicked(MouseEvent e) {
				String orderBy = cbCtmSort.getSelectedItem().toString();
				String orderValue  = getradioButtonText();

				if(orderBy.equals("오름차순")) {
					orderBy = "ASC";
				} else {
					orderBy = "DESC";
				}

				if(orderValue.equals("코드")) {
					orderValue = "COMPANY_CODE";
				} else {
					orderValue = "COMPANY_NAME";
				}

				try {
					ArrayList<VOCustomer> ctm = new CtmViewDAO().getLookUp(orderValue, orderBy);
					setTable(ctm);
				} catch(Exception e1) {
					System.out.println(e1.getMessage());
				}


			}
		});
		btnCtmSearchAll.setFont(new Font("굴림", Font.BOLD, 14));
		btnCtmSearchAll.setBackground(new Color(112, 128, 144));
		btnCtmSearchAll.setBounds(297, 16, 120, 52);
		panelCtmList.add(btnCtmSearchAll);




		scrollPaneCtmList.addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent event) {
			}
			public void ancestorMoved(AncestorEvent event) {
			}
			public void ancestorRemoved(AncestorEvent event) {
			}
		});;
		scrollPaneCtmList.setBounds(14, 159, 403, 260);
		panelCtmList.add(scrollPaneCtmList);

		cbCtmSort = new JComboBox<String>();
		cbCtmSort.setModel(new DefaultComboBoxModel<String>(new String[] {"\uC624\uB984\uCC28\uC21C", "\uB0B4\uB9BC\uCC28\uC21C"}));
		cbCtmSort.setBounds(14, 125, 79, 24);
		panelCtmList.add(cbCtmSort);

		rdbtnCtmSortToCode = new JRadioButton("\uCF54\uB4DC");
		rdbtnCtmSortToCode.setBounds(155, 124, 57, 27);
		panelCtmList.add(rdbtnCtmSortToCode);
		rdbtnCtmSortToCode.setSelected(true);
		btngpsort.add(rdbtnCtmSortToCode);

		rdbtnCtmSortToDate = new JRadioButton("거래처명");
		rdbtnCtmSortToDate.setBounds(277, 124, 96, 27);
		panelCtmList.add(rdbtnCtmSortToDate);
		btngpsort.add(rdbtnCtmSortToDate);

		btnCtmSelectIndex = new JButton("\uAC80\uC0C9");
		btnCtmSelectIndex.addActionListener(new ActionListener() {
			//@@@@@ 거래처 검색 하는 이벤트 (거래처명, 거래처코드)
			public void actionPerformed(ActionEvent e) {
				
				String orderBy = cbCtmSort.getSelectedItem().toString();
				String orderValue  = getradioButtonText();
				String target = cbCtmSeach.getSelectedItem().toString();
				String searchText = tfCtmSearch.getText();
				
				if(orderValue.equals("거래처명")) {
					orderValue = "COMPANY_NAME";
				} else {
					orderValue = "COMPANY_CODE";
				}
				
				if(orderBy.equals("오름차순")) {
					orderBy = "ASC";
				} else {
					orderBy = "DESC";
				}
				
				if(target.equals("거래처명")) target = "company_name";
				else target = "company_address";

				try {
					ArrayList<VOCustomer> ctmr = new CtmViewDAO().getSelectedSort(target, searchText, orderBy, orderValue);
					setTable(ctmr);
				} catch(Exception e1) {
					System.out.println(e1.getMessage());
				}
			}
		});
		btnCtmSelectIndex.setFont(new Font("굴림", Font.BOLD, 13));
		btnCtmSelectIndex.setBackground(new Color(112, 128, 144));
		btnCtmSelectIndex.setBounds(342, 78, 70, 30);
		panelCtmList.add(btnCtmSelectIndex);

		cbCtmSeach = new JComboBox<String>();
		cbCtmSeach.setFont(new Font("굴림", Font.PLAIN, 12));
		cbCtmSeach.setModel(new DefaultComboBoxModel(new String[] {"거래처명", "주소"}));
		cbCtmSeach.setBounds(14, 78, 74, 30);
		panelCtmList.add(cbCtmSeach);

		tfCtmSearch = new JTextField();
		tfCtmSearch.setBounds(100, 78, 235, 30);
		panelCtmList.add(tfCtmSearch);
		tfCtmSearch.setColumns(10);
		add(panelCtmInfo);

		tfCtmAddress = new JTextField();
		tfCtmAddress.setColumns(10);
		tfCtmAddress.setBounds(104, 231, 175, 29);
		panelCtmInfo.add(tfCtmAddress);

		tfctmTel = new JTextField();
		tfctmTel.setBounds(104, 151, 175, 29);
		panelCtmInfo.add(tfctmTel);
		tfctmTel.setColumns(10);

		lblCtmTel = new JLabel("\uB300\uD45C\uBC88\uD638");
		lblCtmTel.setBounds(14, 151, 97, 29);
		panelCtmInfo.add(lblCtmTel);
		lblCtmTel.setFont(new Font("굴림", Font.BOLD, 17));
		
		lblCtmInfo = new JLabel("\uC0C1\uC138 \uC815\uBCF4");
		lblCtmInfo.setFont(new Font("굴림", Font.BOLD, 17));
		lblCtmInfo.setBounds(14, 276, 89, 29);
		panelCtmInfo.add(lblCtmInfo);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(104, 274, 275, 92);
		panelCtmInfo.add(scrollPane);
		
		textAreaCtmInfo = new JTextArea();
		scrollPane.setViewportView(textAreaCtmInfo);
		add(panelCtmList);
		
		button_1 = new JButton("파일로 저장");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectExcelFilePath();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("굴림", Font.PLAIN, 15));
		button_1.setBounds(291, 429, 126, 35);
		panelCtmList.add(button_1);
		setVisible(true);

	}
	//@@@@@ 라디오버튼을 하나로 묶음
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
	
	//@@@@@ table하나로 묶어서 세팅
	public void setTable(ArrayList<VOCustomer> ctmnpdlist) {
		ctmmodel.setRowCount(0);
		for(int i = 0; i < ctmnpdlist.size(); i++) {
			ctmmodel.addRow(new String[] {ctmnpdlist.get(i).getCustomer_code(), 
					ctmnpdlist.get(i).getCustomer_name(), 
					ctmnpdlist.get(i).getCustomer_email(), ""+ctmnpdlist.get(i).getCustomer_tel(),
					""+ctmnpdlist.get(i).getCustomer_address(), ctmnpdlist.get(i).getCustomer_info()});
		}
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
			tableselectedRow = tableCtmList.getSelectedRow();
			
			Row label = sheet.createRow(0);		// 테이블 레이블
			for (int colnum = 0; colnum < tableCtmList.getColumnCount(); colnum++) {	// table 레이블 엑셀에 출력
				Cell cell = label.createCell(colnum);	// 셀 생성
				cell.setCellValue(tableCtmList.getColumnName(colnum));	// 셀 삽입
			}
			for (rownum = 1; rownum < tableCtmList.getRowCount()+1; rownum++) {	// 테이블내용 엑셀에  출력
				Row row = sheet.createRow(rownum);	// row 생성

				for (int colnum = 0; colnum < tableCtmList.getColumnCount(); colnum++) {	// 테이블 열 카운트 만큼 반복
					Cell cell = row.createCell(colnum);	//셀 생성
					cell.setCellValue(tableCtmList.getValueAt(rownum-1, colnum).toString());	// 셀 삽입
					
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
	
	public void updateTable(VOCustomer ctm, int row) {
		ctmmodel.removeRow(row);
		ctmmodel.addRow(new String[] {ctm.getCustomer_code(), 
				ctm.getCustomer_name(), 
				ctm.getCustomer_email(), ""+ctm.getCustomer_tel(),
				""+ctm.getCustomer_address(),  ctm.getCustomer_info()});
	}
	
	public void clearField() {
		labelCtmCode.setText("");
		tfCtmName.setText("");
		tfctmTel.setText("");
		tfCtmEmail.setText("");
		tfCtmAddress.setText("");
		textAreaCtmInfo.setText("");
	}
	
}