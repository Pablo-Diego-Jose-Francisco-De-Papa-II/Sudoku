/**
 * Enum trieda Difficulty definuje rôzne úrovne obťiažnosti pre sudoku.
 * Každá úroveň obsahuje počet políčok, ktoré sa majú odstrániť a popis obtiažnosti.
 */
public enum Difficulty {
    EASY        (30, "Ľahké"),
    MEDIUM      (45, "Stredné"),
    HARD        (50, "Ťažké"),
    TEST        (1,  "Test");

    private final int removeTilesCount;
    private final String description;

    /**
     * Konštruktor pre nastavenie počtu odtránených políčok a popisu obtiažnosti.
     *
     * @param removeTilesCount Počet políčok, ktoré sa majú odstrániť.
     * @param description Popis obtiažnosti.
     */
    Difficulty(int removeTilesCount, String description) {
        this.removeTilesCount = removeTilesCount;
        this.description = description;
    }

    /**
     * Získanie počet políčok, ktoré sa majú odstrániť pre danú obtiažnosť.
     *
     * @return Počet odstránených políčok.
     */
    public int getRemoveTilesCount() {
        return this.removeTilesCount;
    }

    /**
     * Získanie popisu obtiažnosti.
     *
     * @return Popis obtiažnosti.
     */
    public String getDescription() {
        return this.description;
    }
}
