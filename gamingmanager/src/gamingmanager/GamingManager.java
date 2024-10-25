
package gamingmanager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GamingManager extends JFrame {
    private JPanel mainPanel;
    private List<GamePackage> gamePackages;

    public GamingManager() {
        setTitle("Gaming Manager");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 3)); // 3 columns
        add(mainPanel, BorderLayout.CENTER);

        gamePackages = new ArrayList<>();
        loadGamePackages();
        refreshGamePanels();
    }

    private void loadGamePackages() {
        // Add the Snake game package with the correct thumbnail path
        gamePackages.add(new GamePackage("Snake", "snake_game.SnakeGame", "path/to/snake_thumbnail.png"));
    }

    private void refreshGamePanels() {
        mainPanel.removeAll();
        for (GamePackage game : gamePackages) {
            JButton gameButton = new JButton(new ImageIcon(game.getThumbnail()));
            gameButton.setText(game.getName());
            gameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    loadAndStartGame(game);
                }
            });
            mainPanel.add(gameButton);
        }
        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private void loadAndStartGame(GamePackage game) {
        try {
            Class<?> gameClass = Class.forName(game.getPath());
            gameClass.getMethod("start").invoke(gameClass.getDeclaredConstructor().newInstance());
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to start game: " + game.getName() + "\nError: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GamingManager manager = new GamingManager();
            manager.setVisible(true);
        });
    }

    class GamePackage {
        private String name;
        private String path;
        private String thumbnail;

        public GamePackage(String name, String path, String thumbnail) {
            this.name = name;
            this.path = path;
            this.thumbnail = thumbnail;
        }

        public String getName() {
            return name;
        }

        public String getPath() {
            return path;
        }

        public String getThumbnail() {
            return thumbnail;
        }
    }
}
