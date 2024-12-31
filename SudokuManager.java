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
     * @return true, ak sa podarilo sudoku úspešne doriešiť.
     */
    public boolean solve(int[][] grid) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        // Prechádza všetky riadky a stĺpce mriežky.
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Ak dané políčko nemá ešte zadanú hodnotu.
                if (grid[row][col] == 0) {
                    Collections.shuffle(numbers);

                    // Skúša hodnoty od 1-9 v náhodnom poradí.
                    for (int value : numbers) {
                        // Overí, či daná hodnota môže byť uložená na dané políčko.
                        if (this.container.isValid(value, row, col)) {
                            grid[row][col] = value;

                            // Rekurzívne volá metódu solve na zvyšok mriežky.
                            if (this.solve(grid)) {
                                return true; // Ak riešenie funguje, sudoku je vyriešené.
                            }

                            // Resetuje uloženú hodnotu, ak riešenie zlyhalo.
                            grid[row][col] = 0;
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
        int count = difficulty.getRemoveTilesCount();
        int[][] playersSudoku = this.grid.getPlayersSudoku();

        // Skopíruje hodnoty zo playersSudoku do solvedSudoku.
        for (int i = 0; i < 9; i++) {
            System.arraycopy(playersSudoku[i], 0, this.grid.getSolvedSudoku()[i], 0, 9);
        }

        // Náhodné odstraňovanie hodnôt z playersSudoku.
        while (count > 0) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);

            // Odstáni hodnotu v políčku ak jeho hodnota nie je 0.
            if (playersSudoku[x][y] != 0) {
                int savedValue = playersSudoku[x][y];
                playersSudoku[x][y] = 0;

                // Overenie, či sudoku má stále len jedno riešenie.
                if (this.countSolutions(playersSudoku) != 1) {
                    playersSudoku[x][y] = savedValue; // Vráti hodnotu naspäť.
                } else {
                    count--; // Políčko bolo úspešne odstránené.
                    this.grid.setFixed(x, y, false); // Nastaví, že políčko je prázdne.
                }
            }
        }

        // Nastaví true do listu isFixed, ak je políčko pevné.
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                if (this.grid.getPlayersValue(row, col) != 0) {
                    this.grid.setFixed(row, col, true);
                }
            }
        }
    }

    /**
     * Spočíta počet možných riešení pre aktuálnu sudoku mriežku.
     *
     * @param grid Sudoku mriežka, s ktorou bude metóda pracovať.
     * @return Počet možných riešení
     */
    public int countSolutions(int[][] grid) {
        return this.countSolutions(grid, 0, 0);
    }

    /**
     * Pomocná rekurzívna metóda na spočítanie počtu riešení.
     *
     * @param grid Sudoku mriežka.
     * @param row Aktuálny riadok
     * @param col Aktuálny stĺpec.
     * @return Počet riešení.
     */
    private int countSolutions(int[][] grid, int row, int col) {
        int solutions = 0;

        // Ak sa dostal na koniec mriežky, má 1 riešenie.
        if (row == 9) {
            return 1;
        }

        // Preskočenie políčka, ktoré nie je prázdne.
        if (grid[row][col] != 0) {
            return this.countSolutions(grid, col == 8 ? row + 1 : row, (col + 1) % 9);
        }

        // Skúša hodnoty od 1-9.
        for (int num = 1; num <= 9; num++) {
            // Overí, či daná hodnota môže byť uložená na dané políčko.
            if (this.container.isValid(num, row, col)) {
                grid[row][col] = num;
                solutions += this.countSolutions(grid, col == 8 ? row + 1 : row, (col + 1) % 9);
                grid[row][col] = 0; // Resetuje uloženú hodnotu, ak riešenie zlyhalo.

                // Ak počet riešení presiahne 1, ukončí hľadanie.
                if (solutions > 1) {
                    return solutions;
                }
            }
        }
        return solutions;
    }

    /**
     * Generovanie sudoku mriežky.
     *
     * @param difficulty Obtiaznosť hry, ktorá určuje počet odstránených políčok.
     */
    public void generateSudoku(Difficulty difficulty) {
        this.grid.resetSudoku();
        this.solve(this.grid.getPlayersSudoku()); // Vygenerovanie vyriešeného sudoku.
        this.removeTiles(difficulty); // Odstránenie n-počet políčok.
    }
}
