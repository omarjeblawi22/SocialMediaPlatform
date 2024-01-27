package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import filters.Blur;
import filters.Brighten;
import filters.Contrast;
import filters.EdgeDetection;
import filters.Grayscale;
import filters.Sharpen;
import users.User;
import javax.swing.JProgressBar;
import javax.swing.JSlider;
import javax.swing.JCheckBox;

public class PostAndEditFrame extends JFrame {

	private String profilePicturePath;
	private JPanel contentPane;
	private ImageIcon selectedImage;
	public Blur blur;
	public Grayscale grayscale;
	public static int count = 0;
	public File f = new File("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\UploadedImages\\brrrrr.jpg");
	public boolean publicPost;
	public boolean privatePost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args, User user) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PostAndEditFrame frame = new PostAndEditFrame(user);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
    public File getUploadedImage() {
    	
        if (profilePicturePath != null) {
            return new File(profilePicturePath);
        }
        return null;
    }

	/**
	 * Create the frame.
	 */
	public PostAndEditFrame(User user) {
        LocalTime currentTime = LocalTime.now();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Get the screen size
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

		// Set the bounds to cover the entire screen
		setBounds(0, 0, screenSize.width, screenSize.height);
		
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton pictureSelectionButton = new JButton("UPLOAD");
		pictureSelectionButton.setBounds(10, 538, 408, 134);
		pictureSelectionButton.setFont(new Font("Gill Sans Nova Ultra Bold", Font.PLAIN, 28));
		contentPane.add(pictureSelectionButton);
		
		
		ImageIcon imageIcon = new ImageIcon("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\upload.jpg");
		Image newimage = imageIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newimage);
		JLabel label = new JLabel(image);
		label.setBounds(341, 34, 593, 493);
		label.setBackground(new Color(240, 240, 240));
		label.setForeground(new Color(255, 128, 128));
		contentPane.add(label);
		
		JButton btnPost = new JButton("POST");
		btnPost.setFont(new Font("Gill Sans Nova Ultra Bold", Font.PLAIN, 28));
		btnPost.setBounds(858, 538, 408, 134);
		contentPane.add(btnPost);
		
		
		// FILTERS 
		
		/**
		 * MULTIPLE BUTTONS FOR BLUR AND SHARPNESS
		 * SLIDERS FOR BRIGHTNESS AND CONTRAST
		 * BUTTONS FOR EDGE DETECTION AND GRAYSCALE
		 */
		JButton btnBlur = new JButton("Blur");
		btnBlur.setForeground(new Color(0, 0, 0));
		btnBlur.setBackground(new Color(255, 255, 255));
		btnBlur.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 30));
		btnBlur.setBounds(38, 34, 250, 74);
		contentPane.add(btnBlur);
			
		JButton btnSharpen = new JButton("Sharpen");
		btnSharpen.setBackground(new Color(255, 255, 255));
		btnSharpen.setForeground(new Color(0, 0, 0));
		btnSharpen.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 30));
		btnSharpen.setBounds(988, 34, 250, 74);
		contentPane.add(btnSharpen);
		
		JButton btnContrast = new JButton("Contrast");
		btnContrast.setBackground(new Color(255, 255, 255));
		btnContrast.setForeground(new Color(0, 0, 0));
		btnContrast.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 30));
		btnContrast.setBounds(49, 207, 229, 74);
		contentPane.add(btnContrast);
		
		JButton btnBrightness = new JButton("Brightness");
		btnBrightness.setForeground(new Color(0, 0, 0));
		btnBrightness.setBackground(new Color(255, 255, 255));
		btnBrightness.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 30));
		btnBrightness.setBounds(988, 207, 229, 74);
		contentPane.add(btnBrightness);
		
		JButton btnGrayscale = new JButton("Grayscale");
		btnGrayscale.setForeground(new Color(0, 0, 0));
		btnGrayscale.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 30));
		btnGrayscale.setBounds(10, 431, 322, 89);
		contentPane.add(btnGrayscale);
		
		JButton btnEdgeDetection = new JButton("Edge Detection");
		btnEdgeDetection.setForeground(new Color(0, 0, 0));
		btnEdgeDetection.setFont(new Font("Copperplate Gothic Bold", Font.BOLD | Font.ITALIC, 30));
		btnEdgeDetection.setBounds(944, 431, 322, 89);
		contentPane.add(btnEdgeDetection);
		
		JSlider contrastSlider = new JSlider();
		contrastSlider.setValue(0);
		contrastSlider.setBackground(new Color(240, 240, 240));
		contrastSlider.setBounds(59, 307, 219, 36);
		contentPane.add(contrastSlider);
		
		JSlider brightnessSlider = new JSlider();
		brightnessSlider.setValue(0);
		brightnessSlider.setBounds(988, 307, 219, 36);
		contentPane.add(brightnessSlider);
		
		JButton blur1Button = new JButton("1");
		blur1Button.setBounds(38, 108, 50, 50);
		blur1Button.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		contentPane.add(blur1Button);
		
		JButton blur2Button = new JButton("2");
		blur2Button.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		blur2Button.setBounds(88, 108, 50, 50);
		contentPane.add(blur2Button);
		
		JButton blur3Button = new JButton("3");
		blur3Button.setBounds(139, 108, 50, 50);
		blur3Button.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		contentPane.add(blur3Button);
		
		JButton blur4Button = new JButton("4");
		blur4Button.setBounds(189, 108, 50, 50);
		blur4Button.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		contentPane.add(blur4Button);
		
		JButton blur5Button = new JButton("5");
		blur5Button.setBounds(238, 108, 50, 50);
		blur5Button.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		contentPane.add(blur5Button);
		
		JButton sharp1Button = new JButton("1");
		sharp1Button.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		sharp1Button.setBounds(988, 108, 50, 50);
		contentPane.add(sharp1Button);
		
		JButton sharp2Button = new JButton("2");
		sharp2Button.setBounds(1037, 108, 50, 50);
		sharp2Button.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		contentPane.add(sharp2Button);
		
		JButton sharp3Button = new JButton("3");
		sharp3Button.setBounds(1088, 108, 50, 50);
		sharp3Button.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		contentPane.add(sharp3Button);
		
		JButton sharp4Button = new JButton("4");
		sharp4Button.setBounds(1138, 108, 50, 50);
		sharp4Button.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		contentPane.add(sharp4Button);
		
		JButton sharp5Button = new JButton("5");
		sharp5Button.setBounds(1188, 108, 50, 50);
		sharp5Button.setFont(new Font("Copperplate Gothic Bold", Font.BOLD, 18));
		contentPane.add(sharp5Button);
		
		JCheckBox chckbxPrivate = new JCheckBox("Private");
		chckbxPrivate.setBounds(660, 600, 99, 23);
		contentPane.add(chckbxPrivate);
		
		JCheckBox chckbxPublic = new JCheckBox("Public");
		chckbxPublic.setBounds(518, 600, 99, 23);
		contentPane.add(chckbxPublic);
		
		
		// INDICATES WHETHER PHOTO NEEDS TO BE PUBLIC OR PRIVATE 
		
		chckbxPublic.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxPublic.isSelected()) {
					publicPost = true;
					chckbxPrivate.setSelected(false);
				}
				else {
					publicPost = false;
				}
				
			}
		});
		
		chckbxPrivate.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if (chckbxPrivate.isSelected()) {
					privatePost = true;
					chckbxPublic.setSelected(false);
				}
				else {
					privatePost = false;
				}
				
			}
		});
		
		// ACTION AND CHANGE LISTENERS
		brightnessSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int percentage = brightnessSlider.getValue();
		        if (profilePicturePath != null && !profilePicturePath.isEmpty()&& user.getPrivileges().contains("brightness")) {
		            try {
		                Brighten bright = new Brighten();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage brightenedImage = bright.brightenImage(imageFile, percentage); // Replace '100' with your desired grayscale percentage

		                ImageIcon brightIcon = new ImageIcon(brightenedImage);
		                Image newImage = brightIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		        else {
		        	JOptionPane.showMessageDialog(PostAndEditFrame.this, "You do not have the privilege to perform this filter", "Error", JOptionPane.ERROR_MESSAGE);
		        }
				
				
			}
		});
		
		contrastSlider.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				int percentage = contrastSlider.getValue();
		        if (profilePicturePath != null && !profilePicturePath.isEmpty()&& user.getPrivileges().contains("contrast")) {
		            try {
		                Contrast contrast = new Contrast();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage contrastedImage = contrast.contrastImage(imageFile, percentage/10); // Replace '100' with your desired grayscale percentage

		                ImageIcon contrastIcon = new ImageIcon(contrastedImage);
		                Image newImage = contrastIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		        else {
		        	JOptionPane.showMessageDialog(PostAndEditFrame.this, "You do not have the privilege to perform this filter", "Error", JOptionPane.ERROR_MESSAGE);
		        }
				
				
			}
		});
		
		blur1Button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
		            try {
		                Blur blur = new Blur();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage blurredImage = blur.blurConvert(imageFile, 3);

		                ImageIcon blurIcon = new ImageIcon(blurredImage);
		                Image newImage = blurIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		blur2Button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
		            try {
		                Blur blur = new Blur();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage blurredImage = blur.blurConvert(imageFile, 5);

		                ImageIcon blurIcon = new ImageIcon(blurredImage);
		                Image newImage = blurIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		blur3Button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
		            try {
		                Blur blur = new Blur();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage blurredImage = blur.blurConvert(imageFile, 7);

		                ImageIcon blurIcon = new ImageIcon(blurredImage);
		                Image newImage = blurIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		blur4Button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
		            try {
		                Blur blur = new Blur();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage blurredImage = blur.blurConvert(imageFile, 9);

		                ImageIcon blurIcon = new ImageIcon(blurredImage);
		                Image newImage = blurIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		blur5Button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Check if an image has been selected
		        if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
		            try {
		                Blur blur = new Blur();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage blurredImage = blur.blurConvert(imageFile, 11);

		                ImageIcon blurIcon = new ImageIcon(blurredImage);
		                Image newImage = blurIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		sharp1Button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Check if an image has been selected
		        if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
		            try {
		                Sharpen sharpen = new Sharpen();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage sharpenedImage = sharpen.sharpenImage(imageFile, 11);

		                ImageIcon sharpenedIcon = new ImageIcon(sharpenedImage);
		                Image newImage = sharpenedIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		sharp2Button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Check if an image has been selected
		        if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
		            try {
		                Sharpen sharpen = new Sharpen();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage sharpenedImage = sharpen.sharpenImage(imageFile, 11);

		                ImageIcon sharpenedIcon = new ImageIcon(sharpenedImage);
		                Image newImage = sharpenedIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		sharp3Button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Check if an image has been selected
		        if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
		            try {
		                Sharpen sharpen = new Sharpen();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage sharpenedImage = sharpen.sharpenImage(imageFile, 11);

		                ImageIcon sharpenedIcon = new ImageIcon(sharpenedImage);
		                Image newImage = sharpenedIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		sharp4Button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Check if an image has been selected
		        if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
		            try {
		                Sharpen sharpen = new Sharpen();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage sharpenedImage = sharpen.sharpenImage(imageFile, 11);

		                ImageIcon sharpenedIcon = new ImageIcon(sharpenedImage);
		                Image newImage = sharpenedIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		sharp5Button.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Check if an image has been selected
		        if (profilePicturePath != null && !profilePicturePath.isEmpty()) {
		            try {
		                Sharpen sharpen = new Sharpen();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage sharpenedImage = sharpen.sharpenImage(imageFile, 11);

		                ImageIcon sharpenedIcon = new ImageIcon(sharpenedImage);
		                Image newImage = sharpenedIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		    }
		});
		
		btnGrayscale.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Check if an image has been selected
		        if (profilePicturePath != null && !profilePicturePath.isEmpty() && user.getPrivileges().contains("grayscale")) {
		            try {
		                Grayscale grayscale = new Grayscale();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage grayscaleImage = grayscale.grayscaleConvert(imageFile, 100); // Replace '100' with your desired grayscale percentage

		                ImageIcon grayscaleIcon = new ImageIcon(grayscaleImage);
		                Image newImage = grayscaleIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		                ex.printStackTrace();
		            }
		        }
		        else {
		        	JOptionPane.showMessageDialog(PostAndEditFrame.this, "You do not have the privilege to perform this filter", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});
		
		btnEdgeDetection.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Check if an image has been selected
		        if (profilePicturePath != null && !profilePicturePath.isEmpty() && user.getPrivileges().contains("edgedetection")) {
		            try {
		                EdgeDetection edgeDetector = new EdgeDetection();

		                File imageFile = new File(profilePicturePath);
		                BufferedImage edgeDetectedImage = edgeDetector.EdgeDetector(imageFile);

		                ImageIcon edgeIcon = new ImageIcon(edgeDetectedImage);
		                Image newImage = edgeIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		                ImageIcon selectedImage = new ImageIcon(newImage);
		                label.setIcon(selectedImage);
		            } catch (IOException ex) {
		            	
		                ex.printStackTrace();
		            }
		        }
		        else {
		        	JOptionPane.showMessageDialog(PostAndEditFrame.this, "You do not have the privilege to perform this filter", "Error", JOptionPane.ERROR_MESSAGE);
		        }
		    }
		});


        
        
		pictureSelectionButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser fileChooser = new JFileChooser();
		        int result = fileChooser.showOpenDialog(PostAndEditFrame.this);
		        if (result == JFileChooser.APPROVE_OPTION) {
		            File selectedFile = fileChooser.getSelectedFile();
		            profilePicturePath = selectedFile.getAbsolutePath();
		            
		            // Update the image label with the selected image
		            ImageIcon imageIcon = new ImageIcon(profilePicturePath);
		            Image newImage = imageIcon.getImage().getScaledInstance(597, 541, Image.SCALE_SMOOTH);
		            PostAndEditFrame.this.selectedImage = new ImageIcon(newImage);
		            label.setIcon(selectedImage);
		            
		         
		        }
		       
		    }
		});
		
		btnPost.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        BufferedImage image = new BufferedImage(label.getWidth(), label.getHeight(), BufferedImage.TYPE_INT_RGB);
		        label.paint(image.getGraphics());

		        try {
		            File folder = new File("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\images");
		            if (!folder.exists()) {
		                folder.mkdirs(); // Create the folder if it doesn't exist
		            }
		            // CHANGE
		            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy_MM_dd_HH_mm_ss");
		            LocalDateTime currentDateTime = LocalDateTime.now();
		            String formattedDateTime = currentDateTime.format(formatter);

		            File outputFile = new File(folder, "uploadedImage" + formattedDateTime + ".jpg");
		            ImageIO.write(image, "jpg", outputFile);

		            try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ImageUploadLog", true))) {
		                // The data to append to the file
		            	if (chckbxPublic.isSelected()) {
			                writer.write(user.getNickname() + " " + outputFile.getAbsolutePath() + "\n");
			                dispose();
		            	}
			            try (BufferedWriter myWriter = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ImageDetails", true))) {
			                // The data to append to the file
			                myWriter.write(user.getNickname() + " " + outputFile.getAbsolutePath() + " 0" + "\n");
			                dispose();
			            } catch (IOException d) {
			                System.out.println("An error occurred while writing the file.");
			                d.printStackTrace();
			            }
			            
			            try (BufferedWriter myWriter = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\postLog", true))) {
			                // The data to append to the file
			                myWriter.write(outputFile.getAbsolutePath() + "\n");
			                dispose();
			                
			            } catch (IOException d) {
			    	        try (BufferedWriter Thewriter = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ErrorLog"))) {
			    	            Thewriter.write(d.getMessage());
			    	            System.out.println("Content written to the file successfully.");
			    	        } catch (IOException e6) {
			    	            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
			    	        }
			                System.out.println("An error occurred while writing the file.");
			                d.printStackTrace();
			            }
			            
		            } catch (IOException d) {
		    	        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ErrorLog"))) {
		    	            writer.write(d.getMessage());
		    	            System.out.println("Content written to the file successfully.");
		    	        } catch (IOException e6) {
		    	            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
		    	        }
		                System.out.println("An error occurred while writing the file.");
		                d.printStackTrace();
		            }
		            
		            
		        } catch (IOException ex) {
			        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ErrorLog"))) {
			            writer.write(ex.getMessage());
			            System.out.println("Content written to the file successfully.");
			        } catch (IOException e6) {
			            System.out.println("An error occurred while writing to the file: " + e6.getMessage());
			        }
		            System.out.println("An error occurred while saving the image.");
		            ex.printStackTrace();
		        }
		        
		        ProfilePageFrame.main(null, user, user);
		    }
		});

	}
	public int LineCounter() {
	   
	        String filePath = "C:\\Users\\omarj\\eclipse-workspace\\Project\\src\\sources\\ImageUploadLog"; // Replace with the actual file path
	        
	        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
	            int lineCount = 0;
	            String line;

	            while ((line = br.readLine()) != null) {
	                lineCount++;
	            }

	            return lineCount;
	        } catch (IOException e) {
	            System.out.println("Error reading the file: " + e.getMessage());
	        
	    }
			return 0;
	}
}
