import java.util.Random;

public class Player {
    private final Random random = new Random();
    private String name;

    private void generateNewName() {
        this.name = String.format("Guest%d", this.random.nextInt(9000) + 1000);
    }

    public String getGeneratedName() {
        this.generateNewName();
        return this.name;
    }
}
