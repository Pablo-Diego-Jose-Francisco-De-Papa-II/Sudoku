import javax.swing.JFrame;
import javax.swing.JButton;

public class SudokuGameGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sudoku :3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 750);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

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
    }
}
