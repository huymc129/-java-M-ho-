package Bai7;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public LoginForm() {
        setTitle("Login");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);
        loginButton = new JButton("Login");

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    if (login(username, password)) {
                        JOptionPane.showMessageDialog(null, "Login successful!");
                        new WelcomeForm();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Login failed! Invalid username or password.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Login failed! " + ex.getMessage());
                }
            }
        });

        panel.add(new JLabel("Username: "));
        panel.add(usernameField);
        panel.add(new JLabel("Password: "));
        panel.add(passwordField);
        panel.add(loginButton);

        add(panel);
        setVisible(true);
    }

    private boolean login(String username, String password) throws SQLException {
        Connection connection = DBConnection.getConnection();
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, username);
        statement.setString(2, password);
        ResultSet resultSet = statement.executeQuery();
        boolean success = resultSet.next();
        resultSet.close();
        statement.close();
        connection.close();
        return success;
    }

    public static void main(String[] args) {
        new LoginForm();
    }
}
