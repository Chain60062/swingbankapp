package viniciusmiranda.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import viniciusmiranda.controller.ManagerController;
import viniciusmiranda.model.Bank;

public class CreateAccountView extends JFrame {
    private JRadioButton checkingAccountRadio = new JRadioButton("Corrente");
    private JRadioButton savingsAccountRadio = new JRadioButton("Poupança");
    private JButton registerButton = new JButton("Cadastrar");
    private ButtonGroup btnGroup = new ButtonGroup();
    private JLabel limitLabel = new JLabel("Limite: ");
    private JTextField limitField = new JTextField();
    private JLabel chooseAccLabel = new JLabel("Escolha uma conta");
    private JList<String> clientList;
    private JScrollPane clientsScrollPane;
    private ManagerController managerController = new ManagerController();
    private Bank bank = Bank.getInstance();

    public CreateAccountView() {
        var clients = bank.getUsernamesArray();// array de username dos clientes
        clientList = new JList<>(clients);
        // list selection mode single-selection.
        clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Adicionar JList ao JscrollPane.
        clientsScrollPane = new JScrollPane(clientList);
        clientsScrollPane.setPreferredSize(new Dimension(200, 300));
        
        addComponentsToFrame();
        setupFrame();

    }
    private void setupFrame(){
        setTitle("Cadastrar Usuário");
        setLayout(new GridLayout(0, 2, 12, 4));
        setSize(400, 400);
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void addComponentsToFrame() {
        btnGroup.add(checkingAccountRadio);
        btnGroup.add(savingsAccountRadio);
        add(checkingAccountRadio);
        add(savingsAccountRadio);
        add(limitLabel);
        add(limitField);
        add(chooseAccLabel);
        add(clientsScrollPane);
        add(new JLabel());
        add(registerButton);
    }
}
