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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.StringJoiner;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.border.SoftBevelBorder;

import users.User;

import javax.swing.border.BevelBorder;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;

public class Settings extends JFrame {

	private JPanel contentPane;
	private JTextField passwordField;
	private JTextField nameField;
	private JTextField surnameField;
	private JTextField ageField;
	private JTextField emailField;
	private String profilePicturePath;

	/**
	 * Launch the application.
	 * CHANGES INFO AND DETAILS
	 */
	public static void main(String[] args, User user) {
		
		run(user);
	}
	public static void run(User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Settings frame = new Settings(user);
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
	public Settings(User user) {
		setBackground(new Color(255, 255, 255));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		// THE LABELS
		JLabel signUpLabel = new JLabel("Edit Profile");
		signUpLabel.setBounds(3, 3, 430, 21);
		signUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
		signUpLabel.setForeground(new Color(0, 0, 0));
		signUpLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 16));
		contentPane.add(signUpLabel);
		
		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(157, 90, 145, 26);
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
		
		passwordField = new JTextField(user.getPassword());
		passwordField.setBounds(156, 121, 96, 20);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		
		nameField = new JTextField(user.getName());
		nameField.setBounds(30, 54, 96, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		surnameField = new JTextField(user.getSurname());
		surnameField.setBounds(30, 121, 96, 20);
		contentPane.add(surnameField);
		surnameField.setColumns(10);
		
		ageField = new JTextField(user.getAge());
		ageField.setBounds(30, 183, 96, 20);
		contentPane.add(ageField);
		ageField.setColumns(10);
		
		emailField = new JTextField(user.getMailAddress());
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
		
		
		JButton saveButton = new JButton("Save");
		saveButton.setFont(new Font("Mongolian Baiti", Font.PLAIN, 16));
		saveButton.setBounds(311, 229, 89, 23);
		contentPane.add(saveButton);
		
		// THE EXTRAS
		
		ImageIcon imageIcon = new ImageIcon(user.getProfilePicture());
		Image newimage = imageIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newimage);
		JLabel pfpPicLabel = new JLabel(image);
		pfpPicLabel.setBounds(312, 11, 100, 100);
		pfpPicLabel.setBackground(new Color(240, 240, 240));
		pfpPicLabel.setForeground(new Color(255, 128, 128));
		contentPane.add(pfpPicLabel);
		
	    // THE ACTION LISTENERS CHANGE THIS UP 
		pictureSelectionButton.addActionListener(new ActionListener() {	
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					File file = fileChooser.getSelectedFile();
					Image img;
					try {
						img = ImageIO.read(file);
						pfpPicLabel.setIcon(new ImageIcon(new ImageIcon(file.getAbsolutePath()).getImage().getScaledInstance(pfpPicLabel.getWidth(), pfpPicLabel.getHeight(), Image.SCALE_SMOOTH)));
						user.setProfilePicture(file.getAbsolutePath());
				
						File dataFile = new File("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\UserDatabase.txt");
						Scanner scanner = new Scanner(dataFile);
						Path path = Paths.get("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\UserDatabase.txt");
						List<String> myfile = new ArrayList<String>(Files.readAllLines(path, StandardCharsets.UTF_8)); 
						for (int i = 0; i < myfile.size(); i++) {
		                    String myLine = myfile.get(i);
		                    String[] data = myLine.split(" ");
		                    if (data[0].equals(user.getNickname())) {
		                        myfile.set(i, nameField.getText() + "," + surnameField.getText() + "," + ageField.getText() + "," + emailField.getText() + "," + user.getNickname() + "," + passwordField.getText() + "," + user.getProfilePicture() + "," + data[7]);
		                        break;
		                    }
		                }
		                Files.write(path, myfile, StandardCharsets.UTF_8);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
				}
				
			}
		});
	
		
	    quitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
	    	
	    });
	    
	    
	    
		saveButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
				File dataFile = new File("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\UserDatabase.txt");
				Scanner scanner = new Scanner(dataFile);
				Path path = Paths.get("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\UserDatabase.txt");
				List<String> myfile = new ArrayList<String>(Files.readAllLines(path, StandardCharsets.UTF_8)); 
				for (int i = 0; i < myfile.size(); i++) {
                    String myLine = myfile.get(i);
                    String[] data = myLine.split(",");
                    if (data[4].equals(user.getNickname())) {
                    	myfile.set(i, nameField.getText() + "," + surnameField.getText() + "," + ageField.getText() + "," + emailField.getText() + "," + user.getNickname() + "," + passwordField.getText() + "," + user.getProfilePicture() + "," + data[7]);
                        break;
                    }
                }
                Files.write(path, myfile, StandardCharsets.UTF_8);
				
			}
			catch (IOException e1) {
				e1.printStackTrace();
				}
				
				ProfilePageFrame.main(null, user, user);
				dispose();
			}
		});
		
		
	}
}
