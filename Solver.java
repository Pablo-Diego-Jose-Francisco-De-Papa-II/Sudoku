public class Solver {
    private final int GRID_SIZE = 9;

    // Overí, či sa číslo nachádza v danom riadku.
    private boolean isNumberInRow(int value, int row) {
        Grid grid = new Grid();

        for (int index = 0; index < this.GRID_SIZE; index++) {
            if (value == grid.getPlayersWholeSudoku()[row][index]) {
                return true; // Nachádza sa.
            }
        }

        return false; // Nenachádza sa riadku.
    }

    // Overí, či sa číslo nachádza v danom stĺpci.
    private boolean isNumberInColumn(int value, int col) {
        Grid grid = new Grid();

        for (int index = 0; index < this.GRID_SIZE; index++) {
            if (value == grid.getPlayersWholeSudoku()[index][col]) {
                return true; // Nachádza sa.
            }
        }

        return false; // Nenachádza sa v stĺpci.
    }

    // Overí, či sa číslo nachádza v danom bloku.
    private boolean isNumberInBox(int value, int row, int col) {
        Grid grid = new Grid();

        int[][] playersSudoku = grid.getPlayersWholeSudoku();
        int x = (row / 3) * 3; // Začiatok bloku v riadku.
        int y = (col / 3) * 3; // Začiatok blocu v stĺpci.

        // Prehladá 3x3 blok
        for (int col_index = 0; col_index < 3; col_index++) {
            for (int row_index = 0; row_index < 3; row_index++) {
                if (value == playersSudoku[x + col_index][y + row_index]) {
                    return true; // Nachádza sa.
                }
            }
        }

        return false; // Nenachádza sa v bloku.
    }



    public static void main(String[] args) {
        Solver idk = new Solver();
        System.out.println(idk.isNumberInRow(4, 8));
        System.out.println(idk.isNumberInColumn(9, 5));
        System.out.println(idk.isNumberInBox(6, 3, 6));
    }
}
