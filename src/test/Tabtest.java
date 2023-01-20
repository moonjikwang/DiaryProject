package test;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Tabtest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tabtest frame = new Tabtest();
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
	public Tabtest() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 24, 434, 237);
		contentPane.add(panel);
		panel.setLayout(new CardLayout(0, 0));
		
		JPanel panel_1 = new JPanel();
		panel.add(panel_1, "name_12212682973700");
		
		JLabel lblNewLabel = new JLabel("Tab 1");
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "name_12229082456900");
		
		JLabel lblNewLabel_1 = new JLabel("Tab 2");
		panel_2.add(lblNewLabel_1);
		
		JPanel panel_3 = new JPanel();
		panel.add(panel_3, "name_12230562638100");
		
		JLabel lblNewLabel_2 = new JLabel("Tab 3");
		panel_3.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("1페이지");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton) {
					panel_1.setVisible(true);
					panel_2.setVisible(false);
					panel_3.setVisible(false);
				}
			}
		});
		btnNewButton.setBounds(0, 0, 97, 23);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("2페이지");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource() == btnNewButton_1) {
					panel_1.setVisible(false);
					panel_2.setVisible(true);
					panel_3.setVisible(false);
				}
			}
		});
		btnNewButton_1.setBounds(96, 0, 97, 23);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("3페이지");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()== btnNewButton_2) {
					panel_1.setVisible(false);
					panel_2.setVisible(false);
					panel_3.setVisible(true);
				}
			}
		});
		btnNewButton_2.setBounds(195, 0, 97, 23);
		contentPane.add(btnNewButton_2);
	}
}
