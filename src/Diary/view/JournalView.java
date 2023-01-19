package Diary.view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.text.textarea.FlatTextArea;

import Diary.model.JournalDAO;
import Diary.model.JournalDTO;


public class JournalView  extends JFrame {
	private ImageIcon icon = new ImageIcon(JournalView.class.getResource("bg.png"));
	private ImageIcon logoim = new ImageIcon(JournalView.class.getResource("logo6.png"));
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JList list;
	FlatTextArea jourDesc; 
	private DefaultListModel model;
	private int row;
	
	String columnNames[] = {"날짜","텍스트"};
	//Jlist에 출력할 데이터 배열
		String data[][] = {
				{"2023-01-19","텍스트_1"},
				{"2023-01-19","텍스트_2"}};
//		DefaultListModel<String> model = new DefaultListModel<String>();
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JournalView frame = new JournalView();
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
	@SuppressWarnings("rawtypes")
	public JournalView() {
		
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
		panel_1_1.setBackground(new Color(255, 234, 151));
		panel_1_1.setBounds(725, 0, 309, 111);
		AccountPanel.add(panel_1_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("기능별 로고");
		lblNewLabel_1_1.setBounds(0, 10, 297, 91);
		panel_1_1.add(lblNewLabel_1_1);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(251, 234, 189));
		panel_2.setBounds(70, 121, 893, 442);
		AccountPanel.add(panel_2);
		panel_2.setLayout(null);
		
		FlatPanel jourList = new FlatPanel();
		jourList.setBackground(new Color(255, 255, 255));
		jourList.setBounds(266, 10, 615, 422);
		panel_2.add(jourList);
		jourList.setLayout(null);
		
		//일기 저장 버튼
		FlatButton saveBtn = new FlatButton();
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==saveBtn) {
					String journal = jourDesc.getText();
					String userId = "SY";
					JournalDTO journaldto = new JournalDTO(userId,journal);
					JournalDAO.getInstance().registerJournal(journaldto);
					jourList.revalidate();
					jourList.repaint();
					jourDesc.setText("");
				}
			}
		});
		saveBtn.setBackground(new Color(242, 206, 96));
		saveBtn.setText("저장");
		saveBtn.setBounds(484, 359, 119, 53);
		jourList.add(saveBtn);
		
		//일기입력부분
		jourDesc = new FlatTextArea();
		jourDesc.setText("안녕하세요:)\r\n스마트 다이어리입니다.\r\n\r\n오늘의 일기나 기분을 기록으로 남겨보세요. \r\n기록을 남기고 하단의 Save를 누르시면 저장됩니다.\r\n저장된 일기는 왼쪽의 목록에서 확인 할 수 있습니다.\r\n\r\n매일 매일 기록을 쌓아보세요.\r\n");
		jourDesc.setBounds(0, 0, 615, 349);
		jourList.add(jourDesc);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(251, 234, 189));
		panel.setBounds(12, 10, 242, 422);
		panel_2.add(panel);
		panel.setLayout(null);
		
		//리스트모델 불러오기
		model = new DefaultListModel();
			list = new JList(model);
		list.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				String item = list.getSelectedValue().toString();
			
				//출력할 곳 입력
				// .setText(item);
			}
		});
		list.setBackground(new Color(255, 255, 255));
		list.setBounds(0, 0, 242, 422);
		panel.add(list);

	
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 234, 151));
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