package viniciusmiranda.view;

import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

import viniciusmiranda.controller.AccountController;
import viniciusmiranda.model.Bank;

public class ClientView extends JFrame {
    private Bank bank = Bank.getInstance();
    private AccountController accountController = new AccountController();
    private String accountNumber;

    public ClientView() {
        bank.getLoggedInClient().loadClientAccounts();
        String[] accounts = bank.getLoggedInClient().getAccountNumbersArray();// array de numeros das contas
        setTitle("Bem vindo cliente, escolha uma conta");
        setLayout(new GridLayout(0, 1));
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 400);
        JList<String> accountList = new JList<>(accounts);
        // list selection mode single-selection.
        accountList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Adicionar JList ao JscrollPane.
        JScrollPane scrollPane = new JScrollPane(accountList);
        scrollPane.setPreferredSize(new Dimension(200, 300));

        JLabel jlab = new JLabel("Escolha uma conta");
        JButton confirmButton = new JButton("Confirmar");

        accountList.addListSelectionListener(le -> {
            accountNumber = accountList.getSelectedValue();
        });
        confirmButton.addActionListener(ae -> SwingUtilities
                .invokeLater(() -> new AccountOperationsView(accountController.getAccountByNumber(accountNumber))));
        // Add the list and label to the content pane.
        add(scrollPane);
        add(jlab);
        add(confirmButton);
        // Display the frame.
        setVisible(true);
    }
}