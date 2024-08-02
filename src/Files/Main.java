package Files;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Main extends JFrame implements ActionListener {

    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;

    public Main() {
        // Set up the main frame
        setTitle("Application");
        setSize(1200, 800); // Initial size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set application icon
        setAppIcon("D:\\Mini_project_files\\App_Icons\\padlock.png");

        // Create a label, set its alignment, and increase its font size
        JLabel label = new JLabel("Password Management System", SwingConstants.CENTER);
        label.setFont(new Font("Serif", Font.BOLD, 32)); // Adjusted font size to fit better

        // Create buttons
        button1 = new JButton("Password Generator");
        button2 = new JButton("Password Checker");
        button3 = new JButton("Storing Password");
        button4 = new JButton("Personal Data");

        // Add action listeners to buttons
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);

        // Set layout manager
        setLayout(new BorderLayout());

        // Add label to the top
        add(label, BorderLayout.NORTH);

        // Create a panel for buttons and set its layout
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // Add buttons to the panel
        buttonPanel.add(button1);
        buttonPanel.add(Box.createRigidArea(new Dimension(50, 80)));
        buttonPanel.add(button2);
        buttonPanel.add(Box.createRigidArea(new Dimension(50, 80)));
        buttonPanel.add(button3);
        buttonPanel.add(Box.createRigidArea(new Dimension(50, 80)));
        buttonPanel.add(button4);

        // Add the button panel to the center of the frame
        add(buttonPanel, BorderLayout.CENTER);

        // Load and scale images
        JLabel leftImage = new JLabel(loadAndScaleImage("D:\\Mini_project_files\\App_Icons\\key.png", 0.7));
        JLabel rightImage = new JLabel(loadAndScaleImage("D:\\Mini_project_files\\App_Icons\\Menu_icon.png", 0.7));

        // Add images to the left and right
        add(leftImage, BorderLayout.WEST);
        add(rightImage, BorderLayout.EAST);

        // Add a component listener to handle resize events
        addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent e) {
                adjustComponentSizes();
            }
        });

        // Initial adjustment of component sizes
        adjustComponentSizes();

        // Create and set up the menu bar
        JMenuBar menuBar = new JMenuBar();
        
        // Add logo to the menu bar
        JLabel logoLabel = new JLabel(loadAndScaleImage("D:\\Mini_project_files\\App_Icons\\padlock.png", 0.1));
        menuBar.add(logoLabel);

        JMenu homeMenu = new JMenu("Home");
        JMenu servicesMenu = new JMenu("Services");
        JMenu aboutUsMenu = new JMenu("About Us");

        // Add menu items
        JMenuItem currentPageItem = new JMenuItem("Current Page");
        JMenuItem servicesItem = new JMenuItem("Services");
        JMenuItem aboutUsItem = new JMenuItem("About Us");

        // Add action listeners to menu items
        currentPageItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigateToCurrentPage();
            }
        });
        servicesItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigateToServices();
            }
        });
        aboutUsItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                navigateToAboutUs();
            }
        });

        // Add items to menus
        homeMenu.add(currentPageItem);
        servicesMenu.add(servicesItem);
        aboutUsMenu.add(aboutUsItem);

        // Add menus to the menu bar
        menuBar.add(homeMenu);
        menuBar.add(servicesMenu);
        menuBar.add(aboutUsMenu);

        // Set the menu bar
        setJMenuBar(menuBar);
    }

    private ImageIcon loadAndScaleImage(String path, double scale) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(path));
            int newWidth = (int) (originalImage.getWidth() * scale);
            int newHeight = (int) (originalImage.getHeight() * scale);
            Image scaledImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
            return new ImageIcon(scaledImage);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void setAppIcon(String path) {
        try {
            BufferedImage appIcon = ImageIO.read(new File(path));
            setIconImage(appIcon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void adjustComponentSizes() {
        int width = getWidth();
        int height = getHeight();

        // Adjust font size based on window size
        int fontSize = Math.min(width / 20, height / 20);
        ((JLabel)getContentPane().getComponent(0)).setFont(new Font("Serif", Font.BOLD, fontSize));

        // Adjust button sizes based on window size
        Dimension buttonSize = new Dimension(Math.min(400, width - 200), Math.min(80, height / 10));
        button1.setMaximumSize(buttonSize);
        button2.setMaximumSize(buttonSize);
        button3.setMaximumSize(buttonSize);
        button4.setMaximumSize(buttonSize);

        // Revalidate and repaint to apply changes
        revalidate();
        repaint();
    }

    public void actionPerformed(ActionEvent e) {
        // Determine which button was clicked and open a new frame
        if (e.getSource() == button1) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new Password_gendration().setVisible(true);
                }
            });
        } else if (e.getSource() == button2) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new Password_health_testing().setVisible(true);
                }
            });
        } else if (e.getSource() == button3) {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    new Storing_Passwords().setVisible(true);
                }
            });
        } else if (e.getSource() == button4) {
            Display_Passwords.showEntries();
        }
    }

    private void navigateToCurrentPage() {
        // Logic to navigate to the current page
        JOptionPane.showMessageDialog(this, "You are already on the current page.");
    }

    private void navigateToServices() {
        // Logic to navigate to the services page
//        JOptionPane.showMessageDialog(this, "Navigating to Services page...");
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Serrvice_Page().service_page();
            }
        });
    }

    private void navigateToAboutUs() {
        // Logic to navigate to the about us page
    	SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                About_us.About();
            }
        });
    }

    public static void main(String[] args) {
        // Create and display the main frame
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Main().setVisible(true);
            }
        });
    }
}
