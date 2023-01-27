package Diary.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;

import Diary.Controller.AirCondition;
import Diary.Controller.Proverb;
import Diary.model.MemberDAO;
import Diary.model.MemberDTO;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.Font;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.button.FlatButton;
import javax.swing.SwingConstants;

public class Menu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ImageIcon menuImg = new ImageIcon(getClass().getClassLoader().getResource("menu2.png"));
	private ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("bg.png"));

	public Menu(MemberDTO member) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 663);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("정보");
		mnNewMenu.setFont(new Font("Dialog", Font.BOLD, 12));
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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
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
		
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBounds(0, 0, 1034, 624);
		contentPane.add(MenuPanel);
		MenuPanel.setLayout(null);
		
		JPanel logoPanel_1 = new JPanel();
		logoPanel_1.setLayout(null);
		logoPanel_1.setBackground(new Color(255, 255, 255, 0));
		logoPanel_1.setBounds(361, 96, 298, 54);
		MenuPanel.add(logoPanel_1);
		
		JLabel logo_1 = new JLabel("SMART DIARY");
		logo_1.setHorizontalAlignment(SwingConstants.CENTER);
		logo_1.setForeground(new Color(95, 80, 89));
		logo_1.setFont(new Font("Comic Sans MS", Font.BOLD, 29));
		logo_1.setBounds(0, 6, 286, 36);
		logoPanel_1.add(logo_1);
		
		FlatLabel flatLabel = new FlatLabel();
		flatLabel.setText(userName(member)+"님 반갑습니다.");
		flatLabel.setFont(new Font("Dialog", Font.PLAIN, 14));
		flatLabel.setBackground(new Color(255, 234, 151));
		flatLabel.setBounds(0, 0, 608, 46);
		MenuPanel.add(flatLabel);
		
		FlatButton flatButton_2 = new FlatButton();
		flatButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==flatButton_2) {
					Journal journal = new Journal();
					journal.setVisible(true);
					setVisible(false);
				}
			}
		});
		flatButton_2.setBackground(new Color(196, 174, 119, 0));
		flatButton_2.setBounds(623, 283, 58, 54);
		MenuPanel.add(flatButton_2);
		
		FlatButton flatButton_3 = new FlatButton();
		flatButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==flatButton_3) {
					Calender calender = new Calender();
					calender.setVisible(true);
					setVisible(false);
				}
			}
		});
		flatButton_3.setBackground(new Color(196, 174, 119, 0));
		flatButton_3.setBounds(840, 283, 58, 54);
		MenuPanel.add(flatButton_3);
		
		FlatButton flatButton_1 = new FlatButton();
		flatButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==flatButton_1) {
					Account account = new Account();
					account.setVisible(true);
					setVisible(false);
				}
			}
		});
		flatButton_1.setBackground(new Color(196, 174, 119, 0));
		flatButton_1.setBounds(401, 283, 58, 54);
		MenuPanel.add(flatButton_1);
		
		FlatButton flatButton = new FlatButton();
		flatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==flatButton) {
					Translator translator = new Translator();
					translator.setVisible(true);
					setVisible(false);
				}
			}
		});
		flatButton.setBackground(new Color(196, 174, 119, 0));
		flatButton.setBounds(177, 283, 58, 54);
		MenuPanel.add(flatButton);
		
		JLabel menuimg = new JLabel("");
		menuimg.setBounds(12, 10, 1010, 604);
		MenuPanel.add(menuimg);
		menuimg.setIcon(menuImg);
		JLabel bg = new JLabel();
		bg.setFont(new Font("Artifakt Element Black", Font.PLAIN, 19));
		bg.setBounds(0, 0, 1040, 630);
		bg.setForeground(new Color(255, 252, 250,50));
		bg.setIcon(icon);
		MenuPanel.add(bg);
		
		JLabel bg_1 = new JLabel();
		bg_1.setForeground(new Color(255, 252, 250, 50));
		bg_1.setFont(new Font("Dialog", Font.PLAIN, 19));
		bg_1.setBounds(0, 0, 1040, 630);
		MenuPanel.add(bg_1);
		
		
		
	}
	public String userName(MemberDTO member) {
		String result = null;
		if(member != null) {
			result = MemberDAO.getInstance().idToName(member);
		}
		return result;
	}
}
