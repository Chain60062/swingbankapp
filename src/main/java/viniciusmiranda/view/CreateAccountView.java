package viniciusmiranda.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import viniciusmiranda.controller.ManagerController;
import viniciusmiranda.model.Bank;

import static javax.swing.JOptionPane.showMessageDialog;

public class CreateAccountView extends JFrame {
    private JRadioButton checkingAccountRadio = new JRadioButton("Corrente", true);
    private JRadioButton savingsAccountRadio = new JRadioButton("Poupança");
    private JButton registerButton = new JButton("Cadastrar");
    private ButtonGroup btnGroup = new ButtonGroup();
    private JLabel limitLabel = new JLabel("Limite: ");
    private JTextField limitField = new JTextField("0.00");
    private JLabel chooseAccLabel = new JLabel("Escolha um de seus clientes: ");
    private JButton backButton = new JButton("Voltar");
    private JList<String> clientList;
    private JScrollPane clientsScrollPane;
    private ManagerController managerController = new ManagerController();
    private Bank bank = Bank.getInstance();
    private String username;
    private String[] clients;

    public CreateAccountView() {
        clients = managerController.getClients(bank.getLoggedInUser().getId());
        clientList = new JList<>(clients);
        // list selection mode single-selection.
        clientList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Adicionar JList ao JscrollPane.
        clientsScrollPane = new JScrollPane(clientList);
        clientsScrollPane.setPreferredSize(new Dimension(200, 300));

        addComponentsToFrame();
        setupFrame();
        // ao selecionar na list atualizar variavel
        clientList.addListSelectionListener(le -> username = clientList.getSelectedValue());

        // cadastrar com controller
        registerButton.addActionListener(ae -> {
            String limit = limitField.getText();
            if (limit.isEmpty() || !isValidNumber(limit) || Double.parseDouble(limit) < 1.0) {
                showMessageDialog(null, "Limite Inválido");
            } else {
                managerController.registerNewClientAccountByUsername(username, Double.parseDouble(limit),
                        savingsAccountRadio.isSelected());
                showMessageDialog(null, "Nova conta para cliente " + username + " foi criada.");
            }
        });
        backButton.addActionListener(ae -> {
            setVisible(false);
            SwingUtilities.invokeLater(ManagerView::new);
        });
    }

    private boolean isValidNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void setupFrame() {
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
        add(backButton);
        add(registerButton);
    }
}
