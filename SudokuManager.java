import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Trieda SudokuManager slúži na riešenie i generovanie mriežky sudoku.
 * Obsahuje referencie na triedy Grid a Container, ktoré sú potrebné na validáciu a prácu s mriežkou sudoku.
 */
public class SudokuManager  {
    private final Grid grid;
    private final Container container;

    /**
     * Konštruktor triedy SudokuManager, ktorý inicializuje objekty Grid a Container.
     *
     * @param grid Sudoku mriežka, s ktorou bude trieda pracovať.
     */
    public SudokuManager (Grid grid) {
        this.grid = grid;
        this.container = new Container(grid);
    }

    /**
     * Rieši sudoku pomocou back-tracking algoritmu.
     *
     * @param grid sudoku grid, ktorý sa má vyriešiť.
     * @return true, ak sa podarilo sudoku úspešne doriešiť, inak false.
     */
    public boolean solve(int[][] sudoku) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Prechádza všetky riadky a stĺpce mriežky.
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Ak dané políčko nemá ešte zadanú hodnotu.
                if (sudoku[row][col] == 0) {
                    Collections.shuffle(numbers);

                    // Skúša hodnoty od 1-9 v náhodnom poradí.
                    for (int value : numbers) {
                        // Overí, či daná hodnota môže byť uložená na dané políčko.
                        if (this.container.isValid(value, row, col)) {
                            sudoku[row][col] = value;

                            // Rekurzívne volá metódu solve na zvyšok mriežky.
                            if (this.solve(sudoku)) {
                                return true; // Ak riešenie funguje, sudoku je vyriešené.
                            }

                            // Resetuje uloženú hodnotu, ak riešenie zlyhalo.
                            sudoku[row][col] = 0;
                        }
                    }
                    return false; // Sudoku nemá riešenie.
                }
            }
        }
        return true; // Sudoku bolo úspešne vyriešené.
    }

    /**
     * Odstráni zadaný počet políčok z mriežky na základe obtiažnosti.
     *
     * @param difficulty Obtiažnosť hry, ktoré určuje počet odtránených políčok.
     */
    public void removeTiles(Difficulty difficulty) {
        Random random = new Random();
        int[][] playersSudoku = this.grid.getPlayersSudoku();
        int count = difficulty.getRemoveTilesCount();

        while (count > 0) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);

            // Odstáni hodnotu v políčku ak jeho hodnota nie je 0.
            if (playersSudoku[x][y] != 0) {
                playersSudoku[x][y] = 0;
                count--;
            }
        }
    }

    /**
     * Generovanie sudoku mriežky
     *
     * @param playersSudoku Dvojrozmerné pole do ktorého sa vygeneruje sudoku pre hráča.
     * @param difficulty Obtiaznosť hry, ktorá určuje počet odstránených políčok.
     */
    public void generateSudoku(int[][] playersSudoku, Difficulty difficulty) {
        this.solve(playersSudoku); // Vygenerovanie vyriešeného sudoku.
        this.removeTiles(difficulty); // Odstránenie n-počet políčok.
    }
}
