import java.util.Arrays;
import java.util.Collections;
import java.util.List;

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
}
