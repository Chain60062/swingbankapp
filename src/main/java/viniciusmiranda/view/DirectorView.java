package viniciusmiranda.view;

import java.awt.GridLayout;

import javax.swing.*;

import viniciusmiranda.controller.DirectorController;
import viniciusmiranda.model.Bank;

import static javax.swing.JOptionPane.showMessageDialog;

public class DirectorView extends JFrame {
    private JButton registerButton;
    private JButton backButton;
    private JButton registerNewAccountButton;
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

    private DirectorController directorController = new DirectorController();
    private Bank bank = Bank.getInstance();

    public DirectorView() {
        setTitle("Cadastrar Gerente");
        setLayout(new GridLayout(0, 2, 12, 4));

        // Create Register button
        registerButton = new JButton("Cadastrar");
        backButton = new JButton("Sair");
        registerNewAccountButton = new JButton("Nova Conta");

        // Action listener for Register button
        registerButton.addActionListener(ae -> {
            // verificar se nulo
            var name = nameField.getText();
            var username = usernameField.getText();
            var cpf = cpfField.getText();
            var address = addressField.getText();
            var cellphone = cellphoneField.getText();
            var password = new String(passwordField.getPassword());

            directorController.registerNewManager(name, username, password, address, cpf, cellphone);

            showMessageDialog(null, "Gerente cadastrado com sucesso.");
        });

        addComponentsToFrame();
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        backButton.addActionListener(ae -> System.exit(0));

        registerNewAccountButton.addActionListener(ae -> {
            setVisible(false);
            SwingUtilities.invokeLater(CreateAccountView::new);
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
        add(registerNewAccountButton);
        add(backButton);
        add(registerButton);
    }
}
