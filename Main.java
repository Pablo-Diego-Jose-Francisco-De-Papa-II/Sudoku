public class Main {
    public static void main(String[] args) {
        Grid grid = new Grid();
        Solver solver = new Solver(grid);
        Generator generator = new Generator(grid);

        /*
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1; i++) {
            solver.solve(grid);

            grid.printSudoku(grid.playersSudoku);
            System.out.println(i + "\n");

            grid.playersSudoku = new int[][]{
                    {7, 0, 0, 2, 0, 0, 0, 0, 5},
                    {3, 0, 0, 0, 8, 0, 0, 2, 0},
                    {2, 0, 6, 7, 4, 3, 0, 0, 0},
                    {0, 0, 0, 0, 0, 0, 0, 7, 0},
                    {5, 9, 0, 3, 0, 4, 1, 0, 0},
                    {0, 0, 4, 1, 0, 0, 0, 3, 0},
                    {0, 2, 0, 0, 0, 8, 9, 0, 0},
                    {6, 8, 0, 0, 0, 0, 2, 5, 0},
                    {0, 0, 5, 0, 0, 0, 0, 8, 7}
            };
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Time taken: " + (endTime - startTime) + "ms");
        */

        solver.solve(grid.getPlayersSudoku());
        grid.printSudoku(grid.getPlayersSudoku());

        System.out.println();

        generator.removeTiles(Difficulty.TEST);
        grid.printSudoku(grid.getPlayersSudoku());
    }
}
