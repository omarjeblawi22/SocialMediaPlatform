package main;

import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import users.User;

public class DiscoverPage extends JFrame {
	
    private JPanel contentPane;
    private String[] paths;

    public static void main(String[] args, User viewingUser) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    DiscoverPage frame = new DiscoverPage(viewingUser);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
/**
 * 
 * @param viewinguser
 */
    public DiscoverPage(User viewinguser) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        paths = new String[Main.paths.size()];
        for (int i = 0; i < Main.paths.size(); i++) {
            paths[i] = Main.paths.get(i);
        }
        
        // SETS UP THE GRIDLAYOUT TO COVER THE ENTIRE PAGE AND SHOWCASE ALL PUBLICLY POSTED PHOTOS
        int numRows = (int) Math.ceil((double) paths.length / 3);
        contentPane.setLayout(new GridLayout(numRows, 3, 10, 10));

        for (String path : paths) {
            ImageIcon imageIcon = new ImageIcon(path);
            JLabel label = new JLabel(imageIcon);
            label.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    PictureFrame.main(null, path, viewinguser);
                    dispose();
                }
            });
            contentPane.add(label);
        }

        JScrollPane scrollPane = new JScrollPane(contentPane);
        setContentPane(scrollPane);
    }
}

