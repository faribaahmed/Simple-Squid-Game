 import java.util.*;
//import java.io.*;
import javax.swing.*;
import javax.swing.Timer;
//import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
//import java.awt.Graphics;


public class MemoryMatch {
    public static void main(String[] args) {
        new MemoryGameGUI();
    }
}



class Tile {

    protected String symbol;
    protected boolean revealed = false;

    public Tile(String symbol) {
        this.symbol = symbol;
    }

    public void reveal() {
        revealed = true;
    }

    public void hide() {
        revealed = false;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public String getSymbol() {
        return symbol;
    }
}

class SquidTile extends Tile {
    public SquidTile(String symbol) {
        super(symbol);
    }
}

class MemoryGame {

    private String[] symbols = {"▲", "●", "■", "♡", "★", "☀"};
    public Tile[] tiles = new Tile[12];
    public int matches = 0;

    public MemoryGame() {
        generateTiles();
    }

    private void generateTiles() {
        List<String> pool = new ArrayList<>();

        for (String s : symbols) {
            pool.add(s);
            pool.add(s); // create pairs
        }

        Collections.shuffle(pool);

        for (int i = 0; i < 12; i++) {
            // polymorphism demo
            tiles[i] = (i % 2 == 0) ? new SquidTile(pool.get(i)) : new Tile(pool.get(i));
        }
    }

    public boolean checkMatch(int a, int b) throws InvalidTileException {
        if (a == b || a < 0 || b < 0 || a >= tiles.length || b >= tiles.length)
            throw new InvalidTileException("Invalid Tile Selection!");

        if (tiles[a].getSymbol().equals(tiles[b].getSymbol())) {
            tiles[a].reveal();
            tiles[b].reveal();
            matches++;
            return true;
        }
        return false;
    }
}

    class MemoryGameGUI extends JFrame implements Clickable {

        private MemoryGame game = new MemoryGame();
        private JButton[] buttons = new JButton[12];
        private int first = -1, second = -1;
        private Timer revealTimer;
        private Timer gameTimer;
        private int timeLeft = 40;
        private JLabel timerLabel;

        public MemoryGameGUI() {
            setTitle("Squid Game Memory Match");
            setSize(1500, 800);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setLayout(new BorderLayout());

            // Timer label
            timerLabel = new JLabel("Time Left: " + timeLeft, SwingConstants.CENTER);
            timerLabel.setFont(new Font("Arial", Font.BOLD, 20));
            add(timerLabel, BorderLayout.NORTH);

            // Grid
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

            startTimer();
            setVisible(true);
        }

        @Override
        public void onClick() {}

        private void handleClick(int index) {
            try {
                if (game.tiles[index].isRevealed()) return;

                buttons[index].setText(game.tiles[index].getSymbol());

                if (first == -1) {
                    first = index;
                } else if (second == -1) {
                    second = index;

                    for (JButton b : buttons) b.setEnabled(false);

                    revealTimer = new Timer(700, e -> {
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

                            ((Timer)e.getSource()).stop();

                            if (game.matches == 6) {
                                 
                                JOptionPane.showMessageDialog(this, "YOU WIN!");
                                System.exit(0);
                            }

                        } catch (InvalidTileException ex) {
                            JOptionPane.showMessageDialog(this, ex.getMessage());
                        }
                    });

                    revealTimer.setRepeats(false);
                    revealTimer.start();
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error!");
            }
        }

        private void startTimer() {
            gameTimer = new Timer(1000, e -> {
                timeLeft--;
                timerLabel.setText("Time Left: " + timeLeft);
                if (timeLeft <= 0) {
                    JOptionPane.showMessageDialog(this, "Time Over! ELIMINATED!");
                    System.exit(0);
                }
            });
            gameTimer.start();
        }
    }

    class InvalidTileException extends Exception {
        public InvalidTileException(String message) {
            super(message);
        }
    }

    interface Clickable {
        void onClick();
    }


