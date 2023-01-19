package Diary.view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.text.textfield.FlatTextField;
import com.mommoo.flat.component.FlatScrollPane;
import com.mommoo.flat.text.textarea.FlatTextArea;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JTextArea;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;


public class Chatbot extends JFrame {
	private ImageIcon icon = new ImageIcon(DiaryView.class.getResource("chatBg.png"));
	private ImageIcon talk = new ImageIcon(DiaryView.class.getResource("talk.png"));
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	JLabel myTalk;
	FlatTextField flatTextField;
	FlatPanel myTalkPanel;
	JLabel lblNewLabel_1;
	/**
	 * Create the frame.
	 */
	public Chatbot() {
		
		setBounds(100, 100, 328, 549);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 0, 0,0));
		panel_2.setBounds(12, 10, 288, 447);
		contentPane.add(panel_2);
		panel_2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 462, 312, 48);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		FlatButton flatButton = new FlatButton();
		flatButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == flatButton) {
					//textArea.setText(textArea.getText()+"\n"+"<나>"+flatTextField.getText());
					myTalkPanel.setVisible(true);
				}
			}
		});
		flatButton.setText("전송");
		flatButton.setBackground(new Color(242, 206, 96));
		flatButton.setBounds(239, 0, 73, 48);
		panel_1.add(flatButton);
		
		flatTextField = new FlatTextField(false);
		flatTextField.setBounds(0, 0, 239, 48);
		panel_1.add(flatTextField);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 312, 510);
		contentPane.add(panel);
		panel.setLayout(null);
		
		myTalkPanel = new FlatPanel();
		myTalkPanel.setBounds(350, 0, 312, 50);
		myTalkPanel.setVisible(false);
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
}
