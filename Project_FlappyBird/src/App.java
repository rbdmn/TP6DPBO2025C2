import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App {
    public static void main(String[] args) {
        JFrame menuFrame = new JFrame("Main Menu - Flappy Bird");
        menuFrame.setSize(360, 640);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setResizable(false);

        JButton startButton = new JButton("start da gaem");
        startButton.setFont(new Font("Arial", Font.BOLD, 24));
        startButton.setFocusPainted(false);
        startButton.addActionListener(e -> {
            menuFrame.dispose();
            startGame();
        });

        menuFrame.setLayout(new GridBagLayout());
        menuFrame.add(startButton);
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
