package flappyBird;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class FlappyBird {
    private JFrame frame;
    private JPanel panel;
    private Bird bird;
    private Tube tube;
    private Timer timer;

    public FlappyBird() {
        frame = new JFrame("Flappy Bird");
        panel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(bird.getImage(), bird.getX(), bird.getY(), null);
                g.drawImage(tube.getImage(), tube.getX(), tube.getY(), null);
            }
        };
        panel.setBackground(Color.WHITE);
        panel.setPreferredSize(new Dimension(400, 600));
        frame.add(panel);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_SPACE) {
                    bird.jump();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
        });
        bird = new Bird(100, 100);
        tube = new Tube(300, 300);
        timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bird.update();
                tube.update();
                panel.repaint();
            }
        });
        timer.start();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new FlappyBird();
    }
}

class Bird {
    private int x;
    private int y;
    private int dy;
    private BufferedImage image;

    public Bird(int x, int y) {
        this.x = x;
        this.y = y;
        this.dy = 0;
        try {
            image = ImageIO.read(getClass().getResource("bird.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void jump() {
        dy = -10;
    }

    public void update() {
        y += dy;
        dy += 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public BufferedImage getImage() {
        return image;
    }
}

class Tube {
    private int x;
    private int y;
    private int width;
    private int height;
    private BufferedImage image;

    public Tube(int x, int y) {
        this.x = x;
        this.y = y;
        this.width = 50;
        this.height = 200;
        try {
            image = ImageIO.read(getClass().getResource("tube.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        x -= 2;
        if (x < -width) {
            x = 400;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public BufferedImage getImage() {
        return image;
    }
}