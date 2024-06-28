package viniciusmiranda.view;

import javax.swing.*;

import viniciusmiranda.controller.AuthController;
import viniciusmiranda.model.Bank;
import viniciusmiranda.model.UserType;

import java.awt.*;

import static javax.swing.JOptionPane.showMessageDialog;

public class MainWindowView extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private AuthController authController = new AuthController();
    private Bank bank = Bank.getInstance();

    public MainWindowView() {
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
            // verifica se há campos nulos
            if (username.isEmpty() || username.isBlank() || password.isEmpty() || password.isBlank()) {
                showMessageDialog(null, "Usuário e senha não podem ser nulos.");
            }
            // login
            var response = authController.login(username, password);
            // reposta de senha ou usuario invalido
            if (!response)
                showMessageDialog(null, "Senha ou usuário incorreto(s).");
            System.out.println("USER " + bank.getLoggedInUser());
            System.out.println("CLIENT " + bank.getLoggedInClient());
            // checagem de nivel de acesso
            if (bank.getLoggedInUser() != null) {
                var user = bank.getLoggedInUser();
                System.out.println("------------USER----------");
                System.out.println(user.getUserType());
                System.out.println(UserType.MANAGER);
                System.out.println(user.getUserType().equals(UserType.MANAGER));
                if (user.getUserType().equals(UserType.MANAGER)) {
                    System.out.println("USUÁRIO É UM GERENTE");
                    setVisible(false);
                    SwingUtilities.invokeLater(ManagerView::new);
                }
                if (user.getUserType().equals(UserType.DIRECTOR)) {
                    System.out.println("USUÁRIO É UM DIRETOR");
                    setVisible(false);
                    SwingUtilities.invokeLater(DirectorView::new);
                }
            } else if (bank.getLoggedInClient() != null) {
                System.out.println("USUÁRIO É UM CLIENTE");
                setVisible(false);
                SwingUtilities.invokeLater(ClientView::new);
            } else {
                showMessageDialog(null, "Erro inesperado de login, lamentamos.");
            }

        });

        setVisible(true);
    }
}
