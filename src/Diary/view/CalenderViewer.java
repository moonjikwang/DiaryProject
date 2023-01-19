package Diary.view;

/**
 *  할일:
 * 	추후 수정해야할 부분
 * 
 * A파트 : 연월일 선택영역 (미구현)
 * B파트 : 달력 출력 영역 (JDatePicker로 임시 구현)
 * C파트 : 할일 목록 영역 (임시구현)
 * D파트 : 일정 등록 영역 (임시구현)
 * 
 * 제이데이트피커 : https://blog.naver.com/ka28/221975811790
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Diary.model.ScheduleDAO;
import Diary.model.ScheduleDTO;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import java.awt.Font;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.AbstractListModel;

public class CalenderViewer extends JFrame {
	private ImageIcon icon = new ImageIcon("img/bg.png");
	private ImageIcon logoim = new ImageIcon("img/logo6.png");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalenderViewer frame = new CalenderViewer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//---------------<B파트 변수 선언>-----------
	JButton dateButs[][];

	/**
	 * Create the frame.
	 */
	public CalenderViewer() {

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 663);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel AccountPanel = new JPanel();
		AccountPanel.setBounds(0, 0, 1034, 624);
		contentPane.add(AccountPanel);
		AccountPanel.setLayout(null);

		JPanel panel_1_1 = new JPanel();
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(255, 234, 151));
		panel_1_1.setBounds(725, 0, 309, 111);
		AccountPanel.add(panel_1_1);

		JLabel lblNewLabel_1_1 = new JLabel("기능별 로고");
		lblNewLabel_1_1.setBounds(0, 10, 297, 91);
		panel_1_1.add(lblNewLabel_1_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(251, 234, 189));
		panel_2.setBounds(70, 121, 893, 442);
		AccountPanel.add(panel_2);
		panel_2.setLayout(null);
		
// -------------------------<B파트 GUI>-------------------
		//-------------<요일칸>---------------
		
		JPanel panel_8 = new JPanel();
		panel_8.setBounds(46, 72, 500, 46);
		panel_8.setLayout(new GridLayout(1, 7, 2, 2));
		panel_2.add(panel_8);
		
		JButton[] weekDaysName = new JButton[7];	//요일 칸을 버튼의 배열로 정의
		final String WEEK_DAY_NAME[] = { "SUN", "MON", "TUE", "WED", "THR", "FRI", "SAT" };
		for(int i=0 ; i<CAL_WIDTH ; i++){
			weekDaysName[i]=new JButton(WEEK_DAY_NAME[i]);
			weekDaysName[i].setBorderPainted(false);		//테두리 없애기
			weekDaysName[i].setBackground(Color.WHITE);
			
			if(i == 0) weekDaysName[i].setBackground(new Color(200, 100, 100));
			else if (i == 6) weekDaysName[i].setBackground(new Color(100, 150, 200));
			else weekDaysName[i].setBackground(new Color(220, 220, 220));
			
			panel_8.add(weekDaysName[i]);
		}
		
		//-------------<낱짜칸>---------------
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBounds(46, 117, 500, 232);
		panel_3.setLayout(new GridLayout(6, 7, 2, 2));

		dateButs = new JButton[6][7];	//날짜 칸을 버튼의 이중배열로 정의
		
		for(int i=0 ; i<CAL_HEIGHT ; i++){
			for(int j=0 ; j<CAL_WIDTH ; j++){
				
				//날짜 칸 만들기
				dateButs[i][j]=new JButton();		
				dateButs[i][j].setBorderPainted(false);		//테두리 없애기
				dateButs[i][j].setBackground(Color.WHITE);
//				dateButs[i][j].addActionListener(lForDateButs);
				
				if(j==0) dateButs[i][j].setBackground(new Color(255,220,220)); //일요일 색지정
				else if(j==6) dateButs[i][j].setBackground(new Color(220,230,255));	//토요일 색지정
				
				//월별 날짜를 이중배열로 만드는 메서드
				calDates = makeCalData();
	
				//날짜를 날짜칸 버튼에 배치하기
				dateButs[i][j].setText(calDates[i][j]+"");
				
				//날짜가 없는 칸은 감추기
				if(calDates[i][j] == 0) dateButs[i][j].setVisible(false);
				else dateButs[i][j].setVisible(true);
				
				panel_3.add(dateButs[i][j]);
			}
		}
// -------------------------<B파트 GUI 끝>-------------------		

		panel_2.add(panel_3);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(242, 206, 96));
		panel.setBounds(46, 29, 380, 33);
		panel_2.add(panel);

		JButton btnNewButton_2 = new JButton("◀");
		btnNewButton_2.setToolTipText("이전월");
		btnNewButton_2.setBackground(new Color(240,240,240));
		btnNewButton_2.setBorderPainted(false);	
		panel.add(btnNewButton_2);

		yearCombo = new JComboBox();
		setYear();
		yearCombo.setToolTipText("연도 선택");
		yearCombo.setModel(new DefaultComboBoxModel());
