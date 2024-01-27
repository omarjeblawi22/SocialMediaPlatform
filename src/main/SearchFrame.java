package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import users.User;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;

public class SearchFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, User viewinguser) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SearchFrame frame = new SearchFrame(viewinguser);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * RETRIEVES A USERS PROFILE PAGE IF SEARCHED
	 */
	public SearchFrame(User viewinguser) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(102, 73, 234, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("SEARCH USER");
		lblNewLabel.setFont(new Font("Verdana Pro Cond Black", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(102, 11, 234, 25);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("SEARCH");
		btnNewButton.setFont(new Font("Gill Sans Ultra Bold", Font.PLAIN, 24));
		btnNewButton.setBounds(143, 104, 163, 62);
		contentPane.add(btnNewButton);
		
        btnNewButton.addActionListener(e -> {
            String Text = textField.getText();
            if (!Text.isEmpty()) {
                for (User user : Main.users) {
                	if (user.getNickname().equals(Text)) {
                		ProfilePageFrame.main(null, viewinguser, user);
                		dispose();
                	}
                }

                
            }
        });
	}
}
