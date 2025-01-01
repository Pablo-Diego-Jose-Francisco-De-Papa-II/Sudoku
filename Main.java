/**
 * Trieda Main, ktorá spúšťa aplikáciu Sudoku.
 */
public class Main {

    /**
     * Hlavná metóda aplikácie.
     * Spúšťa hru Sudoku a inicializuje grafické rozhranie.
     *
     * @param args argumenty príkazového riadku.
     */
    public static void main(String[] args) {
        // Vytvorenie novej inštancie hry Sudoku s prázdnou mriežkou.
        SudokuGame game = new SudokuGame(new Grid());

        // Nastavenie grafického rozhrania pre hru.
        game.setUpGUI();
    }
}
