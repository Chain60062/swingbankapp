package viniciusmiranda;

import javax.swing.SwingUtilities;

import viniciusmiranda.controller.BankController;
import viniciusmiranda.model.Bank;
import viniciusmiranda.view.*;

public class Main {
    public static void main(String[] args) {
        BankController bankController = new BankController();
        bankController.loadUsersAndAccounts();

        Bank bank = Bank.getInstance();
        System.out.println(bank.getClients().size());
        for (var client : bank.getClients()) {
            System.out.println(client.getName() + " possui as contas: " + client.getAccounts());
        }

        Main2.test();
        System.out.println(bank.getTest());
        // preencher model com dados do banco de dados
        SwingUtilities.invokeLater(ClientView::new);
    }
}