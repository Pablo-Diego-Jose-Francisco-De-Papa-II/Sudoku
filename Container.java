/**
 * Trieda Cointainer slúži na kontrolu pohybov v sudoku.
 * Obsahuje metódy na kontrolu prítomnosti čísla v riadku, stĺpci a bloku.
 */

public class Container {
    private final Grid grid;

    /**
     * Vytvára nový Cointainer s referenciou na daný Grid.
     * @param grid Grid reprezentujúci aktuálnu Sudoku mriežku.
     */
    public Container(Grid grid) {
        this.grid = grid;
    }

    /**
     * Overí, či sa číslo nachádza v danom riadku.
     * @param value Kontrolovaná hodnota.
     * @param row Kontrolovaný riadok pre prítomnosť hodnoty.
     * @return true, ak sa hodnota nachádza v riadku, inak false.
     */
    private boolean isNumberInRow(int value, int row) {
        for (int index = 0; index < 9; index++) {
            if (value == grid.getPlayersSudoku()[row][index]) {
                return true; // Nachádza sa.
            }
        }

        return false; // Nenachádza sa riadku.
    }

    /**
     * Overí, či sa číslo nachádza v danom stĺpci.
     * @param value Kontrolovaná hodnota.
     * @param col Kontrolovaný stĺpec pre prítomnosť hodnoty.
     * @return true, ak sa hodnota nachádza v stĺpci, inak false.
     */
    private boolean isNumberInColumn(int value, int col) {
        for (int index = 0; index < 9; index++) {
            if (value == grid.getPlayersSudoku()[index][col]) {
                return true; // Nachádza sa.
            }
        }

        return false; // Nenachádza sa v stĺpci.
    }

    /**
     * Overí, či sa číslo nachádza v danom bloku.
     * @param value Kontrolovaná hodnota.
     * @param row Riadok, kde začína blok.
     * @param col Stĺpec, kde začína blok
     * @return true, ak sa hodnota nachádza v bloku, inak false.
     */
    private boolean isNumberInBox(int value, int row, int col) {
        int[][] playersSudoku = grid.getPlayersSudoku();
        int x = (row / 3) * 3; // Začiatok bloku v riadku.
        int y = (col / 3) * 3; // Začiatok blocu v stĺpci.

        // Prehladá 3x3 blok.
        for (int col_index = 0; col_index < 3; col_index++) {
            for (int row_index = 0; row_index < 3; row_index++) {
                if (value == playersSudoku[x + col_index][y + row_index]) {
                    return true; // Nachádza sa.
                }
            }
        }

        return false; // Nenachádza sa v bloku.
    }

    /**
     * Overí pomocou predošlých metód, či hodnota môže byť doplnená na to políčko.
     * @param value Kontrolovaná hodnota.
     * @param row Riadok, kde sa nachádza políčko.
     * @param col Stĺpec, kde sa nachádza políčko.
     * @return true, ak je hodnota validná, inak false.
     */
    public boolean isValid(int value, int row, int col) {
        return !isNumberInRow(value, row) &&
                !isNumberInColumn(value, col) &&
                !isNumberInBox(value, row, col);
    }
}
