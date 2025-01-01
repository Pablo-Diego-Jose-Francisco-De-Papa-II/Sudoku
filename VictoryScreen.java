import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Cursor;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class VictoryScreen {
    private final Player player;

    public VictoryScreen(Player player) {
        this.player = player;
    }

    public void setUpGui() {
        JFrame frame = new JFrame("YOU WON! :3");
        frame.setSize(400, 175);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Title
        JLabel title = new JLabel("CONGRATULATIONS!");
        title.setBounds(32, 20, 320, 30);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(title);

        // Time taken label
        long elapsedTimeMillis = this.player.getElapsedTime();
        String formattedTime = this.player.formatTime(elapsedTimeMillis);
        JLabel takenTime = new JLabel("Solved in " + formattedTime + "!");
        takenTime.setBounds(120, 60, 320, 20);
        takenTime.setFont(new Font("Arial", Font.ITALIC, 20));
        frame.add(takenTime);

        // Name input field
        JTextField nameTextField = new JTextField(" Enter your playersName...");
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        nameTextField.setCaretPosition(nameTextField.getText().length());
        nameTextField.setBounds(10, 90, 230, 30);
        frame.add(nameTextField);

        // Submit button
        JButton submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.setBackground(Color.LIGHT_GRAY);
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setBounds(250, 90, 120, 30);
        frame.add(submitButton);

        submitButton.addActionListener(ae -> {
            String name = nameTextField.getText().trim();
            if (name.isEmpty() || name.equals("Enter your playersName...")) {
                name = this.player.getGeneratedName();
            }

            this.writeIntoFile(name, this.player.getDifficulty(), String.valueOf(this.player.getElapsedTime() / 1000));
        });

        frame.setVisible(true);
    }

    public void writeIntoFile(String name, String difficulty, String seconds) {
        File file = new File("leaderboard.txt");
        String data = String.format("%s,%s,%s,%s\n", name, difficulty, seconds, this.player.getTime());

        try {
            FileWriter txtWriter = new FileWriter(file, true);
            txtWriter.write(data);
            txtWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
