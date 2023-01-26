package Diary.view;

/*
 * 1. 윈도우 빌더 밑그림
 * 2. 저장버튼
 * 3. 목록 출력
 * 4. 목록 i 선택 -> 일기 출력 및 편집 : update
 * 5. 목록 i 선택 -> 삭제버튼 : 데이터삭제
 */
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.text.textarea.FlatTextArea;

import Diary.model.JournalDAO;
import Diary.model.JournalDTO;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Journal extends JFrame {
	private ImageIcon icon = new ImageIcon("img/bg.png");
	private ImageIcon logoim = new ImageIcon("img/logo6.png");
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel list;
	FlatTextArea jourDesc;
	JButton update;
	int updateNum;
	JPanel panel_jourInput;
	JournalDTO jdto;


	/**
	 * Create the frame.
	 */
	@SuppressWarnings("rawtypes")
	public Journal() {

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

		// --------------일기 저장 버튼---------------
		FlatButton saveBtn = new FlatButton();
		saveBtn.setFont(new Font("나눔고딕", Font.BOLD, 21));
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() == saveBtn) {
					String journal = jourDesc.getText();
					String userId = "SY";
					JournalDTO journaldto = new JournalDTO(userId, journal);
					JournalDAO.getInstance().registerJournal(journaldto);
					panel_jourInput.revalidate();// 빈화면 세팅
					panel_jourInput.repaint();
					jourDesc.setText("");
					list.setVisible(false);
					list.removeAll();
					viewJourList();
					list.setVisible(true);
				}
			}
		});
		saveBtn.setBackground(new Color(242, 206, 96));
		saveBtn.setText("저장");
		saveBtn.setBounds(484, 359, 119, 53);
		panel_jourInput.add(saveBtn);
		// --------------일기 저장 버튼 종료------------

		// --------------일기 입력----------------------
		jourDesc = new FlatTextArea();
		jourDesc.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		jourDesc.setText(
				"안녕하세요:)\r\n스마트 다이어리입니다.\r\n\r\n오늘의 일기나 기분을 기록으로 남겨보세요. \r\n기록을 남기고 하단의 저장을 누르시면 일기가 저장됩니다.\r\n저장된 일기는 왼쪽의 목록에서 확인 할 수 있습니다.\r\n\r\n매일 매일 기록을 쌓아보세요.\r\n");
		jourDesc.setBounds(0, 0, 615, 349);
		panel_jourInput.add(jourDesc);
		// -------------일기 입력 종료------------------

		// -------------삭제 버튼, 리스트 클릭 -> 삭제-----------------------
		FlatButton delBtn;
		delBtn = new FlatButton("삭제");
		delBtn.setFont(new Font("나눔고딕", Font.BOLD, 21));
				delBtn.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						System.out.println(updateNum);
						JournalDAO.getInstance().deleteJour(updateNum);
						panel_jourInput.revalidate(); // 빈화면 보이게 만들기
						panel_jourInput.repaint();
						jourDesc.setText("");
						list.setVisible(false);
						list.removeAll();
						viewJourList();
						list.setVisible(true);
					}
				});
			

		delBtn.setText("삭제");
		delBtn.setBackground(new Color(242, 206, 96));
		delBtn.setBounds(353, 359, 119, 53);
		panel_jourInput.add(delBtn);
		// -------------삭제 버튼 종료-------------------

		// -------------수정 버튼------------------------
		FlatButton editBtn;
		editBtn = new FlatButton("수정");
		editBtn.setFont(new Font("나눔고딕", Font.BOLD, 21));
		editBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				jdto = new JournalDTO();
				jdto.setjournal(jourDesc.getText());
				jdto.setNum(updateNum);
				JournalDAO.getInstance().updateJour(jdto);

				list.remove(1);
				list.setVisible(false);
				list.removeAll();
				viewJourList();
				list.setVisible(true);

			}
		});

		editBtn.setBackground(new Color(242, 206, 96));
		editBtn.setBounds(222, 359, 119, 53);
		panel_jourInput.add(editBtn);
		// -------------수정 버튼 종료-------------------

		JPanel panel_jourList = new JPanel();
		panel_jourList.setBackground(new Color(251, 234, 189));
		panel_jourList.setBounds(12, 10, 224, 422);
		panel_2.add(panel_jourList);
		panel_jourList.setLayout(null);

		list = new JPanel();
		viewJourList();
		list.setFont(new Font("나눔고딕", Font.PLAIN, 14));
		list.setBackground(new Color(255, 255, 255));
		list.setBounds(0, 0, 224, 422);

		panel_jourList.add(list);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 234, 151));
		panel_1.setBounds(0, 0, 203, 111);
		AccountPanel.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(e.getSource() == lblNewLabel_1) {
					Menu menu = new Menu(Login.member);
					menu.setVisible(true);
					setVisible(false);
					}
				}
		});
		lblNewLabel_1.setBounds(-25, 10, 252, 91);
		lblNewLabel_1.setIcon(logoim);
		panel_1.add(lblNewLabel_1);

		JLabel lblNewLabel = new JLabel("l");
		lblNewLabel.setBounds(0, 0, 1034, 624);
		lblNewLabel.setIcon(icon);
		AccountPanel.add(lblNewLabel);
	}

	private String userid = userId();

	// ------------------리스트 조회-------------------------
	private void viewJourList() {

		GridBagLayout gbl = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();

		JournalDTO jDTO = new JournalDTO();
		jDTO.setUserId(userid);

		ArrayList<JournalDTO> jours = JournalDAO.getInstance().selectJour(jDTO);

		JButton[] jourList = new JButton[jours.size()];

		for (int i = 0; i < jours.size(); i++) {
			JournalDTO updateDTO = jours.get(i);
			String date = updateDTO.getRegdate().toString();
//			String memo = updateDTO.getjournal().substring(0, 6);
			String memo = updateDTO.getjournal().length() > 7 ? updateDTO.getjournal().substring(0, 7)
					: updateDTO.getjournal();
			String allmemo = updateDTO.getjournal();
			int num = updateDTO.getNum();

			jourList[i] = new JButton(date + " : " + memo);
			jourList[i].setHorizontalAlignment(SwingConstants.LEFT);
			jourList[i].setBackground(Color.WHITE);
			jourList[i].setFont(new Font("나눔고딕", Font.PLAIN, 13));
			jourList[i].setBorderPainted(false);
			System.out.println(date + ":" + memo);
			list.add(jourList[i]);
			jourList[i].addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					System.out.println(updateDTO.getNum() + " : " + updateDTO.getRegdate());
					updateNum = updateDTO.getNum();
					System.out.println(updateNum + " : " + date);
					System.out.println();
					System.out.println(num);

					jourDesc.setText(date);
					jourDesc.setText(allmemo);

					jourDesc.revalidate();
					jourDesc.repaint();
				}// jourList 클릭이벤트 내용
			});// jourList 클릭이벤트 종료

//			list.setLayout(gbl);//그리드정렬
//			gbc.gridx = 1;
//			gbc.anchor = GridBagConstraints.NORTHWEST;
//			gbc.weightx = 1;
//			gbl.setConstraints(jourList[i], gbc);
//			
//			list.add(jourList[i]);
//			list.updateUI();
//			list.setVisible(true);
		}

	}

	private String userId() {
			String result = null;
			if(Login.member != null) {
				result = Login.member.getUserid();
			}
			return result;
	}
}