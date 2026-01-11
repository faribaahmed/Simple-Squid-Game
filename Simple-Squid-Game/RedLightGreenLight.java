import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class RedLightGreenLight {

    public static void main(String[] args) {

        new RedLightGreenLightGUI();

    }
}

class RedLightGreenLightGUI extends JFrame {

    private JLabel statusLabel;
    private JLabel timerLabel;
    private JProgressBar progressBar;
    private JButton moveButton;

    private boolean isGreenLight = true;  // start with blue
    private int timeLeft = 20;
    private int progress = 0;

    private Timer gameTimer;
    private Timer lightSwitchTimer;

    public RedLightGreenLightGUI() {

        setTitle("Red Light, Green Light - Squid Game");
        setSize(1500, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Status Label
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

        setVisible(true);
    }

    private void handleMove() {
        if (isGreenLight) {
            progress += 5;
            progressBar.setValue(progress);

            if (progress >= 100) {
                JOptionPane.showMessageDialog(this, "YOU WIN! You reached the finish line!");
                System.exit(0);
            }

        } else {
            JOptionPane.showMessageDialog(this, "You moved during RED LIGHT! Eliminated!");
            System.exit(0);
        }
    }

    private void startGameTimer() {
        gameTimer = new Timer(1000, e -> {
            timeLeft--;
            timerLabel.setText("Time Left: " + timeLeft);

            if (timeLeft <= 0) {
                JOptionPane.showMessageDialog(this, "Time is up! ELIMINATED!");
                System.exit(0);
            }
        });

        gameTimer.start();
    }

    private void startLightSwitchTimer() {
        Random random = new Random();

        lightSwitchTimer = new Timer(1500 + random.nextInt(1500), e -> {

            isGreenLight = !isGreenLight;  // switch

            if (isGreenLight) {
                statusLabel.setText("GREEN LIGHT – You can move!");
                statusLabel.setBackground(Color.GREEN);
            } else {
                statusLabel.setText("RED LIGHT – STOP!");
                statusLabel.setBackground(Color.RED);
            }

            ((Timer) e.getSource()).setDelay(1500 + random.nextInt(1500));
        });

        lightSwitchTimer.start();
    }

}

