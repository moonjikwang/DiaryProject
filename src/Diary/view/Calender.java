package Diary.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.AbstractButton;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

import com.mommoo.flat.button.FlatButton;

import Diary.model.ScheduleDAO;
import Diary.model.ScheduleDTO;

public class Calender extends JFrame implements ActionListener {
	/**
	 * 템플릿 사용 변수
	 */
	private ImageIcon icon = new ImageIcon("img/bg.png");
	private ImageIcon logoim = new ImageIcon("img/logo6.png");
	private static final long serialVersionUID = 1L;

	/**
	 * 클래스 변수
	 */
	// 기본 컴포넌트
	private JPanel contentPane;
	private JPanel AccountPanel;
	private JPanel panel; // 콤보박스의 바탕 패널
	private JPanel panel_2; // 달력과 탭의 바탕 패널
	JPanel panel_8; // 요일 패널
	JPanel panel_4; // 일정 추가 패널

	// 콤보박스 : 최초 날짜 세팅
	JPanel panel_3; // 날짜 패널
	JButton btnNewButton_2; // 월이동버튼
	JButton btnNewButton_3; // 월이동버튼
	JButton btnNewButton_4; // 오늘날짜보기
	// ---현재날짜 구하기
	Calendar date = Calendar.getInstance();
	LocalDate now = LocalDate.now();
	int nowyear = now.getYear();
	int nowmonth = now.getMonthValue();
	int nowdate = now.getDayOfMonth();
	// ---콤보박스에 세팅된 날짜 세팅하기
	JComboBox yearCombo;
	DefaultComboBoxModel<Integer> yearModel = new DefaultComboBoxModel<Integer>();
	JComboBox monthCombo;
	DefaultComboBoxModel<Integer> monthModel = new DefaultComboBoxModel<Integer>();

	// 달력내용
	// ---달력 칸수
	static final int CAL_WIDTH = 7;
	final static int CAL_HEIGHT = 6;
	int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
	// ---달력에 출력할 날짜
	int calYear;
	int calMonth;
	int calDayOfMon;
	private FlatButton dateButs[][];

	// 탭
	JTabbedPane panel_5; // 기본적인 탭 구조
	JPanel panel_5_2; // 이달의일정 탭
	JPanel panel_5_3; // 상세 탭
	JPanel panel_5_1; // 편집 탭
	// ---이달의일정
	JPanel sp; // 검색결과 표시 패널
	JTextField stf; // 검색창
	// ---상세
	JCheckBox chk;
	JLabel jl1;
	JLabel jl2;
	JLabel jl3;
	JTextField jf1;
	JTextField jf2;
	JTextArea jta1;
	JButton quit;
	JButton edit;
	JButton del;
//	ScheduleDTO editDTO = null;
	int editnum; // 글번호 가져가기.

	// 일정추가 영역
	JCheckBox chckbxNewCheckBox; // 중요체크
	private JTextField textField; // 날짜
	private JTextField textField_1; // 제목
	private JTextField textField_2; // 메모

	// 일정 추가 : 텍스트 필드에 입력된 내용을 DB로 보내주기
	ScheduleDTO sdto;
//	MemberDTO  mdto; //할일:MemberDTO 필요
	private String userid = userId(); // 할일:MemberDTO.getUserid();
	private String sdate;
	private String title;
	private String memo;
	private boolean attention;

	// 실행 메서드 : 본체에 이식 한 뒤 삭제예정


	/**
	 * Create the frame.
	 */
	// 생성자 : 기본프레임 구조
	public Calender() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 663);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		AccountPanel = new JPanel();
		AccountPanel.setBounds(0, 0, 1034, 624);
		contentPane.add(AccountPanel);
		AccountPanel.setLayout(null);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(255, 234, 151));
		panel_1_1.setBounds(725, 0, 309, 111);
		AccountPanel.add(panel_1_1);

		panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 234, 151));
		panel_2.setBounds(70, 121, 851, 353);
		AccountPanel.add(panel_2);
		panel_2.setLayout(null);

		panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(255, 234, 151));
		panel.setBounds(46, 29, 380, 43);
		panel_2.add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 234, 151));
		panel_1.setBounds(0, 0, 203, 111);
		AccountPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_2 = new JLabel("New label");
		lblNewLabel_1_2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == lblNewLabel_1_2) {
					Menu menu = new Menu(Login.member);
					menu.setVisible(true);
					setVisible(false);
				}
			}
		});
		lblNewLabel_1_2.setIcon(new ImageIcon("img/logo6.png"));
		lblNewLabel_1_2.setBounds(-25, 10, 252, 91);
		panel_1.add(lblNewLabel_1_2);

