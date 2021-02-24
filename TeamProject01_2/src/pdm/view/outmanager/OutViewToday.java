package pdm.view.outmanager;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import pdm.vo.VOSellProduct;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileOutputStream;
import java.awt.event.ActionEvent;


public class OutViewToday extends JFrame {
	
	private JTable tableDeList;
	private JScrollPane scrollPaneDeList;
	private DefaultTableModel sellListModel;
	public OutViewToday() {

		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("금일 판매 내역");
		getContentPane().setLayout(null);
		setBounds(224, 5, 434, 442);
		String[][] sellrow = new String[][] { };
		String[] sellcol = new String[] { "판매코드","거래품목","거래처","수량","총액","날짜"};

		sellListModel = new DefaultTableModel(sellrow, sellcol);
		tableDeList = new JTable(sellListModel);

		scrollPaneDeList = new JScrollPane(tableDeList);
		scrollPaneDeList.setBounds(14, 109, 392, 201);
		getContentPane().add(scrollPaneDeList);

		JLabel lblNewLabel = new JLabel("금일 판매 내역");
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setBounds(142, 19, 138, 53);
		getContentPane().add(lblNewLabel);

		JButton btnNewButton = new JButton("확인");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnNewButton.setBounds(142, 337, 136, 42);
		getContentPane().add(btnNewButton);

		JButton btnExportListToFile = new JButton("파일로 내보내기");
		btnExportListToFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					selectExcelFilePath();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}});
		btnExportListToFile.setBounds(274, 75, 132, 23);
		getContentPane().add(btnExportListToFile);

		try {
			ArrayList<VOSellProduct> sellList = new OutViewDAO().setTodaySell();
			setTable(sellList);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setVisible(true);
		setLocationRelativeTo(null);
		

	}

	public void setTable(ArrayList<VOSellProduct> sellList) {
		sellListModel.setRowCount(0);
		for(int i = 0; i < sellList.size(); i++) {
			sellListModel.addRow(new String[] {sellList.get(i).getSell_code(), 
					sellList.get(i).getProduct_code(), 
					sellList.get(i).getCompany_code(), ""+sellList.get(i).getSell_quantity(),
					""+sellList.get(i).getSell_amount(), sellList.get(i).getOutput_date()});
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
						System.out.println("else");
						createExcel(savefile);
						break;
					}
				}
				else {
					System.out.println("else");
					createExcel(savefile);
					break;
				}
			}
			else
				break;
		}
		
	}

	public void createExcel(File saveFile) throws Exception {
		FileOutputStream fos = null;
		HSSFSheet sheet = null;
		HSSFWorkbook workbook = null;
		System.out.println(saveFile.toString());
		try {
			workbook = new HSSFWorkbook();	//Excel Workbook 생성
			sheet = workbook.createSheet();	//Excel Sheet 생성
			fos = null;
			
			Row label = sheet.createRow(0);		// 테이블 레이블
			for (int colnum = 0; colnum < tableDeList.getColumnCount(); colnum++) {	// table 레이블 엑셀에 출력
				Cell cell = label.createCell(colnum);	// 셀 생성
				cell.setCellValue(tableDeList.getColumnName(colnum));	// 셀 삽입
			}
			
			for (int rownum = 1; rownum < tableDeList.getRowCount()+1; rownum++) {	// 테이블내용 엑셀에  출력
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

}