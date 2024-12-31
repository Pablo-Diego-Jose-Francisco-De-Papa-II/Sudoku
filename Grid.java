/**
 * Trieda Grid reprezentuje Sudoku mriežku, ktorá obsahuje vyriešenú i hráčsku mriežku.
 * Poskytuje metódy na získanie hodnôt, tlač Sudoku do konzoly a resetovanie mriežky.
 */
public class Grid {
    private int[][] solvedSudoku = new int[9][9];
    private int[][] playersSudoku = new int[9][9];
    private boolean[][] isFixed = new boolean[9][9];

    /**
     * Získa správnu hodnotu z vyriešeného sudoku pre daný riadok a stĺpec.
     *
     * @param row Riadok, z ktorého sa hodnota získava.
     * @param col Stĺpec, z ktorého sa hodnota získava.
     * @return Hodnota z vyriešeného sudoku na zadaných súradniciach.
     */
    public int getCorrectValue(int row, int col) {
        return this.solvedSudoku[row][col];
    }

    /**
     * Získa hodnotu z hráčovej Sudoku mriežky pre daný riadok a stĺpec.
     *
     * @param row Riadok, z ktorého sa hodnota získava.
     * @param col Stĺpec, z ktorého sa hodnota získava.
     * @return Hodnota z hráčskej sudoku na zadaných súradniciach.
     */
    public int getPlayersValue(int row, int col) {
        return this.playersSudoku[row][col];
    }

    /**
     * Získa celú sudoku mriežku určenú pre hráča.
     *
     * @return 2D pole reprezentujúce aktuálny stav sudoku mriežky určenú pre hráča.
     */
    public int[][] getPlayersSudoku() {
        return this.playersSudoku;
    }

    /**
     * Získa vyriešené sudoku.
     *
     * @return 2D pole reprezentujúce vyriešenú sudoku mriežku.
     */
    public int[][] getSolvedSudoku() {
        return this.solvedSudoku;
    }

    /**
     * Vytlačí zadanú sudoku mriežku do konzoly.
     *
     * @param grid 2D pole reprezentujúce sudoku mriežku, ktorú chceme vytlačiť.
     */
    public void printSudoku(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            // Vloží vodorovnú čiaru po každom 3. riadku.
            if (row % 3 == 0 && row != 0) {
                System.out.println("---------------------");
            }

            for (int col = 0; col < grid[row].length; col++) {
                // Vytlačí hodnotu na daných súradniciach.
                System.out.print(grid[row][col] + " ");

                // Vloží zvislú čiaru po každom 3. stĺpci.
                if ((col + 1) % 3 == 0 && col != 8) {
                    System.out.print("| ");
                }
            }
            // Prejde na nový riadok.
            System.out.println();
        }
    }

    /**
     * Resetuje obe mriežky na 2 prázdne 2D polia.
     */
    public void resetSudoku() {
        this.playersSudoku = new int[9][9];
        this.solvedSudoku = new int[9][9];
        this.isFixed = new boolean[9][9];
    }

    /**
     * Nastaví, či je dané políčko v sudoku mriežke fixné.
     *
     * @param row Riadok, s ktorým pracujeme.
     * @param col Stĺpec, s ktorým pracujeme.
     * @param fixed Určuje, či je políčko fixné.
     */
    public void setFixed(int row, int col, boolean fixed) {
        this.isFixed[row][col] = fixed;
    }

    /**
     * Zistí, či je dané políčko v sudoku mriežke fixné.
     *
     * @param row Riadok, z ktorého sa hodnota získava.
     * @param col Stĺpec, z ktorého sa hodnota získava.
     * @return Vráti true, ak je políčko fixné.
     */
    public boolean isFixed(int row, int col) {
        return this.isFixed[row][col];
    }

    public void printIsFixed() {
        for (int row = 0; row < isFixed.length; row++) {
            // Vloží vodorovnú čiaru po každom 3. riadku.
            if (row % 3 == 0 && row != 0) {
                System.out.println("---------------------");
            }

            for (int col = 0; col < isFixed[row].length; col++) {
                // Vytlačí hodnotu na daných súradniciach.
                System.out.print(isFixed[row][col] + " ");

                // Vloží zvislú čiaru po každom 3. stĺpci.
                if ((col + 1) % 3 == 0 && col != 8) {
                    System.out.print("| ");
                }
            }
            // Prejde na nový riadok.
            System.out.println();
        }
    }
}
