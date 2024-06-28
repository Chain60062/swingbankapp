package viniciusmiranda.view;

import java.awt.GridLayout;

import javax.swing.*;

import viniciusmiranda.controller.ManagerController;

public class ManagerView extends JFrame {
    private JButton registerButton;
    private JButton backButton;
    private JPasswordField passwordField = new JPasswordField();
    private JTextField usernameField = new JTextField();
    private JTextField nameField = new JTextField();
    private JTextField cpfField = new JTextField();
    private JTextField addressField = new JTextField();
    private JTextField cellphoneField = new JTextField();
    private JLabel usernameLabel = new JLabel("Nome de usuário:");
    private JLabel nameLabel = new JLabel("Nome completo:");
    private JLabel cpfLabel = new JLabel("CPF:");
    private JLabel passwordLabel = new JLabel("Senha:");
    private JLabel addressLabel = new JLabel("Endereço:");
    private JLabel cellphoneLabel = new JLabel("Celular:");
    private ManagerController managerController = new ManagerController();

    public ManagerView() {
        setTitle("Cadastrar Cliente");
        setLayout(new GridLayout(0, 2, 12, 4));

        // Create Register button
        registerButton = new JButton("Cadastrar");
        backButton = new JButton("Voltar");

        // Action listener for Register button
        registerButton.addActionListener(ae -> {
            // verificar se nulo
            var name = nameField.getText();
            var username = usernameField.getText();
            var cpf = cpfField.getText();
            var address = addressField.getText();
            var cellphone = cellphoneField.getText();
            var password = new String(passwordField.getPassword());

            managerController.registerNewClient(name, username, password, cpf, address, cellphone);
        });

        addComponentsToFrame();
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        backButton.addActionListener(ae -> {
            setVisible(false);
            SwingUtilities.invokeLater(MainWindowView::new);
        });
    }

    private void addComponentsToFrame() {
        add(nameLabel);
        add(nameField);
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(cpfLabel);
        add(cpfField);
        add(addressLabel);
        add(addressField);
        add(cellphoneLabel);
        add(cellphoneField);
        add(backButton);
        add(registerButton);
    }
}
