public class Grid {
    int[][] solvedSudoku = {
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

    int[][] playersSudoku = {
            {5, 0, 0, 0, 7, 0, 0, 1, 0},
            {0, 7, 0, 1, 0, 0, 3, 0, 0},
            {0, 0, 8, 0, 0, 2, 0, 6, 0},
            {8, 0, 0, 0, 6, 0, 0, 2, 0},
            {0, 0, 6, 8, 0, 3, 0, 0, 1},
            {0, 1, 0, 0, 2, 0, 0, 0, 6},
            {0, 6, 0, 5, 0, 0, 2, 0, 0},
            {0, 0, 7, 0, 0, 9, 0, 3, 0},
            {0, 4, 0, 0, 8, 0, 0, 0, 9}
    };

    public int getCorrectValue(int row, int col) {
        return solvedSudoku[row][col];
    }

    public int getPlayersValue(int row, int col) {
        return playersSudoku[row][col];
    }

    public int[][] getPlayersWholeSudoku() {
        return playersSudoku;
    }

    public static void main(String[] args) {
        Grid idk = new Grid();
        System.out.println(idk.getCorrectValue(1,8));
    }
}
