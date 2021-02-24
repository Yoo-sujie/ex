package pdm.view.main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileFilter;

import java.awt.Color;

import pdm.view.ctmmanager.CtmView;
import pdm.view.inmanager.InView;
import pdm.view.outmanager.OutView;
import pdm.view.pdmanager.PdView;
import java.awt.Font;

import java.awt.event.ActionListener;

import java.util.HashMap;

import java.awt.event.ActionEvent;

public class MainView extends JFrame implements ActionListener {
	private JPanel mainPane; // 메인 판넬
	private JPanel mainPanel;
	private JTabbedPane mainTab;
	private PdView Panelpd;
	private CtmView Panelctm;
	private InView PanelIn;
	private OutView PanelOut;
	private JButton btnExit;

	private JTable P1table, P2table, P3table;

	HashMap<String, Integer> hm = new HashMap<>();

	public MainView() {

		Panelpd = new PdView();
		P1table = Panelpd.getP1table();
		Panelctm = new CtmView();
		PanelIn = new InView();
		PanelOut = new OutView();
		setTitle("\uAD00\uB9AC\uD504\uB85C\uADF8\uB7A8");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false); // 창크기 고정
		setBounds(100, 100, 1000, 650);

		// 메인 가장 자리 테두리
		mainPane = new JPanel();
		mainPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(mainPane);
		mainPane.setLayout(null);

		// 메인 JPanel 초기화
		mainPanel = new JPanel();
		mainPanel.setBounds(14, 44, 966, 559);
		mainPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainPane.add(mainPanel);
		mainPanel.setLayout(null);

		mainTab = new JTabbedPane();
		mainTab.setFont(new Font("굴림", Font.PLAIN, 18));
		mainTab.setBounds(0, 0, 966, 559);

		mainTab.addTab("물품 관리", Panelpd);
		mainTab.addTab("입고 관리", PanelIn);
		mainTab.addTab("판매 관리", PanelOut);
		mainTab.addTab("거래처 관리", Panelctm);
		mainPanel.add(mainTab);
		getContentPane().add(mainPanel);

		btnExit = new JButton("\uD504\uB85C\uADF8\uB7A8 \uC885\uB8CC");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("굴림", Font.BOLD, 17));
		btnExit.setBounds(831, 5, 149, 29);
		mainPane.add(btnExit);

		System.out.println(P1table);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private String extension;
	private String description;

	public FileFilter FileTypeFilter(String exString, String deString) {
		// TODO Auto-generated constructor stub
		this.extension = exString;
		this.description = deString;
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
