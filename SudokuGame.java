import javax.swing.border.Border;
import java.util.Arrays;
import java.util.Random;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Cursor;
import javax.swing.JTextField;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import java.awt.Component;
import javax.swing.JPopupMenu;
import javax.swing.JMenuItem;

public class SudokuGame {
    private static Grid grid;
    private static SudokuManager sudokuManager;
    private static Leaderboard leaderboard;
    private static VictoryScreen victoryScreen;

    private JPanel gridPanel;
    private int numberOfHints = 5;

    public SudokuGame(Grid grid) {
        SudokuGame.grid = grid;
        SudokuGame.sudokuManager = new SudokuManager(grid);
        SudokuGame.sudokuManager.generateSudoku(Difficulty.MEDIUM);
        SudokuGame.leaderboard = new Leaderboard();
        SudokuGame.victoryScreen = new VictoryScreen();
    }

    public static void main(String[] args) {
        SudokuGame game = new SudokuGame(new Grid());
        game.setUpGUI();
    }

    public void setUpGUI() {
        JFrame frame = new JFrame("Sudoku :3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 675);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Tlačidlá na vrchu
        JButton hintButton = new JButton("HINT");
        hintButton.setFont(new Font("Arial", Font.BOLD, 15));
        hintButton.setBackground(Color.LIGHT_GRAY);
        hintButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        hintButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        hintButton.setBounds(10, 0, 134, 50);
        frame.add(hintButton);

        JButton solveButton = new JButton("SOLVE");
        solveButton.setFont(new Font("Arial", Font.BOLD, 15));
        solveButton.setBackground(Color.LIGHT_GRAY);
        solveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        solveButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        solveButton.setBounds(154, 0, 134, 50);
        frame.add(solveButton);

        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 15));
        newGameButton.setBackground(Color.LIGHT_GRAY);
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        newGameButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        newGameButton.setBounds(298, 0, 134, 50);
        frame.add(newGameButton);

        JPopupMenu difficultyMenu = new JPopupMenu();
        difficultyMenu.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        difficultyMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        JMenuItem easyItem = new JMenuItem("EASY");
        difficultyMenu.add(easyItem);
        easyItem.addActionListener(e -> this.startNewGame(Difficulty.EASY));

        JMenuItem mediumItem = new JMenuItem("MEDIUM");
        difficultyMenu.add(mediumItem);
        mediumItem.addActionListener(e -> this.startNewGame(Difficulty.MEDIUM));

        JMenuItem hardItem = new JMenuItem("HARD");
        difficultyMenu.add(hardItem);
        hardItem.addActionListener(e -> this.startNewGame(Difficulty.HARD));

        JMenuItem testItem = new JMenuItem("TEST");
        difficultyMenu.add(testItem);
        testItem.addActionListener(e -> this.startNewGame(Difficulty.TEST));



        JButton leaderboardButton = new JButton("LEADERBOARD");
        leaderboardButton.setFont(new Font("Arial", Font.BOLD, 15));
        leaderboardButton.setBackground(Color.LIGHT_GRAY);
        leaderboardButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        leaderboardButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        leaderboardButton.setBounds(442, 0, 134, 50);
        frame.add(leaderboardButton);

