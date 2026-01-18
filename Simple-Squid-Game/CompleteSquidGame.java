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


}
