package montyandflower;

import javax.swing.*;
import main.Main1;
import java.awt.*;
import java.util.Random;

public class WhacAMole {
    int boardWidth = 600;
    int boardHeight = 650; // 50 for the text panel on top

    JFrame frame = new JFrame("Mario: Whac A Mole");
    JLabel textLabel = new JLabel();
    JPanel textPanel = new JPanel();
    JPanel boardPanel = new JPanel();
    JPanel buttonPanel = new JPanel(); // Panel for buttons

    JButton[] board = new JButton[9];
    ImageIcon moleIcon;
    ImageIcon plantIcon;
    ImageIcon hammerIcon; // Image for hitting the mole

    JButton currMoleTile;
    JButton currPlantTile;

    Random random = new Random();
    Timer setMoleTimer;
    int score = 0;

    boolean isMoleActive = false; // Flag to track mole state

    // Constructor for the game
    public WhacAMole() {
        initializeGame();
    }

    // Initialize the game components
    private void initializeGame() {
        frame.setSize(boardWidth, boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        textLabel.setFont(new Font("Arial", Font.PLAIN, 50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Score: " + score);
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel, BorderLayout.NORTH);

        boardPanel.setLayout(new GridLayout(3, 3));
        frame.add(boardPanel);

        // Panel for buttons
        buttonPanel.setLayout(new FlowLayout());
        JButton resetButton = new JButton("Reset");
        JButton exitButton = new JButton("Exit");
        exitButton.setFocusable(false);
        resetButton.setFocusable(false);

        resetButton.addActionListener(e -> resetGame());
        exitButton.addActionListener(e -> {
            frame.setVisible(false);
            new Main1();
        }); // Exit the game

        buttonPanel.add(resetButton);
        buttonPanel.add(exitButton);
        frame.add(buttonPanel, BorderLayout.SOUTH); // Add button panel to the bottom

        // Load images
        loadImages();

        for (int i = 0; i < 9; i++) {
            JButton tile = new JButton();
            board[i] = tile;
            boardPanel.add(tile);
            tile.setFocusable(false);

            tile.addActionListener(e -> handleTileClick(tile));
        }

        setMoleTimer = new Timer(1000, e -> setMoleAndMovePlant()); // Mole and plant move together every 1.5 sec
        setMoleTimer.start();

        frame.setVisible(true);
    }

    // Handle tile click events
    private void handleTileClick(JButton tile) {
        if (tile == currMoleTile) {
            score += 10;
            textLabel.setText("Score: " + score);

            // Show hammer image when mole is hit
            tile.setIcon(hammerIcon); // Set hammer icon
            Timer resetIconTimer = new Timer(100, e -> {
                tile.setIcon(null); // Reset to no icon
                isMoleActive = false; // Reset mole active state
                setMoleAndMovePlant(); // Set new mole and plant
            });
            resetIconTimer.setRepeats(false); // Only run once
            resetIconTimer.start();
        } else if (tile == currPlantTile) {
            textLabel.setText("Game Over: " + score);
            setMoleTimer.stop();
            for (JButton button : board) {
                button.setEnabled(false);
            }
        }
    }

    // Load game images
    private void loadImages() {
        String plantImagePath = "C:\\Users\\MUGILAN\\Downloads\\image\\piranha - Copy.png"; // Adjust if needed
        String moleImagePath = "C:\\Users\\MUGILAN\\Downloads\\image\\monty - Copy.png"; // Adjust if needed
        String hammerImagePath = "C:\\Users\\MUGILAN\\Downloads\\image\\mario.jpg"; // Path for the hammer image

        try {
            plantIcon = new ImageIcon(new ImageIcon(plantImagePath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            moleIcon = new ImageIcon(new ImageIcon(moleImagePath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
            hammerIcon = new ImageIcon(new ImageIcon(hammerImagePath).getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH)); // Resize hammer icon
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Error loading images: " + e.getMessage());
        }
    }

    // Set mole and move plant on the board
    private void setMoleAndMovePlant() {
        // Move the mole
        if (currMoleTile != null) {
            currMoleTile.setIcon(null); // Clear the previous mole
        }

        int moleIndex;
        do {
            moleIndex = random.nextInt(9); // Generate a random number between 0 and 8
        } while (currPlantTile == board[moleIndex]); // Ensure the mole does not appear on the plant tile

        currMoleTile = board[moleIndex];
        currMoleTile.setIcon(moleIcon);
        isMoleActive = true; // Set the mole as active

        // Move the plant immediately after mole is set
        setPlant();
    }

    // Set plant on the board
    private void setPlant() {
        if (currPlantTile != null) {
            currPlantTile.setIcon(null); // Clear the previous plant
        }

        int plantIndex;
        do {
            plantIndex = random.nextInt(9); // Generate a random number between 0 and 8
        } while (currMoleTile == board[plantIndex]); // Ensure the plant does not appear on the mole tile

        currPlantTile = board[plantIndex];
        currPlantTile.setIcon(plantIcon);
    }

    // Reset the game state
    private void resetGame() {
        score = 0;
        textLabel.setText("Score: " + score);
        isMoleActive = false; // Reset mole active state
        currMoleTile = null;
        currPlantTile = null;

        // Clear the board
        for (JButton button : board) {
            button.setIcon(null);
            button.setEnabled(true); // Enable all tiles
        }

        setMoleTimer.start();
    }

    // Main method to start the game
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginPanel("Welcome to Whac-A-Mole!", "Start Game", "C:\\Users\\MUGILAN\\Downloads\\image\\whac a mole.jpg")); // Adjust image path
    }
}
