package Diary.view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.text.textfield.FlatTextField;

import Diary.Controller.Trans;
import Diary.model.ChatbotDAO;

import com.mommoo.flat.component.FlatScrollPane;
import com.mommoo.flat.text.textarea.FlatTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class Chatbot extends JFrame {
	private ImageIcon icon = new ImageIcon("img/chatBg.png");
	private ImageIcon talk = new ImageIcon("img/talk.png");
	Image img = new ImageIcon("img/chatBg.png").getImage();
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel myTalk;
	FlatTextField flatTextField;
	FlatPanel myTalkPanel;
	JLabel lblNewLabel_1;
	FlatTextArea textarea;
	int chatStats = 0;
	/**
	 * Create the frame.
	 */
	public Chatbot() {
		setResizable(false);
		
		setBounds(100, 100, 328, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 462, 312, 48);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		FlatButton flatButton = new FlatButton();
		flatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == flatButton) {
					if(flatTextField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(flatButton, "값을 입력하고 전송해주세요.");
					}else {
					textarea.setText(textarea.getText()+"\n"+"<나>"+flatTextField.getText());
					if(chatStats == 0) {
					makeAnswer(flatTextField.getText());
					}else if(chatStats == 2) {
						textarea.setText(textarea.getText()+Trans.trans(flatTextField.getText()).toString());
						flatTextField.setText("");
						chatStats = 0;
					}else {
						addAnswer(flatTextField.getText());
					}
					flatTextField.setText("");
					}
					myTalkPanel.setVisible(true);
				
				}
			}


		});
		flatButton.setText("전송");
		flatButton.setBackground(new Color(242, 206, 96));
		flatButton.setBounds(239, 0, 73, 48);
		panel_1.add(flatButton);
		
		flatTextField = new FlatTextField(false);
		flatTextField.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == 10) {
					if(flatTextField.getText().isEmpty()) {
						JOptionPane.showMessageDialog(flatButton, "값을 입력하고 전송해주세요.");
					}else if(chatStats == 2) {
						textarea.setText(textarea.getText()+Trans.trans(flatTextField.getText()).toString());
						flatTextField.setText("");
						chatStats = 0;
					}else {
					textarea.setText(textarea.getText()+"\n"+"<나>"+flatTextField.getText());
					if(chatStats == 0) {
					makeAnswer(flatTextField.getText());
					}else {
						addAnswer(flatTextField.getText());
					}
					flatTextField.setText("");
					}
				}
			}
		});
		flatTextField.setBounds(0, 0, 239, 48);
		panel_1.add(flatTextField);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 312, 510);
		contentPane.add(panel);
		panel.setLayout(null);
		
		myTalkPanel = new FlatPanel();
		myTalkPanel.setBounds(350, 0, 312, 50);
		myTalkPanel.setVisible(false);
		
		FlatScrollPane flatScrollPane = new FlatScrollPane();
		flatScrollPane.setBounds(0, 0, 312, 462);
		panel.add(flatScrollPane);
		
		textarea = new FlatTextArea(){
            { setOpaque( false ) ; }
            public void paintComponent(Graphics g){
                g.drawImage(img,0,0,null);       //이미지 그리기
                super.paintComponent(g);
            }
        };
		textarea.setFont(new Font("나눔고딕", Font.BOLD, 14));
        textarea.setText("<스마트 다이어리 챗봇서비스 입니다>\n <현재 학습된 상황 수 " + ChatbotDAO.getInstance().list() + "건 입니다.>");
		textarea.setEditable(false);;
		flatScrollPane.setViewportView(textarea);
		panel.add(myTalkPanel);
		myTalkPanel.setLayout(null);
		
		myTalk = new JLabel("New label");
		myTalk.setHorizontalAlignment(SwingConstants.RIGHT);
		myTalk.setBounds(12, 10, 274, 30);
		myTalkPanel.add(myTalk);
		
		lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(0, 0, 312, 50);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setIcon(talk);
		myTalkPanel.add(lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 312, 510);
		lblNewLabel.setIcon(icon);
		panel.add(lblNewLabel);
	}
	private void addAnswer(String text) {
		ChatbotDAO.getInstance().addWord(text);
		textarea.setText(textarea.getText()+"\n"+"<스마트다이어리>이해했어요!");
		chatStats = 0;
	}

	private void makeAnswer(String text) {
		String response = ChatbotDAO.getInstance().response(text);
		if(response == null) {
			response = "제가 모르는 내용이네요. 어떤 대답을 원하세요?";
			chatStats = 1;
		}else if(response.equals("번역기실행")){
			response = "번역할 내용을 입력해주세요 ~";
			chatStats = 2;
		}else {
			chatStats = 0;
		}
		textarea.setText(textarea.getText()+"\n"+"<스마트다이어리>"+response);
	}
}
