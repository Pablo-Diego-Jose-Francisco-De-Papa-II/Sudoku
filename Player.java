import java.util.Random;

public class Player {
    private final Random random = new Random();
    private String name;

    public void setName() {
        this.name = String.format("Guest-%d", this.random.nextInt(900) + 100);
    }

    public String getName() {
        return this.name;
    }
}
