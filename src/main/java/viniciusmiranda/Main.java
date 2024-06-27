package viniciusmiranda;

import javax.swing.SwingUtilities;

import viniciusmiranda.controller.AccountController;
import viniciusmiranda.controller.BankController;
import viniciusmiranda.model.Bank;
import viniciusmiranda.view.*;

public class Main {
    public static void main(String[] args) {
        BankController bankController = new BankController();
        AccountController accountController = new AccountController();
        Bank bank = Bank.getInstance();
        bankController.loadUsersAndAccounts();
        bank.setLoggedInClient(bank.getClients().get(0));

        // preencher model com dados do banco de dados
        SwingUtilities.invokeLater(CreateAccountView::new);
    }
}