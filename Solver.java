/**
 * Trieda Solver rieši sudoku pomocou back-tracking algoritmu.
 * Obsahuje referencie na triedy Grid a Container, ktoré sú potrebné na validáciu a prácu s hráčskym sudoku.
 */

public class Solver {
    private final Grid grid;
    private final Container container;

    /**
     * Konštruktor triedy Solver.
     * Inicializuje objekty Grid a Container.
     */
    public Solver(Grid grid) {
        this.grid = grid;
        this.container = new Container(grid);
    }

    /**
     * Rieši sudoku pomocou back-tracking algoritmu.
     * @param grid sudoku grid, ktorý sa má vyriešiť.
     * @return true, ak sa podarilo sudoku úspešne doriešiť, inak false.
     */
    public boolean solve(Grid grid) {
       int[][] playersSudoku = grid.getPlayersSudoku();

       // Prechádza všetky riadky a stĺpce gridu.
        for (int row = 0; row < 9; row++) {
            for (int col = 0; col < 9; col++) {
                // Ak dané políčko nemá ešte zadanú hodnotu.
                if (playersSudoku[row][col] == 0) {
                    // Skúša hodnoty od 1-9.
                    for (int value = 1; value <= 9; value++) {
                        // Overí, či daná hodnota môže byť uložená na dané políčko.
                        if (this.container.isValid(value, row, col)) {
                            playersSudoku[row][col] = value;

                            // Rekurzívne volá metódu solve na zvyšok gridu
                            if (solve(grid)) {
                                return true; // Ak riešenie funguje, Sudoku je vyriešené
                            }

                            // Resetuje uloženú hodnotu, keď riešenie zlyhalo.
                            playersSudoku[row][col] = 0;
                        }
                    }
                    return false; // Sudoku nemá riešenie.
                }
            }
        }
        return true; // Sudoku bolo úspešne vyriešené.
    }
}
