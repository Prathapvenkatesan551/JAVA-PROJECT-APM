package Files;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

public class Password_health_testing extends JFrame {

    // Constructor to set up the JFrame
    public Password_health_testing() {
        // Set up the frame
        setTitle("Password Health Testing");
        setSize(500, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Label for password input
        JLabel inputLabel = new JLabel("Enter Password:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(inputLabel, gbc);

        // Text field for password input
        final JTextField passwordField = new JTextField(20);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Button to check password strength
        JButton checkButton = new JButton("Check Strength");
        gbc.gridx = 2;
        gbc.insets = new Insets(0, 10, 0, 0); // Add gap between button and text field
        panel.add(checkButton, gbc);

        // Label to display the result
        final JLabel resultLabel = new JLabel("Password Strength: ");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(resultLabel, gbc);

        // Label for warning message
        final JLabel warningLabel = new JLabel();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        panel.add(warningLabel, gbc);

        // Add action listener to the button
        checkButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Check the password strength
                String password = passwordField.getText();
                String strength = checkPasswordStrength(password);
                // Display the result with different colors
                if (strength.equals("Very Weak")) {
                    warningLabel.setText("Too weak password!");
                    JOptionPane.showMessageDialog(null, "Password is too weak!", "Warning", JOptionPane.WARNING_MESSAGE);
                    resultLabel.setForeground(Color.RED);
                } else if (strength.equals("Weak")) {
                    warningLabel.setText("");
                    resultLabel.setForeground(Color.ORANGE);
                } else if (strength.equals("Moderate")) {
                    warningLabel.setText("");
                    resultLabel.setForeground(Color.YELLOW);
                } else if (strength.equals("Strong")) {
                    warningLabel.setText("");
                    resultLabel.setForeground(Color.GREEN);
                } else {
                    warningLabel.setText("");
                    resultLabel.setForeground(Color.BLUE);
                }
                resultLabel.setText("Password Strength: " + strength);
            }
        });

        // Add panel to the frame
        add(panel);
    }

    // Method to check password strength using multiple algorithms
    private String checkPasswordStrength(String password) {
        int score = 0;
        int length = password.length();

        // Algorithm 1: Length check
        if (length >= 8) score++;
        if (length >= 12) score++;

        // Algorithm 2: Upper case letter check
        if (Pattern.compile("[A-Z]").matcher(password).find()) score++;

        // Algorithm 3: Lower case letter check
        if (Pattern.compile("[a-z]").matcher(password).find()) score++;

        // Algorithm 4: Digit check
        if (Pattern.compile("[0-9]").matcher(password).find()) score++;

        // Algorithm 5: Special character check
        if (Pattern.compile("[!@#$%^&*()-_+=<>?]").matcher(password).find()) score++;

        // Determine strength based on score
        if (score >= 5) {
            return "Very Strong";
        } else if (score >= 4) {
            return "Strong";
        } else if (score >= 3) {
            return "Moderate";
        } else if (score >= 2) {
            return "Weak";
        } else {
            return "Very Weak";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Password_health_testing().setVisible(true);
            }
        });
    }
}
