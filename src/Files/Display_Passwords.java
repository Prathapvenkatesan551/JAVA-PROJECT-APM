package Files;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Display_Passwords extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField serialNumberField;
    private JButton decryptButton;
    private JButton updateButton;
    private JButton deleteButton;
    private List passwords;

    public Display_Passwords() {
        createUI();
        loadFile();
    }

    private void createUI() {
        setTitle("Display Passwords");
        setSize(700, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        tableModel = new DefaultTableModel(new Object[]{"Serial No.", "Field", "Encrypted Password"}, 0);
        table = new JTable(tableModel);
        add(new JScrollPane(table), BorderLayout.CENTER);

        JPanel controlPanel = new JPanel(new FlowLayout());
        serialNumberField = new JTextField(10);
        decryptButton = new JButton("Decrypt");
        decryptButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                decryptPassword();
            }
        });
        updateButton = new JButton("Update");
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                updatePassword();
            }
        });
        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                deletePassword();
            }
        });

        controlPanel.add(new JLabel("Enter Serial No.: "));
        controlPanel.add(serialNumberField);
        controlPanel.add(decryptButton);
        controlPanel.add(updateButton);
        controlPanel.add(deleteButton);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void loadFile() {
        String filePath = "D:\\Mini_project_files\\Storing_Passwords\\passwords.txt";
        passwords = new ArrayList(); // Explicit type parameter for compatibility with Java 6

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", Password: ");
                if (parts.length == 2) {
                    passwords.add(parts[1]); // Store the password in the class variable
                    tableModel.addRow(new Object[]{Integer.toString(passwords.size()), parts[0].replace("Field: ", ""), encryptPassword(parts[1])});
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private String encryptPassword(String password) {
        StringBuilder encrypted = new StringBuilder();
        for (int i = 0; i < password.length(); i++) {
            encrypted.append("*");
        }
        return encrypted.toString();
    }

    private void decryptPassword() {
        try {
            int serialNumber = Integer.parseInt(serialNumberField.getText());
            if (serialNumber >= 1 && serialNumber <= passwords.size()) {
                String decryptedPassword = (String) passwords.get(serialNumber - 1);
                JOptionPane.showMessageDialog(this, "Original Password: " + decryptedPassword, "Decrypted Password", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid serial number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid serial number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updatePassword() {
        try {
            int serialNumber = Integer.parseInt(serialNumberField.getText());
            if (serialNumber >= 1 && serialNumber <= passwords.size()) {
                String newPassword = JOptionPane.showInputDialog(this, "Enter new password:");
                if (newPassword != null && !newPassword.trim().isEmpty()) {
                    passwords.set(serialNumber - 1, newPassword);
                    tableModel.setValueAt(encryptPassword(newPassword), serialNumber - 1, 2);
                    JOptionPane.showMessageDialog(this, "Password updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Password cannot be empty", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Invalid serial number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid serial number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletePassword() {
        try {
            int serialNumber = Integer.parseInt(serialNumberField.getText());
            if (serialNumber >= 1 && serialNumber <= passwords.size()) {
                passwords.remove(serialNumber - 1);
                tableModel.removeRow(serialNumber - 1);

                // Update serial numbers
                for (int i = serialNumber - 1; i < tableModel.getRowCount(); i++) {
                    tableModel.setValueAt(Integer.toString(i + 1), i, 0);
                }

                JOptionPane.showMessageDialog(this, "Password deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Invalid serial number", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid serial number", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void showEntries() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Display_Passwords();
            }
        });
    }

    public static void main(String[] args) {
        showEntries();
    }
}