        // Sudoku mriežka
        this.gridPanel = new JPanel(new GridLayout(9, 9));
        this.gridPanel.setBounds(10, 60, 566, 566);
        frame.add(this.gridPanel);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = new JTextField();
                setupCell(cell, row, col, grid.getPlayersSudoku()[row][col], grid.isFixed(row, col));
                this.gridPanel.add(cell);
            }
        }

        this.setUpButtonListeners(hintButton, solveButton, newGameButton, leaderboardButton, difficultyMenu);
        frame.setVisible(true);
    }

    private void startNewGame(Difficulty difficulty) {
        this.numberOfHints = 5;
        sudokuManager.generateSudoku(difficulty);
        this.refreshGUI(this.gridPanel);
        JOptionPane.showMessageDialog(null, "New game generated! \nDifficulty: " + difficulty);
    }

    private void setupCell(JTextField cell, int row, int col, int value, boolean isFixed) {
        cell.setHorizontalAlignment(SwingConstants.CENTER);
        cell.setFont(new Font("Arial", Font.BOLD, 30));
        cell.setBorder(this.createCellBorder(row, col));

        if (isFixed) {
            cell.setText(String.valueOf(value));
            cell.setBackground(Color.LIGHT_GRAY);
            cell.setEditable(false);
        } else {
            cell.setText(value != 0 ? String.valueOf(value) : "");
            cell.setBackground(Color.WHITE);
            cell.setEditable(true);

            cell.addActionListener(ae -> {
                try {
                    int input = Integer.parseInt(cell.getText());

                    if (input >= 1 && input <= 9) {
                        SudokuGame.this.validatePlacement(row, col, input, cell);
                    } else {
                        cell.setText("");
                        cell.setBackground(Color.WHITE);
                    }
                } catch (NumberFormatException e) {
                    cell.setText("");
                    cell.setBackground(Color.WHITE);
                }
            });
        }
    }

    public void validatePlacement(int row, int col, int input, JTextField cell) {
        grid.getPlayersSudoku()[row][col] = input;

        if (!grid.isFixed(row, col)) {
            if (grid.getCorrectValue(row, col) == input)  {
                cell.setBackground(Color.WHITE);
            } else {
                cell.setBackground(Color.RED);
            }
        }

        if (this.isSolved()) {
            victoryScreen.setUpGui();
        }
    }

    private Border createCellBorder(int row, int col) {
        return BorderFactory.createMatteBorder(
                row % 3 == 0 ? 3 : 1, // TOP
                col % 3 == 0 ? 3 : 1, // LEFT
                row % 3 == 2 ? 3 : 1, // BOTTOM
                col % 3 == 2 ? 3 : 1, // RIGHT
                Color.BLACK
        );
    }

    private void getHint() {
        Random random = new Random();

        if (this.numberOfHints <= 0) {
            JOptionPane.showMessageDialog(null, "Out of hints!");
            return;
        }

        while (this.numberOfHints > 0) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);

            if (grid.getPlayersSudoku()[x][y] == 0) {
                grid.getPlayersSudoku()[x][y] = grid.getCorrectValue(x, y);
                this.numberOfHints--;
                System.out.println("Hint provided. " + this.numberOfHints + " hints left.");
                return;
            }

            if (this.isSolved()) {
                JOptionPane.showMessageDialog(null, "Sudoku is already solved!");
                return;
            }
        }
    }

    private boolean isSolved() {
        return Arrays.deepEquals(grid.getPlayersSudoku(), grid.getSolvedSudoku());
    }

    private void refreshGUI(JPanel gridPanel) {
        Component[] components = gridPanel.getComponents();

        for (int i = 0; i < components.length; i++) {
            if (components[i] instanceof JTextField cell) {
                int row = i / 9;
                int col = i % 9;
                int value = grid.getPlayersSudoku()[row][col];

                setupCell(cell, row, col, value, grid.isFixed(row, col));
            }
        }
    }

    public void setUpButtonListeners(JButton hintButton, JButton solveButton, JButton newGameButton, JButton leaderboardButton, JPopupMenu difficultyMenu) {
        hintButton.addActionListener(ae -> {
            SudokuGame.this.getHint();
            SudokuGame.this.refreshGUI(SudokuGame.this.gridPanel);
        });

        solveButton.addActionListener(ae -> {
            sudokuManager.solve(grid.getPlayersSudoku());
            SudokuGame.this.refreshGUI(SudokuGame.this.gridPanel);
        });

        newGameButton.addActionListener(ae -> difficultyMenu.show(newGameButton, 0, newGameButton.getHeight()));

        leaderboardButton.addActionListener(ae -> leaderboard.setUpGUI());
    }
}
