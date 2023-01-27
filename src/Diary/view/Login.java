package Diary.view;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URI;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.json.simple.JSONObject;
import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.text.textfield.FlatTextField;
import Diary.Controller.AirCondition;
import Diary.Controller.Proverb;
import Diary.model.MemberDAO;
import Diary.model.MemberDTO;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class Login extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ImageIcon logoim = new ImageIcon(getClass().getClassLoader().getResource("logo6.png"));
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
	public static MemberDTO member;
	private MemberDTO logininfo;
	private final JLabel logo_2 = new JLabel("");

	public Login() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 663);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBorderPainted(false);
		menuBar.setBackground(new Color(238, 231, 110));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("정보");
		mnNewMenu.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("우리동네 미세먼지");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==mntmNewMenuItem) {
					try {
						JSONObject jsonObj = AirCondition.airCon();
						JOptionPane.showMessageDialog(mntmNewMenuItem, jsonObj.get("stationName") +"" + jsonObj.get("dataTime") + " 기준\n미세먼지(PM10) 농도 : " + jsonObj.get("pm10Value") +
								"\n" + "초미세먼지(PM2.5) 농도 : " + jsonObj.get("pm25Value"),"우리동네 미세먼지",JOptionPane.PLAIN_MESSAGE);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		mntmNewMenuItem.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem todayProverb = new JMenuItem("오늘의 속담");
		todayProverb.setFont(new Font("Dialog", Font.BOLD, 12));
		todayProverb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()== todayProverb) {
					JOptionPane.showMessageDialog(todayProverb,new Proverb().wordGame("다"),"오늘의 속담",JOptionPane.PLAIN_MESSAGE);
				}
			}
		});
		mnNewMenu.add(todayProverb);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
				JPanel panel = new JPanel();
				panel.setBackground(new Color(0, 0, 0,0));
				panel.setBounds(942, 509, 92, 82);
				contentPane.add(panel);
				panel.setLayout(null);
				
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
						ImageIcon bg6 = new ImageIcon(getClass().getClassLoader().getResource("bg6.png"));
						bg.setIcon(bg6);
			
				

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
				Menu frame = new Menu(member);
				frame.setVisible(true);
				this.dispose();
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
}
