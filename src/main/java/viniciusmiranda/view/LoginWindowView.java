package viniciusmiranda.view;

import javax.swing.*;
import java.awt.*;

public class LoginWindowView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginWindowView() {
        // Set up the frame
        setTitle("Login Window");
        setSize(600, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Create components
        JPanel panel = new JPanel();
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");

        usernameField = new JTextField(20);
        passwordField = new JPasswordField(20);

        JButton loginButton = new JButton("Login");

        // Add components to the panel
        panel.add(usernameLabel);
        panel.add(usernameField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(loginButton);

        // Add the panel to the frame
        add(panel, BorderLayout.CENTER);

        // Set up the action listener for the login button
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            System.out.println("Username: " + username + ", Password: " + password);
            // Here you can add your logic to handle the login (e.g., check against a
            // database)
        });

        setVisible(true);
    }
}
