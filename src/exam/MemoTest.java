package exam;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.mommoo.flat.button.FlatButton;
import com.mommoo.flat.component.FlatPanel;
import com.mommoo.flat.text.textarea.FlatTextArea;

import Diary.model.MemoDAO;
import Diary.model.MemoDTO;

import javax.swing.JList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import com.mommoo.flat.component.FlatScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;


public class MemoTest extends JFrame {
	private ImageIcon icon = new ImageIcon(MemoTest.class.getResource("bg.png"));
	private ImageIcon logoim = new ImageIcon(MemoTest.class.getResource("logo6.png"));
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	FlatTextArea memoDesc;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MemoTest frame = new MemoTest();
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
	public MemoTest() {
		
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
		
		FlatPanel memoList = new FlatPanel();
		memoList.setBackground(new Color(255, 255, 255));
		memoList.setBounds(266, 10, 615, 422);
		panel_2.add(memoList);
		memoList.setLayout(null);
		
		FlatButton saveBtn = new FlatButton();
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==saveBtn) {
					String memo = memoDesc.getText();
					String userId = "jikwang";
					MemoDTO memodto = new MemoDTO(userId,memo);
					MemoDAO.getInstance().registerMemo(memodto);
					memoList.revalidate();
					memoList.repaint();
					memoDesc.setText("");
					
				}
			}
		});
		saveBtn.setBackground(new Color(242, 206, 96));
		saveBtn.setText("저장");
		saveBtn.setBounds(484, 359, 119, 53);
		memoList.add(saveBtn);
		
		memoDesc = new FlatTextArea();
		memoDesc.setBounds(0, 0, 615, 349);
		memoList.add(memoDesc);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(251, 234, 189));
		panel.setBounds(12, 10, 242, 422);
		panel_2.add(panel);
		panel.setLayout(null);

	
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
