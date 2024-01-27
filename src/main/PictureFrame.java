package main;

import java.awt.Dimension;
import java.awt.EventQueue;
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
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import users.User;
import java.awt.Color;
import java.time.LocalDateTime;  
import java.time.format.DateTimeFormatter; 

public class PictureFrame extends JFrame {

    private JPanel contentPane;
    private JLabel pictureLabel;
    private JButton profileButton;
    private JButton likeButton;
    private User postOwner;
    private int likeCount;
    private JTextArea commentTextArea;
    private JButton commentButton;
    private JTextArea commentArea;
    private JLabel lblNewLabel;
    private JButton DeleteButton;
    public static int deleteCount;
    

    public static void main(String[] args, String path, User viewingUser) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PictureFrame frame = new PictureFrame(path, viewingUser);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public PictureFrame(String path, User viewingUser) {

        for (User user : Main.users) {
            for (String postPath : user.getPosts()) {
                if (postPath.equals(path)) {
                    postOwner = user;
                    break;
                }
            }
        }

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 400)); // Adjust the size of the frame as desired
        getContentPane().setLayout(null); // Use absolute layout

        // Create a JLabel for displaying the picture
        pictureLabel = new JLabel();
        pictureLabel.setBounds(0, 0, getWidth(), getHeight());
        getContentPane().add(pictureLabel);

        // Load the image and set it to the picture label
        ImageIcon imageIcon = new ImageIcon(path);
        pictureLabel.setIcon(imageIcon);

        // Likes on the post
        JLabel likesLabel = new JLabel("Likes: " + likeCount);
        likesLabel.setBounds(10, 10, 100, 20);
        getContentPane().add(likesLabel);

        // Owner
        JLabel ownerLabel = new JLabel("Owner: " + postOwner.getNickname());
        ownerLabel.setBounds(10, 40, 100, 20);
        getContentPane().add(ownerLabel);

        profileButton = new JButton("Go to Profile");
        profileButton.setBounds(10, 70, 120, 30);
        getContentPane().add(profileButton);

        profileButton.addActionListener(e -> {
            ProfilePageFrame.main(null, viewingUser, postOwner);
            dispose();
        });

        
        likeButton = new JButton("Like");
        likeButton.setBounds(140, 70, 80, 30);
        getContentPane().add(likeButton);
        
        commentTextArea = new JTextArea();
        commentTextArea.setBounds(10, 110, 196, 20);
        getContentPane().add(commentTextArea);
        
        commentButton = new JButton("Post Comment");
        commentButton.setBounds(10, 141, 130, 30);
        getContentPane().add(commentButton);
        
        commentArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(commentArea);
        scrollPane.setBounds(10, 180, 200, 180);
        getContentPane().add(scrollPane);
        
        lblNewLabel = new JLabel("New label");
        lblNewLabel.setBounds(255, 13, 321, 339);
        String imagePath = path;
        ImageIcon pfpimageIcon = new ImageIcon(imagePath);
        Image image = pfpimageIcon.getImage().getScaledInstance(379, 316, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        lblNewLabel.setIcon(resizedIcon);
        
        getContentPane().add(lblNewLabel);
        
        DeleteButton = new JButton("Delete");
        DeleteButton.setForeground(new Color(255, 0, 0));
        DeleteButton.setBounds(140, 5, 80, 30);
        getContentPane().add(DeleteButton);
        
        if (viewingUser.getPrivileges().contains("delete") || viewingUser.getNickname().equals(postOwner.getNickname())) {
        	DeleteButton.setVisible(true);
        }
        else {
        	DeleteButton.setVisible(false);
        }

        
        DeleteButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				try {
					removeLinesFromImageLog(path);
				} catch (IOException e1) {
			        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ErrorLog"))) {
			            writer.write(e1.getMessage());
			            System.out.println("Content written to the file successfully.");
			        } catch (IOException e6) {
			            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
			        }
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				dispose();
				
			
			}
		});
        // Add an action listener to the button (optional)
        likeButton.addActionListener(e -> {
            likeCount++;
            likesLabel.setText("Likes: " + likeCount);
        });
        
        try {
            // Read the file
            File file = new File("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\postlog");
            BufferedReader reader = new BufferedReader(new FileReader(file));

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split line into parts, using comma as the delimiter
                if (parts.length > 1 && parts[0].trim().equals(path)) {
                    for (int i = 1; i < parts.length; i++) {
                        String comment = parts[i].trim(); // Extract the comment
                        commentArea.append(comment + "\n"); // Append the comment to a new line
                    }
                }
            }

            // Close the resources
            reader.close();

        } catch (IOException e) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ErrorLog"))) {
	            writer.write(e.getMessage());
	            System.out.println("Content written to the file successfully.");
	        } catch (IOException e6) {
	            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
	        }
            e.printStackTrace();
        }

        
        
        
        /**
         * WRITES ALL THE COMMENTS POSTED BEFOREHAND IN THE COMMENT SECTION ALONG WITH THE USER WHO COMMENTED
         * AND ALLOWS FOR NEW COMMENTS TO BE DISPLAYED IF WRITTEN
         */
        commentButton.addActionListener(e -> {
            String commentText = commentTextArea.getText();
            if (!commentText.isEmpty()) {
                commentArea.append(viewingUser.getNickname() + ": " + commentText + "\n");
                commentTextArea.setText("");
                
                try {
                    // ADDS COMMENTS BY REWRITING THE FILE USING A TMP FILE
                    File file = new File("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\postlog");
                    BufferedReader reader = new BufferedReader(new FileReader(file));

                    File tempFile = new File("temp2.txt");
                    BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] words = line.split(",", 2); // Split line into words, using COMMA
                        if (words.length > 0 && words[0].equals(path)) {
                            line = line + "," + viewingUser.getNickname() + ": " + commentText; // Append the word to the line
                        }
                        
                        writer.write(line);
                        writer.newLine();
                    
                    }

                    // Close the resources
                    reader.close();
                    writer.close();

                    // Replace the original file with the modified file
                    if (file.delete()) {
                        tempFile.renameTo(file);
                        System.out.println("File modified successfully.");
                    } else {
                        System.out.println("Failed to modify the file.");
                    }

                } catch (IOException e4) {
        	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ErrorLog"))) {
        	            writer.write(e4.getMessage());
        	            System.out.println("Content written to the file successfully.");
        	        } catch (IOException e6) {
        	            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
        	        }
                    e4.printStackTrace();
                }
            }
        });
        
        

        pack(); // Adjust the frame size based on the components' preferred sizes
        setLocationRelativeTo(null); // Center the frame on the screen
    }
    
    public static void removeLinesFromImageLog(String path) throws IOException {
        File inputFile = new File("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ImageUploadLog");
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 2) {
                    String value = parts[1];
                    if (value.equals(path)) {
                        continue; // Skip writing this line to the temporary file
                    }
                }
                // Write the line to the temporary file
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ErrorLog"))) {
	            writer.write(e.getMessage());
	            System.out.println("Content written to the file successfully.");
	        } catch (IOException e6) {
	            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
	        }
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
            return;
        }

        // Replace the input file with the temporary file
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
            System.out.println("Lines containing path have been removed from the file.");
        } else {
            throw new IOException("Failed to delete the input file");
        }
    }
    
    // REMOVES THE LINE CONTAINING THE UNWANTED IMAGE PATH (DELETED IMAGES PATH) FROM IMAGEDETAILS TXT FILE
    public static void removeLinesFromImageDetails(String path) throws IOException {
        File inputFile = new File("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ImageDetails");
        File tempFile = new File("temp.txt");

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                if (parts.length >= 3) {
                    String value = parts[1];
                    if (value.equals(path)) {
                        continue; // Skip writing this line to the temporary file
                    }
                }
                // Write the line to the temporary file
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ErrorLog"))) {
	            writer.write(e.getMessage());
	            System.out.println("Content written to the file successfully.");
	        } catch (IOException e6) {
	            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
	        }
            System.out.println("An error occurred while reading the file.");
            e.printStackTrace();
            return;
        }

        // Replace the input file with the temporary file
        if (inputFile.delete()) {
            tempFile.renameTo(inputFile);
            System.out.println("Lines containing path have been removed from the file.");
        } else {
            throw new IOException("Failed to delete the input file");
        }
    }
}
