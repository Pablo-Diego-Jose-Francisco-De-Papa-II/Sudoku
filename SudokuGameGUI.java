import javax.swing.*;
import java.awt.*;

public class SudokuGameGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sudoku :3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 750);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Tlačidlá na vrchu
        JButton hintButton = new JButton("HINT");
        hintButton.setBounds(10, 0, 134, 50);
        frame.add(hintButton);

        JButton solveButton = new JButton("SOLVE");
        solveButton.setBounds(154, 0, 134, 50);
        frame.add(solveButton);

        JButton newGameButton = new JButton("NEW GAME");
        newGameButton.setBounds(298, 0, 134, 50);
        frame.add(newGameButton);

        JButton leaderboardButton = new JButton("LEADERBOARD");
        leaderboardButton.setBounds(442, 0, 134, 50);
        frame.add(leaderboardButton);

        // Sudoku mriežka
        JPanel gridPanel = new JPanel(new GridLayout(9, 9));
        gridPanel.setBounds(10, 60, 566, 566);
        frame.add(gridPanel);

        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JPanel cell = getCell(row, col);
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

        frame.setVisible(true);
    }

    private static JPanel getCell(int row, int col) {
        JPanel cell = new JPanel();
        cell.setBorder(BorderFactory.createMatteBorder(
                row % 3 == 0 ? 3 : 1, // TOP
                col % 3 == 0 ? 3 : 1, // LEFT
                row % 3 == 2 ? 3 : 1, // BOTTOM
                col % 3 == 2 ? 3 : 1, // RIGHT
                Color.BLACK));
        return cell;
    }
}