//---<콤보박스 GUI>--------------------
		init_Combo();
		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(251, 234, 189, 0));
		panel_7.setBounds(438, 29, 111, 33);
		panel_2.add(panel_7);

		btnNewButton_4 = new FlatButton("오늘 날짜 보기");
		btnNewButton_4.setBackground(new Color(242, 206, 96));
		btnNewButton_4.setFont(new Font("나눔고딕", Font.PLAIN, 11));
		btnNewButton_4.setPreferredSize(new Dimension(100, 25));
		btnNewButton_4.setForeground(Color.black);

		btnNewButton_4.setBorderPainted(false);
		btnNewButton_4.addActionListener(this);
		panel_7.add(btnNewButton_4);

//---<탭 영역>------------------------
		init_Tabs();
//---<일정추가 영역 GUI>----------------
		init_Addsche();
//---<달력내용 GUI>--------------------
		init_Dates();


//---<일정 탭>------------------------

	}

	private String userId() {
		String result = null;
		if(Login.member != null) {
			result = Login.member.getUserid();
		}
		return result;
	}

	private void init_Tabs() {

		panel_5 = new JTabbedPane();

		panel_5_2 = new JPanel();
		panel_5_3 = new JPanel(); // 이달의 일정
		panel_5_1 = new JPanel(); // 편집모드

		sp = new JPanel();
		sp.setBackground(Color.white);

		panel_5.setBounds(563, 29, 283, 320);

		panel_5.addTab("이달의 일정", panel_5_3);
		panel_5.setFont(new Font("나눔고딕", Font.PLAIN, 12));

		JScrollPane scrollPane = new JScrollPane(sp, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panel_5_3.add(scrollPane);

		panel_2.add(panel_5);

		// 상세일정
		panel_5_2.setLayout(new BorderLayout());

		chk = new JCheckBox("중요");
		chk.setFont(new Font("나눔고딕", Font.BOLD, 12));

		jl1 = new JLabel("날짜");
		jl1.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		jf1 = new JTextField();
		jf1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		jf1.setSize(100, 20);
		jf1.setOpaque(true);
		jf1.setBackground(Color.white);

		jl2 = new JLabel("제목");
		jl2.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		jf2 = new JTextField();
		jf2.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		jf2.setSize(100, 20);
		jf2.setOpaque(true);
		jf2.setBackground(Color.white);

		jl3 = new JLabel("내용");
		jl3.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		jta1 = new JTextArea();
		jta1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		jta1.setOpaque(true);
		jta1.setBackground(Color.white);

		quit = new FlatButton("닫기");
		quit.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		quit.setBackground(new Color(242, 206, 96));
		quit.setBorderPainted(false);
		quit.setPreferredSize(new Dimension(60, 25));
		quit.setForeground(Color.black);
		quit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				panel_5.removeTabAt(1);
				panel_5.setSelectedIndex(0);
			}
		});

		edit = new FlatButton("수정");
		edit.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		edit.setBackground(new Color(242, 206, 96));
		edit.setBorderPainted(false);
		edit.setPreferredSize(new Dimension(60, 25));
		edit.setForeground(Color.black);
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sdto = new ScheduleDTO();
				sdto.setSdate(jf1.getText());
				sdto.setTitle(jf2.getText());
				sdto.setMemo(jta1.getText());
				sdto.setAttention(chk.isSelected());
				sdto.setNum(editnum);
				ScheduleDAO.getInstance().edit(sdto);

				panel_5.remove(1);
				sp.setVisible(false);
				sp.removeAll();
				scheduleList();
				sp.setVisible(true);

				panel_5.setSelectedIndex(0);

			}
		}); // edit 클릭이벤트

		del = new FlatButton("삭제");
		del.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		del.setBackground(new Color(242, 206, 96));
		del.setBorderPainted(false);
		del.setPreferredSize(new Dimension(60, 25));
		del.setForeground(Color.black);
		del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ScheduleDAO.getInstance().delete(editnum);

				panel_5.remove(1);
				sp.setVisible(false);
				sp.removeAll();
				scheduleList();
				sp.setVisible(true);

				panel_5.setSelectedIndex(0);
			}
		});

		panel_5_2.add("North", chk);

		JPanel pane = new JPanel();

		pane.setLayout(null);

		jl1.setBounds(20, 5, 100, 20);
		jf1.setBounds(70, 5, 150, 20);
		pane.add(jl1);
		pane.add(jf1);

		jl2.setBounds(20, 40, 100, 20);
		jf2.setBounds(70, 40, 150, 20);
		pane.add(jl2);
		pane.add(jf2);

		jl3.setBounds(20, 70, 100, 20);
		jta1.setBounds(70, 70, 150, 155);

		pane.add(jl3);
		pane.add(jta1);

		panel_5_2.add("Center", pane);

		JPanel pane2 = new JPanel();

		pane2.add(edit);
		pane2.add(del);
		pane2.add(quit);

		panel_5_2.add("South", pane2);

		// 이달의일정
		// ---검색파트

		panel_5_3.setLayout(new BorderLayout());

		JPanel sf = new JPanel();
		stf = new JTextField();
		stf.setColumns(10);
		FlatButton sbtt = new FlatButton("검색");

		sbtt.setBorderPainted(false);
		sbtt.setBackground(new Color(242, 206, 96));
		sbtt.setForeground(Color.black);
		sbtt.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		sbtt.setBorderPainted(false);
		sbtt.setPreferredSize(new Dimension(60, 25));
		sbtt.setForeground(Color.black);
		sbtt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				sp.setVisible(false);
				sp.removeAll();

				if (!stf.getText().equals("")) {
					scheduleList();
				} // if문 끝
				else {
					JTextPane msg = new JTextPane();
					msg.setText("검색어를 입력해주세요");
					msg.setFont(new Font("나눔고딕", Font.PLAIN, 12));
					msg.setBackground(Color.white);
					sp.add(msg);
					sp.updateUI();
					sp.setVisible(true);
				}
			}
		});// 검색버튼 클릭시 이벤트 끝

		sf.add(stf);
		sf.add(sbtt);

		// 검색창 초기화
		FlatButton reset = new FlatButton("초기화");

		reset.setBorderPainted(false);
		reset.setBackground(new Color(242, 206, 96));
		reset.setPreferredSize(new Dimension(60, 25));
		reset.setForeground(Color.black);
		reset.setFont(new Font("나눔고딕", Font.PLAIN, 12));

		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				sp.setVisible(false);
				sp.removeAll();
				scheduleList();
				sp.setVisible(true);
			}
		});

		sf.add(reset);

		panel_5_3.add("North", sf);

		// 이달의 일정 파트

		scheduleList();

