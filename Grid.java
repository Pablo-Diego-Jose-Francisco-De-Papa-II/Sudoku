public class Grid {
    private int[][] solvedSudoku = {
            {5, 3, 4, 6, 7, 8, 9, 1, 2},
            {6, 7, 2, 1, 9, 5, 3, 4, 8},
            {1, 9, 8, 3, 4, 2, 5, 6, 7},
            {8, 5, 9, 7, 6, 1, 4, 2, 3},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {7, 1, 3, 9, 2, 4, 8, 5, 6},
            {9, 6, 1, 5, 3, 7, 2, 8, 4},
            {2, 8, 7, 4, 1, 9, 6, 3, 5},
            {3, 4, 5, 2, 8, 6, 1, 7, 9}
    };

    private int[][] playersSudoku = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {4, 2, 6, 8, 5, 3, 7, 9, 1},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
    };


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

    public void printSudoku(int[][] sudoku) {
        for (int row = 0; row < sudoku.length; row++) {
            if (row % 3 == 0 && row != 0) {
                System.out.println("---------------------");
            }

            for (int col = 0; col < sudoku[row].length; col++) {
                System.out.print(sudoku[row][col] + " ");
                if ((col + 1) % 3 == 0 && col != 8) {
                    System.out.print("| ");
                }
            }
            System.out.println();
        }
    }
}
