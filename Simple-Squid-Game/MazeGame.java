 import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MazeGame extends JPanel implements KeyListener {
    private int timeLeft = 30;
    private JLabel timerLabel;
    private Timer countdownTimer;


    // Maze map
    // 1 = wall, 0 = path, 9 = goal
    private int[][] maze = {

            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,1,1,1},
            {1,1,0,1,0,1,0,1,1,0,1,1,1,0,1,0,1,0,1,1,1,0,1,1,1,0,1,1,0,1,0,1,1,0,1,1,1},
            {1,1,0,1,0,0,0,0,1,0,0,0,1,0,0,0,1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1},
            {1,1,0,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1,0,1,0,1,1,1,1,0,1,1},
            {1,1,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,0,0,1,0,0,0,0,0,1,0,1,0,0,0,0,0,0,1,1},
            {1,1,0,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,0,1,1},
            {1,1,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,1,0,0,1,1},
            {1,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,1,0,1,1,1,0,1,1,1},
            {1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,1,1},
            {1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,1,1,1,1,0,1,1,1},
            {1,1,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,1,0,0,1,1},
            {1,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,0,1,1,1,0,1,1,1},
            {1,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0,0,1,0,0,0,0,0,0,1,0,0,0,1,1},
            {1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,1,1,0,1,1,1,0,1,1,1,0,1,1,1},
            {1,1,0,0,1,0,0,0,1,0,0,0,0,1,0,0,0,0,1,0,0,0,0,0,0,0,0,0,1,0,0,0,1,0,0,1,1},
            {1,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,0,1,1,1,1,1,0,1,1,1},
            {1,1,1,0,0,0,1,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,1,0,0,0,0,0,1,0,0,0,0,9,0,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}

    };

    int playerX = 1;  // starting row
    int playerY = 1;  // starting col
    int cellSize = 40;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Draw maze
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++) {

                if (maze[i][j] == 1) {
                    g.setColor(Color.BLACK); // wall
                } else if (maze[i][j] == 9) {
                    g.setColor(Color.GREEN); // goal
                } else {
                    g.setColor(Color.WHITE); // path
                }

                g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                g.setColor(Color.GRAY);
                g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
            }
        }

        // Draw player
        g.setColor(Color.RED);
        g.fillOval(playerY * cellSize + 10, playerX * cellSize + 10, 20, 20);
    }


    // Player movement logic
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        int newX = playerX;
        int newY = playerY;

        if (key == KeyEvent.VK_UP) newX--;
        else if (key == KeyEvent.VK_DOWN) newX++;
        else if (key == KeyEvent.VK_LEFT) newY--;
        else if (key == KeyEvent.VK_RIGHT) newY++;

        // Check if move hits a wall
        if (maze[newX][newY] != 1) {
            playerX = newX;
            playerY = newY;

            // Check for win
            if (maze[playerX][playerY] == 9) {
                JOptionPane.showMessageDialog(this, "🎉 PASS!");
            }
        }

        repaint();
    }

    public MazeGame() {
        setFocusable(true);
        addKeyListener(this);

        // Timer label
        timerLabel = new JLabel("Time Left: " + timeLeft + "s");
        timerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        timerLabel.setForeground(Color.RED);

        // Add timer label to the panel
        setLayout(null); // absolute positioning
        timerLabel.setBounds(1200, 20, 200, 40); // adjust position
        add(timerLabel);

        startCountdown();
    }


    private void startCountdown() {
        countdownTimer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft + "s");

            if (timeLeft <= 0) {
                countdownTimer.stop();
                JOptionPane.showMessageDialog(this, "⏰ Time's up! ELIMINATED!");
                System.exit(0);
            }
        });
        countdownTimer.start();
    }


    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    public static void main(String[] args) {
        JFrame frame = new JFrame("Maze Game");
        MazeGame mazeGame = new MazeGame();
        //InstructionBox2 instruction3 = new InstructionBox2();
        frame.add(mazeGame);
        frame.setSize(1500, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
