import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Trieda Leaderboard slúži pre zobrazenie rebríčku v grafickom užívateľskom rozhraní.
 * Zobrazí tabuľku s výsledkami hráčov, zoredených podľa obtiažnosti.
 */
public class Leaderboard {

    /**
     * Nastavenie grafického rozhrania pre leaderboard.
     * Vytvára okno, nadpis, tlačidlá pre výber úrovne obtiažnosti a samotnú tabuľku TOP 10 výsledkami.
     */
    public void setUpGUI() {
        // Vytorenie okna pre leaderboard.
        JFrame frame = new JFrame("Leaderboard :3");
        frame.setSize(400, 420);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Nadpis slova "LEADERBOARD".
        JLabel title = new JLabel("LEADERBOARD");
        title.setBounds(35, 10, 320, 40);
        title.setFont(new Font("Arial", Font.BOLD, 40));
        frame.add(title);

        // Tlačidlá pre výber úrovne obtiažnosti.
        JButton easyButton = this.createButton("EASY", 10);
        JButton mediumButton = this.createButton("MEDIUM", 104);
        JButton hardButton = this.createButton("HARD", 198);
        JButton testButton = this.createButton("TEST", 291);

        // Pridanie tlačidiel do okna.
        frame.add(easyButton);
        frame.add(mediumButton);
        frame.add(hardButton);
        frame.add(testButton);

        // Nastavenie názvov stĺpcov tabuľky.
        String[] columns = {"#", "Name", "Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Vytvorenie tabuľky a nastavenie jej vlastností.
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setDefaultEditor(Object.class, null); // Zablokovanie úprav bunkám.
        table.setRowHeight(25);

        // Pridanie tabuľky do okna
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 366, 273);
        frame.add(scrollPane);

        // Akcie pre tlačidlá, ktoré načítajú dáta podľa zvolenej obtiažnosti.
        easyButton.addActionListener(e -> this.loadLeaderboardData(tableModel, "Easy"));
        mediumButton.addActionListener(e -> this.loadLeaderboardData(tableModel, "Medium"));
        hardButton.addActionListener(e -> this.loadLeaderboardData(tableModel, "Hard"));
        testButton.addActionListener(e -> this.loadLeaderboardData(tableModel, "Test"));

        // Zobrazenie okna.
        frame.setVisible(true);
    }

    /**
     * Načíta údaje leaderboardu z txt súboru a vloží ich do tabuľky.
     *
     * @param tableModel Model tabuľky, do ktorého sa načítané údaje pridajú.
     * @param difficulty Úroveň obtiažnosti, pre ktorú sa načítajú údaje.
     */
    private void loadLeaderboardData(DefaultTableModel tableModel, String difficulty) {
        tableModel.setRowCount(0); // Vyresetovanie tabuľky.
        ArrayList<String[]> players = new ArrayList<>();

        try {
            // Načítanie dát zo súboru "leaderboard.txt"
            Scanner scanner = new Scanner(new File("leaderboard.txt"));
            String[] line;

            // Spracovanie každého riadku v súbore.
            while (scanner.hasNextLine()) {
                line = scanner.nextLine().split(",");

                // Ak sa úroveň obtiažnosti zhoduje, pridá hráča do listu.
                if (line[1].equals(difficulty)) {
                    players.add(new String[]{line[0], line[2]});
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        // Zoradenie hráčov podľa času.
        players.sort(Comparator.comparingInt(p -> Integer.parseInt(p[1])));

        // Pridanie prvých 10 hráčov do tabuľky.
        for (int i = 0; i < 10; i++) {
            tableModel.addRow(new Object[]{i + 1, players.get(i)[0], this.convertTime(Integer.parseInt(players.get(i)[1]))});
        }
    }

    /**
     * Vytvorenie tlačidla pre výber obtiažnosti.
     *
     * @param text Nápis v tlačidle.
     * @param x Pozícia na osi x.
     * @return Vytvorené tlačidlo.
     */
    private JButton createButton(String text, int x) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 15));
        button.setBackground(Color.LIGHT_GRAY);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        button.setBounds(x, 60, 84, 30);
        return button;
    }

    /**
     * Prevod času zo sekúnd na formát MM:SS.
     *
     * @param totalSeconds Celkový čas v sekundách.
     * @return Čas vo formáte MM:SS.
     */
    private String convertTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