//		panel_5_3.add("Center", sp);
		panel_5_3.add("Center", scrollPane);

//		panel_5_3.add(sp);
	}

	
	
	

	private void scheduleList() {
		int yy = (Integer) yearCombo.getSelectedItem();
		int mm = (Integer) monthCombo.getSelectedItem();

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		ScheduleDTO sDTO = new ScheduleDTO();
		sDTO.setUserId(userid);
		sDTO.setSdate(yy + "-" + mm);

		String keyword = stf.getText();
		ArrayList<ScheduleDTO> schedules;

		if (keyword.equalsIgnoreCase("")) {
			schedules = ScheduleDAO.getInstance().select(sDTO);
		} else {
			schedules = ScheduleDAO.getInstance().search(sDTO, keyword);
		}

		JButton[] items = new JButton[schedules.size()];

		for (int i = 0; i < schedules.size(); i++) {
			ScheduleDTO editDTO = schedules.get(i);
			String sDate = editDTO.getSdate().substring(0, 10);
			String title = editDTO.getTitle();
			String memo = editDTO.getMemo();
			boolean att = editDTO.isAttention();

			items[i] = new JButton("[" + sDate + "] " + title);
			items[i].setHorizontalAlignment(SwingConstants.LEFT);

			items[i].setBackground(Color.WHITE);
			items[i].setFont(new Font("나눔고딕", Font.PLAIN, 12));
			items[i].setBorderPainted(false);
			items[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					editnum = editDTO.getNum();

					jf1.setText(sDate);
					jf2.setText(title);
					jta1.setText(memo);
					chk.setSelected(att);

					panel_5.addTab("상세일정", panel_5_2);
					panel_5.setSelectedIndex(1);

				}// 리스트 아이템 클릭이벤트내용
			});// 리스트 아이템 클릭이벤트

			sp.setLayout(gbl);
			gbc.gridx = 1;
			gbc.anchor = GridBagConstraints.NORTHWEST;
			gbc.weightx = 0.9;
			gbl.setConstraints(items[i], gbc);

			sp.add(items[i]);
			sp.updateUI();
			sp.setVisible(true);
			stf.setText(null);

		} // for문 끝

	}// schedeleList 끝

	private void init_Addsche() {
		JPanel jp1 = new JPanel();
		jp1.setBackground(new Color(255, 255, 255, 0));

		panel_4 = new JPanel();
		panel_4.setBounds(113, 484, 800, 60);
		AccountPanel.add(panel_4);
		panel_4.setLayout(new BorderLayout());
		panel_4.setBackground(new Color(255, 255, 255, 100));

		chckbxNewCheckBox = new JCheckBox("중요");
		chckbxNewCheckBox.setFont(new Font("나눔고딕", Font.PLAIN, 12));

		chckbxNewCheckBox.setBackground(new Color(255, 242, 192));
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == chckbxNewCheckBox) {
					boolean check = chckbxNewCheckBox.isSelected();
					attention = true;
				}
			}
		});
		jp1.add(chckbxNewCheckBox);

		JLabel lblNewLabel_1 = new JLabel("날짜");
		lblNewLabel_1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		jp1.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setEnabled(true);
		textField.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		jp1.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("일정제목");
		lblNewLabel_2.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		jp1.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == textField_1) {
					String data = textField_1.getText();
					title = data;
				}

			}
		});
		jp1.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("일정내용");
		lblNewLabel_3.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		jp1.add(lblNewLabel_3);

		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == textField_2) {
					String data = textField_2.getText();
					memo = data;
				}
			}
		});
		jp1.add(textField_2);
		textField_2.setColumns(30);

		panel_4.add("North", jp1);

		JPanel jp2 = new JPanel();
		jp2.setBackground(new Color(255, 255, 255, 0));
		FlatButton btnNewButton = new FlatButton("지우기");
		btnNewButton.setBackground(new Color(242, 206, 96));
		btnNewButton.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btnNewButton.setBorderPainted(false);
		btnNewButton.setPreferredSize(new Dimension(70, 25));
		btnNewButton.setForeground(Color.black);
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textField_1.setText("");
				textField_2.setText("");
				textField.setText("");
				chckbxNewCheckBox.setSelected(false);

			}
		});
		jp2.add(btnNewButton);

		FlatButton btnNewButton_1 = new FlatButton("일정추가");
		btnNewButton_1.setBackground(new Color(242, 206, 96));
		btnNewButton_1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btnNewButton_1.setBorderPainted(false);
		btnNewButton_1.setPreferredSize(new Dimension(70, 25));
		btnNewButton_1.setForeground(Color.black);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addSchedule();
				sp.setVisible(false);
				sp.removeAll();
				scheduleList();
				sp.updateUI();
				sp.setVisible(true);
			}
		});
		jp2.add(btnNewButton_1);
		panel_4.add("South", jp2);

		JLabel lblNewLabel = new JLabel("l");
		lblNewLabel.setBounds(0, 0, 1034, 624);
		lblNewLabel.setIcon(icon);
		AccountPanel.add(lblNewLabel);
	}

	private void init_Dates() {
		panel_3 = new JPanel();
		panel_8 = new JPanel();

		panel_3.setBackground(new Color(255, 242, 192));

		makeWeek(panel_8); // 캘린더 만드는 부분을 메서드로 분리
		makeCalendar(panel_3, makeCalData(nowyear, nowmonth)); // 캘린더 만드는 부분을 메서드로 분리
		panel_2.add(panel_8);
		panel_2.add(panel_3);

	}

	private void init_Combo() {
		yearCombo = new JComboBox();
		setYear();
		btnNewButton_2 = new FlatButton("◀");
		btnNewButton_2.setToolTipText("이전월");
		btnNewButton_2.setFont(new Font("나눔 고딕", Font.PLAIN, 14));
		btnNewButton_2.setBackground(new Color(242, 206, 96));
		btnNewButton_2.setBorderPainted(false);
		btnNewButton_2.setPreferredSize(new Dimension(50, 25));
		btnNewButton_2.addActionListener(this);
		panel.add(btnNewButton_2);
		yearCombo.setSelectedItem(nowyear);
		yearCombo.setToolTipText("연도 선택");
		panel.add(yearCombo);

		JLabel lblNewLabel_4 = new JLabel("년");
		lblNewLabel_4.setFont(new Font("나눔 고딕", Font.PLAIN, 12));
		panel.add(lblNewLabel_4);

		monthCombo = new JComboBox();
		setMonth();
		monthCombo.setSelectedItem(nowmonth);
		monthCombo.setToolTipText("월 선택");
		panel.add(monthCombo);

		yearCombo.addActionListener(this);
		monthCombo.addActionListener(this);

		JLabel lblNewLabel_5 = new JLabel("월");
		lblNewLabel_5.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel.add(lblNewLabel_5);

		btnNewButton_3 = new FlatButton("▶");
		btnNewButton_3.setToolTipText("다음월");
		btnNewButton_3.setBackground(new Color(242, 206, 96));
		btnNewButton_3.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		btnNewButton_3.setPreferredSize(new Dimension(50, 25));
		btnNewButton_3.setBorderPainted(false);
		btnNewButton_3.addActionListener(this);
		panel.add(btnNewButton_3);

	}

