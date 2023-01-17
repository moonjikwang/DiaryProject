package test;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.text.textfield.FlatTextField;

public class AccountDiary extends JFrame {
	private ImageIcon icon = new ImageIcon(AccountDiary.class.getResource("bg5.png"));
	private ImageIcon logoim = new ImageIcon(AccountDiary.class.getResource("logo6.png"));
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTable table;
	String columnNames[] = {"no.","수입/지출","카테고리","날짜","금액","메모" };
    
    // 테이블에 출력할 데이터 배열
        String data[][] ={
                {"1", "수입", "로또당첨금","2023-01-18","500,000","로또 1등 당첨금"},
                {"2", "수입", "로또당첨금","2023-01-18","500,000","로또 1등 당첨금"},
                {"3", "수입", "로또당첨금","2023-01-18","500,000","로또 1등 당첨금"}};
        DefaultTableModel model = new DefaultTableModel(data,columnNames);

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AccountDiary frame = new AccountDiary();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AccountDiary() {
		
		String[] category = {"쇼핑","배달","관리비","월급","용돈","로또당첨"};
		String[] type = {"수입","지출"};
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
		
		JLabel lblNewLabel_1_1 = new JLabel("가계부로고");
		lblNewLabel_1_1.setBounds(0, 10, 297, 91);
		panel_1_1.add(lblNewLabel_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(234, 220, 215));
		panel_2.setBounds(70, 121, 893, 442);
		AccountPanel.add(panel_2);
		panel_2.setLayout(null);
		
		FlatPanel flatPanel = new FlatPanel();
		flatPanel.setBounds(12, 10, 869, 95);
		panel_2.add(flatPanel);
		flatPanel.setLayout(null);
		
		FlatButton flatButton = new FlatButton();

		JSpinner date_sp = new JSpinner();
		date_sp.setLocation(243, 36);
		date_sp.setSize(130, 30);
	     Calendar calendar = Calendar.getInstance(); //calendar 인스턴스 생성
	     Date curDate = (Date)calendar.getTime(); // 현재 날짜 가져옴
	     calendar.add(Calendar.YEAR, -50); 
	     Date minDate = (Date)calendar.getTime(); //minDate = curDate - 50년
	     calendar.add(Calendar.YEAR, 100);
	     Date maxDate = (Date)calendar.getTime(); //maxDate = minDate + 100년
	     SpinnerDateModel dateModel = new SpinnerDateModel(curDate, minDate,maxDate, Calendar.YEAR);
	     date_sp.setModel(dateModel); 
	     date_sp.setEditor(new JSpinner.DateEditor(date_sp, "yyyy년MM월dd일"));
	     flatPanel.add(date_sp);
		
		FlatTextField flatTextField = new FlatTextField(false);
		flatTextField.setHint("로또1등 당첨금");
		flatTextField.setBounds(548, 37, 174, 30);
		flatPanel.add(flatTextField);
		flatButton.setText("등록");
		flatButton.setBounds(755, 27, 96, 40);
		flatPanel.add(flatButton);
		
		JLabel lblNewLabel_2 = new JLabel("가계부 작성");
		lblNewLabel_2.setBounds(16, 6, 61, 16);
		flatPanel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("메모");
		lblNewLabel_3.setBounds(517, 44, 31, 16);
		flatPanel.add(lblNewLabel_3);
		
		JComboBox strCombo= new JComboBox(category);
		strCombo.setBounds(82, 43, 123, 23);
		flatPanel.add(strCombo);
		
		JLabel lblNewLabel_3_1 = new JLabel("금액");
		lblNewLabel_3_1.setBounds(378, 44, 31, 16);
		flatPanel.add(lblNewLabel_3_1);
		
		FlatTextField flatTextField_1 = new FlatTextField(false);
		flatTextField_1.setHint("100000");
		flatTextField_1.setBounds(412, 37, 93, 30);
		flatPanel.add(flatTextField_1);
		
		JLabel lblNewLabel_3_1_1 = new JLabel("날짜");
		lblNewLabel_3_1_1.setBounds(216, 45, 31, 16);
		flatPanel.add(lblNewLabel_3_1_1);
		
		JComboBox strCombo_1 = new JComboBox(type);
		strCombo_1.setBounds(6, 43, 77, 23);
		flatPanel.add(strCombo_1);

        
		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 114, 869, 318);
		panel_2.add(panel_4);
		panel_4.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 869, 318);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_4.add(scrollPane);
		
		table = new JTable(model);
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(251, 222, 224));
		panel_1.setBounds(0, 0, 203, 111);
		AccountPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(-25, 10, 252, 91);
		lblNewLabel_1.setIcon(logoim);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("l");
		lblNewLabel.setBounds(0, 0, 1034, 624);
		lblNewLabel.setIcon(icon);
		AccountPanel.add(lblNewLabel);
	}
}