//		yearCombo.setSelectedIndex(5);
		
		panel.add(yearCombo);

		JLabel lblNewLabel_4 = new JLabel("년");
		panel.add(lblNewLabel_4);

		monthCombo = new JComboBox();
		setMonth();
		monthCombo.setToolTipText("월 선택");
		monthCombo.setModel(new DefaultComboBoxModel());
//		monthCombo.setSelectedIndex(nowmonth - 1);
		panel.add(monthCombo);

		JLabel lblNewLabel_5 = new JLabel("월");
		panel.add(lblNewLabel_5);

		JButton btnNewButton_3 = new JButton("▶");
		btnNewButton_3.setToolTipText("다음월");
		btnNewButton_3.setBackground(new Color(240,240,240));
		btnNewButton_3.setBorderPainted(false);	
		panel.add(btnNewButton_3);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(46, 359, 800, 60);
		panel_2.add(panel_4);

		JLabel lblNewLabel_1 = new JLabel("날짜");
		lblNewLabel_1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_4.add(lblNewLabel_1);

		textField = new JTextField();
		textField.setEnabled(false);
		panel_4.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("일정제목");
		lblNewLabel_2.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_4.add(lblNewLabel_2);

		textField_1 = new JTextField();
		textField_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == textField_1) {
					String data = textField_1.getText();
					title = data;
				}

			}
		});
		panel_4.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("일정내용");
		lblNewLabel_3.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		panel_4.add(lblNewLabel_3);

		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == textField_2) {
					String data = textField_2.getText();
					memo = data;
				}
			}
		});
		panel_4.add(textField_2);
		textField_2.setColumns(30);

		chckbxNewCheckBox = new JCheckBox("중요");
		chckbxNewCheckBox.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == chckbxNewCheckBox) {
					boolean check = chckbxNewCheckBox.isSelected();
					attention = true;
				}
			}
		});
		panel_4.add(chckbxNewCheckBox);

		JButton btnNewButton = new JButton("지우기");
		btnNewButton.setBackground(new Color(242, 206, 96));
		btnNewButton.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				viewSchedule();

			}
		});
		panel_4.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("일정추가");
		btnNewButton_1.setBackground(new Color(242, 206, 96));
		btnNewButton_1.setFont(new Font("나눔고딕", Font.PLAIN, 12));
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				addSchedule();
			}
		});
		panel_4.add(btnNewButton_1);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(563, 29, 283, 320);
		panel_2.add(panel_5);
		panel_5.setLayout(new CardLayout(0, 0));

		JList list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = viewSchedule();

			public int getSize() {
				return values.length;
			}

			public Object getElementAt(int index) {
				return values[index];
			}
		});
		list.setToolTipText("일정목록");
		panel_5.add(list, "name_9384279322200");

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(438, 69, 111, -42);
		panel_2.add(panel_6);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(242, 206, 96));
		panel_7.setBounds(438, 29, 111, 33);
		panel_2.add(panel_7);

		JButton btnNewButton_4 = new JButton("오늘 날짜 보기");
		btnNewButton_4.setBackground(new Color(240,240,240));
		btnNewButton_4.setBorderPainted(false);	
		panel_7.add(btnNewButton_4);
		
		

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 234, 151));
		panel_1.setBounds(0, 0, 203, 111);
		AccountPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_2 = new JLabel("New label");
		lblNewLabel_1_2.setIcon(new ImageIcon("img/logo6.png"));
		lblNewLabel_1_2.setBounds(-25, 10, 252, 91);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel = new JLabel("l");
		lblNewLabel.setBounds(0, 0, 1034, 624);
		lblNewLabel.setIcon(icon);
		AccountPanel.add(lblNewLabel);

	}
	
//-------------------<A파트 : 연월일 선택영역 (미구현)>------------------------------------------
		// 콤보박스에 날짜 세팅 : 이후 이 날짜에 따라 달력을 출력할 예정
		Calendar date = Calendar.getInstance();
		LocalDate now = LocalDate.now();
		int nowyear = now.getYear();
		int nowmonth = now.getMonthValue();
		int nowdate = now.getDayOfMonth();

	
		JComboBox yearCombo;
		JComboBox monthCombo;

		// 년도세팅
