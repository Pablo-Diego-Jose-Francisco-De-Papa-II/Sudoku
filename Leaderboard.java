import javax.swing.*;
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

        // 4 buttons pre výber
        JButton easyButton = new JButton("EASY");
        easyButton.setFont(new Font("Arial", Font.BOLD, 15));
        easyButton.setBackground(Color.lightGray);
        easyButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        easyButton.setBounds(10, 60, 84, 30);
        frame.add(easyButton);

        JButton mediumButton = new JButton("MEDIUM");
        mediumButton.setFont(new Font("Arial", Font.BOLD, 15));
        mediumButton.setBackground(Color.lightGray);
        mediumButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        mediumButton.setBounds(104, 60, 84, 30);
        frame.add(mediumButton);

        JButton hardButton = new JButton("HARD");
        hardButton.setFont(new Font("Arial", Font.BOLD, 15));
        hardButton.setBackground(Color.lightGray);
        hardButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        hardButton.setBounds(198, 60, 84, 30);
        frame.add(hardButton);

        JButton testButton = new JButton("TEST");
        testButton.setFont(new Font("Arial", Font.BOLD, 15));
        testButton.setBackground(Color.lightGray);
        testButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        testButton.setBounds(291, 60, 84, 30); // 375
        frame.add(testButton);



        String[] columns = {"#", "Name", "Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setSelectionBackground(Color.lightGray);
        table.setDefaultEditor(Object.class, null);
        table.setRowHeight(25);

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
            Scanner scanner = new Scanner(new File("leaderboard.txty"));
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
