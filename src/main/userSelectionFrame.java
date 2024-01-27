package main;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import net.miginfocom.swing.MigLayout;
import users.FreeUser;
import users.HobbyistUser;
import users.ProfessionalUser;
import users.User;

import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class userSelectionFrame extends JFrame {

	private JPanel contentPane;
	private ArrayList<User> myUsers = new ArrayList<User>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					userSelectionFrame frame = new userSelectionFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * CREATES THE USER OBJECTS HERE TO GO OVER TO THEIR SET UP PROFILE PAGE
	 */
	public userSelectionFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCreateFreeUser = new JButton("Create Free User");
		btnCreateFreeUser.setForeground(new Color(64, 128, 128));
		btnCreateFreeUser.setFont(new Font("Rockwell Nova", Font.BOLD | Font.ITALIC, 12));
		btnCreateFreeUser.setBounds(237, 52, 146, 147);
		contentPane.add(btnCreateFreeUser);
		
		JButton btnCreateHobbyist = new JButton("Create Hobbyist\r\n");
		btnCreateHobbyist.setForeground(new Color(128, 0, 255));
		btnCreateHobbyist.setFont(new Font("Verdana Pro Cond Black", Font.BOLD | Font.ITALIC, 12));
		btnCreateHobbyist.setBounds(237, 210, 146, 147);
		contentPane.add(btnCreateHobbyist);
		
		JButton btnCreateProfessional = new JButton("Create Professional");
		btnCreateProfessional.setForeground(new Color(255, 128, 192));
		btnCreateProfessional.setFont(new Font("Script MT Bold", Font.ITALIC, 14));
		btnCreateProfessional.setBounds(237, 368, 146, 147);
		contentPane.add(btnCreateProfessional);
		
		JTextArea txtrTheFreeUser = new JTextArea();
		txtrTheFreeUser.setEditable(false);
		txtrTheFreeUser.setBackground(new Color(64, 128, 128));
		txtrTheFreeUser.setFont(new Font("Gill Sans Nova Cond XBd", Font.BOLD | Font.ITALIC, 13));
		txtrTheFreeUser.setText("THE FREE USER:\r\nFor our day-to-day \r\ncasual users.\r\nPrivileges:\r\nBlur, Sharpen Filters.");
		txtrTheFreeUser.setBounds(35, 52, 171, 147);
		contentPane.add(txtrTheFreeUser);
		
		JTextArea txtrTheHobbyistUser = new JTextArea();
		txtrTheHobbyistUser.setEditable(false);
		txtrTheHobbyistUser.setText("THE HOBBYIST USER:\r\nFor our more ambitious\r\nand skilled users.\r\nPrivileges:\r\nBlur, Sharpen, Brightness,\r\nContrast Filters.");
		txtrTheHobbyistUser.setFont(new Font("Gill Sans Nova Cond XBd", Font.BOLD | Font.ITALIC, 13));
		txtrTheHobbyistUser.setBackground(new Color(128, 0, 255));
		txtrTheHobbyistUser.setBounds(35, 210, 171, 147);
		contentPane.add(txtrTheHobbyistUser);
		
		JTextArea txtrTheProfessionalUser = new JTextArea();
		txtrTheProfessionalUser.setEditable(false);
		txtrTheProfessionalUser.setText("THE PROFESSIONAL USER:\r\nFor our serious and \r\nexperienced users.\r\nPrivileges:\r\nBlur, Sharpen, Brightness,\r\nContrast, Grayscale, \r\nEdge Detection Filters.");
		txtrTheProfessionalUser.setFont(new Font("Gill Sans Nova Cond XBd", Font.BOLD | Font.ITALIC, 13));
		txtrTheProfessionalUser.setBackground(new Color(255, 128, 192));
		txtrTheProfessionalUser.setBounds(35, 368, 171, 147);
		contentPane.add(txtrTheProfessionalUser);
		
		JLabel selectLabel = new JLabel("User Selection\r\n");
		selectLabel.setHorizontalAlignment(SwingConstants.CENTER);
		selectLabel.setFont(new Font("Traditional Arabic", Font.BOLD | Font.ITALIC, 20));
		selectLabel.setBounds(35, 11, 348, 30);
		contentPane.add(selectLabel);
		
		
		
		btnCreateFreeUser.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        List<String> elementsList = splitLastLine();

		        // Insert the userType at the beginning of the list
		        elementsList.add("FreeUser");

		        // Construct a comma-separated string from the elements in the list
		        String csvString = String.join(",", elementsList) + "\n";

		        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\UserDatabase.txt", true))) {
		            // Write the comma-separated string to the file
		            writer.write(csvString);
		        } catch (IOException i) {
		            System.out.println("An error occurred while writing the file.");
		            i.printStackTrace();
		        }
		        
		        FreeUser registeredFreeUser = new FreeUser(elementsList.get(4), elementsList.get(5), elementsList.get(0), elementsList.get(1), elementsList.get(2), elementsList.get(3), elementsList.get(6));
		        
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\users", true))) {
				    // The data to write into the file
				    writer.write(registeredFreeUser + "\n");
				} catch (IOException i) {
				    System.out.println("An error occurred while writing the file.");
				    i.printStackTrace();
				}
		        
				ProfilePageFrame.main(null, registeredFreeUser, registeredFreeUser);
		        dispose();
		    }
		});
		
		btnCreateHobbyist.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        List<String> elementsList = splitLastLine();

		        // Insert the userType at the beginning of the list
		        elementsList.add("HobbyistUser");

		        // Construct a comma-separated string from the elements in the list
		        String csvString = String.join(",", elementsList) + "\n";

		        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\UserDatabase.txt", true))) {
		            // Write the comma-separated string to the file
		            writer.write(csvString);
		        } catch (IOException i) {
		            System.out.println("An error occurred while writing the file.");
		            i.printStackTrace();
		        }
		        
		        HobbyistUser registeredHobbyistUser = new HobbyistUser(elementsList.get(4), elementsList.get(5), elementsList.get(0), elementsList.get(1), elementsList.get(2), elementsList.get(3), elementsList.get(6));
		        
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\users", true))) {
				    // The data to write into the file
				    writer.write(registeredHobbyistUser + "\n");
				} catch (IOException i) {
				    System.out.println("An error occurred while writing the file.");
				    i.printStackTrace();
				}
		        
				
		        ProfilePageFrame.main(null, registeredHobbyistUser, registeredHobbyistUser);
		        dispose();
		    }
		});
		
		btnCreateProfessional.addActionListener(new ActionListener() {
			@Override
		    public void actionPerformed(ActionEvent e) {
		        List<String> elementsList = splitLastLine();

		        // Insert the userType at the beginning of the list
		        elementsList.add("ProfessionalUser");

		        // Construct a comma-separated string from the elements in the list
		        String csvString = String.join(",", elementsList) + "\n";

		        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\UserDatabase.txt", true))) {
		            // Write the comma-separated string to the file
		            writer.write(csvString);
		        } catch (IOException i) {
		            System.out.println("An error occurred while writing the file.");
		            i.printStackTrace();
		        }
		        
		        ProfessionalUser registeredProUser = new ProfessionalUser(elementsList.get(4), elementsList.get(5), elementsList.get(0), elementsList.get(1), elementsList.get(2), elementsList.get(3), elementsList.get(6));
		        
				try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\users", true))) {
				    // The data to write into the file
				    writer.write(registeredProUser + "\n");
				} catch (IOException i) {
				    System.out.println("An error occurred while writing the file.");
				    i.printStackTrace();
				}
		        
		        ProfilePageFrame.main(null, registeredProUser, registeredProUser);
		        dispose();
		    }
		});
	}
	
	public static String readLastLineFromDatabase(String filePath) {
        String lastLine = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lastLine = line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lastLine;
    }
	
	public static List<String> splitLastLine() {
		String line = readLastLineFromDatabase("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\database.txt");
		
        // Split the line by a delimiter (comma)
        String[] elements = line.split(",");

        // Trim whitespace from each element
        for (int i = 0; i < elements.length; i++) {
            elements[i] = elements[i].trim();
        }

        // Create a list and add the elements
        List<String> elementsList = new ArrayList<>(Arrays.asList(elements));
        return elementsList;
	}
	

	
	public static List<String> findAndSplitData(String username){
        try (BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\UserDatabase.txt"))) {
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
                	return elementsList;
                
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
	}
	
	public ArrayList<User> getUserList(){
		return myUsers;
	}
	
	public void addUser(User user) {
		myUsers.add(user);
	}
}