// -------------------<콤보박스>------------------------------------------
	// 년도세팅
	public void setYear() {
		for (int i = nowyear - 100; i <= nowyear + 50; i++) {
			yearModel.addElement(i);
		}
		yearCombo.setModel(yearModel);
	}

	// 월세팅
	public void setMonth() {
		for (int i = 1; i <= 12; i++) {
			monthModel.addElement(i);
		}
		monthCombo.setModel(monthModel);
	}

//------------------<달력 출력 영역 >--------------------------------
	// ---------(월 날짜 만들기)-------------
	private int[][] makeCalData(int calYear, int calMonth) { // 일자를 7*6의 이중배열로 만들기

		Calendar sDay = Calendar.getInstance(); // 첫날 객체 생성
		sDay.set(calYear, calMonth - 1, 1); // 첫날 날짜 세팅

		Calendar eDay = Calendar.getInstance(); // 막날 객체 생성
		eDay.set(calYear, calMonth, 1); // 다음달 첫날에서 하루 전 날이 이번달의 막날임
		eDay.add(Calendar.DATE, -1);

		int start_Day_of_week = sDay.get(Calendar.DAY_OF_WEEK) - 1; // 첫날 요일 찾기
		int end_Day = eDay.get(Calendar.DATE);

		// 달력 배열 초기화
		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {
				calDates[i][j] = 0;
			}
		}
		// 달력 배열에 값 채워넣기
		for (int i = 0, num = 1, k = 0; i < CAL_HEIGHT; i++) {
			if (i == 0)
				k = start_Day_of_week;
			else
				k = 0;
			for (int j = k; j < CAL_WIDTH; j++) {
				if (num <= end_Day)
					calDates[i][j] = num++;
			}
		}
		return calDates;
	}

