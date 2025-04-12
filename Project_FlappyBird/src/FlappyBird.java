import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class FlappyBird extends JPanel implements ActionListener, KeyListener {

    int frameWidth = 360;
    int frameHeight = 640;

    // images
    Image backgroundImage;
    Image birdImage;
    Image lowerPipeImage;
    Image upperPipeImage;

    // player
    int playerStartPosX = frameWidth / 8;
    int playerStartPosY = frameHeight / 2;
    int playerWidth = 34;
    int playerHeight = 24;
    Player player;

    // pipes
    int pipeStartPosX = frameWidth;
    int pipeStartPosY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;
    ArrayList<Pipe> pipes;

    // timer
    Timer gameLoop;
    Timer pipesCooldown;

    int gravity = 1;

    // game state
    boolean isGameOver = false;
    int skor = 0;
    JLabel skorLabel;
    ArrayList<Boolean> pipePassed; 


    public FlappyBird(){
        setPreferredSize(new Dimension(360,640));
        setFocusable(true);
        addKeyListener(this);

        skorLabel = new JLabel("Skor: 0", SwingConstants.CENTER);
        skorLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
        skorLabel.setForeground(Color.WHITE);
        skorLabel.setBackground(new Color(0, 0, 0, 150));
        skorLabel.setOpaque(true);
        skorLabel.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));

        int labelWidth = 200;
        int labelHeight = 40;
        int frameWidth = 360;
        int x = (frameWidth - labelWidth) / 2;
        int y = 10;

        this.setLayout(null);
        skorLabel.setBounds(x, y, labelWidth, labelHeight);
        this.add(skorLabel);

        backgroundImage = new ImageIcon(getClass().getResource("assets/background.png")).getImage();
        birdImage = new ImageIcon(getClass().getResource("assets/bird.png")).getImage();
        lowerPipeImage = new ImageIcon(getClass().getResource("assets/lowerPipe.png")).getImage();
        upperPipeImage = new ImageIcon(getClass().getResource("assets/upperPipe.png")).getImage();

        player = new Player(playerStartPosX, playerStartPosY, playerWidth, playerHeight, birdImage);
        pipes = new ArrayList<>();
        pipePassed = new ArrayList<>();

        pipesCooldown = new Timer(5000, e -> {
            if (!isGameOver) placePipes();
        });
        pipesCooldown.start();

        gameLoop = new Timer(1000/60, this);
        gameLoop.start();
    }

    public void placePipes(){
        int randomPosY = (int) (pipeStartPosY - pipeHeight/6 - Math.random() * (pipeHeight/2));
        int openingSpace = frameHeight/4;

        Pipe upperPipe = new Pipe(pipeStartPosX, randomPosY, pipeWidth, pipeHeight, upperPipeImage);
        Pipe lowerPipe = new Pipe(pipeStartPosX, (randomPosY + openingSpace + pipeHeight), pipeWidth, pipeHeight, lowerPipeImage);

        pipes.add(upperPipe);
        pipes.add(lowerPipe);
        pipePassed.add(false);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImage, 0,0, frameWidth, frameHeight, null);
        g.drawImage(player.getImage(), player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight(), null);

        for (Pipe pipe : pipes) {
            g.drawImage(pipe.getImage(), pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight(), null);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 24));
//        g.drawString("skor: " + skor, 20, 30);

        if (isGameOver) {
            g.setFont(new Font("Comic Sans MS", Font.BOLD, 36));
            g.drawString("Game Over", frameWidth / 2 - 100, frameHeight / 2 - 30);

            g.setFont(new Font("Comic Sans MS", Font.PLAIN, 20));
            g.drawString("Pencet R untuk mengulang", frameWidth / 2 - 120, frameHeight / 2 + 10);

        }
    }

    public void move(){
        if (isGameOver) return;

        player.setVelocityY(player.getVelocityY() + gravity);
        player.setPosY(player.getPosY() + player.getVelocityY());
        player.setPosY(Math.max(player.getPosY(), 0));

        Rectangle playerRect = new Rectangle(player.getPosX(), player.getPosY(), player.getWidth(), player.getHeight());

        for (int i = 0; i < pipes.size(); i++) {
            Pipe pipe = pipes.get(i);
            pipe.setPosX(pipe.getPosX() + pipe.getVelocityX());

            Rectangle pipeRect = new Rectangle(pipe.getPosX(), pipe.getPosY(), pipe.getWidth(), pipe.getHeight());
            if (playerRect.intersects(pipeRect)) {
                isGameOver = true;
                gameLoop.stop();
                pipesCooldown.stop();
            }
        }

        for (int i = 0; i < pipes.size(); i += 2) {
            Pipe upper = pipes.get(i);
            if (!pipePassed.get(i / 2) && player.getPosX() > upper.getPosX() + upper.getWidth()) {
                skor++;
                pipePassed.set(i / 2, true);
                skorLabel.setText("Skor: " + skor);
                skorLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 24));
            }
        }

        if (player.getPosY() + player.getHeight() >= frameHeight) {
            isGameOver = true;
            gameLoop.stop();
            pipesCooldown.stop();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e){
        if (e.getKeyCode() == KeyEvent.VK_SPACE && !isGameOver){
            player.setVelocityY(-10);
        } else if (e.getKeyCode() == KeyEvent.VK_R && isGameOver) {
            resetGame();
        }
    }

    public void resetGame() {
        player.setPosY(playerStartPosY);
        player.setVelocityY(0);
        pipes.clear();
        pipePassed.clear();
        skor = 0;
        isGameOver = false;
        gameLoop.start();
        pipesCooldown.start();
    }

    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}
}