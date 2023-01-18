package Journal;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mommoo.flat.button.FlatButton;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.JList;
import java.awt.TextArea;

public class JournalDiary extends JFrame {
	private ImageIcon icon = new ImageIcon(JournalDiary.class.getResource("bg.png"));
	private ImageIcon logoim = new ImageIcon(JournalDiary.class.getResource("logo6.png"));
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList list;
//	String columnNames[] = {"날짜","일기" };
//		
//		String data[][] = {
//				{"저장한 날짜 8글자", "글 내용 8글자"},
//				{"저장한 날짜 8글자", "글 내용 8글자"},
//				{"저장한 날짜 8글자", "글 내용 8글자"},
//				{"저장한 날짜 8글자", "글 내용 8글자"},
//				{"저장한 날짜 8글자", "글 내용 8글자"}
//		};
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JournalDiary frame = new JournalDiary();
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
	public JournalDiary() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1050, 663);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel InDiaryPanel = new JPanel();
		InDiaryPanel.setBounds(0, 0, 1034, 624);
		contentPane.add(InDiaryPanel);
		InDiaryPanel.setLayout(null);
		
		JPanel panel_IndiaryLogo = new JPanel();
		panel_IndiaryLogo.setLayout(null);
		panel_IndiaryLogo.setBackground(new Color(251, 222, 224));
		panel_IndiaryLogo.setBounds(725, 0, 309, 111);
		InDiaryPanel.add(panel_IndiaryLogo);
		
		JLabel lblNewLabel_1_1 = new JLabel("기능별 로고");
		lblNewLabel_1_1.setBounds(0, 10, 297, 91);
		panel_IndiaryLogo.add(lblNewLabel_1_1);
		
		JPanel panel_textInsert = new JPanel();
		panel_textInsert.setBackground(new Color(234, 220, 215));
		panel_textInsert.setBounds(375, 137, 605, 426);
		InDiaryPanel.add(panel_textInsert);
		panel_textInsert.setLayout(null);
		
		TextArea textArea = new TextArea();
		textArea.setBounds(0, 0, 605, 426);
		panel_textInsert.add(textArea);
		textArea.setText("안녕하세요:)\r\n스마트 다이어리입니다.\r\n\r\n오늘의 일기나 기분을 기록으로 남겨보세요. \r\n기록을 남기고 하단의 Save를 누르시면 저장됩니다.\r\n저장된 일기는 왼쪽의 목록에서 확인 할 수 있습니다.\r\n\r\n매일 매일 기록을 쌓아보세요.\r\n");
		textArea.setFont(new Font("AcadEref", Font.PLAIN, 12));
		textArea.setBackground(new Color(254, 238, 163));

	
		JPanel panel_Dlogo = new JPanel();
		panel_Dlogo.setBackground(new Color(251, 222, 224));
		panel_Dlogo.setBounds(0, 0, 203, 111);
		InDiaryPanel.add(panel_Dlogo);
		panel_Dlogo.setLayout(null);
		
		JPanel panel_diaryList = new JPanel();
		panel_diaryList.setLayout(null);
		panel_diaryList.setBounds(49, 137, 290, 426);
		InDiaryPanel.add(panel_diaryList);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 290, 426);
		panel_diaryList.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		
		list = new JList();
		list.setToolTipText("");
		list.setBackground(new Color(254, 238, 163));
		scrollPane.setViewportView(list);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(-25, 10, 252, 91);
		lblNewLabel_1.setIcon(logoim);
		panel_Dlogo.add(lblNewLabel_1);
		
		FlatButton fltbtn_Save = new FlatButton("저장");
		fltbtn_Save.setText("Save");
		fltbtn_Save.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		fltbtn_Save.setBackground(new Color(95, 80, 89));
		fltbtn_Save.setBounds(833, 573, 147, 31);
//		add(fltbtn_Save);
		InDiaryPanel.add(fltbtn_Save);
		
		JLabel lblNewLabel = new JLabel("l");
		lblNewLabel.setBounds(0, 0, 1034, 624);
		lblNewLabel.setIcon(icon);
		InDiaryPanel.add(lblNewLabel);
		
	}
}
