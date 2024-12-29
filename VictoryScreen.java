import javax.swing.*;
import java.awt.*;

public class VictoryScreen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("YOU WON! :3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Nadpis
        JLabel title = new JLabel("CONGRATULATIONS!");
        title.setBounds(32, 20, 320, 30);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(title);
        frame.setVisible(true);

    }
}
