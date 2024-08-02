package Files;
import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;

public class Password_gendration extends JFrame {

    public Password_gendration() {
        // Set up the frame
        setTitle("Password Generator");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create a panel to hold components
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Label for the title
        JLabel titleLabel = new JLabel("Password Generator");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25)); // Set font and size
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span across the entire width
        gbc.anchor = GridBagConstraints.CENTER; // Align center
        panel.add(titleLabel, gbc);

        // Checkboxes for password generation conditions
        final JCheckBox upperCaseCheckBox = new JCheckBox("Include Upper Case Letters");
        final JCheckBox lowerCaseCheckBox = new JCheckBox("Include Lower Case Letters");
        final JCheckBox numbersCheckBox = new JCheckBox("Include Numbers");
        final JCheckBox specialCharsCheckBox = new JCheckBox("Include Special Characters");

        gbc.gridy++;
        gbc.gridwidth = 1; // Reset grid width
        gbc.anchor = GridBagConstraints.WEST; // Align left
        panel.add(upperCaseCheckBox, gbc);

        gbc.gridy++;
        panel.add(lowerCaseCheckBox, gbc);

        gbc.gridy++;
        panel.add(numbersCheckBox, gbc);

        gbc.gridy++;
        panel.add(specialCharsCheckBox, gbc);

        // Label to display the generated password
        JLabel passwordLabel = new JLabel("Generated Password:");
        gbc.gridy++;
        panel.add(passwordLabel, gbc);

        // Text field to show the generated password
        final JTextField passwordField = new JTextField(20);
        passwordField.setEditable(false);
        gbc.gridx = 1;
        panel.add(passwordField, gbc);

        // Button to generate the password
        JButton generateButton = new JButton("Generate Password");
        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 0, 0); // Add top margin
        panel.add(generateButton, gbc);

        // Button to copy the password to clipboard
        JButton copyButton = new JButton("Copy Password");
        gbc.gridy++;
        gbc.anchor = GridBagConstraints.CENTER; // Align center
        panel.add(copyButton, gbc);

        // Add action listener to the generate button
        generateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Generate a random password based on selected conditions
                String password = generatePassword(12, upperCaseCheckBox.isSelected(), lowerCaseCheckBox.isSelected(), numbersCheckBox.isSelected(), specialCharsCheckBox.isSelected());
                // Set the password in the text field
                passwordField.setText(password);
            }
        });

        // Add action listener to the copy button
        copyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Copy the password to the clipboard
                String password = passwordField.getText();
                StringSelection selection = new StringSelection(password);
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(selection, null);
            }
        });

        // Add panel to the frame
        add(panel);
    }

    // Method to generate a random password
    private String generatePassword(int length, boolean includeUpperCase, boolean includeLowerCase, boolean includeNumbers, boolean includeSpecialChars) {
        String upperCaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowerCaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numbers = "0123456789";
        String specialCharacters = "!@#$%^&*()-_+=<>?";
        StringBuilder combinedChars = new StringBuilder();

        if (includeUpperCase) combinedChars.append(upperCaseLetters);
        if (includeLowerCase) combinedChars.append(lowerCaseLetters);
        if (includeNumbers) combinedChars.append(numbers);
        if (includeSpecialChars) combinedChars.append(specialCharacters);

        if (combinedChars.length() == 0) {
            return "Select at least one condition!";
        }

        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(combinedChars.length());
            password.append(combinedChars.charAt(index));
        }

        return password.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Password_gendration().setVisible(true);
            }
        });
    }
}
