import javax.swing.*;
import java.awt.*;

public class VictoryScreen {
    public static void main(String[] args) {
        JFrame frame = new JFrame("YOU WON! :3");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 175);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setLayout(null);

        // Nadpis
        JLabel title = new JLabel("CONGRATULATIONS!");
        title.setBounds(32, 20, 320, 30);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        frame.add(title);

        JLabel takenTime = new JLabel("Solved in x:xx!");
        takenTime.setBounds(120, 60, 320, 20);
        takenTime.setFont(new Font("Arial", Font.ITALIC, 20));
        frame.add(takenTime);

        JTextField nameTextField = new JTextField(" Enter your name...");
        nameTextField.setFont(new Font("Arial", Font.PLAIN, 14));
        nameTextField.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        nameTextField.setCaretPosition(nameTextField.getText().length());
        nameTextField.setBounds(10, 90, 230, 30);
        frame.add(nameTextField);

        JButton submitButton = new JButton("SUBMIT");
        submitButton.setFont(new Font("Arial", Font.BOLD, 20));
        submitButton.setBackground(Color.lightGray);
        submitButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        submitButton.setBounds(250, 90, 120, 30);
        frame.add(submitButton);

        frame.setVisible(true);
    }
}
