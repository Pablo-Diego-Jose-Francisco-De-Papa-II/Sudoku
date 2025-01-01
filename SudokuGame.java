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

/**
 * Trieda SudokuGame reprezentuje hru sudoku, ktorá spravuje hernú logiku, vykresluje grafické rozhranie a poskytuje
 * možnosti interakcie s hráčom.
 * Hlavné fukcie zahŕňa generovanie hry, poskytovanie nápovedy, riešenie hry a spravovanie skóre.
 */
public class SudokuGame {
    private final Grid grid;
    private final SudokuManager sudokuManager;
    private final Leaderboard leaderboard;
    private final VictoryScreen victoryScreen;
    private final Player player;

    private JPanel gridPanel;
    private int numberOfHints = 5;
    private boolean gameWon = false;


    /**
     * Konštruktor triedy SudokuGame, ktorý inicializuje potrebné objekty a
     * spustí generovanie sudoku podľa strednej obtiažnosti.
     *
     * @param grid mriežka Sudoku
     */
    public SudokuGame(Grid grid) {
        this.grid = grid;
        this.sudokuManager = new SudokuManager(grid);
        this.sudokuManager.generateSudoku(Difficulty.MEDIUM);
        this.leaderboard = new Leaderboard();
        this.player = new Player();
        this.victoryScreen = new VictoryScreen(this.player);
        this.player.startTimer();
        this.player.setDifficulty(Difficulty.MEDIUM);
    }

    /**
     * Nastaví grafické užívateľské rozhranie pre hru vrátane tlačidiel,
     * mriežky, a nastaví poslucháče pre tlačidlá.
     */
    public void setUpGUI() {
        JFrame frame = new JFrame("Sudoku :3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 675);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Tlačidlá na vrchu.
        JButton hintButton = this.createButton("HINT", 10);
        frame.add(hintButton);

        JButton solveButton = this.createButton("SOLVE", 154);
        frame.add(solveButton);

        JButton newGameButton = this.createButton("NEW GAME", 298);
        frame.add(newGameButton);

        JButton leaderboardButton = this.createButton("LEADERBOARD", 442);
        frame.add(leaderboardButton);

        // Menu pre výber obtiažnosti.
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



        // Vytvorenie mriežky.
        this.gridPanel = new JPanel(new GridLayout(9, 9));
        this.gridPanel.setBounds(10, 60, 566, 566);
        frame.add(this.gridPanel);

        // Nastavenie jednotlivých buniek v mriežke.
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                JTextField cell = new JTextField(); // Vytvorenie textového poľa pre každú políčko.
                this.setupCell(cell, row, col, this.grid.getPlayersSudoku()[row][col], this.grid.isFixed(row, col));
                this.gridPanel.add(cell); // Pridanie políčka do mriežky.
            }
        }

        // Nastavenie poslucháčov pre tlačidlá.
        this.setUpButtonListeners(hintButton, solveButton, newGameButton, leaderboardButton, difficultyMenu);

