package Bai7;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton registerButton;

    public RegisterForm() {
        setTitle("Register");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        registerButton = new JButton("Register");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    register(username, password);
                    JOptionPane.showMessageDialog(null, "Registration successful!");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Registration failed! " + ex.getMessage());
                }
            }
        });

        panel.add(new JLabel("Username: "));
        panel.add(usernameField);
        panel.add(new JLabel("Password: "));
        panel.add(passwordField);
        panel.add(registerButton);

        add(panel);
        setVisible(true);
    }

    private void register(String username, String password) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "INSERT INTO users (username, password) VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static void main(String[] args) {
        new RegisterForm();
    }
}
