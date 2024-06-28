package viniciusmiranda;

import javax.swing.SwingUtilities;

import viniciusmiranda.controller.*;
import viniciusmiranda.model.Bank;
import viniciusmiranda.view.MainWindowView;
import viniciusmiranda.view.ManagerView;

public class Main {

    public static void main(String[] args) {
        BankController bankController = new BankController();
        bankController.loadUsersAndAccounts();
        
        SwingUtilities.invokeLater(MainWindowView::new);
    }
}