import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Leaderboard {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Leaderboard :3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 420);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Nadpis
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

        // Vytvorenie modelu tabuľky
        String[] columns = {"#", "Name", "Time"};
        DefaultTableModel tableModel = new DefaultTableModel(columns, 0);

        // Vytvorenie tabuľky s modelom
        JTable table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 12));
        table.setRowHeight(25);

        // Pridanie dát do tabuľky
        tableModel.addRow(new Object[]{1, "Player 1", "1:30"});
        tableModel.addRow(new Object[]{2, "Player 2", "2:00"});
        tableModel.addRow(new Object[]{3, "Player 3", "2:30"});
        tableModel.addRow(new Object[]{4, "Player 3", "2:30"});
        tableModel.addRow(new Object[]{5, "Player 3", "2:30"});
        tableModel.addRow(new Object[]{6, "Player 3", "2:30"});
        tableModel.addRow(new Object[]{7, "Player 3", "2:30"});
        tableModel.addRow(new Object[]{8, "Player 3", "2:30"});
        tableModel.addRow(new Object[]{9, "Player 3", "2:30"});
        tableModel.addRow(new Object[]{10, "Player 3", "2:30"});


        // Umiestnenie tabuľky do JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 100, 365, 275); // Nastavujeme pozíciu a veľkosť
        frame.add(scrollPane);

        frame.setVisible(true);
    }
}
