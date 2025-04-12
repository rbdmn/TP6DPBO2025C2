import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class App {
    public static void main(String[] args) {
        JFrame menuFrame = new JFrame("Main Menu - Flappy Bird");
        menuFrame.setSize(360, 640);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setResizable(false);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image bg = ImageIO.read(App.class.getResource("/assets/background.png"));
                    g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
                } catch (IOException e) {
                    System.out.println("Gagal load background: " + e.getMessage());
                }
            }
        };

        JButton startButton = new JButton("start da gaem");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> {
            menuFrame.dispose();
            startGame();
        });

        backgroundPanel.add(startButton);
        menuFrame.setContentPane(backgroundPanel);
        menuFrame.setVisible(true);
    }

    public static void startGame() {
        JFrame frame = new JFrame("Flappy Bird");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(360, 640);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        FlappyBird flappyBird = new FlappyBird();
        frame.add(flappyBird);
        frame.pack();
        flappyBird.requestFocus();
        frame.setVisible(true);
    }
}
