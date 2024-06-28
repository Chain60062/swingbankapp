package viniciusmiranda.view;

import java.awt.GridLayout;

import javax.swing.*;

import viniciusmiranda.controller.AccountController;
import viniciusmiranda.model.*;

import static javax.swing.JOptionPane.showMessageDialog;

//View das operações de um uma conta(saque, deposito, simular rendimento)
public class AccountOperationsView extends JFrame {
    private AccountController accountController = new AccountController();
    // conta escolhida na lista
    Account account;
    private Bank bank = Bank.getInstance();
    private JLabel depositLabel = new JLabel("Depositar");
    private JLabel withdrawLabel = new JLabel("Sacar");
    private JTextField withdrawField = new JTextField("0.00");
    private JTextField depositField = new JTextField("0.00");
    private JButton withdrawButton = new JButton("Sacar");
    private JButton depositButton = new JButton("Depositar");
    private double balance = 0.0;
    private JLabel balanceValueLabel = new JLabel();
    private JLabel balanceLabel = new JLabel("Saldo: ");

    public AccountOperationsView(Account account) {
        this.account = account;
        setTitle("Sua conta");
        setLayout(new GridLayout(0, 2, 12, 4));
        setSize(400, 400);
        setVisible(true);

        balanceValueLabel.setText(String.valueOf(balance));
        add(balanceLabel);
        add(balanceValueLabel);
        add(depositLabel);
        add(depositField);
        add(new JLabel());
        add(depositButton);
        add(withdrawLabel);
        add(withdrawField);
        add(new JLabel());
        add(withdrawButton);

        depositButton.addActionListener(ae -> {
            String value = depositField.getText();
            if (value.isEmpty() || !isValidNumber(value) || Double.parseDouble(value) < 1.0) {
                showMessageDialog(null, "Valor de depósito inválido");
            } else {
                accountController.deposit(account, Double.parseDouble(value));
                showMessageDialog(null, "Depósito realizado com sucesso na conta " + account);
                balance = accountController.getUpdatedBalance(account.getAccountNumber());
                balanceValueLabel.setText(String.valueOf(balance));
            }
        });
        withdrawButton.addActionListener(ae -> {
            String value = withdrawField.getText();
            if (value.isEmpty() || !isValidNumber(value) || Double.parseDouble(value) < 1.0) {
                showMessageDialog(null, "Valor de saque inválido");
            } else {
                boolean response = accountController.withdraw(account, Double.parseDouble(value));

                if (!response)
                    showMessageDialog(null, "Valor de saque excede saldo da conta");
                else {
                    showMessageDialog(null, "Saque realizado com sucesso na conta " + account);
                    balance = accountController.getUpdatedBalance(account.getAccountNumber());
                    balanceValueLabel.setText(String.valueOf(balance));
                }
            }
        });
    }

    // Helper method to check if a string is a valid number
    private boolean isValidNumber(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