//		public String[] setYear() {
//			String[] years = new String[10];
//			int j = 0;
//			for (int i = nowyear - 5; i < nowyear + 5; i++) { // 해당구문을 반복문을돌려서 배열로 만들어주는 메서드
//
//				String srt = Integer.toString(i);
//				years[j] = srt;
//				j++;
//			}
//
//			return years;
//		}

		public void setYear() {
			for (int i = nowyear - 5; i < nowyear + 5; i++) {
				yearCombo.addItem(i);
			}
//			yearCombo.setSelectedItem(yearCombo); // 콤보박스에 담지만 이벤트와 연동시켜주기위해 선언이라고 함..
		}
		
		
		// 월세팅
		public void setMonth() {
			for (int i = 1; i <= 12; i++) {
				monthCombo.addItem(i);
			}
//			monthCombo.setSelectedItem(nowmonth); // 콤보박스에 담지만 이벤트와 연동시켜주기위해 선언이라고 함..
		}

//-------------------<A파트 끝>------------------------------------------
		
		
//------------------<B파트 : 달력 출력 영역 >-----------
		static final int CAL_WIDTH = 7;
		final static int CAL_HEIGHT = 6;
		int calDates[][] = new int[CAL_HEIGHT][CAL_WIDTH];
		int calYear;
		int calMonth;
		int calDayOfMon;
		
		
	
		private int[][] makeCalData(){ //일자를 7*6의 이중배열로 만들기
//						
//			calYear = Integer.parseInt(years[yearCombo.getSelectedIndex()]);
//			calMonth = (int) monthCombo.getSelectedItem();
//			
//			
			calYear = nowyear;
			calMonth = nowmonth;
			
			Calendar sDay = Calendar.getInstance();		//첫날 객체 생성
			sDay.set(calYear, calMonth-1, 1);			//첫날 날짜 세팅
			
			
			Calendar eDay = Calendar.getInstance();		//막날 객체 생성
			eDay.set(calYear, calMonth, 1); 			//다음달 첫날에서 하루 전 날이 이번달의 막날임
			eDay.add(Calendar.DATE, -1);
			
			int start_Day_of_week = sDay.get(Calendar.DAY_OF_WEEK);		//첫날 요일 찾기
			int end_Day = eDay.get(Calendar.DATE);
			
			// 달력 배열 초기화
			for(int i = 0 ; i<CAL_HEIGHT ; i++){
				for(int j = 0 ; j<CAL_WIDTH ; j++){
					calDates[i][j] = 0;
				}
			}
			// 달력 배열에 값 채워넣기
			for(int i = 0, num = 1, k = 0 ; i<CAL_HEIGHT ; i++){
				if(i == 0) k = start_Day_of_week;
				else k = 0;
				for(int j = k ; j<CAL_WIDTH ; j++){
					if(num <= end_Day) calDates[i][j]=num++;
				}
			}
			return calDates;
			
		}
		
		
	
//--------------------------------------<B파트 끝>-----------------------
	


//------------------<C파트 : 할일 목록 영역 (임시구현)>---------------------------------
	// 일정 조회
	private String[] viewSchedule() {
		ArrayList schedules = ScheduleDAO.getInstance().select(userid);
		String[] schList = new String[schedules.size()];

		for (int i = 0; i < schedules.size(); i++) {

			ScheduleDTO dto = (ScheduleDTO) schedules.get(i);
			schList[i] = "[" + dto.getSdate().substring(0, 10) + "] " + dto.getTitle();

			System.out.println(schList[i]);

		}

		return schList;

	}
//------------------<C파트 끝>---------------------------------

//---------------<D파트 : 일정 등록 영역 (임시구현)>-----------------------
	// 일정 추가 : 텍스트 필드에 입력된 내용을 DB로 보내주기
	ScheduleDTO sdto;
//			MemberDTO  mdto; //할일:MemberDTO 필요

	private String sdate;
	private String title;
	private String memo;
	private boolean attention;

	private String userid = "yh"; // 할일:MemberDTO 필요
	private LocalDate localDate;

	UtilDateModel model;
	JDatePanelImpl datePanel;
	JDatePickerImpl datePicker; // 할일: 직접 코드 구현후 삭제예정

	JCheckBox chckbxNewCheckBox;

	private void addSchedule() {
		sdto = new ScheduleDTO();

		sdate = model.getYear() + "-" + (model.getMonth() + 1) + "-" + model.getDay();

		title = textField_1.getText();
		memo = textField_2.getText();
		attention = chckbxNewCheckBox.isSelected();

		sdto.setUserId(userid);
		sdto.setSdate(sdate);

		sdto.setTitle(title);
		sdto.setMemo(memo);
		sdto.setAttention(attention);

		int result = ScheduleDAO.getInstance().insert(sdto);
	}
// -----------------------------<D파트 끝>-----------------------
}
