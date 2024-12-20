import java.util.Random;

public class Generator {
    private final Grid grid;

    public Generator(Grid grid) {
        this.grid = grid;
    }

    public void removeTiles(Difficulty difficulty) {
        Random random = new Random();
        int[][] playersSudoku = this.grid.getPlayersSudoku();
        int count = difficulty.getRemoveTilesCount();

        while (count > 0) {
            int x = random.nextInt(9);
            int y = random.nextInt(9);

            if (playersSudoku[x][y] != 0) {
                playersSudoku[x][y] = 0;
                count--;
            }
        }
    }
}