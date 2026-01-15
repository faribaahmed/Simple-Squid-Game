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

}
