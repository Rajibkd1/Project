import javax.swing.*;
import java.awt.*;

public class NumberPlateDetectionSystemGUI extends JFrame {
    private JButton browseButton;
    private JButton showButton;
    private JButton detectButton;

    public NumberPlateDetectionSystemGUI() {
        try {
            // Set Substance Gemini Look and Feel
            UIManager.setLookAndFeel(new SubstanceGeminiLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setTitle("The Number Plate Detection System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create panel to hold the buttons
        JPanel buttonPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        buttonPanel.setOpaque(false); // Make the panel transparent

        // Create browse button
        browseButton = new JButton("Browse");
        browseButton.setForeground(Color.WHITE); // Set text color
        browseButton.setBackground(new Color(63, 127, 191)); // Set background color
        browseButton.setFocusPainted(false); // Remove focus border

        // Create show button
        showButton = new JButton("Show");
        showButton.setForeground(Color.WHITE); // Set text color
        showButton.setBackground(new Color(127, 191, 63)); // Set background color
        showButton.setFocusPainted(false); // Remove focus border

        // Create detect button
        detectButton = new JButton("Detect");
        detectButton.setForeground(Color.WHITE); // Set text color
        detectButton.setBackground(new Color(191, 63, 127)); // Set background color
        detectButton.setFocusPainted(false); // Remove focus border

        // Add buttons to the panel
        buttonPanel.add(browseButton);
        buttonPanel.add(showButton);
        buttonPanel.add(detectButton);

        // Set panel as the content pane
        setContentPane(buttonPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new NumberPlateDetectionSystemGUI();
            }
        });
    }
}
