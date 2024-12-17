public class Generator {
    private final Grid grid;
    private final int difficulty;

    public Generator(Grid grid, int difficulty) {
        this.grid = grid;
        this.difficulty = difficulty;
    }

    public int getDifficulty() {
        return this.difficulty;
    }

    public void idk() {
        int[] randomFirstLine = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        grid.getSolvedSudoku();
    }

    public void removeCells(int difficulty) {

    }
}