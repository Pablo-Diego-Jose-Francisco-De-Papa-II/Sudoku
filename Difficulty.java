public enum Difficulty {
    TEST        (1,  "Test"),
    EASY        (40, "Ľahké"),
    MEDIUM      (50, "Stredné"),
    HARD        (60, "Ťažké"),
    VERY_HARD   (70, "Veľmi ťažké");

    private final int removeTilesCount;
    private final String description;

    Difficulty(int removeTilesCount, String description) {
        this.removeTilesCount = removeTilesCount;
        this.description = description;
    }

    public int getRemoveTilesCount() {
        return this.removeTilesCount;
    }

    public String getDescription() {
        return this.description;
    }
}
