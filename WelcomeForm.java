package Bai7;

import javax.swing.*;
import java.awt.*;

public class WelcomeForm extends JFrame {
    public WelcomeForm() {
        setTitle("Welcome");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        JLabel welcomeLabel = new JLabel("Welcome!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        panel.add(welcomeLabel);

        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new WelcomeForm();
    }
}