        // Zobrazenie okna.
        frame.setVisible(true);
    }

    /**
     * Začne novú hru s vybranou obtiažnosťou.
     *
     * @param difficulty zvolená obtiažnosť hry.
     */
    private void startNewGame(Difficulty difficulty) {
        this.sudokuManager.generateSudoku(difficulty);
        this.player.setDifficulty(difficulty);

        this.numberOfHints = 5;
        this.gameWon = false;
        this.refreshGUI(this.gridPanel);

        // Začiatok časovača pre novú hru
        this.player.startTimer();

        // Zobrazenie správy o začatí novej hry.
        JOptionPane.showMessageDialog(null, "New game generated! \nDifficulty: " + difficulty);
    }

    /**
     * Nastaví vzhľad jednotlivých políčok v mriežke (farba, text, možnosť úpravy).
     *
     * @param cell textové pole pre jednu políčko.
     * @param row riadok políčka v mriežke.
     * @param col stĺpec políčka v mriežke.
     * @param value hodnota, ktorá sa zobrazuje v políčku.
     * @param isFixed určuje, či je políčko pevné (nemeniteľné).
     */
    private void setupCell(JTextField cell, int row, int col, int value, boolean isFixed) {
        cell.setHorizontalAlignment(SwingConstants.CENTER);
        cell.setFont(new Font("Arial", Font.BOLD, 30));
        cell.setBorder(this.createCellBorder(row, col));

        if (isFixed) { // Ak je políčko pevné, zobrazí sa hodnota a nie je možné ho upravovať.
            cell.setText(String.valueOf(value));
            cell.setBackground(Color.LIGHT_GRAY);
            cell.setEditable(false);
        } else { // Ak nie je pevná, umožní sa úprava.
            cell.setText(value != 0 ? String.valueOf(value) : "");
            cell.setBackground(Color.WHITE);
            cell.setEditable(true);

            // Poslucháč pre zmenu hodnoty v políčku.
            cell.addActionListener(ae -> {
                try {
                    int input = Integer.parseInt(cell.getText());

                    if (input >= 1 && input <= 9) {
                        SudokuGame.this.validatePlacement(row, col, input, cell);
                    } else {
                        cell.setText(""); // Vymazanie hodnoty, ak je mimo rozsahu.
                        cell.setBackground(Color.WHITE);
                    }
                } catch (NumberFormatException e) { // Ak je zadaná neplatná hodnota.
                    cell.setText(""); // Vymazanie hodnoty.
                    cell.setBackground(Color.WHITE);
                }
            });
        }
    }

    /**
     * Overí, či je číslo, ktoré užívateľ zadal, správne a zobrazuje príslušnú spätnú väzbu.
     *
     * @param row riadok políčka.
     * @param col stĺpec políčka.
     * @param input číslo, ktoré užívateľ zadal.
     * @param cell políčko, ktorá sa aktualizuje.
     */
    private void validatePlacement(int row, int col, int input, JTextField cell) {
        this.grid.getPlayersSudoku()[row][col] = input;

        if (!this.grid.isFixed(row, col)) {
            if (this.grid.getCorrectValue(row, col) == input) { // Ak je hodnota správna.
                cell.setBackground(Color.WHITE);
            } else {
                cell.setBackground(Color.RED); // Ak je hodnota nesprávna, nastavenie červenej farby.
            }
        }

        // Ak je celé Sudoku vyriešené spávne.
        if (this.isSolved() && !this.gameWon) {
            this.gameWon = true; // Hra je vyhraná.
            this.player.endTimer(); // Zastavenie časovača
            this.victoryScreen.setUpGui(); // Zobrazenie obrazovky víťazstva
        }
    }

    /**
     * Vytvorí orámovanie pre políčka v mriežke na základe jej pozície.
     *
     * @param row riadok políčka.
     * @param col stĺpec políčka.
     * @return orámovanie políčka.
     */
    private Border createCellBorder(int row, int col) {
        return BorderFactory.createMatteBorder(
                row % 3 == 0 ? 3 : 1, // Horné orámovanie.
                col % 3 == 0 ? 3 : 1, // Ľavé orámovanie.
                row % 3 == 2 ? 3 : 1, // Spodné orámovanie.
                col % 3 == 2 ? 3 : 1, // Pravé orámovanie.
                Color.BLACK // Farba orámovania.
        );
    }

    /**
     * Poskytne nápovedu užívateľovi tým, že náhodne vyplní prázdne políčko.
     */
    private void getHint() {
        Random random = new Random();

        if (this.numberOfHints <= 0) { // Ak už nie sú k dispozícii žiadne nápovedy.
            JOptionPane.showMessageDialog(null, "Out of hints!");
            return;
        }

        // Náhodné vyplnenie prázdneho políčka.
        while (this.numberOfHints > 0) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);

            if (this.grid.getPlayersSudoku()[x][y] == 0) { // Ak je políčko prázdna
                this.grid.getPlayersSudoku()[x][y] = this.grid.getCorrectValue(x, y); // Nastavenie správnej hodnoty
                this.numberOfHints--; // Zníženie počtu dostupných nápoved
                System.out.println("Hint provided. " + this.numberOfHints + " hints left.");
                return;
            }

            if (this.isSolved()) { // Ak je Sudoku už vyriešené.
                JOptionPane.showMessageDialog(null, "Sudoku is already solved!");
                return;
            }
        }
    }


    /**
     * Obnoví grafické rozhranie na základe aktuálneho stavu mriežky.
     *
     * @param gridPanel panel obsahujúci mriežku.
     */
    private void refreshGUI(JPanel gridPanel) {
        Component[] components = gridPanel.getComponents();

        // Prejdeme všetky komponenty v paneli a aktualizujeme ich.
        for (int i = 0; i < components.length; i++) {
            JTextField cell = (JTextField)components[i];
            int row = i / 9;
            int col = i % 9;
            int value = this.grid.getPlayersSudoku()[row][col];

            // Nastaví hodnotu a vlastnosti pre dané políčko.
            this.setupCell(cell, row, col, value, this.grid.isFixed(row, col));
        }
    }

    /**
     * Nastaví poslucháčov pre tlačidlá v GUI.
     *
     * @param hintButton tlačidlo pre nápovedu.
     * @param solveButton tlačidlo pre vyriešenie.
     * @param newGameButton tlačidlo pre novú hru.
     * @param leaderboardButton tlačidlo pre tabuľku.
     * @param difficultyMenu menu pre výber obtiažnosti.
     */
    private void setUpButtonListeners(JButton hintButton, JButton solveButton, JButton newGameButton, JButton leaderboardButton, JPopupMenu difficultyMenu) {
        // Poslucháč pre tlačidlo nápovedy.
        hintButton.addActionListener(ae -> {
            SudokuGame.this.getHint();
            SudokuGame.this.refreshGUI(SudokuGame.this.gridPanel);
        });

        // Poslucháč pre tlačidlo na vyriešenie sudoku.
        solveButton.addActionListener(ae -> {
            this.sudokuManager.solve(this.grid.getPlayersSudoku());
            SudokuGame.this.refreshGUI(SudokuGame.this.gridPanel);
        });

        // Poslucháč pre tlačidlo na začatie novej hry.
        newGameButton.addActionListener(ae -> difficultyMenu.show(newGameButton, 0, newGameButton.getHeight()));

        // Poslucháč pre tlačidlo na zobrazenie tabuľky výsledkov.
        leaderboardButton.addActionListener(ae -> this.leaderboard.setUpGUI());
    }

    /**
     * Vytvorí tlačidlo s daným textom a pozíciou.
     *
     * @param text text tlačidla.
     * @param x pozícia tlačidla na osi x.
     * @return vytvorené tlačidlo.
     */
    private JButton createButton(String text, int x) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setBounds(x, 0, 134, 50);
        return button;
    }

    /**
     * Skontroluje, či je Sudoku správne vyriešené.
     *
     * @return true, ak je Sudoku vyriešené.
     */
    private boolean isSolved() {
        return Arrays.deepEquals(this.grid.getPlayersSudoku(), this.grid.getSolvedSudoku());
    }
}
