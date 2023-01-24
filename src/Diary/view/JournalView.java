package Diary.view;
/*
 * 1. 윈도우 빌더 밑그림
 * 2. 저장버튼 : 저장메서드 연결
 * 3. 목록 출력 : 출력메서드 연결 //
 * 4. 목록 선택버튼 -> 일기 출력 및 편집 : update //
 * 5. 목록 선택버튼 -> 삭제버튼 : 데이터삭제 //
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.AbstractListModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.border.EmptyBorder;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.text.textarea.FlatTextArea;

import Diary.model.JournalDAO;
import Diary.model.JournalDTO;

public class JournalView  extends JFrame {
	private ImageIcon icon = new ImageIcon("img/bg.png");
	private ImageIcon logoim = new ImageIcon("img/logo6.png");
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
		
		FlatPanel panel_jourInput = new FlatPanel();
		panel_jourInput.setBackground(new Color(255, 255, 255));
		panel_jourInput.setBounds(266, 10, 615, 422);
		panel_2.add(panel_jourInput);
		panel_jourInput.setLayout(null);
		
		//--------------일기 저장 버튼---------------
		FlatButton saveBtn = new FlatButton();
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==saveBtn) {
					String journal = jourDesc.getText();
					String userId = "SY";
					JournalDTO journaldto = new JournalDTO(userId,journal);
					JournalDAO.getInstance().registerJournal(journaldto);
					panel_jourInput.revalidate();
					panel_jourInput.repaint();
					jourDesc.setText("");
				}
			}
		});
		saveBtn.setBackground(new Color(242, 206, 96));
		saveBtn.setText("저장");
		saveBtn.setBounds(484, 359, 119, 53);
		panel_jourInput.add(saveBtn);
		//--------------일기 저장 버튼 종료------------
		
		//--------------일기 입력----------------------
		jourDesc = new FlatTextArea();
		jourDesc.setText("안녕하세요:)\r\n스마트 다이어리입니다.\r\n\r\n오늘의 일기나 기분을 기록으로 남겨보세요. \r\n기록을 남기고 하단의 Save를 누르시면 저장됩니다.\r\n저장된 일기는 왼쪽의 목록에서 확인 할 수 있습니다.\r\n\r\n매일 매일 기록을 쌓아보세요.\r\n");
		jourDesc.setBounds(0, 0, 615, 349);
		panel_jourInput.add(jourDesc);
		//-------------일기 입력 종료------------------
		
		//Jpanel 객체 생성
		JPanel panel_jourList = new JPanel();
		panel_jourList.setBackground(new Color(251, 234, 189));
		panel_jourList.setBounds(12, 10, 242, 422);
		panel_2.add(panel_jourList);
		panel_jourList.setLayout(null);
		
		//------------------ 리스트 출력 구현---------------
		//리스트모델 불러오기
		list = new JList();
		list.setModel(new AbstractListModel() {
			String[] values = viewJourList();

			@Override
			public int getSize() {
				return values.length;
			}

			@Override
			public Object getElementAt(int index) {
				return values[index];
			}
		});
		
//		model = (DefaultListModel)list.getModel(); 
//		model.addElement(panel_jourList);//여기까지 뭔가 뜨기는 함
		
		list.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		list.setBackground(new Color(255, 255, 255));
		list.setBounds(0, 0, 242, 422);
		
		panel_jourList.add(list);
		
		JScrollBar scrollBar = new JScrollBar();
		scrollBar.setBounds(0, 0, 17, 422);
		panel_jourList.add(scrollBar);

	
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

	private String userid = "SY";
	
	//------------------리스트 조회-------------------------
	private String[] viewJourList() {
		// TODO Auto-generated method stub
		ArrayList jours = JournalDAO.getInstance().selectJour(userid);
		String[] jourList = new String[jours.size()];
		
		for(int i = 0; i < jours.size(); i++) {
			JournalDTO dto = (JournalDTO)jours.get(i);
			jourList[i] = dto.getRegdate().toString();
			System.out.println(jourList[i]);
		}
		return jourList;
	}
	
	//-----------------리스트 클릭 -> 수정 ----------------
	private void updateJour() {
		
	}
	//-----------------리스트 클릭 -> 삭제-----------------
	private void deleteJour() {
		
	}
	
}