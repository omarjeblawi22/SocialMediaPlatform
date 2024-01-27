package main;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import users.User;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import net.miginfocom.swing.MigLayout;
import java.awt.FlowLayout;
import com.jgoodies.forms.layout.FormSpecs;
import javax.swing.ScrollPaneConstants;

public class ProfilePageFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, User userviewer, User userviewed) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProfilePageFrame frame = new ProfilePageFrame(userviewer, userviewed);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * HAS A BUNCH OF BUTTONS THAT NAVIGATE THROUGH THE APPLICATION.
	 * ALSO ONLY DISPLAYS PRIVATE INFORMATION IF THE VIWEING USER IS THE SAME AS THE VIEWED USER.
	 */
	public ProfilePageFrame(User userviewer, User userviewed) {
		
		int columns = 2;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Get the screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Set the bounds to cover the entire screen
		setBounds(0, 0, screenSize.width, screenSize.height);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Profile Page");
		lblNewLabel.setBackground(new Color(240, 240, 240));
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 44));
		lblNewLabel.setBounds(10, 11, 345, 102);
		contentPane.add(lblNewLabel);
		
		JLabel lblUsername = new JLabel(userviewed.getNickname());
		lblUsername.setForeground(new Color(0, 0, 0));
		lblUsername.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 50));
		lblUsername.setBackground(SystemColor.menu);
		lblUsername.setBounds(10, 199, 333, 102);
		contentPane.add(lblUsername);
        ImageIcon imageIcon = new ImageIcon(userviewed.getProfilePicture());
        
        JLabel lblNewLabel_1 = new JLabel("User Info: ");
        lblNewLabel_1.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 26));
        lblNewLabel_1.setForeground(new Color(0, 0, 0));
        lblNewLabel_1.setBounds(10, 429, 397, 40);
        contentPane.add(lblNewLabel_1);
        
        JTextArea txtrName = new JTextArea();
        txtrName.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        txtrName.setBackground(new Color(0, 0, 0));
        txtrName.setForeground(new Color(255, 255, 255));
        txtrName.setText("Name: " + userviewed.getName() + "\r\n\r\nSurname:" + userviewed.getSurname() + "\r\n\r\nAge: " + userviewed.getAge() + "\r\n");
        txtrName.setEditable(false);
        txtrName.setBounds(10, 477, 333, 123);
        contentPane.add(txtrName);
        
        JTextArea txtrEmailPassword = new JTextArea();
        txtrEmailPassword.setForeground(new Color(255, 255, 255));
        txtrEmailPassword.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 16));
        txtrEmailPassword.setBackground(new Color(0, 0, 0));
        txtrEmailPassword.setText("Email: " + userviewed.getMailAddress() + "\r\n\r\nPassword: " + userviewed.getPassword());
        txtrEmailPassword.setEditable(false);
        txtrEmailPassword.setBounds(10, 598, 333, 74);
        contentPane.add(txtrEmailPassword);
        
        JButton discoverButton = new JButton("DISCOVER");
        discoverButton.setForeground(new Color(0, 0, 0));
        discoverButton.setBackground(new Color(128, 128, 255));
        discoverButton.setFont(new Font("Verdana Pro Cond Black", Font.BOLD, 26));
        discoverButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        discoverButton.setBounds(365, 444, 188, 228);
        contentPane.add(discoverButton);
        
        JButton btnPost = new JButton("POST");
        btnPost.setForeground(new Color(255, 255, 255));
        btnPost.setFont(new Font("Verdana Pro Cond Black", Font.BOLD, 26));
        btnPost.setBackground(new Color(0, 0, 0));
        btnPost.setBounds(556, 444, 188, 228);
        contentPane.add(btnPost);
        
        if (userviewed.equals(userviewer)) {
        	txtrEmailPassword.setVisible(true);
        	btnPost.setVisible(true);
        }
        else {
        	txtrEmailPassword.setVisible(false);
        	btnPost.setVisible(false);
        }
        
        JLabel postsLabel = new JLabel("~ P O S T S ~");
        postsLabel.setFont(new Font("Bodoni MT Condensed", Font.BOLD | Font.ITALIC, 97));
        postsLabel.setForeground(new Color(0, 0, 0));
        postsLabel.setBounds(810, 48, 446, 65);
        contentPane.add(postsLabel);
        
     // Create a panel with a grid layout for the photo grid
        JPanel photoGridPanel = new JPanel(new GridLayout(0, columns, 10, 10));
      
        // Create a scroll pane and add the photo grid panel to it
        JScrollPane scrollPane = new JScrollPane(photoGridPanel);
        scrollPane.setBounds(810, 123, 400, 600);

        // Add the scroll pane to the content pane
        contentPane.add(scrollPane);

        // Add the photos to the photo grid
        for (String photoPath : userviewed.getPosts()) {
            ImageIcon theImageIcon = new ImageIcon(photoPath);
            Image image = theImageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH);
            ImageIcon resizedIcon = new ImageIcon(image);
            JLabel photoLabel = new JLabel(resizedIcon);
            photoLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    PictureFrame.main(null, photoPath, userviewed);
                    dispose();
                }
            });
            photoGridPanel.add(photoLabel);
        }

        JLabel profileLabel = new JLabel("New label");
        profileLabel.setBounds(365, 102, 379, 316);
        String imagePath = userviewed.getProfilePicture();
        ImageIcon pfpimageIcon = new ImageIcon(imagePath);
        Image image = pfpimageIcon.getImage().getScaledInstance(379, 316, Image.SCALE_SMOOTH);
        ImageIcon resizedIcon = new ImageIcon(image);
        profileLabel.setIcon(resizedIcon);
        contentPane.add(profileLabel);
        
        JButton btnNewButton = new JButton("Edit Profile");
        btnNewButton.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));
        btnNewButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        
        btnNewButton.setBounds(385, 61, 145, 23);
        contentPane.add(btnNewButton);
        
        JButton btnSearchUsers = new JButton("Search Users");
        btnSearchUsers.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 14));
        btnSearchUsers.setBounds(568, 62, 145, 23);
        contentPane.add(btnSearchUsers);
        
        if (userviewed.getNickname().equals(userviewer.getNickname())) {
        	btnNewButton.setVisible(true);
        }
        btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Settings.main(null, userviewed);
				dispose();
			}
		});
        
        btnSearchUsers.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SearchFrame.main(null, userviewer);
				
			}
		});
        btnPost.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
				PostAndEditFrame.main(null, userviewed);
			}
		});
        
        discoverButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				DiscoverPage.main(null, userviewer);
				
			}
		});
        
    }
}