//----------(달력 모양 만들기)--------------
	// -------------<요일칸>---------------
	private void makeWeek(JPanel panel_8) {
		panel_8.setLayout(new GridLayout(1, 7, 2, 2));
		panel_8.setBounds(46, 72, 500, 46);

		JButton[] weekDaysName = new JButton[7]; // 요일 칸을 버튼의 배열로 정의
		final String WEEK_DAY_NAME[] = { "SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT" };
		for (int i = 0; i < CAL_WIDTH; i++) {
			weekDaysName[i] = new JButton(WEEK_DAY_NAME[i]);
			weekDaysName[i].setBorderPainted(false); // 테두리 없애기
			weekDaysName[i].setBackground(Color.WHITE);

			if (i == 0)
				weekDaysName[i].setBackground(new Color(200, 100, 100));
			else if (i == 6)
				weekDaysName[i].setBackground(new Color(100, 150, 200));
			else
				weekDaysName[i].setBackground(new Color(220, 220, 220));

			panel_8.add(weekDaysName[i]);
		}
	}

	// -------------<낱짜칸>---------------
	private void makeCalendar(JPanel panel_3, int[][] calDates) {
		panel_3.setBorder(null);
		panel_3.setBounds(46, 117, 500, 232);
		panel_3.setLayout(new GridLayout(6, 7, 2, 2));

		int yy = (Integer) yearCombo.getSelectedItem();
		int mm = (Integer) monthCombo.getSelectedItem();

		
		
		dateButs = new FlatButton[6][7]; // 날짜 칸을 버튼의 이중배열로 정의

		for (int i = 0; i < CAL_HEIGHT; i++) {
			for (int j = 0; j < CAL_WIDTH; j++) {

				// 날짜 칸 만들기
				dateButs[i][j] = new FlatButton();
				dateButs[i][j].setBorderPainted(false); // 테두리 없애기
				dateButs[i][j].setBackground(Color.WHITE);

				dateButs[i][j].addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						Object eventObj = e.getSource();

						String strdate = ((AbstractButton) eventObj).getText();

						if (eventObj instanceof FlatButton) {
							String strYear = Integer.toString(yy);
							String strMon = Integer.toString(mm);

							textField.setText(strYear + "-" + strMon + "-" + strdate); //
							textField.updateUI();
						} else {
							return;
						}

					}
				});

				dateButs[i][j].setFont(new Font("나눔고딕", Font.PLAIN, 12));
				dateButs[i][j].setForeground(Color.black);


				
				if (j == 0)
					dateButs[i][j].setBackground(new Color(255, 220, 220)); // 일요일 색지정
				else if (j == 6)
					dateButs[i][j].setBackground(new Color(220, 230, 255)); // 토요일 색지정

				// 날짜를 날짜칸 버튼에 배치하기
				dateButs[i][j].setText(calDates[i][j] + "");

				// 날짜가 없는 칸은 감추기
				if (calDates[i][j] == 0)
					dateButs[i][j].setVisible(false);
				else
					dateButs[i][j].setVisible(true);

				// 오늘날짜 표시
				if (calDates[i][j] == nowdate && yy == nowyear && mm == nowmonth)
					dateButs[i][j].setBackground(new Color(251, 234, 151));

				panel_3.add(dateButs[i][j]);
			}
		}
	}

