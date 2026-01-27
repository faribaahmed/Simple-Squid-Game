 import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class CompleteSquidGame {

    private JFrame mainFrame;
    private int currentGame = 0;  
    private int playerNumber;

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new CompleteSquidGame().startGame());
    }

    private void startGame() {
        mainFrame = new JFrame("Squid Game");
        mainFrame.setSize(1500, 800);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setFocusable(true);

        generatePlayerNumber();
        WelcomeScreen();
    }

    private void generatePlayerNumber() {
        Random rand = new Random();
        playerNumber = 2 + rand.nextInt(456);  
    }
    // ==================== WELCOME SCREEN ====================
    private void WelcomeScreen() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel symbols = new JLabel("⭕ 🔺 🔲 ");
        symbols.setFont(new Font("SansSerif", Font.BOLD, 100));
        symbols.setForeground(new Color(204, 0, 102));
        symbols.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel title = new JLabel("S Q U I D   G A M E");
        title.setFont(new Font("SansSerif", Font.BOLD, 70));
        title.setForeground(Color.WHITE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel welcome = new JLabel("Welcome to the game");
        welcome.setFont(new Font("SansSerif", Font.PLAIN, 50));
        welcome.setForeground(Color.WHITE);
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel starting = new JLabel("Would you like to play the game?");
        starting.setFont(new Font("SansSerif", Font.PLAIN, 40));
        starting.setForeground(Color.WHITE);
        starting.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel choicePanel = new JPanel();
        choicePanel.setBackground(Color.BLACK);
        choicePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 40, 20));

        JButton yesButton = new JButton("⭕");
        yesButton.setFont(new Font("SansSerif", Font.BOLD, 50));
        yesButton.setForeground(new Color(0, 128, 0));
        yesButton.setBackground(Color.BLACK);
        yesButton.setBorderPainted(false);
        yesButton.setFocusPainted(false);

        JButton noButton = new JButton("❌");
        noButton.setFont(new Font("SansSerif", Font.BOLD, 50));
        noButton.setForeground(new Color(220, 20, 60));
        noButton.setBackground(Color.BLACK);
        noButton.setBorderPainted(false);
        noButton.setFocusPainted(false);

        choicePanel.add(yesButton);
        choicePanel.add(noButton);

        yesButton.addActionListener(e -> PlayerNumberScreen());

        noButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(mainFrame,
                    "GoodBye.",
                    "Exiting the game",
                    JOptionPane.CLOSED_OPTION);
            System.exit(0);
        });

        panel.add(Box.createVerticalStrut(25));
        panel.add(symbols);
        panel.add(Box.createVerticalStrut(25));
        panel.add(title);
        panel.add(Box.createVerticalStrut(25));
        panel.add(welcome);
        panel.add(Box.createVerticalStrut(20));
        panel.add(starting);
        panel.add(Box.createVerticalStrut(30));
        panel.add(choicePanel);

        mainFrame.getContentPane().removeAll();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }
    // ==================== PLAYER NUMBER SCREEN ====================
    private void PlayerNumberScreen() {
        CardLayout cardLayout = new CardLayout();
        JPanel mainPanel = new JPanel(cardLayout);

        JPanel playerPanel = new JPanel();
        playerPanel.setBackground(new Color(0, 40, 20));
        playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.Y_AXIS));

        JLabel playerNumberLabel = new JLabel(String.valueOf(playerNumber));
        playerNumberLabel.setFont(new Font("Orbitron", Font.BOLD, 150));
        playerNumberLabel.setForeground(Color.WHITE);
        playerNumberLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        playerPanel.add(Box.createVerticalStrut(50));
        playerPanel.add(playerNumberLabel);
        playerPanel.add(Box.createVerticalStrut(50));

        // ==================== SMILE SCREEN ====================
        JPanel smilePanel = new JPanel();
        smilePanel.setBackground(new Color(0, 40, 20));
        smilePanel.setLayout(new BoxLayout(smilePanel, BoxLayout.Y_AXIS));

        JLabel smileEmoji = new JLabel("🙂");
        smileEmoji.setFont(new Font("Orbitron", Font.BOLD, 500));
        smileEmoji.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel smileText = new JLabel("SMILE");
        smileText.setFont(new Font("Orbitron", Font.BOLD, 80));
        smileText.setForeground(Color.WHITE);
        smileText.setAlignmentX(Component.CENTER_ALIGNMENT);

        smilePanel.add(Box.createVerticalStrut(10));
        smilePanel.add(smileEmoji);
        smilePanel.add(Box.createVerticalStrut(35));
        smilePanel.add(smileText);

        mainPanel.add(playerPanel, "PLAYER");
        mainPanel.add(smilePanel, "SMILE");

        mainFrame.getContentPane().removeAll();
        mainFrame.add(mainPanel);
        mainFrame.revalidate();
        mainFrame.repaint();

        cardLayout.show(mainPanel, "PLAYER");

        java.util.Timer utilTimer = new java.util.Timer();
        utilTimer.schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    cardLayout.show(mainPanel, "SMILE");
                    mainFrame.addKeyListener(new KeyAdapter() {
                        @Override
                        public void keyPressed(KeyEvent e) {
                            mainFrame.removeKeyListener(this);
                            Instruction1();
                        }
                    });
                    mainFrame.requestFocusInWindow();
                });
            }
        }, 2000); // 2 seconds
    }
