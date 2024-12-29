import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.Random;

public class SudokuGame {
    private static Grid grid;
    private static SudokuManager sudokuManager;

    private int numberOfHints = 5;

    public SudokuGame(Grid grid) {
        this.grid = grid;
        this.sudokuManager = new SudokuManager(grid);
        this.sudokuManager.generateSudoku(Difficulty.EASY);
    }

    public static void main(String[] args) {
        grid = new Grid();
        SudokuGame game = new SudokuGame(grid);
        game.setUpGUI();
    }

    public void setUpGUI() {
        SudokuGame idk = new SudokuGame(new Grid());

        JFrame frame = new JFrame("Sudoku :3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 750);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Tlačidlá na vrchu
        JButton hintButton = new JButton("HINT");
        hintButton.setFont(new Font("Arial", Font.BOLD, 15));
        hintButton.setBackground(Color.lightGray);
        hintButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        hintButton.setBounds(10, 0, 134, 50);
        frame.add(hintButton);

        JButton solveButton = new JButton("SOLVE");
        solveButton.setFont(new Font("Arial", Font.BOLD, 15));
        solveButton.setBackground(Color.lightGray);
        solveButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        solveButton.setBounds(154, 0, 134, 50);
        frame.add(solveButton);

        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setFont(new Font("Arial", Font.BOLD, 15));
        newGameButton.setBackground(Color.lightGray);
        newGameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        newGameButton.setBounds(298, 0, 134, 50);
        frame.add(newGameButton);

        JButton leaderboardButton = new JButton("LEADERBOARD");
        leaderboardButton.setFont(new Font("Arial", Font.BOLD, 15));
        leaderboardButton.setBackground(Color.lightGray);
        leaderboardButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        leaderboardButton.setBounds(442, 0, 134, 50);
        frame.add(leaderboardButton);

        // Sudoku mriežka
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        gridPanel.setBounds(10, 60, 566, 566);
        frame.add(gridPanel);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = getCell(row, col, grid.getPlayersSudoku()[row][col]);
                gridPanel.add(cell);
            }
        }

        // Číselné tlačidlá dole
        int x = 10;
        int w = 48;

        JButton oneButton = new JButton("1");
        oneButton.setBounds(x, 650, w, w);
        frame.add(oneButton);

        JButton twoButton = new JButton("2");
        twoButton.setBounds(x + w + x, 650, w, w);
        frame.add(twoButton);

        JButton threeButton = new JButton("3");
        threeButton.setBounds(x + (2 * (w + x)), 650, w, w);
        frame.add(threeButton);

        JButton fourButton = new JButton("4");
        fourButton.setBounds(x + (3 * (w + x)), 650, w, w);
        frame.add(fourButton);

        JButton fiveButton = new JButton("5");
        fiveButton.setBounds(x + (4 * (w + x)), 650, w, w);
        frame.add(fiveButton);

        JButton sixButton = new JButton("6");
        sixButton.setBounds(x + (5 * (w + x)), 650, w, w);
        frame.add(sixButton);

        JButton sevenButton = new JButton("7");
        sevenButton.setBounds(x + (6 * (w + x)) - 1, 650, w, w);
        frame.add(sevenButton);

        JButton eightButton = new JButton("8");
        eightButton.setBounds(x + (7 * (w + x)) - 2, 650, w, w);
        frame.add(eightButton);

        JButton nineButton = new JButton("9");
        nineButton.setBounds(x + (8 * (w + x)) - 3, 650, w, w);
        frame.add(nineButton);

        JButton xButton = new JButton("X");
        xButton.setBounds(x + (9 * (w + x)) - 4, 650, w, w);
        frame.add(xButton);

        this.setUpButtonListeners(hintButton, solveButton, newGameButton, leaderboardButton);

        frame.setVisible(true);
    }

    private static JTextField getCell(int row, int col, int value) {
        JTextField cell = new JTextField();
        cell.setHorizontalAlignment(SwingConstants.CENTER);
        cell.setFont(new Font("Arial", Font.BOLD, 30));
        cell.setBorder(BorderFactory.createMatteBorder(
                row % 3 == 0 ? 3 : 1, // TOP
                col % 3 == 0 ? 3 : 1, // LEFT
                row % 3 == 2 ? 3 : 1, // BOTTOM
                col % 3 == 2 ? 3 : 1, // RIGHT
                Color.BLACK
        ));

        if (value != 0) {
            cell.setText(String.valueOf(value));
            cell.setBackground(Color.lightGray);
            cell.setEditable(false);
        }

        return cell;
    }

    private void getHint() {
        Random random = new Random();

        if (this.numberOfHints > 0) {
            while (true) {
                int x = random.nextInt(9);
                int y = random.nextInt(9);

                if (grid.getPlayersSudoku()[x][y] == 0) {
                    grid.getPlayersSudoku()[x][y] = grid.getSolvedSudoku()[x][y];
                    this.numberOfHints--;
                    return;
                }

                if (Arrays.deepEquals(grid.getPlayersSudoku(), grid.getSolvedSudoku())) {
                    return;
                }
            }
        }
    }

    public void setUpButtonListeners(JButton hintButton, JButton solveButton, JButton newGameButton, JButton leaderboardButton) {
        hintButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                SudokuGame.this.getHint();
                System.out.println("Hint provided.");
            }
        });

        solveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                sudokuManager.solve(grid.getPlayersSudoku());
                System.out.println("Sudoku solved.");
            }
        });

        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("New game generated.");
            }
        });

        leaderboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                System.out.println("Displaying leaderboard...");
            }
        });
    }
}
