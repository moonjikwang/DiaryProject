package Diary.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.json.simple.JSONObject;
import Diary.Controller.AirCondition;
import Diary.Controller.Trans;
import Diary.Controller.Proverb;
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
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import com.mommoo.flat.text.label.FlatLabel;
import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.text.textarea.FlatTextArea;

public class Translator extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private ImageIcon papagoImg = new ImageIcon("img/papa.png");
	private ImageIcon icon = new ImageIcon("img/bg.png");
	private ImageIcon logoim = new ImageIcon("img/logo6.png");

	public Translator() {
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
		ChatbotBtn.setIcon(new ImageIcon("img/chat.png"));
		ChatbotBtn.setBounds(0, 0, 92, 82);
		panel.add(ChatbotBtn);
		
		JPanel MenuPanel = new JPanel();
		MenuPanel.setBounds(0, 0, 1034, 624);
		contentPane.add(MenuPanel);
		MenuPanel.setLayout(null);
		
		JPanel brandPanel = new JPanel();
		brandPanel.setLayout(null);
		brandPanel.setBackground(new Color(255, 234, 151));
		brandPanel.setBounds(725, 0, 309, 111);
		MenuPanel.add(brandPanel);
		
		JLabel papago = new JLabel("");
		papago.setIcon(papagoImg);
		papago.setBounds(0, 10, 297, 91);
		brandPanel.add(papago);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(null);
		mainPanel.setBackground(new Color(251, 234, 189));
		mainPanel.setBounds(70, 121, 893, 442);
		MenuPanel.add(mainPanel);
		
		FlatTextArea beforeArea = new FlatTextArea();
		beforeArea.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		beforeArea.setBounds(12, 44, 427, 340);
		
		mainPanel.add(beforeArea);
		
		FlatTextArea afterArea = new FlatTextArea();
		afterArea.setFont(new Font("맑은 고딕", Font.BOLD, 18));
		afterArea.setEditable(false);
		afterArea.setBounds(451, 44, 427, 340);
		mainPanel.add(afterArea);
		
		afterArea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == afterArea) {
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					String copyString = afterArea.getText();
					if ( copyString != null) {
					     StringSelection contents = new StringSelection(copyString);
					     clipboard.setContents(contents, null);
					     JOptionPane.showMessageDialog(mainPanel, "복사되었습니다.");
					}
				}
			}
		});
		
		FlatLabel beforeLabel = new FlatLabel();
		beforeLabel.setText("번역할 내용");
		beforeLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		beforeLabel.setBounds(12, 23, 75, 21);
		mainPanel.add(beforeLabel);
		
		FlatLabel afterLabel = new FlatLabel();
		afterLabel.setText("번역된 내용");
		afterLabel.setFont(new Font("Dialog", Font.BOLD, 12));
		afterLabel.setBounds(451, 23, 75, 21);
		mainPanel.add(afterLabel);
		
		FlatButton transBtn = new FlatButton();
		transBtn.setBackground(new Color(242, 206, 96));
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
		
		
		mainPanel.add(transBtn);
		
		FlatButton resetBtn = new FlatButton();
		resetBtn.setBackground(new Color(242, 206, 96));
		resetBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				beforeArea.setText("");
				afterArea.setText("");
				
			}
		});
		resetBtn.setText("초기화");
		resetBtn.setBounds(451, 394, 99, 38);
		
		mainPanel.add(resetBtn);
		
		JPanel logoPanel = new JPanel();
		logoPanel.setLayout(null);
		logoPanel.setBackground(new Color(255, 234, 151));
		logoPanel.setBounds(0, 0, 203, 111);
		MenuPanel.add(logoPanel);
		
		JLabel transLangBg = new JLabel("l");
		transLangBg.setBounds(0, 0, 1034, 624);
		MenuPanel.add(transLangBg);
		
		JLabel transLangLogo = new JLabel("New label");
		transLangLogo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == transLangLogo) {
					Menu menu = new Menu(Login.member);
					menu.setVisible(true);
					setVisible(false);
				}
			}
		});
		transLangLogo.setBounds(-25, 10, 252, 91);
		transLangLogo.setIcon(logoim);
		logoPanel.add(transLangLogo);
		transLangBg.setBounds(0, 0, 1034, 624);
		transLangBg.setIcon(icon);
		logoPanel.add(transLangLogo);
		

		
		
		
	}
}