// ==================== (RED LIGHT GREEN LIGHT) INSTRUCTION ====================
    private void Instruction1() {
        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel game = new JLabel("------RED LIGHT GREEN LIGHT------ ");
        game.setFont(new Font("Arial", Font.BOLD, 70));
        game.setForeground(new Color(204, 0, 102));
        game.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instruction = new JLabel("<html>" +
                "1. When the light is GREEN, you may move forward by clicking the MOVE button.<br>" +
                "2. When the light turns RED, STOP IMMEDIATELY! Moving during RED LIGHT will eliminate you.<br>" +
                "3. Reach the finish line (100% progress) before time runs out.<br>" +
                "4. <span style='color:green'>⋆₊ ⊹YOU WIN!⋆₊ ⊹</span><br>" +
                "5. If you can't: <span style='color:red'>ELIMINATED</span> ☠" +
                "</html>");
        instruction.setFont(new Font("Arial", Font.PLAIN, 30));
        instruction.setForeground(Color.WHITE);
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("START GAME");
        startButton.setFont(new Font("Arial", Font.BOLD, 35));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> startRedLightGreenLight());

        panel.add(Box.createVerticalStrut(50));
        panel.add(game);
        panel.add(Box.createVerticalStrut(30));
        panel.add(instruction);
        panel.add(Box.createVerticalStrut(50));
        panel.add(startButton);

        mainFrame.getContentPane().removeAll();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    // ==================== GAME 1: RED LIGHT GREEN LIGHT ====================
    private void startRedLightGreenLight() {
        currentGame = 0;
        mainFrame.setVisible(false);
        new RedLightGreenLightGUI();
    }
    // ==================== INSTRUCTION 2 (MEMORY GAME) ====================
    private void Instruction2() {
        currentGame = 1;

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel game = new JLabel("------MEMORY GAME------ ");
        game.setFont(new Font("Arial", Font.BOLD, 70));
        game.setForeground(new Color(204, 0, 102));
        game.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instruction = new JLabel("<html>" +
                "1. You will be shown 12 cards.<br>" +
                "2. There are 6 different symbols.<br>" +
                "3. All of them are in pairs.<br>" +
                "4. Flip the cards to see the symbols.<br>" +
                "5. Match them in pairs in 40 seconds.<br>" +
                "6. Complete it in the given time: <span style='color:green'>⋆₊ ⊹YOU WIN!⋆₊ ⊹</span><br>" +
                "7. If you can't: <span style='color:red'>ELIMINATED</span> ☠" +
                "</html>");
        instruction.setFont(new Font("Arial", Font.PLAIN, 30));
        instruction.setForeground(Color.WHITE);
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("START GAME");
        startButton.setFont(new Font("Arial", Font.BOLD, 35));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> startMemoryGame());

        panel.add(Box.createVerticalStrut(50));
        panel.add(game);
        panel.add(Box.createVerticalStrut(30));
        panel.add(instruction);
        panel.add(Box.createVerticalStrut(50));
        panel.add(startButton);

        mainFrame.getContentPane().removeAll();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    // ==================== GAME 2: MEMORY GAME ====================
    private void startMemoryGame() {
        currentGame = 1;
        mainFrame.setVisible(false);
        new MemoryGameGUI();
    }

    // ==================== INSTRUCTION 3 (MAZE GAME) ====================
    private void Instruction3() {
        currentGame = 2;

        JPanel panel = new JPanel();
        panel.setBackground(Color.BLACK);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel game = new JLabel("------MAZE GAME------ ");
        game.setFont(new Font("Arial", Font.BOLD, 70));
        game.setForeground(new Color(204, 0, 102));
        game.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel instruction = new JLabel("<html>" +
                "1. You will be shown a maze.<br>" +
                "2. You need to get to the green point within 30 seconds.<br>" +
                "3. If you solve the maze in time: <span style='color:green'>⋆₊ ⊹YOU WIN!⋆₊ ⊹</span><br>" +
                "4. If you can't: <span style='color:red'>ELIMINATED</span> ☠" +
                "</html>");
        instruction.setFont(new Font("Arial", Font.PLAIN, 30));
        instruction.setForeground(Color.WHITE);
        instruction.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton startButton = new JButton("START GAME");
        startButton.setFont(new Font("Arial", Font.BOLD, 35));
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> MazeGame());

        panel.add(Box.createVerticalStrut(50));
        panel.add(game);
        panel.add(Box.createVerticalStrut(30));
        panel.add(instruction);
        panel.add(Box.createVerticalStrut(50));
        panel.add(startButton);

        mainFrame.getContentPane().removeAll();
        mainFrame.add(panel);
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.setVisible(true);
    }

    // ==================== GAME 3: MAZE GAME ====================
    private void MazeGame() {
        currentGame = 2;
        mainFrame.setVisible(false);
        new MazeGameGUI();
    }
    // ==================== LAST PANEL (CONGRATULATIONS) ====================
    private void LastPanel() {
        mainFrame.setVisible(false);
        new LastPanelGUI();
    }

    // ==================== GAME PASS / FAIL HANDLERS (with logging) ====================
    public void gamePassed() {
        String levelName;
        switch (currentGame) {
            case 0: levelName = "Red Light Green Light"; break;
            case 1: levelName = "Memory Match"; break;
            case 2: levelName = "Maze Game"; break;
            default: levelName = "Unknown"; break;
        }

        GameRecord.passed(playerNumber, levelName);

        // proceed to next screen on EDT
        SwingUtilities.invokeLater(() -> {
            switch (currentGame) {
                case 0: Instruction2(); break;
                case 1: Instruction3(); break;
                case 2: LastPanel(); break;
            }
        });
    }

    public void gameFailed(String message) {
        String levelName;
        switch (currentGame) {
            case 0: levelName = "Red Light Green Light"; break;
            case 1: levelName = "Memory Match"; break;
            case 2: levelName = "Maze Game"; break;
            default: levelName = "Unknown"; break;
        }

        GameRecord.failed(playerNumber, levelName);

        SwingUtilities.invokeLater(() -> {
            JOptionPane.showMessageDialog(null, message);
            System.exit(0);
        });
    }

    // ==================== GAME 1 CLASS: RED LIGHT GREEN LIGHT ====================
    class RedLightGreenLightGUI extends JFrame {
        private JLabel statusLabel;
        private JLabel timerLabel;
        private JProgressBar progressBar;
        private JButton moveButton;

        private boolean isGreenLight = true;
        private int timeLeft = 20;
        private int progress = 0;

        private javax.swing.Timer gameTimer;
        private javax.swing.Timer lightSwitchTimer;

        public RedLightGreenLightGUI() {
            setTitle("Red Light, Green Light - Squid Game");
            setSize(1500, 800);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());
            setLocationRelativeTo(null);

            statusLabel = new JLabel("Green LIGHT – You can move!", SwingConstants.CENTER);
            statusLabel.setFont(new Font("Arial", Font.BOLD, 22));
            statusLabel.setOpaque(true);
            statusLabel.setBackground(Color.GREEN);
            add(statusLabel, BorderLayout.NORTH);

            timerLabel = new JLabel("Time Left: " + timeLeft, SwingConstants.CENTER);
            timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
            add(timerLabel, BorderLayout.SOUTH);

            progressBar = new JProgressBar(0, 100);
            progressBar.setValue(0);
            progressBar.setStringPainted(true);
            progressBar.setFont(new Font("Arial", Font.BOLD, 18));
            add(progressBar, BorderLayout.CENTER);

            moveButton = new JButton("MOVE");
            moveButton.setFont(new Font("Arial", Font.BOLD, 22));
            moveButton.addActionListener(e -> handleMove());
            add(moveButton, BorderLayout.EAST);

            startGameTimer();
            startLightSwitchTimer();

            addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    stopTimers();
                }
            });

            setVisible(true);
        }

        private void stopTimers() {
            if (gameTimer != null) gameTimer.stop();
            if (lightSwitchTimer != null) lightSwitchTimer.stop();
            dispose();
        }

        private void handleMove() {
            if (isGreenLight) {
                progress += 5;
                progressBar.setValue(progress);

                if (progress >= 100) {
                    stopTimers();
                    dispose();
                    gamePassed();
                }
            } else {
                stopTimers();
                gameFailed("You moved during RED LIGHT! Eliminated!");
            }
        }

        private void startGameTimer() {
            gameTimer = new javax.swing.Timer(1000, e -> {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft);

                if (timeLeft <= 0) {
                    stopTimers();
                    gameFailed("Time is up! ELIMINATED!");
                }
            });
            gameTimer.start();
        }

        private void startLightSwitchTimer() {
            Random random = new Random();
            lightSwitchTimer = new javax.swing.Timer(1500 + random.nextInt(1500), e -> {
                isGreenLight = !isGreenLight;

                if (isGreenLight) {
                    statusLabel.setText("GREEN LIGHT – You can move!");
                    statusLabel.setBackground(Color.GREEN);
                } else {
                    statusLabel.setText("RED LIGHT – STOP!");
                    statusLabel.setBackground(Color.RED);
                }
                lightSwitchTimer.setDelay(1500 + random.nextInt(1500));
            });
            lightSwitchTimer.start();
        }
    }

    // ==================== GAME 2 CLASS: MEMORY GAME ====================
    class MemoryGameGUI extends JFrame {
        private MemoryGame game = new MemoryGame();
        private JButton[] buttons = new JButton[12];
        private int first = -1, second = -1;
        private javax.swing.Timer revealTimer;
        private javax.swing.Timer gameTimer;
        private int timeLeft = 40;
        private JLabel timerLabel;

        public MemoryGameGUI() {
            setTitle("Squid Game Memory Match");
            setSize(1500, 800);
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setLayout(new BorderLayout());
            setLocationRelativeTo(null);

            timerLabel = new JLabel("Time Left: " + timeLeft, SwingConstants.CENTER);
            timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
            add(timerLabel, BorderLayout.NORTH);

            JPanel grid = new JPanel(new GridLayout(3, 4, 5, 5));
            add(grid, BorderLayout.CENTER);

            for (int i = 0; i < 12; i++) {
                buttons[i] = new JButton("?");
                buttons[i].setFont(new Font("Segoe UI Symbol", Font.BOLD, 32));
                buttons[i].setBackground(Color.PINK);
                int index = i;

                buttons[i].addActionListener(e -> handleClick(index));
                grid.add(buttons[i]);
            }

            addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    stopTimers();
                }
            });

            startTimer();
            setVisible(true);
        }

        private void stopTimers() {
            if (gameTimer != null) gameTimer.stop();
            if (revealTimer != null) revealTimer.stop();
            dispose();
        }

        private void handleClick(int index) {
            try {
                if (game.tiles[index].isRevealed()) return;

                buttons[index].setText(game.tiles[index].getSymbol());

                if (first == -1) {
                    first = index;
                } else if (second == -1) {
                    second = index;

                    for (JButton b : buttons) b.setEnabled(false);

                    revealTimer = new javax.swing.Timer(700, e -> {
                        try {
                            boolean match = game.checkMatch(first, second);

                            if (!match) {
                                game.tiles[first].hide();
                                game.tiles[second].hide();
                                buttons[first].setText("?");
                                buttons[second].setText("?");
                            }

                            for (int i = 0; i < 12; i++) {
                                if (!game.tiles[i].isRevealed())
                                    buttons[i].setEnabled(true);
                            }

                            first = -1;
                            second = -1;

                            revealTimer.stop();

                            if (game.matches == 6) {
                                stopTimers();
                                dispose();
                                gamePassed();
                            }

                        } catch (InvalidTileException ex) {
                            gameFailed(ex.getMessage());
                        }
                    });

                    revealTimer.setRepeats(false);
                    revealTimer.start();
                }

            } catch (Exception e) {
                gameFailed("Error in game!");
            }
            if (game.matches == 6) {

                if (gameTimer != null) gameTimer.stop();
                if (revealTimer != null) revealTimer.stop();

                dispose();
                gamePassed();
            }

        }

        private void startTimer() {
            gameTimer = new javax.swing.Timer(1000, e -> {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft);
                if (timeLeft <= 0) {
                    if (revealTimer != null) revealTimer.stop();
                    stopTimers();
                    gameFailed("Time Over! ELIMINATED!");
                }
            });
            gameTimer.start();
        }
    }

    // ==================== GAME 3 CLASS: MAZE GAME ====================
    class MazeGameGUI extends JPanel implements KeyListener {
        private int timeLeft = 30;
        private javax.swing.Timer countdownTimer;

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

        int playerX = 1;
        int playerY = 1;
        int cellSize = 40;
        private JFrame frame;

        public MazeGameGUI() {
            frame = new JFrame("Maze Game");
            frame.setSize(1500, 800);
            frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame.setLocationRelativeTo(null);

            setFocusable(true);
            addKeyListener(this);

            frame.add(this);
            frame.setVisible(true);
            startCountdown();
            requestFocus();
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            // Draw maze
            for (int i = 0; i < maze.length; i++) {
                for (int j = 0; j < maze[i].length; j++) {
                    if (maze[i][j] == 1) {
                        g.setColor(Color.BLACK);
                    } else if (maze[i][j] == 9) {
                        g.setColor(Color.GREEN);
                    } else {
                        g.setColor(Color.WHITE);
                    }
                    g.fillRect(j * cellSize, i * cellSize, cellSize, cellSize);
                    g.setColor(Color.GRAY);
                    g.drawRect(j * cellSize, i * cellSize, cellSize, cellSize);
                }
            }

            // Draw player
            g.setColor(Color.RED);
            g.fillOval(playerY * cellSize + 10, playerX * cellSize + 10, 20, 20);

            // Draw timer
            g.setColor(Color.RED);
            g.setFont(new Font("Arial", Font.BOLD, 24));
            g.drawString("Time Left: " + timeLeft + "s", 1200, 30);
        }

        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            int newX = playerX;
            int newY = playerY;

            if (key == KeyEvent.VK_UP) newX--;
            else if (key == KeyEvent.VK_DOWN) newX++;
            else if (key == KeyEvent.VK_LEFT) newY--;
            else if (key == KeyEvent.VK_RIGHT) newY++;

            if (maze[newX][newY] != 1) {
                playerX = newX;
                playerY = newY;

                if (maze[playerX][playerY] == 9) {
                    countdownTimer.stop();
                    frame.dispose();
                    gamePassed();
                }
            }
            repaint();
        }

        private void startCountdown() {
            countdownTimer = new javax.swing.Timer(1000, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    timeLeft--;
                    if (timeLeft <= 0) {
                        countdownTimer.stop();
                        frame.dispose();
                        gameFailed("⏰ Time's up! ELIMINATED!");
                    }
                    repaint();
                }
            });
            countdownTimer.start();
        }

        public void keyTyped(KeyEvent e) {}
        public void keyReleased(KeyEvent e) {}
    }


}
