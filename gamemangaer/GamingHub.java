

package game_management_system;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.lang.reflect.*;

// Main class for the Gaming Hub
public class GamingHub {
    private JFrame frame;
    private JPanel gridPanel;
    private Map<String, String> gamesMap;

    public GamingHub() {
        // Initialize the map with game names and their corresponding class paths
        gamesMap = new HashMap<>();
        gamesMap.put("Snake", "snakegame.SnakeGame");
        gamesMap.put("Tic Tac Toe", "tictactoe.TicTacToe");
        gamesMap.put("Memory Card", "memorygame.MemoryCardGame");

        // Set up the frame
        frame = new JFrame("Gaming Hub");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Create a grid panel to hold game labels (thumbnails)
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(2, 2, 10, 10)); // Adjust grid as per number of games

        // Add game labels (thumbnails) to the grid panel
        for (String gameName : gamesMap.keySet()) {
            JLabel gameLabel = createGameLabel(gameName);
            gridPanel.add(gameLabel);
        }

        // Add grid panel to the frame
        frame.add(gridPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    // Create a JLabel for each game, acting as the thumbnail
    private JLabel createGameLabel(String gameName) {
        JLabel label = new JLabel(gameName, SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setOpaque(true);
        label.setBackground(Color.LIGHT_GRAY); // Placeholder background color
        label.setPreferredSize(new Dimension(150, 150));
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Add click listener to load the game on click
        label.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                launchGame(gamesMap.get(gameName));
            }
        });
        return label;
    }

    // Method to dynamically load and launch the game
    private void launchGame(String className) {
        try {
            // Dynamically load the game class
            Class<?> gameClass = Class.forName(className);
            // Instantiate the game class using reflection
            Constructor<?> constructor = gameClass.getDeclaredConstructor();
            constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Failed to launch game: " + className);
        }
    }

    public static void main(String[] args) {
        // Launch the Gaming Hub
        SwingUtilities.invokeLater(GamingHub::new);
    }
}
