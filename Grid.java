public class Grid {
    private final int[][] solvedSudoku = new int[9][9];
    private final int[][] playersSudoku = new int[9][9];

    public int getCorrectValue(int row, int col) {
        return this.solvedSudoku[row][col];
    }

    public int getPlayersValue(int row, int col) {
        return this.playersSudoku[row][col];
    }

    public int[][] getPlayersSudoku() {
        return this.playersSudoku;
    }

    public int[][] getSolvedSudoku() {
        return this.solvedSudoku;
    }

    public void printSudoku(int[][] grid) {
        for (int row = 0; row < grid.length; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("---------------------");
            }

            for (int col = 0; col < grid[row].length; col++) {
                System.out.print(grid[row][col] + " ");
                if ((col + 1) % 3 == 0 && col != 8) {
                    System.out.print("| ");
                }
            }
            System.out.println();
        }
    }
}
