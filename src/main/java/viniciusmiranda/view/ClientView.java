package viniciusmiranda.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.*;

import viniciusmiranda.controller.AccountController;
import viniciusmiranda.model.Bank;

public class ClientView extends JFrame {

    private Bank bank = Bank.getInstance();
    private AccountController accountController = new AccountController();
    private String accountNumber;

    public ClientView() {
        var accounts = bank.getLoggedInClient().getAccountNumbersArray();// array de numeros das contas
        setTitle("Escolha uma conta");
        setLayout(new GridLayout(0,1));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        JList<String> jlst = new JList<>(accounts);
        // list selection mode single-selection.
        jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        // Adicionar JList ao JscrollPane.
        JScrollPane jscrlp = new JScrollPane(jlst);
        jscrlp.setPreferredSize(new Dimension(200, 300));

        JLabel jlab = new JLabel("Escolha uma conta");
        JButton confirmButton = new JButton("Confirmar");

        jlst.addListSelectionListener(le -> {
            accountNumber = jlst.getSelectedValue();
        });
        confirmButton.addActionListener(ae -> SwingUtilities
                .invokeLater(() -> new AccountOperationsView(accountController.getAccountByNumber(accountNumber))));
        // Add the list and label to the content pane.
        add(jscrlp);
        add(jlab);
        add(confirmButton);
        // Display the frame.
        setVisible(true);
    }
}