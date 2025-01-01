import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * Trieda Player reprezentuje hráča, obsahuje jeho meno, obtiažnosť a časové údaje súvislé s hrou.
 */
public class Player {
    private final Random random = new Random();
    private String playersName;
    private String difficulty;

    private long startTime;
    private long endTime;

    /**
     * Spustenie časovaču.
     */
    public void startTimer() {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Ukončenie časovaču.
     */
    public void endTimer() {
        this.endTime = System.currentTimeMillis();
    }

    /**
     * Získa uplynutý čas medzi začiatkom a koncom hry v milisekundách.
     *
     * @return Uplynutý čas v milisekundách.
     */
    public long getElapsedTime() {
        return this.endTime - this.startTime;
    }

    /**
     * Vráti aktuálny čas a dátum vo formáte "yyyy/MM/dd HH:mm:ss".
     *
     * @return Formátovaný aktuálny čas a dátum.
     */
    public String getTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    /**
     * Vráti vygenerované meno hráča. Ak ešte neexistuje, automaticky sa vygeneruje.
     *
     * @return Vygenerované meno hráča.
     */
    public String getGeneratedName() {
        this.generateNewName();
        return this.playersName;
    }

    /**
     * Vygeneruje nové meno pre hráča vo formáte "GuestXXXX", kde XXXX je náhodné číslo.
     */
    private void generateNewName() {
        this.playersName = String.format("Guest%d", this.random.nextInt(9000) + 1000);
    }

    /**
     * Nastaví obtiažnosť hry pre hráča.
     *
     * @param difficulty Obtiažnosť hry (napr. ľahká, stredná, ťažká).
     * */
    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = String.valueOf(difficulty);
    }

    /**
     * Získa aktuálnu obtiažnosť hry.
     *
     * @return Obtiažnosť hry ako reťazec.
     */
    public String getDifficulty() {
        return this.difficulty;
    }

    /**
     * Formátuje čas v milisekundách na formát "MM:SS".
     *
     * @param timeInMillis Čas v milisekundách.
     * @return Formátovaný čas vo formáte "MM:SS".
     */
    public String formatTime(long timeInMillis) {
        long minutes = timeInMillis / 60000;
        long seconds = (timeInMillis % 60000) / 1000;
        return String.format("%d:%02d", minutes, seconds);
    }
}
