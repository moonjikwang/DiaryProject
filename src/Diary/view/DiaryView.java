package Diary.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URI;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textarea.FlatTextArea;
import com.mommoo.flat.text.textfield.FlatTextField;

import Diary.Controller.Trans;
import Diary.model.AccountDAO;
import Diary.model.AccountDTO;
import Diary.model.MemberDAO;
import Diary.model.MemberDTO;
import net.proteanit.sql.DbUtils;

public class DiaryView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ImageIcon icon = new ImageIcon(DiaryView.class.getResource("bg.png"));
	private ImageIcon logoim = new ImageIcon(DiaryView.class.getResource("logo6.png"));
	private ImageIcon papagoImg = new ImageIcon(DiaryView.class.getResource("papa.png"));
	private ImageIcon menuImg = new ImageIcon(DiaryView.class.getResource("menu2.png"));
	private String[] category = {"쇼핑","배달","관리비","월급","용돈","로또당첨"};
	private String[] type = {"수입","지출"};
	private JTable table;
	private JPanel contentPane;
	private JPanel bgPanel = new JPanel();
	private FlatLabel loginAlert = new FlatLabel();
	private JPanel signUpPanel = new JPanel();
	private JPanel logInpanel = new JPanel();
	private JPanel IdPanel = new JPanel();
	private FlatTextField userID = new FlatTextField(false);
	private JPanel pwPanel = new JPanel();
	private FlatTextField userPw = new FlatTextField(true);
	private JPanel logoPanel = new JPanel();
	private JLabel logo = new JLabel("");
	private JPanel btnPanel = new JPanel();
	private FlatButton fltbtnLogin = new FlatButton();
	private FlatButton fltbtnSignup = new FlatButton();
	private FlatTextField signUp_userPassword = new FlatTextField(true);
	private FlatLabel signUpAlert = new FlatLabel();
	private MemberDTO member;
	private MemberDTO logininfo;
	private final JPanel menuPanel = new JPanel();
	private final FlatLabel flatLabel = new FlatLabel();
	private final FlatButton flatButton_2 = new FlatButton();
	private final FlatButton flatButton_3 = new FlatButton();
	private final FlatButton flatButton_1 = new FlatButton();
	private final FlatButton flatButton = new FlatButton();
	private final JLabel menuimg = new JLabel("");
	private final JPanel logoPanel_1 = new JPanel();
	private final JLabel logo_1 = new JLabel("SMARAT DIARY");
	private final JLabel bg_1 = new JLabel();
	private final JLabel logo_2 = new JLabel("");
	private final JPanel transLang = new JPanel();
	private final JPanel panel_1_1 = new JPanel();
	private final JLabel papago = new JLabel("");
	private final JPanel panel_2 = new JPanel();
	private final FlatTextArea beforeArea = new FlatTextArea();
	private final FlatTextArea afterArea = new FlatTextArea();
	private final FlatLabel flatLabel_1 = new FlatLabel();
	private final FlatLabel flatLabel_1_1 = new FlatLabel();
	private final FlatButton transBtn = new FlatButton();
	private final FlatButton resetBtn = new FlatButton();
	private final JPanel panel_1 = new JPanel();
	private final JLabel transLangLogo = new JLabel("New label");
	private final JLabel transLangBg = new JLabel("l");
	private JPanel accountPanel_3 = new JPanel();
	private FlatTextField accountAmount = new FlatTextField(false);
	private JComboBox accountCategory = new JComboBox(category);
	private  FlatButton accountMemo = new FlatButton();
	private final JLabel AccountLogo = new JLabel("");
	private final JPanel accountPanel_2 = new JPanel();
	private final JPanel accountPanel_1 = new JPanel();
	private final JPanel accountPanel = new JPanel(false);
	private final JLabel lblNewLabel_1_1 = new JLabel("가계부로고");
	private final FlatPanel flatPanel = new FlatPanel();
	private  FlatTextField flatTextField = new FlatTextField(false);
	private final JLabel lblNewLabel_2 = new JLabel("가계부 작성");
	private final JLabel lblNewLabel_3 = new JLabel("메모");
	private final JLabel lblNewLabel_3_1 = new JLabel("금액");
	private final JLabel lblNewLabel_3_1_1 = new JLabel("날짜");
	private final JComboBox accountType = new JComboBox(type);
	private final JPanel panel_4 = new JPanel();
	private final JScrollPane scrollPane = new JScrollPane();
	private final JLabel lblNewLabel = new JLabel("");

	public DiaryView() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("img/icon.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 663);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
				menuPanel.setLayout(null);
				menuPanel.setBounds(0, 0, 1034, 624);
				menuPanel.setVisible(false);
				accountPanel.setVisible(false);
				transLang.setVisible(false);
				
				// 백그라운드 패널
				bgPanel.setBounds(0, 0, 1034, 624);
				contentPane.add(bgPanel);
				bgPanel.setLayout(null);
				//로그인 패널
				logInpanel.setBounds(580, 65, 362, 255);
				bgPanel.add(logInpanel);
				logInpanel.setForeground(new Color(255, 252, 250, 0));
				logInpanel.setBackground(new Color(255, 255, 255, 0));
				logInpanel.setLayout(null);
				//아이디 입력창패널
				IdPanel.setBackground(new Color(255, 255, 255, 0));
				IdPanel.setBounds(64, 140, 222, 45);
				logInpanel.add(IdPanel);
				IdPanel.setLayout(null);
				//아이디 입력필드
				userID.setSelectionColor(new Color(240, 228, 9));
				userID.setFocusGainedColor(new Color(240, 228, 9));
				userID.setHint("USER ID");
				userID.setBounds(6, 6, 210, 33);
				IdPanel.add(userID);
				//비밀번호 패널
				pwPanel.setLayout(null);
				pwPanel.setBackground(new Color(255, 255, 255, 0));
				pwPanel.setBounds(64, 186, 222, 45);
				logInpanel.add(pwPanel);
				//비밀번호 입력 필드
				userPw.setSelectionColor(new Color(240, 228, 9));
				userPw.setFocusGainedColor(new Color(240, 228, 9));
				userPw.setHint("USER PASSWORD");
				userPw.setBounds(6, 6, 210, 33);
				pwPanel.add(userPw);
				//로고 입력 패널
				logoPanel.setLayout(null);
				logoPanel.setBackground(new Color(255, 255, 255, 0));
				logoPanel.setBounds(64, 10, 222, 121);
				logInpanel.add(logoPanel);
				//로고 입력 라벨
				logo.setHorizontalAlignment(SwingConstants.CENTER);
				logo.setIcon(logoim);
				logo.setBounds(20, 35, 181, 87);
				logoPanel.add(logo);
				//로그인 상태창
				loginAlert.setBackground(new Color(255, 255, 255));
				loginAlert.setFont(new Font("굴림", Font.BOLD, 12));
				loginAlert.setForeground(new Color(255, 0, 0));
				loginAlert.setBounds(74, 234, 201, 21);
				logInpanel.add(loginAlert);
				//버튼 패널 
				btnPanel.setBounds(580, 320, 362, 209);
				bgPanel.add(btnPanel);
				btnPanel.setBackground(new Color(255, 252, 250, 0));
				btnPanel.setLayout(null);
				//로그인버튼
				fltbtnLogin.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
				fltbtnLogin.setBackground(new Color(231, 184, 126));
				fltbtnLogin.setText("LOGIN");
				fltbtnLogin.setBounds(75, 6, 202, 31);
				btnPanel.add(fltbtnLogin);
				//로그인버튼 액션
				fltbtnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==fltbtnLogin) {
							String id = userID.getText();
							String password = userPw.getText();
							logininfo = new MemberDTO(id,password);
							member = logininfo;
							int logInVal = MemberDAO.getInstance().logIn(logininfo);
							logInVal(logInVal);
						}
						}
				});
				//회원가입버튼
				fltbtnSignup.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
				fltbtnSignup.setBackground(new Color(231, 184, 126));
				fltbtnSignup.setText("SIGN UP");
				fltbtnSignup.setBounds(75, 41, 202, 31);
				btnPanel.add(fltbtnSignup);
				
				JLabel lblNewLabel_1 = new JLabel("");
				lblNewLabel_1.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
							if(e.getSource()== lblNewLabel_1) {
								 Desktop desktop = Desktop.getDesktop();
						            try {
						                URI uri = new URI("https://nid.naver.com/oauth2.0/authorize?response_type=token&client_id=aqCPYfEE4Bkvh2QLKvGk&redirect_uri=https%3A%2F%2Fjikwang.net%2Fcallback.html&state=29acb18d-62bd-4100-a447-407962acd0ff");
						                desktop.browse(uri);
						            } catch (Exception ex) {
						                ex.printStackTrace();
						            }
							
						}
					}
				});
				lblNewLabel_1.setBounds(75, 112, 196, 31);
				btnPanel.add(lblNewLabel_1);
				//회원가입 버튼 액션
				fltbtnSignup.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==fltbtnSignup) {
							logInpanel.setVisible(false);
							btnPanel.setVisible(false);
							accountPanel.setVisible(false);
							signUpPanel.setVisible(true);
						}
					}
				});
				
				//회원가입 폼 패널
				signUpPanel.setBounds(580, 65, 362, 464);
				signUpPanel.setForeground(new Color(255, 252, 250, 0));
				signUpPanel.setBackground(new Color(255, 252, 250, 0));
				signUpPanel.setVisible(false);
				bgPanel.add(signUpPanel);
				signUpPanel.setLayout(null);
				
				JPanel signUpPanel_1 = new JPanel();
				signUpPanel_1.setLayout(null);
				signUpPanel_1.setBackground(new Color(255, 255, 255, 0));
				signUpPanel_1.setBounds(64, 10, 222, 110);
				signUpPanel.add(signUpPanel_1);

				logo_2.setHorizontalAlignment(SwingConstants.CENTER);
				logo_2.setBounds(20, 35, 181, 87);
				logo_2.setIcon(logoim);
				
				signUpPanel_1.add(logo_2);
				
				JPanel IdPanel_2 = new JPanel();
				IdPanel_2.setLayout(null);
				IdPanel_2.setBackground(new Color(255, 255, 255, 0));
				IdPanel_2.setBounds(64, 140, 222, 45);
				signUpPanel.add(IdPanel_2);
				
				FlatTextField signUp_userId = new FlatTextField(false);
				signUp_userId.setSelectionColor(new Color(95, 80, 89));
				signUp_userId.setHint("USER ID");
				signUp_userId.setFocusGainedColor(new Color(95, 80, 89));
				signUp_userId.setBounds(6, 6, 210, 33);
				IdPanel_2.add(signUp_userId);
				
				JPanel IdPanel_1_1 = new JPanel();
				IdPanel_1_1.setLayout(null);
				IdPanel_1_1.setBackground(new Color(255, 255, 255, 0));
				IdPanel_1_1.setBounds(64, 186, 222, 45);
				signUpPanel.add(IdPanel_1_1);
				
				FlatTextField signUp_userName = new FlatTextField(false);
				signUp_userName.setSelectionColor(new Color(95, 80, 89));
				signUp_userName.setHint("USER NAME");
				signUp_userName.setFocusGainedColor(new Color(95, 80, 89));
				signUp_userName.setBounds(6, 6, 210, 33);
				IdPanel_1_1.add(signUp_userName);
				
				FlatButton fltbtnSignUp = new FlatButton();
				fltbtnSignUp.setText("SIGN UP");
				fltbtnSignUp.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
				fltbtnSignUp.setBackground(new Color(231, 184, 126));
				fltbtnSignUp.setBounds(75, 275, 202, 31);
				signUpPanel.add(fltbtnSignUp);
				fltbtnSignUp.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == fltbtnSignUp) {
							String id = signUp_userId.getText();
							String name = signUp_userName.getText();
							String password = signUp_userPassword.getText();
							MemberDTO newMem = new MemberDTO(id,name,password);
							int logInVal = MemberDAO.getInstance().registerId(newMem);
							switch (logInVal) {
							case 1:
								signUpAlert.setText("회원가입 완료");
								break;
							default:
								signUpAlert.setText("회원가입 실패");
								break;
							}
						}
					}
				});
				//회원가입 알림
				signUpAlert.setForeground(Color.RED);
				signUpAlert.setFont(new Font("굴림", Font.BOLD, 12));
				signUpAlert.setBackground(new Color(255, 255, 255));
				signUpAlert.setBounds(64, 443, 201, 21);
				signUpPanel.add(signUpAlert);
				
				JPanel IdPanel_1_1_1 = new JPanel();
				IdPanel_1_1_1.setLayout(null);
				IdPanel_1_1_1.setBackground(new Color(255, 255, 255, 0));
				IdPanel_1_1_1.setBounds(64, 232, 222, 45);
				signUpPanel.add(IdPanel_1_1_1);
				//회원가입_비밀번호입력필드
				signUp_userPassword.setSelectionColor(new Color(95, 80, 89));
				signUp_userPassword.setHint("USER PASSWORD");
				signUp_userPassword.setFocusGainedColor(new Color(95, 80, 89));
				signUp_userPassword.setBounds(6, 6, 210, 33);
				IdPanel_1_1_1.add(signUp_userPassword);
				
				FlatButton fltbtnBack = new FlatButton();
				fltbtnBack.setText("Go Back");
				fltbtnBack.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
				fltbtnBack.setBackground(new Color(231, 184, 126));
				fltbtnBack.setBounds(75, 310, 202, 31);
				signUpPanel.add(fltbtnBack);
				fltbtnBack.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == fltbtnBack) {
							logInpanel.setVisible(true);
							btnPanel.setVisible(true);
							signUpPanel.setVisible(false);
						}
					}
				});
				
						JLabel bg = new JLabel();
						bg.setFont(new Font("Artifakt Element Black", Font.PLAIN, 19));
						bg.setBounds(0, 0, 1040, 630);
						bgPanel.add(bg);
						bg.setForeground(new Color(255, 252, 250));
						ImageIcon bg6 = new ImageIcon(DiaryView.class.getResource("bg6.png"));
						bg.setIcon(bg6);
				
				contentPane.add(menuPanel);
				flatLabel.setFont(new Font("MD개성체", Font.BOLD, 16));
				flatLabel.setBackground(new Color(255, 234, 151));
				flatLabel.setBounds(438, 152, 206, 33);
				
				menuPanel.add(flatLabel);
				flatButton_2.setBackground(new Color(196, 174, 119, 0));
				flatButton_2.setBounds(623, 283, 58, 54);
				
				menuPanel.add(flatButton_2);
				flatButton_3.setBackground(new Color(196, 174, 119, 0));
				flatButton_3.setBounds(840, 283, 58, 54);
				
				menuPanel.add(flatButton_3);
				flatButton_1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == flatButton_1) {
							bgPanel.setVisible(false);
							menuPanel.setVisible(false);
							transLang.setVisible(false);
							accountPanel.setVisible(true);
							accountList();
						}
					}

				});
				flatButton_1.setBackground(new Color(196, 174, 119, 0));
				flatButton_1.setBounds(401, 283, 58, 54);
				
				menuPanel.add(flatButton_1);
				flatButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource() == flatButton) {
							bgPanel.setVisible(false);
							menuPanel.setVisible(false);
							transLang.setVisible(true);
						}
					}
				});
				flatButton.setBackground(new Color(196, 174, 119, 0));
				flatButton.setBounds(177, 283, 58, 54);
				
				menuPanel.add(flatButton);
				menuimg.setBounds(12, 10, 1010, 604);
				menuPanel.add(menuimg);
				menuimg.setIcon(menuImg);
				
				menuPanel.add(menuimg);
				logoPanel_1.setLayout(null);
				logoPanel_1.setBackground(new Color(255, 255, 255, 0));
				logoPanel_1.setBounds(361, 96, 298, 54);
				
				menuPanel.add(logoPanel_1);
				logo_1.setHorizontalAlignment(SwingConstants.CENTER);
				logo_1.setForeground(new Color(95, 80, 89));
				logo_1.setFont(new Font("Comic Sans MS", Font.BOLD, 29));
				logo_1.setBounds(0, 6, 286, 36);
				
				logoPanel_1.add(logo_1);
				bg_1.setFont(new Font("Artifakt Element Black", Font.PLAIN, 19));
				bg_1.setBounds(0, 0, 1040, 630);
				menuPanel.add(bg_1);
				bg_1.setForeground(new Color(255, 252, 250,50));
				bg_1.setIcon(icon);
				
				menuPanel.add(bg_1);
				transLang.setLayout(null);
				transLang.setVisible(false);
				transLang.setBounds(0, 0, 1034, 624);
				
				contentPane.add(transLang);
				panel_1_1.setLayout(null);
				panel_1_1.setBackground(new Color(251, 222, 224));
				panel_1_1.setBounds(725, 0, 309, 111);
				
				transLang.add(panel_1_1);
				papago.setBounds(0, 10, 297, 91);
				papago.setIcon(papagoImg);
				panel_1_1.add(papago);
				panel_2.setLayout(null);
				panel_2.setBackground(new Color(234, 220, 215));
				panel_2.setBounds(70, 121, 893, 442);
				
				transLang.add(panel_2);
				beforeArea.setFont(new Font("맑은 고딕", Font.BOLD, 18));
				beforeArea.setBounds(12, 44, 427, 340);
				
				panel_2.add(beforeArea);
				afterArea.setFont(new Font("맑은 고딕", Font.BOLD, 18));
				afterArea.setEditable(false);
				afterArea.setBounds(451, 44, 427, 340);
				
				panel_2.add(afterArea);
				flatLabel_1.setText("번역할 내용");
				flatLabel_1.setFont(new Font("나눔고딕", Font.BOLD, 12));
				flatLabel_1.setBounds(12, 23, 75, 21);
				
				panel_2.add(flatLabel_1);
				flatLabel_1_1.setText("번역된 내용");
				flatLabel_1_1.setFont(new Font("나눔고딕", Font.BOLD, 12));
				flatLabel_1_1.setBounds(451, 23, 75, 21);
				
				panel_2.add(flatLabel_1_1);
				transBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						String trans = null;
						trans = beforeArea.getText();
						String content = Trans.trans(trans).toString();
						content = content.replaceAll("\\\\n", "\n");
						afterArea.setText(content);
					}
				});
				transBtn.setText("번역하기");
				transBtn.setBounds(341, 394, 99, 38);
				
				
				panel_2.add(transBtn);
				resetBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						beforeArea.setText("");
						afterArea.setText("");
						
					}
				});
				resetBtn.setText("초기화");
				resetBtn.setBounds(451, 394, 99, 38);
				
				panel_2.add(resetBtn);
				panel_1.setLayout(null);
				panel_1.setBackground(new Color(251, 222, 224));
				panel_1.setBounds(0, 0, 203, 111);
				
				transLang.add(panel_1);
				transLangLogo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(e.getSource() == transLangLogo) {
							menuPanel.setVisible(true);
							transLang.setVisible(false);
						}
					}
				});
				transLangLogo.setBounds(-25, 10, 252, 91);
				transLangLogo.setIcon(logoim);
				panel_1.add(transLangLogo);
				transLangBg.setBounds(0, 0, 1034, 624);
				transLangBg.setIcon(icon);
				
				transLang.add(transLangBg);
				accountPanel.setLayout(null);
				accountPanel.setBounds(0, 0, 1034, 624);
				
				contentPane.add(accountPanel);
				accountPanel_1.setLayout(null);
				accountPanel_1.setBackground(new Color(251, 222, 224));
				accountPanel_1.setBounds(725, 0, 309, 111);
				
				accountPanel.add(accountPanel_1);
				lblNewLabel_1_1.setBounds(0, 10, 297, 91);
				
				accountPanel_1.add(lblNewLabel_1_1);
				accountPanel_2.setLayout(null);
				accountPanel_2.setBackground(new Color(234, 220, 215));
				accountPanel_2.setBounds(70, 121, 893, 442);
				
				accountPanel.add(accountPanel_2);
				flatPanel.setLayout(null);
				flatPanel.setBounds(12, 10, 869, 95);
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
				accountPanel_2.add(flatPanel);
				date_sp.setBounds(243, 36, 130, 30);
				
				flatPanel.add(date_sp);
				flatTextField.setHint("로또1등 당첨금");
				flatTextField.setBounds(548, 37, 174, 30);
				
				flatPanel.add(flatTextField);
				accountMemo.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						if(e.getSource()==accountMemo) {
							String date = (new SimpleDateFormat("yyyy-MM-dd").format(date_sp.getValue())).toString();
							String type = accountType.getSelectedItem().toString();
							String amount = accountAmount.getText();
							String memo = flatTextField.getText();
							String category = accountCategory.getSelectedItem().toString();
							AccountDTO account = new AccountDTO(category,type,member.getUserid(),date,amount,memo);
							int result = AccountDAO.getInstance().add(account);
							System.out.println(result);
							if(result == 1) {
								accountPanel.revalidate();
								accountPanel.repaint();
								accountAmount.setText("");
								flatTextField.setText("");
								accountCategory.setSelectedIndex(0);
								accountList();
							}
							
						}
					}
				});
				accountMemo.setText("등록");
				accountMemo.setBounds(755, 27, 96, 40);
				
				flatPanel.add(accountMemo);
				lblNewLabel_2.setBounds(16, 6, 65, 16);
				
				flatPanel.add(lblNewLabel_2);
				lblNewLabel_3.setBounds(517, 44, 31, 16);
				
				flatPanel.add(lblNewLabel_3);
				accountCategory.setBounds(82, 43, 123, 23);
				
				flatPanel.add(accountCategory);
				lblNewLabel_3_1.setBounds(378, 44, 31, 16);
				
				flatPanel.add(lblNewLabel_3_1);
				accountAmount.setHint("100000");
				accountAmount.setBounds(412, 37, 93, 30);
				
				flatPanel.add(accountAmount);
				lblNewLabel_3_1_1.setBounds(216, 45, 31, 16);
				
				flatPanel.add(lblNewLabel_3_1_1);
				accountType.setBounds(6, 43, 77, 23);
				
				flatPanel.add(accountType);
				panel_4.setLayout(null);
				panel_4.setBounds(12, 114, 869, 318);
				
				accountPanel_2.add(panel_4);
				scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
				scrollPane.setBounds(0, 0, 869, 318);
				
				String columnNames[] = {"no.","날짜","카테고리","수입/지출","메모","금액" };
			    
			    // 테이블에 출력할 데이터 배열
			        String data[][] ={
			                {"1", "수입", "로또당첨금","2023-01-18","500,000","로또 1등 당첨금"},
			                {"2", "수입", "로또당첨금","2023-01-18","500,000","로또 1등 당첨금"},
			                {"3", "수입", "로또당첨금","2023-01-18","500,000","로또 1등 당첨금"}};
			     
			        DefaultTableModel model = new DefaultTableModel(data,columnNames);
				table = new JTable(model);
				table.setAutoCreateColumnsFromModel(false);
				table.setModel(DbUtils.resultSetToTableModel(AccountDAO.getInstance().list(userId())));
				scrollPane.setViewportView(table);
				
				panel_4.add(scrollPane);
				accountPanel_3.setLayout(null);
				accountPanel_3.setBackground(new Color(251, 222, 224));
				accountPanel_3.setBounds(0, 0, 203, 111);
				
				accountPanel.add(accountPanel_3);
				AccountLogo.setBounds(-25, 10, 252, 91);
				AccountLogo.setIcon(logoim);
				accountPanel_3.add(AccountLogo);
				lblNewLabel.setBounds(0, 0, 1034, 624);
				lblNewLabel.setIcon(icon);
				accountPanel.add(lblNewLabel);
				AccountLogo.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						if(e.getSource() == AccountLogo) {
							menuPanel.setVisible(true);
							accountPanel.setVisible(false);
						}
					}
				});

	}
	public void logInVal(int logInVal) {
		switch (logInVal) {
		case -1:
				loginAlert.setText("해당 아이디는 없습니다:(");
			break;
		case 0:
				loginAlert.setText("비밀번호가 틀렸습니다.");
			break;
		case 1:
			loginAlert.setText("로그인에 성공했습니다 ");
			try {
				menuPanel.setVisible(true);
				flatLabel.setText(userName()+"님 반갑습니다!");
				bgPanel.setVisible(false);
//				Menu frame = new Menu(member);
//				frame.setVisible(true);
//				this.dispose();
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
	}
	public String userName() {
		String result = null;
		if(member != null) {
			result = MemberDAO.getInstance().idToName(member);
		}
		return result;
	}
	public String userId() {
		String result = null;
		if(member != null) {
			result = member.getUserid();
		}
		return result;
	}
	private void accountList() {
		table.setModel(DbUtils.resultSetToTableModel(AccountDAO.getInstance().list(userId())));
	}
}
