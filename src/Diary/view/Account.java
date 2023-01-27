package Diary.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.ImageIcon;
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

import Diary.model.AccountDAO;
import Diary.model.AccountDTO;
import net.proteanit.sql.DbUtils;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Account extends JFrame {
	private ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("bg.png"));
	private ImageIcon logoim = new ImageIcon(getClass().getClassLoader().getResource("logo6.png"));
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	JPanel tablePanel;
	String columnNames[] = {"no.","수입/지출","카테고리","날짜","금액","메모" };
    
    // 테이블에 출력할 데이터 배열
        String data[][] ={
                {"1", "수입", "로또당첨금","2023-01-18","500,000","로또 1등 당첨금"},
                {"2", "수입", "로또당첨금","2023-01-18","500,000","로또 1등 당첨금"},
                {"3", "수입", "로또당첨금","2023-01-18","500,000","로또 1등 당첨금"}};
        DefaultTableModel model = new DefaultTableModel(data,columnNames);

	public Account() {
		
		String[] category = {"쇼핑","배달","관리비","월급","용돈","로또당첨"};
		String[] type = {"수입","지출"};
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 663);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(0, 0, 0, 0));
		panel.setBounds(942, 509, 92, 82);
		contentPane.add(panel);
		
		JLabel ChatbotBtn = new JLabel("");
		ChatbotBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource()==ChatbotBtn) {
					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								Chatbot frame = new Chatbot();
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}
			}
		});
		ChatbotBtn.setIcon(new ImageIcon(getClass().getClassLoader().getResource("chat.png")));
		ChatbotBtn.setBounds(0, 0, 92, 82);
		panel.add(ChatbotBtn);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel AccountPanel = new JPanel();
		AccountPanel.setBounds(0, 0, 1034, 624);
		contentPane.add(AccountPanel);
		AccountPanel.setLayout(null);
		
		JPanel brandLogo = new JPanel();
		brandLogo.setLayout(null);
		brandLogo.setBackground(new Color(255, 234, 151));
		brandLogo.setBounds(725, 0, 309, 111);
		AccountPanel.add(brandLogo);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBackground(new Color(251, 234, 189));
		mainPanel.setBounds(70, 121, 893, 442);
		AccountPanel.add(mainPanel);
		mainPanel.setLayout(null);
		
		FlatPanel inputPanel = new FlatPanel();
		inputPanel.setBounds(12, 10, 869, 95);
		mainPanel.add(inputPanel);
		inputPanel.setLayout(null);
		

	

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
	     inputPanel.add(date_sp);
		
		FlatTextField accountMemo = new FlatTextField(false);
		accountMemo.setHint("로또1등 당첨금");
		accountMemo.setBounds(548, 37, 174, 30);
		inputPanel.add(accountMemo);
		
		JLabel descLabel = new JLabel("가계부 작성");
		descLabel.setBounds(16, 6, 102, 16);
		inputPanel.add(descLabel);
		
		JLabel memoLabel = new JLabel("메모");
		memoLabel.setBounds(517, 44, 31, 16);
		inputPanel.add(memoLabel);
		
		JComboBox<Object> cetegory= new JComboBox<Object>(category);
		cetegory.setBounds(82, 43, 123, 23);
		inputPanel.add(cetegory);
		
		JLabel priceLabel = new JLabel("금액");
		priceLabel.setBounds(378, 44, 31, 16);
		inputPanel.add(priceLabel);
		
		FlatTextField price = new FlatTextField(false);
		price.setHint("100000");
		price.setBounds(412, 37, 93, 30);
		inputPanel.add(price);
		
		JLabel dateLabel = new JLabel("날짜");
		dateLabel.setBounds(216, 45, 31, 16);
		inputPanel.add(dateLabel);
		
		JComboBox<Object> accountType = new JComboBox<Object>(type);
		accountType.setBounds(6, 43, 77, 23);
		inputPanel.add(accountType);

		FlatButton submitBtn = new FlatButton();
		submitBtn.setBackground(new Color(242, 206, 96));
		submitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==submitBtn) {
					String date = (new SimpleDateFormat("yyyy-MM-dd").format(date_sp.getValue())).toString();
					String type = accountType.getSelectedItem().toString();
					String amount = price.getText();
					String memo = accountMemo.getText();
					String category = cetegory.getSelectedItem().toString();
					AccountDTO account = new AccountDTO(category,type,userId(),date,amount,memo);
					int result = AccountDAO.getInstance().add(account);
					System.out.println(result);
					if(result == 1) {
						tablePanel.revalidate();
						tablePanel.repaint();
						price.setText("");
						accountMemo.setText("");
						cetegory.setSelectedIndex(0);
						accountList();
					}
					
				}
			}
		});
		submitBtn.setText("등록");
		submitBtn.setBounds(755, 27, 96, 40);
		
		inputPanel.add(submitBtn);
		
		tablePanel = new JPanel();
		tablePanel.setBounds(12, 114, 869, 318);
		mainPanel.add(tablePanel);
		tablePanel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 869, 318);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		tablePanel.add(scrollPane);
		
		table = new JTable(model);
		String columnNames[] = {"no.","날짜","카테고리","수입/지출","메모","금액" };
	    
	    // 테이블에 출력할 데이터 배열
	        String data[][] ={
	                {"1", "수입", "로또당첨금","2023-01-18","500,000","로또 1등 당첨금"},
	                {"2", "수입", "로또당첨금","2023-01-18","500,000","로또 1등 당첨금"},
	                {"3", "수입", "로또당첨금","2023-01-18","500,000","로또 1등 당첨금"}};
	     
	        DefaultTableModel model = new DefaultTableModel(data,columnNames);
					table = new JTable(model);
					table.setShowGrid(false);
					table.setRowSelectionAllowed(false);
					table.setAutoCreateColumnsFromModel(false);
					table.setModel(DbUtils.resultSetToTableModel(AccountDAO.getInstance().list(userId())));
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 234, 151));
		panel_1.setBounds(0, 0, 203, 111);
		AccountPanel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == lblNewLabel_1) {
					Menu menu = new Menu(Login.member);
					menu.setVisible(true);
					setVisible(false);
				}
			}
		});
		lblNewLabel_1.setBounds(-25, 10, 252, 91);
		lblNewLabel_1.setIcon(logoim);
		panel_1.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("l");
		lblNewLabel.setBounds(0, 0, 1034, 624);
		lblNewLabel.setIcon(icon);
		AccountPanel.add(lblNewLabel);
	}
	public String userId() {
		String result = null;
		if(Login.member != null) {
			result = Login.member.getUserid();
		}
		return result;
	}
	private void accountList() {
		table.setModel(DbUtils.resultSetToTableModel(AccountDAO.getInstance().list(userId())));
	}
}
