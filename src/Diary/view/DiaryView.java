package Diary.view;

import java.awt.Color;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.text.textfield.FlatTextField;

import Diary.Controller.Trans;
import Diary.model.MemberDAO;
import Diary.model.MemberDTO;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.mommoo.flat.text.label.FlatLabel;
import java.awt.Toolkit;
import com.mommoo.flat.text.textarea.FlatTextArea;

public class DiaryView extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private ImageIcon icon = new ImageIcon(DiaryView.class.getResource("bg5.png"));
	private ImageIcon logoim = new ImageIcon(DiaryView.class.getResource("logo6.png"));
	private ImageIcon papagoImg = new ImageIcon(DiaryView.class.getResource("papa.png"));
private ImageIcon menuImg = new ImageIcon(DiaryView.class.getResource("menu.png"));
	private JPanel contentPane;
	JPanel bgPanel = new JPanel();
	private FlatLabel loginAlert = new FlatLabel();
	JPanel signUpPanel = new JPanel();
	JPanel logInpanel = new JPanel();
	JPanel loginLabelpane = new JPanel();
	JLabel loginLabel = new JLabel("SIGN IN");
	JPanel IdPanel = new JPanel();
	FlatTextField userID = new FlatTextField(false);
	JPanel pwPanel = new JPanel();
	FlatTextField userPw = new FlatTextField(true);
	JPanel logoPanel = new JPanel();
	JLabel logo = new JLabel("");
	JPanel btnPanel = new JPanel();
	FlatButton fltbtnLogin = new FlatButton();
	FlatButton fltbtnSignup = new FlatButton();
	FlatTextField signUp_userPassword = new FlatTextField(true);
	FlatLabel signInAlert = new FlatLabel();
	FlatLabel signUpAlert = new FlatLabel();
	MemberDTO member;
	MemberDTO logininfo;
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
				// 백그라운드 패널
				bgPanel.setBounds(0, 0, 1034, 624);
				contentPane.add(bgPanel);
				bgPanel.setLayout(null);
				//로그인 패널
				logInpanel.setBounds(327, 121, 362, 255);
				bgPanel.add(logInpanel);
				logInpanel.setForeground(new Color(255, 252, 250, 0));
				logInpanel.setBackground(new Color(255, 255, 255, 0));
				logInpanel.setLayout(null);
				//로그인 라벨 패널
				loginLabelpane.setBackground(new Color(255, 255, 255, 0));
				loginLabelpane.setBounds(64, 74, 222, 54);
				logInpanel.add(loginLabelpane);
				loginLabelpane.setLayout(null);
				//로그인 라벨
				loginLabel.setForeground(new Color(95, 80, 89));
				loginLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
				loginLabel.setBounds(45, 6, 138, 36);
				loginLabelpane.add(loginLabel);
				//아이디 입력창패널
				IdPanel.setBackground(new Color(255, 255, 255, 0));
				IdPanel.setBounds(64, 140, 222, 45);
				logInpanel.add(IdPanel);
				IdPanel.setLayout(null);
				//아이디 입력필드
				userID.setSelectionColor(new Color(95, 80, 89));
				userID.setFocusGainedColor(new Color(95, 80, 89));
				userID.setHint("USER ID");
				userID.setBounds(6, 6, 210, 33);
				IdPanel.add(userID);
				//비밀번호 패널
				pwPanel.setLayout(null);
				pwPanel.setBackground(new Color(255, 255, 255, 0));
				pwPanel.setBounds(64, 186, 222, 45);
				logInpanel.add(pwPanel);
				//비밀번호 입력 필드
				userPw.setSelectionColor(new Color(95, 80, 89));
				userPw.setFocusGainedColor(new Color(95, 80, 89));
				userPw.setHint("USER PASSWORD");
				userPw.setBounds(6, 6, 210, 33);
				pwPanel.add(userPw);
				//로고 입력 패널
				logoPanel.setLayout(null);
				logoPanel.setBackground(new Color(255, 255, 255, 0));
				logoPanel.setBounds(64, -47, 222, 121);
				logInpanel.add(logoPanel);
				//로고 입력 라벨
				logo.setHorizontalAlignment(SwingConstants.CENTER);
				logo.setIcon(logoim);
				logo.setBounds(20, 35, 181, 87);
				logoPanel.add(logo);
				//로그인 상태창
				loginAlert.setBackground(new Color(251, 222, 224));
				loginAlert.setFont(new Font("굴림", Font.BOLD, 12));
				loginAlert.setForeground(new Color(255, 0, 0));
				loginAlert.setBounds(74, 234, 201, 21);
				logInpanel.add(loginAlert);
				//버튼 패널 
				btnPanel.setBounds(327, 374, 362, 96);
				bgPanel.add(btnPanel);
				btnPanel.setBackground(new Color(255, 252, 250, 0));
				btnPanel.setLayout(null);
				//로그인버튼
				fltbtnLogin.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
				fltbtnLogin.setBackground(new Color(95, 80, 89));
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
				fltbtnSignup.setBackground(new Color(95, 80, 89));
				fltbtnSignup.setText("SIGN UP");
				fltbtnSignup.setBounds(75, 41, 202, 31);
				btnPanel.add(fltbtnSignup);
				//회원가입 알림
				signInAlert.setForeground(Color.RED);
				signInAlert.setFont(new Font("굴림", Font.BOLD, 12));
				signInAlert.setBackground(new Color(251, 222, 224));
				signInAlert.setBounds(76, 26, 201, 21);
				btnPanel.add(signInAlert);
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
				signUpPanel.setBounds(321, 65, 362, 464);
				signUpPanel.setForeground(new Color(255, 252, 250, 0));
				signUpPanel.setBackground(new Color(255, 252, 250, 0));
				signUpPanel.setVisible(false);
				bgPanel.add(signUpPanel);
				signUpPanel.setLayout(null);
				
				JPanel signUpPanel_1 = new JPanel();
				signUpPanel_1.setLayout(null);
				signUpPanel_1.setBackground(new Color(255, 255, 255, 0));
				signUpPanel_1.setBounds(72, 10, 222, 110);
				signUpPanel.add(signUpPanel_1);

				logo_2.setHorizontalAlignment(SwingConstants.CENTER);
				logo_2.setBounds(20, 35, 181, 87);
				logo_2.setIcon(logoim);
				
				signUpPanel_1.add(logo_2);
				
				JPanel loginPanel_2 = new JPanel();
				loginPanel_2.setLayout(null);
				loginPanel_2.setBackground(new Color(255, 255, 255, 0));
				loginPanel_2.setBounds(72, 130, 222, 54);
				signUpPanel.add(loginPanel_2);
				
				JLabel lblSignUp = new JLabel("SIGN UP");
				lblSignUp.setForeground(new Color(95, 80, 89));
				lblSignUp.setFont(new Font("Comic Sans MS", Font.BOLD, 26));
				lblSignUp.setBounds(45, 6, 138, 36);
				loginPanel_2.add(lblSignUp);
				
				JPanel IdPanel_2 = new JPanel();
				IdPanel_2.setLayout(null);
				IdPanel_2.setBackground(new Color(255, 255, 255, 0));
				IdPanel_2.setBounds(72, 196, 222, 45);
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
				IdPanel_1_1.setBounds(72, 242, 222, 45);
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
				fltbtnSignUp.setBackground(new Color(95, 80, 89));
				fltbtnSignUp.setBounds(84, 364, 202, 31);
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
				signUpAlert.setBackground(new Color(251, 222, 224));
				signUpAlert.setBounds(83, 339, 201, 21);
				signUpPanel.add(signUpAlert);
				
				JPanel IdPanel_1_1_1 = new JPanel();
				IdPanel_1_1_1.setLayout(null);
				IdPanel_1_1_1.setBackground(new Color(255, 255, 255, 0));
				IdPanel_1_1_1.setBounds(72, 284, 222, 45);
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
				fltbtnBack.setBackground(new Color(95, 80, 89));
				fltbtnBack.setBounds(84, 400, 202, 31);
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
						bg.setIcon(icon);
				
				contentPane.add(menuPanel);
				flatLabel.setFont(new Font("MD개성체", Font.BOLD, 16));
				flatLabel.setBackground(new Color(251, 222, 224));
				flatLabel.setBounds(438, 152, 206, 33);
				
				menuPanel.add(flatLabel);
				flatButton_2.setBackground(new Color(196, 174, 119, 0));
				flatButton_2.setBounds(623, 283, 58, 54);
				
				menuPanel.add(flatButton_2);
				flatButton_3.setBackground(new Color(196, 174, 119, 0));
				flatButton_3.setBounds(840, 283, 58, 54);
				
				menuPanel.add(flatButton_3);
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
				transLangLogo.setBounds(-25, 10, 252, 91);
				transLangLogo.setIcon(logoim);
				panel_1.add(transLangLogo);
				transLangBg.setBounds(0, 0, 1034, 624);
				transLangBg.setIcon(icon);
				
				transLang.add(transLangBg);

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
}
