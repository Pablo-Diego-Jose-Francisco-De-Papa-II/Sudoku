import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.*;
import java.util.HexFormat;

public class LeaderboardGUI {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Leaderboard :3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 700);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Nadpis
        JLabel title = new JLabel("LEADERBOARD");
        title.setBounds(35, 20, 320, 40);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(title);

        // Nápis EASY
        JLabel easy = new JLabel("EASY:");
        easy.setBounds(10, 120, 75, 20);
        easy.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(easy);

        for (int e = 1; e < 6; e++) {
            JLabel easyPlayers = new JLabel(e + ".");
            easyPlayers.setForeground(Color.ORANGE);
            easyPlayers.setBounds(125, 50 + e * 25, 750, 15);
            easyPlayers.setFont(new Font("Arial", Font.BOLD, 15));
            frame.add(easyPlayers);
        }

        // Nápis MEDIUM
        JLabel medium = new JLabel("MEDIUM:");
        medium.setBounds(10, 270, 90, 20);
        medium.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(medium);

        for (int m = 1; m < 6; m++) {
            JLabel easyPlayers = new JLabel(m + ".");
            easyPlayers.setBounds(125, 200 + m * 25, 750, 15);
            easyPlayers.setFont(new Font("Arial", Font.BOLD, 15));
            frame.add(easyPlayers);
        }

        // Nápis HARD
        JLabel hard = new JLabel("HARD:");
        hard.setBounds(10, 420, 75, 20);
        hard.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(hard);

        for (int h = 1; h < 6; h++) {
            JLabel easyPlayers = new JLabel(h + ".");
            easyPlayers.setBounds(125, 350 + h * 25, 750, 15);
            easyPlayers.setFont(new Font("Arial", Font.BOLD, 15));
            frame.add(easyPlayers);
        }

        // Nápis TEST
        JLabel test = new JLabel("TEST:");
        test.setBounds(10, 570, 75, 20);
        test.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(test);

        for (int t = 1; t < 6; t++) {
            JLabel easyPlayers = new JLabel(t + ".");
            easyPlayers.setBounds(125, 500 + t * 25, 750, 15);
            easyPlayers.setFont(new Font("Arial", Font.BOLD, 15));
            frame.add(easyPlayers);
        }

        frame.setVisible(true);

        // TODO -> za cislom pozicia sa zo suboru vypise v takomto poradi: pozicia + meno + cas. a este by si mohol spravit ze prve tri pozicie budu mat inu farbicku reprezentujucu tu danu pozidiu :3
    }
}
