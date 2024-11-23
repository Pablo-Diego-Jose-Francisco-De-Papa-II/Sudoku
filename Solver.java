public class Solver {
    // Overí, či sa číslo nachádza v danom riadku.
    private boolean isNumberInRow(Grid grid, int value, int row) {
        for (int index = 0; index < 9; index++) {
            if (value == grid.getPlayersWholeSudoku()[row][index]) {
                return true; // Nachádza sa.
            }
        }

        return false; // Nenachádza sa riadku.
    }

    // Overí, či sa číslo nachádza v danom stĺpci.
    private boolean isNumberInColumn(Grid grid, int value, int col) {
        for (int index = 0; index < 9; index++) {
            if (value == grid.getPlayersWholeSudoku()[index][col]) {
                return true; // Nachádza sa.
            }
        }

        return false; // Nenachádza sa v stĺpci.
    }

    // Overí, či sa číslo nachádza v danom bloku.
    private boolean isNumberInBox(Grid grid, int value, int row, int col) {
        int[][] playersSudoku = grid.getPlayersWholeSudoku();
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

    private boolean isValid(Grid grid, int value, int row, int col) {
        return !isNumberInRow(grid, value, row) &&
                !isNumberInColumn(grid, value, col) &&
                !isNumberInBox(grid, value, row, col);
    }

    private void solve(Grid grid, int value, int row, int col) {

    }



    public static void main(String[] args) {
        Grid grid = new Grid();
        Solver idk = new Solver();

        System.out.println(idk.isValid(grid, 9, 1, 1));
    }
}