//---------------<일정 등록 영역 (임시구현)>-----------------------

	private void addSchedule() {
		sdto = new ScheduleDTO();

		sdate = textField.getText();
		title = textField_1.getText();
		memo = textField_2.getText();
		attention = chckbxNewCheckBox.isSelected();

		sdto.setUserId(userid);
		sdto.setSdate(sdate);

		sdto.setTitle(title);
		sdto.setMemo(memo);
		sdto.setAttention(attention);

		textField.setText("");
		textField_1.setText("");
		textField_2.setText("");
		chckbxNewCheckBox.setSelected(false);

		int result = ScheduleDAO.getInstance().insert(sdto);
	}

//---------------------<기능 구현>--------------------------
	public void actionPerformed(ActionEvent e) {
		Object eventObj = e.getSource();
		int yy = (Integer) yearCombo.getSelectedItem();
		int mm = (Integer) monthCombo.getSelectedItem();

		// ----<콤보박스에서 선택한 연/월의 달력을 보여주는 이벤트>
		if (eventObj instanceof JComboBox) {
			panel_3.setVisible(false); // 보여지는 패널을 숨긴다.
			panel_3.removeAll(); // 라벨 지우기

			makeCalendar(panel_3, makeCalData(yy, mm));

			panel_3.setVisible(true); // 패널 재출력

			sp.setVisible(false);
			sp.removeAll();
			scheduleList();
			sp.setVisible(true);
		}

		// ----<화살표로 월 이동하는 이벤트>
		if (eventObj == btnNewButton_2) {
			panel_3.setVisible(false);
			panel_3.removeAll();
			if (mm == 1) {
				yy--;
				mm = 12;
			} else {
				mm--;
			}
			makeCalendar(panel_3, makeCalData(yy, mm));
			panel_3.setVisible(true);

			sp.setVisible(false);
			sp.removeAll();
			scheduleList();
			sp.setVisible(true);

		}
		if (eventObj == btnNewButton_3) {
			panel_3.setVisible(false);
			panel_3.removeAll();
			if (mm == 12) {
				yy++;
				mm = 1;
			} else {
				mm++;
			}
			makeCalendar(panel_3, makeCalData(yy, mm));
			panel_3.setVisible(true);
		}

		// --------(오늘날짜 보기)------------
		if (eventObj == btnNewButton_4) {
			panel_3.setVisible(false);
			panel_3.removeAll();
			yy = nowyear;
			mm = nowmonth;
			makeCalendar(panel_3, makeCalData(yy, mm));
			panel_3.setVisible(true);
		}

		yearCombo.setSelectedItem(yy);
		monthCombo.setSelectedItem(mm);
	}

}