package Files;
import javax.swing.*;
import java.awt.*;

public class About_us {

    public static void About() {
        JFrame frame = new JFrame("About Us");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Vertical BoxLayout

        // Labels for important details
        JLabel titleLabel = new JLabel("About Us");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center align

        JLabel collegeLabel = new JLabel("Institution: Erode Sengunthar Engineering College");
        JLabel developersLabel = new JLabel("Developers: PRATHAP, SARANATH RAHARAM, VENKATRAGHAVAN");
        JLabel developerDegree = new JLabel("DEGREE:  BE-COMPUTER SCIENCE AND ENGINEERING");
        JLabel locationLabel = new JLabel("Location: Perundurai, India");

        // Add labels to the panel
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical spacing
        panel.add(titleLabel);
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Add vertical spacing
        panel.add(collegeLabel);
        panel.add(developersLabel);
        panel.add(developerDegree);
        panel.add(locationLabel);

        // Add the panel to the frame
        frame.add(panel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                About();
            }
        });
    }
}
