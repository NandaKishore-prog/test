package snake_game;

import javax.swing.*;

public class SnakeGame {
    public void start() {
        JOptionPane.showMessageDialog(null, "Starting Snake Game!");
        GameFrame gameFrame = new GameFrame();
        gameFrame.setVisible(true); // Show the game frame
    }
}
