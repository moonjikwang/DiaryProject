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
import com.mommoo.flat.text.textfield.FlatTextField;


public class Board extends JFrame {
	private ImageIcon icon = new ImageIcon(Board.class.getResource("55.png"));
	private ImageIcon logoim = new ImageIcon(Board.class.getResource("logo6.png"));
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Board frame = new Board();
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
	public Board() {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		flatButton.setText("전송");
		flatButton.setBackground(new Color(242, 206, 96));
		flatButton.setBounds(239, 0, 73, 48);
		panel_1.add(flatButton);
		
		FlatTextField flatTextField = new FlatTextField(false);
		flatTextField.setBounds(0, 0, 239, 48);
		panel_1.add(flatTextField);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 312, 510);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 312, 510);
		lblNewLabel.setIcon(icon);
		panel.add(lblNewLabel);
	}
}
