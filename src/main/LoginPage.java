package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import users.User;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.awt.GridBagConstraints;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;


public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JPasswordField passwordField;
	private userSelectionFrame userselect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
	/**
	 */
	public LoginPage() {

		setFont(new Font("Georgia Pro Cond", Font.BOLD | Font.ITALIC, 18));
		setTitle("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// BUTTONS, LABELS AND FIELDS
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(424, 19, 2, 2);
		contentPane.add(scrollPane);
		
		JLabel loginLabel = new JLabel("Login");
		loginLabel.setForeground(new Color(0, 0, 255));
		loginLabel.setFont(new Font("Bodoni MT", Font.BOLD | Font.ITALIC, 24));
		loginLabel.setBounds(177, 11, 64, 39);
		contentPane.add(loginLabel);
		
		JLabel userLabel = new JLabel("Username:");
		userLabel.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 24));
		userLabel.setBounds(4, 19, 143, 46);
		contentPane.add(userLabel);
		
		JLabel passLabel = new JLabel("Password:");
		passLabel.setFont(new Font("Simplified Arabic Fixed", Font.PLAIN, 24));
		passLabel.setBounds(4, 102, 172, 39);
		contentPane.add(passLabel);
		
		JLabel accLabel = new JLabel("Don't have an account?");
		JLabel signLabel = new JLabel("Sign up here!");
		accLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		accLabel.setBounds(230, 61, 196, 39);
		signLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		signLabel.setBounds(230, 108, 109, 30);
		contentPane.add(accLabel);
		contentPane.add(signLabel);
		
		JButton logInButton = new JButton("Log in");
		logInButton.setForeground(new Color(0, 0, 255));
		logInButton.setFont(new Font("Simplified Arabic Fixed", Font.BOLD | Font.ITALIC, 16));
		logInButton.setBounds(10, 201, 137, 40);
		contentPane.add(logInButton);
		
		JButton signUpButton = new JButton("Sign up!");
		signUpButton.setForeground(new Color(255, 0, 0));
		signUpButton.setFont(new Font("Simplified Arabic Fixed", Font.BOLD | Font.ITALIC, 20));
		signUpButton.setBounds(230, 141, 196, 46);
		contentPane.add(signUpButton);
		
		usernameField = new JTextField();
		usernameField.setBounds(4, 61, 186, 30);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(4, 152, 186, 30);
		contentPane.add(passwordField);
		
		signUpButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				registerFrame.run();
				dispose();
			}
		});
		
		
		// RUNS A FOR LOOP THROUGH THE LIST OF USERS IN THE DATABASE TO CHECK IF SUCH A USER EXISTS, 
		//IF THE USER EXISTS THEN IT CHECKS IF THE USER PASSWORD IS THE SAME AS THE INPUT PASSWORD
		// IF ANY UNFOUND VALUES OCCUR THEN AN ERROR MESSAGE IS SHOWN 
		// IF FOUND, LOGS IN AND WRITES INTO THE LOG IN LOG
		
		logInButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
	            String inputUsername = usernameField.getText();
	            char[] inputPasswordChars = passwordField.getPassword();
	            String inputPassword = new String(inputPasswordChars);
	            try {
					if (usernameExists(inputUsername)) {
			            try {
							if (usernameMatchesPassword(inputUsername, inputPassword)) {
								
								for (User myUser : Main.users) {
									if (myUser.getNickname().equals(inputUsername)) {
										ProfilePageFrame.main(null, myUser, myUser);
										
								        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\loginlog.txt"))) {
								            writer.write(myUser.getNickname() + " has logged into the system at "+ LocalTime.now());
								            System.out.println("Content written to the file successfully.");
								        } catch (IOException e6) {
								            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
								        }
										
										dispose();
									}
								}
							}
							else {
				                JOptionPane.showMessageDialog(LoginPage.this, "Wrong password", "Error", JOptionPane.ERROR_MESSAGE);
							}
						} catch (FileNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
					else {
		                JOptionPane.showMessageDialog(LoginPage.this, "Username is invalid", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (FileNotFoundException e1) {
					
			        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ErrorLog"))) {
			            writer.write(e1.getMessage());
			            System.out.println("Content written to the file successfully.");
			        } catch (IOException e6) {
			            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
			        }
					e1.printStackTrace();
				} catch (IOException e1) {
			        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ErrorLog"))) {
			            writer.write(e1.getMessage());
			            System.out.println("Content written to the file successfully.");
			        } catch (IOException e6) {
			            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
			        }
					e1.printStackTrace();
				}
	           
	            
			}
		});

	}
	
	public static boolean usernameExists(String username) throws FileNotFoundException, IOException{
		 try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\database.txt"))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	String[] elements = line.split(",");

	                // Trim whitespace from each element
	                for (int i = 0; i < elements.length; i++) {
	                    elements[i] = elements[i].trim();
	                }

	                // Create a list and add the elements
	                List<String> elementsList = new ArrayList<>(Arrays.asList(elements));
	                if (elementsList.get(4).equals(username)) {
	                	return true;
	                }
	        } 
	    }
		return false;
	}
	
	public static boolean usernameMatchesPassword(String username, String password) throws FileNotFoundException, IOException {
		 try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\database.txt"))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	            	String[] elements = line.split(",");

	                // Trim whitespace from each element
	                for (int i = 0; i < elements.length; i++) {
	                    elements[i] = elements[i].trim();
	                }

	                // Create a list and add the elements
	                List<String> elementsList = new ArrayList<>(Arrays.asList(elements));
	                if (elementsList.get(4).equals(username));{
	                	if (elementsList.get(5).equals(password)) {
	                		return true;
	                	}
	                }
	            }	 
		 	}
		return false;
	}
	// FINDS THE DESIRED USER USING THEIR NICKNAME AND USING THE LIST OF USERS
	public User findUserFromList(String nickname) {
		for (User theuser : Main.users) {
			if (theuser.getNickname().equals(nickname)) {
				return theuser;
			}
		}
		return null;
	}
	
}
