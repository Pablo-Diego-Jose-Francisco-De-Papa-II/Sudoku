import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class LeaderboardGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Leaderboard :3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 600);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        JLabel title = new JLabel("LEADERBOARD");
        title.setBounds(35, 20, 320, 40);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(title);

        frame.setVisible(true);
    }
}
