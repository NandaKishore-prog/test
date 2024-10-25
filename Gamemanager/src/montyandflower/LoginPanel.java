

package montyandflower;

import javax.swing.*;
import java.awt.*;

public class LoginPanel {
    private JFrame frame;
    private BackgroundPanel panel;
    private JLabel welcomeLabel;
    private JButton startButton;

    public LoginPanel(String welcomeMessage, String buttonText, String backgroundImagePath) {
        frame = new JFrame("Welcome to the Game");
        panel = new BackgroundPanel(backgroundImagePath);
        welcomeLabel = new JLabel(welcomeMessage);
        startButton = new JButton(buttonText);

        // Customize the look and feel
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 28)); // Increased font size for better visibility
        welcomeLabel.setHorizontalAlignment(JLabel.CENTER); // Center align the text
        startButton.setFont(new Font("Arial", Font.PLAIN, 18)); // Set font for the button
        startButton.setPreferredSize(new Dimension(150, 40)); // Set preferred size for the start button
        startButton.setBackground(Color.BLUE); // Set background color for the button
        startButton.setForeground(Color.WHITE); // Set text color for the button
        startButton.setFocusPainted(false); // Remove focus border on button

        panel.setLayout(new GridBagLayout()); // Use GridBagLayout for better control
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Constraints for the welcome label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 20, 10, 20); // Set margin around the label
        panel.add(welcomeLabel, gbc);

        // Constraints for the start button
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(startButton, gbc);

        // Set up action for the start button
        startButton.addActionListener(e -> {
            frame.dispose(); // Close login screen
            new WhacAMole(); // Start the game
        });

        frame.add(panel);
        frame.setSize(400, 300); // Set the frame size
        frame.setLocationRelativeTo(null); // Center the frame on the screen
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    // Inner class for the background panel
    private class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            try {
                backgroundImage = new ImageIcon(imagePath).getImage();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
