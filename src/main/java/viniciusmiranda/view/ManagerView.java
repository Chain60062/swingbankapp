package viniciusmiranda.view;

import java.awt.GridLayout;

import javax.swing.*;

import viniciusmiranda.controller.ManagerController;

public class ManagerView extends JFrame {
    private JRadioButton clientRadioButton;
    private JRadioButton managerRadioButton;
    private JRadioButton employeeRadioButton;
    private JRadioButton directorRadioButton;
    private JButton registerButton;
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
    private ButtonGroup buttonGroup = new ButtonGroup();

    private int userType;//tipo de usuario(gerente, diretor, cliente...)
    private ManagerController managerController = new ManagerController();

    public ManagerView() {
        setTitle("Cadastrar Usuário");
        setLayout(new GridLayout(0, 2, 12, 4));
        setUpAccountTypeRadioButtons();

        // Create Register button
        registerButton = new JButton("Register");

        // Action listener for Register button
        registerButton.addActionListener(ae -> {

            //verificar se nulo
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
    }

    private void setUpAccountTypeRadioButtons(){
        // Create radio buttons
        clientRadioButton = new JRadioButton("Cliente", true);
        employeeRadioButton = new JRadioButton("Funcionário");
        managerRadioButton = new JRadioButton("Gerente");
        directorRadioButton = new JRadioButton("Diretor");

        // Group radio buttons (only one can be selected)
        buttonGroup = new ButtonGroup();
        buttonGroup.add(clientRadioButton);
        buttonGroup.add(employeeRadioButton);
        buttonGroup.add(managerRadioButton);
        buttonGroup.add(directorRadioButton);
    }

    private void addComponentsToFrame() {
        add(clientRadioButton);
        add(employeeRadioButton);
        add(managerRadioButton);
        add(directorRadioButton);
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
        add(new JLabel());
        add(registerButton);
    }
}
