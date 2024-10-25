package Tic_Tat_Toe;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class TicTacToe implements ActionListener {

    JFrame frame = new JFrame();
    JPanel buttonPanel = new JPanel();
    JLabel textfield = new JLabel();
    JButton[] buttons = new JButton[9];
    boolean player1_turn;
    String player1_name;
    String player2_name;
    String player1_symbol;
    String player2_symbol;
    BufferedImage backgroundImage;
    JButton restartButton, exitButton;

    public TicTacToe() {
        // Load background image
        try {
            backgroundImage = ImageIO.read(new File("C:\\Users\\MUGILAN\\Downloads\\tic-tac-toe-scaled.jpg")); // Update this path
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Setting up the main frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.setLayout(new BorderLayout());

        // Create main panel for CardLayout
        JPanel mainPanel = new JPanel(new CardLayout());
        frame.add(mainPanel, BorderLayout.CENTER);

        // Show login screen
        showLogin(mainPanel);
        frame.setVisible(true);
    }

    public void showLogin(JPanel mainPanel) {
        // Create a login panel with a background image
        BackgroundPanel backgroundPanel = new BackgroundPanel(backgroundImage);
        backgroundPanel.setLayout(new GridBagLayout());

        // Add input fields for player names
        JTextField player1NameField = new JTextField(10);
        JTextField player2NameField = new JTextField(10);

        JComboBox<String> player1SymbolCombo = new JComboBox<>(new String[]{"X", "O"});

        JButton startButton = new JButton("Start Game");

        // Add action listener to start button
        startButton.addActionListener(e -> {
            player1_name = player1NameField.getText();
            player2_name = player2NameField.getText();
            player1_symbol = (String) player1SymbolCombo.getSelectedItem();
            player2_symbol = player1_symbol.equals("X") ? "O" : "X";

            if (player1_name.isEmpty() || player2_name.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Please enter both player names.");
            } else {
                // Switch to the game screen
                mainPanel.removeAll(); // Remove login panel
                initializeGame(mainPanel); // Initialize game panel
                mainPanel.revalidate(); // Refresh the main panel
                mainPanel.repaint();
            }
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        JLabel player1NameLabel = new JLabel("Player 1 Name: ");
        player1NameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        player1NameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        backgroundPanel.add(player1NameLabel, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(player1NameField, gbc);

        JLabel player1SymbolLabel = new JLabel("Player 1 Symbol: ");
        player1SymbolLabel.setFont(new Font("Arial", Font.BOLD, 16));
        player1SymbolLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 1;
        backgroundPanel.add(player1SymbolLabel, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(player1SymbolCombo, gbc);

        JLabel player2NameLabel = new JLabel("Player 2 Name: ");
        player2NameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        player2NameLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        backgroundPanel.add(player2NameLabel, gbc);

        gbc.gridx = 1;
        backgroundPanel.add(player2NameField, gbc);

        startButton.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 1;
        gbc.gridy = 3;
        backgroundPanel.add(startButton, gbc);

        mainPanel.add(backgroundPanel, "loginPanel");
    }

    public void initializeGame(JPanel mainPanel) {
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.setPreferredSize(new Dimension(800, 100));
        titlePanel.setBackground(new Color(0, 0, 0, 150));

        textfield.setBackground(new Color(50, 50, 50, 150));
        textfield.setForeground(new Color(25, 255, 0));
        textfield.setFont(new Font("Ink Free", Font.BOLD, 40));
        textfield.setHorizontalAlignment(JLabel.CENTER);
        textfield.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        textfield.setOpaque(true);
        textfield.setBorder(BorderFactory.createLineBorder(new Color(0, 255, 0), 2, true));
        titlePanel.add(textfield, BorderLayout.CENTER);

        JLabel welcomeLabel = new JLabel("Welcome to Tic Tac Toe!", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 30));
        welcomeLabel.setForeground(Color.WHITE);
        titlePanel.add(welcomeLabel, BorderLayout.SOUTH);

        buttonPanel.setLayout(new GridLayout(3, 3, 10, 10));
        buttonPanel.setBackground(Color.BLACK);

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttonPanel.add(buttons[i]);
            buttons[i].setFont(new Font("MV Boli", Font.BOLD, 120));
            buttons[i].setFocusable(false);
            buttons[i].setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
            buttons[i].addActionListener(this);
        }

        JPanel gamePanel = new JPanel(new BorderLayout());
        gamePanel.setBackground(Color.BLACK);
        gamePanel.add(titlePanel, BorderLayout.NORTH);
        gamePanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.BLACK);

        restartButton = new JButton("Restart");
        restartButton.addActionListener(e -> resetGame());

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));

        bottomPanel.add(restartButton);
        bottomPanel.add(exitButton);
        gamePanel.add(bottomPanel, BorderLayout.SOUTH);

        mainPanel.add(gamePanel, "gamePanel");

        resetGame();

        CardLayout cl = (CardLayout) (mainPanel.getLayout());
        cl.show(mainPanel, "gamePanel");
    }

    public void resetGame() {
        for (JButton button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
        player1_turn = true;
        textfield.setText(player1_name + "'s turn");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (int i = 0; i < 9; i++) {
            if (e.getSource() == buttons[i]) {
                if (buttons[i].getText().equals("")) {
                    if (player1_turn) {
                        buttons[i].setForeground(new Color(255, 0, 0));
                        buttons[i].setText(player1_symbol);
                        player1_turn = false;
                        textfield.setText(player2_name + "'s turn");
                    } else {
                        buttons[i].setForeground(new Color(0, 0, 255));
                        buttons[i].setText(player2_symbol);
                        player1_turn = true;
                        textfield.setText(player1_name + "'s turn");
                    }
                    buttons[i].setEnabled(false);
                    checkWin();
                }
            }
        }
    }

    public void checkWin() {
        String[][] winningPatterns = {
            {buttons[0].getText(), buttons[1].getText(), buttons[2].getText()},
            {buttons[3].getText(), buttons[4].getText(), buttons[5].getText()},
            {buttons[6].getText(), buttons[7].getText(), buttons[8].getText()},
            {buttons[0].getText(), buttons[3].getText(), buttons[6].getText()},
            {buttons[1].getText(), buttons[4].getText(), buttons[7].getText()},
            {buttons[2].getText(), buttons[5].getText(), buttons[8].getText()},
            {buttons[0].getText(), buttons[4].getText(), buttons[8].getText()},
            {buttons[2].getText(), buttons[4].getText(), buttons[6].getText()}
        };

        for (String[] pattern : winningPatterns) {
            if (pattern[0].equals(player1_symbol) && pattern[1].equals(player1_symbol) && pattern[2].equals(player1_symbol)) {
                showEndGameDialog(player1_name + " wins!");
                return;
            } else if (pattern[0].equals(player2_symbol) && pattern[1].equals(player2_symbol) && pattern[2].equals(player2_symbol)) {
                showEndGameDialog(player2_name + " wins!");
                return;
            }
        }

        // Check for draw
        boolean draw = true;
        for (JButton button : buttons) {
            if (button.getText().isEmpty()) {
                draw = false;
                break;
            }
        }
        if (draw) {
            showEndGameDialog("It's a draw!");
        }
    }

    private void showEndGameDialog(String message) {
        JOptionPane.showMessageDialog(frame, message);
        resetGame();
    }

    class BackgroundPanel extends JPanel {
        private BufferedImage image;

        public BackgroundPanel(BufferedImage image) {
            this.image = image;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (image != null) {
                g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToe();
    }
}
