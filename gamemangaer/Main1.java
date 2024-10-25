package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import snake_game.*;
import mem_gamee.*;
import mem_gamee.App;
import spaceinvaders.*;
import tictat.TicTacToe;
import montyandflower.*;

public class Main1 implements ActionListener {
    JFrame frame;
    JButton button1, button2, button3, button4, button5, exitButton;

    ImageIcon icon1 = new ImageIcon(Main1.class.getResource("snake.jpg"));
    Image image1 = icon1.getImage();
    Image resizedImage1 = image1.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
    ImageIcon resizedIcon1 = new ImageIcon(resizedImage1);

    ImageIcon icon2 = new ImageIcon(Main1.class.getResource("memory card.jpg"));
    Image image2 = icon2.getImage();
    Image resizedImage2 = image2.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
    ImageIcon resizedIcon2 = new ImageIcon(resizedImage2);

    ImageIcon icon3 = new ImageIcon(Main1.class.getResource("whacmole.png"));
    Image image3 = icon3.getImage();
    Image resizedImage3 = image3.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
    ImageIcon resizedIcon3 = new ImageIcon(resizedImage3);

    ImageIcon icon4 = new ImageIcon(Main1.class.getResource("spaceinvader.png"));
    Image image4 = icon4.getImage();
    Image resizedImage4 = image4.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
    ImageIcon resizedIcon4 = new ImageIcon(resizedImage4);

    ImageIcon icon5 = new ImageIcon(Main1.class.getResource("TicTacToe.jpg"));
    Image image5 = icon5.getImage();
    Image resizedImage5 = image5.getScaledInstance(300, 300, java.awt.Image.SCALE_SMOOTH);
    ImageIcon resizedIcon5 = new ImageIcon(resizedImage5);

    ImageIcon bgIcon = new ImageIcon(Main1.class.getResource("gameimg01.jpg"));
    Image bgImage = bgIcon.getImage();

    public Main1() {
        frame = new JFrame();
        frame.setContentPane(new BackgroundPanel());

        button1 = new JButton();
        button1.setIcon(resizedIcon1);
        customizeButton(button1);

        button2 = new JButton();
        button2.setIcon(resizedIcon2);
        customizeButton(button2);

        button3 = new JButton();
        button3.setIcon(resizedIcon3);
        customizeButton(button3);

        button4 = new JButton();
        button4.setIcon(resizedIcon4);
        customizeButton(button4);

        button5 = new JButton();
        button5.setIcon(resizedIcon5);
        customizeButton(button5);

        // Exit Button customization
        exitButton = new JButton("EXIT");
        exitButton.setFont(new Font("cursive", Font.PLAIN,19));
        exitButton.setBounds(500, 720, 100, 40);
        customizeButton(exitButton);  // Apply customization to exit button
        exitButton.setForeground(Color.WHITE);  // Set the text color to white

        button1.setBounds(50, 50, 300, 300);
        button2.setBounds(400, 50, 300, 300);
        button3.setBounds(50, 400, 300, 300);
        button4.setBounds(400, 400, 300, 300);
        button5.setBounds(750, 50, 300, 300);

        frame.add(button1);
        frame.add(button2);
        frame.add(button3);
        frame.add(button4);
        frame.add(button5);
        frame.add(exitButton);  // Add Exit Button to the frame

        frame.setLayout(null);
        frame.setSize(1100, 800);
        frame.setVisible(true);
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        exitButton.addActionListener(this);  // ActionListener for Exit Button
    }

    public static void main(String[] args) {
        new Main1();
    }

    // Method to customize buttons
    private void customizeButton(JButton button) {
        button.setContentAreaFilled(false);  // Make the button background transparent
        button.setBorderPainted(false);      // Remove the border
        button.setFocusPainted(false);       // Remove the focus highlight
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == button1) {
            SnakeGame game = new SnakeGame();
            SnakeGame.main(null);
            frame.setVisible(false);
        }
        if (e.getSource() == button2) {
            App app = new App();
            App.main(null);
            frame.setVisible(false);
        }
        if (e.getSource() == button3) {
            App1 app1 = new App1();
            try {
                App1.main(null);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            frame.setVisible(false);
        }
        if (e.getSource() == button4) {
            App game4 = new App();
            SpaceInvaders.main(null);
            frame.setVisible(false);
        }
        if (e.getSource() == button5) {
            TicTacToe game5 = new TicTacToe();
           
            frame.setVisible(false);
        }
        if (e.getSource() == exitButton) {
            System.exit(0);  // Exit the application
        }
    }

    // Custom JPanel to set background image
    class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), this);
        }
    }
}     