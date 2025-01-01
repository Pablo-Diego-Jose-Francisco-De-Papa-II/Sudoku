import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Cursor;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Trieda VictoryScreen zobrazuje obrazovku výhry po úspešnom vyriešení Sudoku.
 * Umožňuje hráčovi zadať svoje meno a uložiť informácie o výhre do súboru.
 */
public class VictoryScreen {
    private final Player player;

    /**
     * Konštruktor triedy VictoryScreen.
     *
     * @param player Objekt hráča, ktorý obsahuje údaje o hráčovi (napr. čas a obtiažnosť).
     */
    public VictoryScreen(Player player) {
        this.player = player;
    }

    /**
     * Nastavuje a zobrazuje GUI obrazovky výhry.
     * Vytvára okno s gratuláciou, zobrazuje čas riešenia a umožňuje hráčovi zadať meno.
     */
    public void setUpGui() {
        JFrame frame = new JFrame("YOU WON! :3");
        frame.setSize(400, 175);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Vytvorenie a nastavenie názvu "CONGRATULATIONS!"
        JLabel title = new JLabel("CONGRATULATIONS!");
        title.setBounds(32, 20, 320, 30);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(title);

        // Vytvorenie a nastavenie textu s časom, za ktorý bolo sudoku vyriešené.
        long elapsedTimeMillis = this.player.getElapsedTime();
        String formattedTime = this.player.formatTime(elapsedTimeMillis);
        JLabel takenTime = new JLabel("Solved in " + formattedTime + "!");
        takenTime.setBounds(120, 60, 320, 20);
        takenTime.setFont(new Font("Arial", Font.ITALIC, 20));
        frame.add(takenTime);

        // Vytvorenie textového poľa pre zadanie mena hráča.
        JTextField nameTextField = new JTextField(" Enter your playersName...");
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        nameTextField.setCaretPosition(nameTextField.getText().length());
        nameTextField.setBounds(10, 90, 230, 30);
        frame.add(nameTextField);

        // Vytvorenie tlačidla "SUBMIT" pre odoslanie údajov.
        JButton submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.setBackground(Color.LIGHT_GRAY);
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setBounds(250, 90, 120, 30);
        frame.add(submitButton);

        // Akcia pri stlačení tlačidla "SUBMIT": Uloženie údajov do súboru.
        submitButton.addActionListener(ae -> {
            String name = nameTextField.getText().trim();
            if (name.isEmpty() || name.equals("Enter your playersName...")) {
                name = this.player.getGeneratedName();
            }

            // Uloženie údajov o hráčovi do súboru.
            this.writeIntoFile(name, this.player.getDifficulty(), String.valueOf(this.player.getElapsedTime() / 1000));
            frame.dispose(); // Zavretie okna po odoslaní údajov.
        });

        // Zobrazenie okna.
        frame.setVisible(true);
    }

    /**
     * Uloží údaje o hráčovi do súboru "leaderboard.txt".
     *
     * @param name Meno hráča.
     * @param difficulty Obtiažnosť hry.
     * @param seconds Čas riešenia v sekundách.
     */
    public void writeIntoFile(String name, String difficulty, String seconds) {
        File file = new File("leaderboard.txt"); // Súbor, do ktorého sa ukladajú údaje.
        String data = String.format("%s,%s,%s,%s\n", name, difficulty, seconds, this.player.getTime()); // Formátovanie údajov.

        try {
            FileWriter txtWriter = new FileWriter(file, true); // Otvorenie súboru v režime "append" (pridanie na koniec).
            txtWriter.write(data); // Zápis údajov do súboru.
            txtWriter.close();  // Zavretie súboru.

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e); // Zobrazenie chybovej správy, ak došlo k chybe pri zápise.
            throw new RuntimeException(e); // Vytvorenie výnimky, ak došlo k chybe pri zápise do súboru.
        }
    }
}
