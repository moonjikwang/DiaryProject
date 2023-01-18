package Diary.view;

/**
 *  할일:
 * 	추후 수정해야할 부분
 * 
 * "2023-01-01" 스트링을 에스큐엘 데이트로 바꾸기
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

public class CalenderViewer extends JFrame {
	private ImageIcon icon = new ImageIcon(CalenderViewer.class.getResource("bg5.png"));
	private ImageIcon logoim = new ImageIcon(CalenderViewer.class.getResource("logo6.png"));
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

	// 직접 작성한 코드
	// 콤보박스에 날짜 세팅 : 이후 이 날짜에 따라 달력을 출력할 예정
	Calendar date = Calendar.getInstance();
	LocalDate now = LocalDate.now();
	int year = now.getYear();
	int month = now.getMonthValue();
	int nowdate = now.getDayOfMonth();

	int calYear = year;
	int calMonth = month;
	int calDate = nowdate;

	JComboBox yearCombo;
	JComboBox monthCombo;

	// 년도세팅
	public String[] setYear() {
		String[] years = new String[10];
		int j = 0;
		for (int i = year - 5; i < year + 5; i++) { // 해당구문을 반복문을돌려서 배열로 만들어주는 메서드

			String srt = Integer.toString(i);
			years[j] = srt;
			j++;
		}

		return years; 
	}

	// 월세팅
	public void setMonth() {
		for (int i = 1; i <= 12; i++) {
			monthCombo.addItem(i);
		}
		monthCombo.setSelectedItem(month); // 콤보박스에 담지만 이벤트와 연동시켜주기위해 선언이라고 함..
	}

	
	
	
	

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
		panel_1_1.setBackground(new Color(251, 222, 224));
		panel_1_1.setBounds(725, 0, 309, 111);
		AccountPanel.add(panel_1_1);

		JLabel lblNewLabel_1_1 = new JLabel("기능별 로고");
		lblNewLabel_1_1.setBounds(0, 10, 297, 91);
		panel_1_1.add(lblNewLabel_1_1);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(234, 220, 215));
		panel_2.setBounds(70, 121, 893, 442);
		AccountPanel.add(panel_2);
		panel_2.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBorder(null);
		panel.setBackground(new Color(234, 220, 215));
		panel.setBounds(46, 29, 380, 33);
		panel_2.add(panel);

		JButton btnNewButton_2 = new JButton("◀");
		btnNewButton_2.setToolTipText("이전월");
		panel.add(btnNewButton_2);

		yearCombo = new JComboBox();
		yearCombo.setToolTipText("연도 선택");
		yearCombo.setModel(new DefaultComboBoxModel(setYear()));
		yearCombo.setSelectedIndex(5);
		panel.add(yearCombo);

		JLabel lblNewLabel_4 = new JLabel("년");
		panel.add(lblNewLabel_4);

		monthCombo = new JComboBox();
		monthCombo.setToolTipText("월 선택");
		monthCombo.setModel(new DefaultComboBoxModel(
				new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
		monthCombo.setSelectedIndex(month - 1);
		panel.add(monthCombo);

		JLabel lblNewLabel_5 = new JLabel("월");
		panel.add(lblNewLabel_5);

		JButton btnNewButton_3 = new JButton("▶");
		btnNewButton_3.setToolTipText("다음월");
		panel.add(btnNewButton_3);

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(null);
		panel_3.setBounds(46, 72, 500, 277);
		panel_2.add(panel_3);
//		panel_3.setLayout(new GridLayout(5, 7, 0, 0));
		panel_3.setLayout(new BorderLayout());

		model = new UtilDateModel();
		datePanel = new JDatePanelImpl(model);
		datePicker = new JDatePickerImpl(datePanel);
		panel_3.add("Center", datePanel);	//할일: 직접 코드 작성하면 삭제할 예정

//		textArea = new JTextArea();
//		
//		panel_3.add(textArea);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(46, 359, 800, 60);
		panel_2.add(panel_4);

		JLabel lblNewLabel_1 = new JLabel("날짜");
		panel_4.add(lblNewLabel_1);

		textField = new JTextField();
		panel_4.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("일정제목");
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
		panel_4.add(btnNewButton);

		JButton btnNewButton_1 = new JButton("일정추가");
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

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(438, 69, 111, -42);
		panel_2.add(panel_6);

		JPanel panel_7 = new JPanel();
		panel_7.setBackground(new Color(234, 220, 215));
		panel_7.setBounds(438, 29, 111, 33);
		panel_2.add(panel_7);

		JButton btnNewButton_4 = new JButton("오늘 날짜 보기");
		panel_7.add(btnNewButton_4);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(251, 222, 224));
		panel_1.setBounds(0, 0, 203, 111);
		AccountPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1_2 = new JLabel("New label");
		lblNewLabel_1_2.setIcon(new ImageIcon(CalenderViewer.class.getResource("/Diary/view/logo6.png")));
		lblNewLabel_1_2.setBounds(-25, 10, 252, 91);
		panel_1.add(lblNewLabel_1_2);

		JLabel lblNewLabel = new JLabel("l");
		lblNewLabel.setBounds(0, 0, 1034, 624);
		lblNewLabel.setIcon(icon);
		AccountPanel.add(lblNewLabel);

	}
	
	
	//일정 추가 : 텍스트 필드에 입력된 내용을 DB로 보내주기
		ScheduleDTO sdto;
//		MemberDTO  mdto; //할일:MemberDTO 필요

		private Calendar sdate = Calendar.getInstance();
		private String title;
		private String memo;
		private boolean attention;
		
		private String userid = "yh"; //할일:MemberDTO 필요
		private LocalDate localDate;
		
		UtilDateModel model;
		JDatePanelImpl datePanel;
		JDatePickerImpl datePicker;	//할일: 직접 코드 구현후 삭제예정
		
		
		JCheckBox chckbxNewCheckBox;
		public void addSchedule() {
			sdto = new ScheduleDTO();
			
			sdate.set(model.getYear(), model.getMonth()+1, model.getDay());
//			localDate.of(model.getYear(), model.getMonth()+1, model.getDay());
			Date setDate = new Date(sdate.getTimeInMillis());
			
			title = textField_1.getText(); 
			memo = textField_2.getText(); 
			attention = chckbxNewCheckBox.isSelected();
				
			sdto.setUserId(userid);
			sdto.setSdate(setDate);
//			sdto.setLocalDate(localDate);	//할일:선택한 날짜로 자동입력
//			sdto.setLocalDate(now);
			sdto.setTitle(title);
			sdto.setMemo(memo);
			sdto.setAttention(attention);
			
			int result = ScheduleDAO.getInstance().insert(sdto);
		}

}
