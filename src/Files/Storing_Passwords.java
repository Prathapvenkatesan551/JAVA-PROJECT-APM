package Files;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Storing_Passwords extends JFrame {
    private JTextField fieldInput;
    private JPasswordField passwordInput;
    private JButton submitButton;

    public Storing_Passwords() {
        createUI();
    }

    private void createUI() {
        setTitle("Store Passwords");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create input fields
        fieldInput = new JTextField(20);
        passwordInput = new JPasswordField(20);

        // Create the submit button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(new SubmitButtonListener());

        // Create a panel to hold components
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 10, 10); // Add gaps between components

        // Add components to the panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Field:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        panel.add(fieldInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        panel.add(passwordInput, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(submitButton, gbc);

        // Add the panel to the frame
        add(panel);

        setVisible(true);
    }

    private class SubmitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String field = fieldInput.getText();
            String password = new String(passwordInput.getPassword());

            if (field.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in both fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            saveToFile(field, password);
        }

        private void saveToFile(String field, String password) {
            String directoryPath = "D:\\Mini_project_files\\Storing_Passwords";
            File directory = new File(directoryPath);

            if (!directory.exists()) {
                directory.mkdirs();
            }

            FileWriter fileWriter = null;
            BufferedWriter writer = null;
            try {
                File file = new File(directoryPath + "\\passwords.txt");
                fileWriter = new FileWriter(file, true);
                writer = new BufferedWriter(fileWriter);
                writer.write("Field: " + field + ", Password: " + password);
                writer.newLine();
                JOptionPane.showMessageDialog(null, "Data saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error saving data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                    if (fileWriter != null) {
                        fileWriter.close();
                    }
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Error closing file writer: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Storing_Passwords();
            }
        });
    }
}
