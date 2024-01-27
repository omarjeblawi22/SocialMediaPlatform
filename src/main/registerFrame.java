package main;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.StringJoiner;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;

import users.User;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class registerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameField;
	private JTextField passwordField;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField ageField;
	private JTextField emailField;
	private String profilePicturePath;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		run();
	}
	public static void run() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					registerFrame frame = new registerFrame();
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
	public registerFrame() {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// THE LABELS
		JLabel signUpLabel = new JLabel("Sign up");
		signUpLabel.setBounds(3, 3, 430, 21);
		signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signUpLabel.setForeground(new Color(0, 0, 0));
		signUpLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		contentPane.add(signUpLabel);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(157, 90, 145, 26);
		usernameLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		contentPane.add(usernameLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(157, 149, 145, 26);
		passwordLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		contentPane.add(passwordLabel);
		
		JLabel nameLabel = new JLabel("Name:");
		nameLabel.setBounds(30, 35, 96, 20);
		nameLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		contentPane.add(nameLabel);
		
		JLabel surnameLabel = new JLabel("Surname:");
		surnameLabel.setBounds(30, 93, 96, 20);
		surnameLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		contentPane.add(surnameLabel);
		
		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setBounds(30, 152, 96, 20);
		ageLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		contentPane.add(ageLabel);
		
		JLabel emailLabel = new JLabel("Email:");
		emailLabel.setBounds(156, 35, 96, 20);
		emailLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		contentPane.add(emailLabel);
		
		JLabel accountCheckLabel = new JLabel("Already have an account?");
		accountCheckLabel.setFont(new Font("Sitka Text", Font.BOLD, 13));
		accountCheckLabel.setForeground(new Color(0, 0, 0));
		accountCheckLabel.setBounds(269, 174, 184, 43);
		contentPane.add(accountCheckLabel);
		
		// THE TEXT FIELDS
		
		usernameField = new JTextField();
		usernameField.setBounds(156, 121, 96, 20);
		contentPane.add(usernameField);
		usernameField.setColumns(10);
		
		passwordField = new JTextField();
		passwordField.setBounds(156, 183, 96, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(30, 54, 96, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		surnameField = new JTextField();
		surnameField.setBounds(30, 121, 96, 20);
		contentPane.add(surnameField);
		surnameField.setColumns(10);
		
		ageField = new JTextField();
		ageField.setBounds(30, 183, 96, 20);
		contentPane.add(ageField);
		ageField.setColumns(10);
		
		emailField = new JTextField();
		emailField.setBounds(156, 54, 96, 20);
		contentPane.add(emailField);
		emailField.setColumns(10);
		
		// THE BUTTONS 
		
		JButton pictureSelectionButton = new JButton("Select ");
		pictureSelectionButton.setBounds(312, 119, 105, 21);
		pictureSelectionButton.setFont(new Font("Georgia Pro Cond Light", Font.PLAIN, 16));
		contentPane.add(pictureSelectionButton);
		
		JButton quitButton = new JButton("Quit");
		quitButton.setBounds(30, 229, 89, 23);
		quitButton.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
		contentPane.add(quitButton);
		
		JButton signUpButton = new JButton("Sign up");
		signUpButton.setBounds(157, 229, 89, 23);
		signUpButton.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
		contentPane.add(signUpButton);
		
		JButton signInButton = new JButton("Sign in");
		signInButton.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
		signInButton.setBounds(311, 229, 89, 23);
		contentPane.add(signInButton);
		
		// THE EXTRAS
		
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\default.jpg");
		Image newimage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newimage);
		JLabel label = new JLabel(image);
		label.setBounds(312, 11, 100, 100);
		label.setBackground(new Color(240, 240, 240));
		label.setForeground(new Color(255, 128, 128));
		contentPane.add(label);
		
	    // THE ACTION LISTENERS
		
		// SELECTS PICTURE FROM LAPTOP LIBRARY
		pictureSelectionButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        int result = fileChooser.showOpenDialog(registerFrame.this);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            profilePicturePath = selectedFile.getAbsolutePath();
          
		            
		            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\profilePhotos", true))) {
					    // The data to write into the file
					    writer.write(profilePicturePath + "\n");
					} catch (IOException i) {
					    System.out.println("An error occurred while writing the file.");
					    i.printStackTrace();
					}
		            
		            // Update the image label with the selected image
		            
		            ImageIcon imageIcon = new ImageIcon(profilePicturePath);
		            Image newImage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		            ImageIcon selectedImage = new ImageIcon(newImage);
		            label.setIcon(selectedImage);
		        }
		    }
		});
		
		// QUITS THE APPLICATION
	    quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
	    	
	    });
	    
	    // SIGNS UP USER BY RETRIEVING ALL THE INFO FROM THE FIELDS AND CHECKS IF EACH ONE IS VALID
	    // WHICHEVER ONES ARE NOT VALID ARE DISPLAYED AS AN ERROR MESSAGE AND PREVENT SIGNING UP
	    signUpButton.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	        	HashMap<String, Boolean> checkers = new HashMap<>();
	        	String errorString = "";
	        	Boolean errorExistence = false;
	            String inputName = nameField.getText();
	            String inputSurname = surnameField.getText();
	            String inputAge = ageField.getText();
	            String inputEmail = emailField.getText();
	            String inputUsername = usernameField.getText();
	            String inputPassword = passwordField.getText();
	            String[] infoForDatabase = {inputName, inputSurname, inputAge, inputEmail, inputUsername, inputPassword, profilePicturePath};
	            StringJoiner joiner = new StringJoiner(", ");
	            for (String str : infoForDatabase) {
	                joiner.add(str);
	            }
	            String infoInFormat = joiner.toString();
	            checkers.put("Invalid Name", validateName(inputName));
	            checkers.put("Invalid Surname", validateSurname(inputSurname));
	            checkers.put("Invalid Age", validateAge(inputAge));
	            checkers.put("Invalid Email", validateEmail(inputEmail));
	            try {
					checkers.put("Email already exists", !emailAlreadyExists(inputEmail));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            try {
					checkers.put("Username already exists", !nicknameAlreadyExists(inputUsername));
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	            for (Entry<String, Boolean> entry : checkers.entrySet()) {
	                String key = entry.getKey();
	                Boolean value = entry.getValue();
	                if (value == false) {
	                	errorString += key + "\n";
	                	errorExistence = true;	
	                }
	            }
	            if (errorExistence) {
	                JOptionPane.showMessageDialog(registerFrame.this, errorString, "Error", JOptionPane.ERROR_MESSAGE);
	            }
	            else {
	            	
	            	// IF ALL DATA FIELDS PASS THE CHECKERS THAN WE WRITE NEW USER INFO INTO OUR TXT FILES/DATABASES FOR 
	            	// LATER USE AND DATA STORAGE
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\emailDatabase", true))) {
				    // The data to write into the file
				    writer.write(inputEmail + "\n");
				} catch (IOException i) {
				    System.out.println("An error occurred while writing the file.");
				    i.printStackTrace();
				}
				
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\nicknameDatabase", true))) {
				    // The data to write into the file
				    writer.write(inputUsername + "\n");
				} catch (IOException i) {
				    System.out.println("An error occurred while writing the file.");
				    i.printStackTrace();
				}
				
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\UserPosts", true))) {
				    // The data to write into the file
				    writer.write(inputUsername + "\n");
				} catch (IOException i) {
				    System.out.println("An error occurred while writing the file.");
				    i.printStackTrace();
				}
				
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\database.txt", true))) {
				    // The data to write into the file
				    writer.write(infoInFormat + "\n");
				} catch (IOException i) {
				    System.out.println("An error occurred while writing the file.");
				    i.printStackTrace();
				}
				
		        String folderPath = "C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\postedphotos\\posts" + inputUsername;

		        File folder = new File(folderPath);
		        if (!folder.exists()) {
		            if (folder.mkdirs()) {
		                System.out.println("Folder created successfully.");
		            } else {
		                System.out.println("Failed to create the folder.");
		            }
		        } else {
		            System.out.println("The folder already exists.");
		        }
		        
	                JOptionPane.showMessageDialog(registerFrame.this,
	                        "Signed up successfully!",
	                        "Success",
	                        JOptionPane.INFORMATION_MESSAGE);
	               userSelectionFrame.main(null);
	               dispose();
	            }
	        }
	    });
	    // TAKES YOU TO THE LOG IN PAGE
		signInButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage.main(null);
				dispose();
			}
		});
	}
	
	// FIELD CHECKS
	
	public boolean validateName(String name) {
		return name.matches("[a-zA-Z]+");
	}
	public boolean validateSurname(String surname) {
		return surname.matches("[a-zA-Z]+");
	}
	public boolean validateAge(String age) {
		int numAge = Integer.parseInt(age);
		return (age.matches("\\d+") && (numAge >= 17));
	}
	public boolean validateEmail(String email) {
		String emailPattern = "^[A-Za-z0-9+_.-]+@(gmail\\.com|yahoo\\.com|ku\\.edu\\.tr|outlook\\.com)$";
		return email.matches(emailPattern);
	}
	public boolean validatePassword(String password) {
		return (password.length() > 7);
	}
	
	// MAKES SURE NO 2 USERS HAVE THE SAME EMAIL OR USERNAME
	
	public boolean emailAlreadyExists(String email) throws FileNotFoundException, IOException {
		 try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\emailDatabase"))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                if(line.equals(email)) {
	                	return true;
	            }
	        } 
	    }
		return false;
	}
	public boolean nicknameAlreadyExists(String nickname) throws FileNotFoundException, IOException {
		 try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\nicknameDatabase"))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                if(line.equals(nickname)) {
	                	return true;
	            }
	        } 
	    }
		return false;
	}
}
