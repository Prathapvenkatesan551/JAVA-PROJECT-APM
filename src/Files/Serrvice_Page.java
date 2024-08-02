package Files;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Serrvice_Page {

    public void service_page() {
        JFrame frame = new JFrame("Password Manager Services");
        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JTextArea infoTextArea = new JTextArea();
        infoTextArea.setEditable(false);
        infoTextArea.setLineWrap(true);
        infoTextArea.setWrapStyleWord(true);

        try {
            // Read the text from a file and display it in the text area
            BufferedReader reader = new BufferedReader(new FileReader("D:\\Mini_project_files\\Services/service_info.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                infoTextArea.append(line + "\n");
            }
            reader.close();
        } catch (IOException ex) {
            ex.printStackTrace();
            infoTextArea.setText("Error loading service information.");
        }

        JScrollPane scrollPane = new JScrollPane(infoTextArea);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new Serrvice_Page().service_page();
            }
        });
    }
}
