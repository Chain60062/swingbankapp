package viniciusmiranda.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import viniciusmiranda.db.DB;
import viniciusmiranda.model.*;

public class BankController {
    private Bank bank = Bank.getInstance();

    public void loadUsersAndAccounts() {
        String userQuery = "SELECT user_id, name, username, cipher, address, cpf, user_type, cellphone FROM person WHERE user_type = 1";// clientes
        String accountQuery = "SELECT account_number, balance, account_limit, account_type FROM account WHERE account_holder_id = ?";

        try (Connection conn = DB.getConnection();
                PreparedStatement userStmt = conn.prepareStatement(userQuery);
                PreparedStatement accountStmt = conn.prepareStatement(accountQuery)) {

            var userRs = userStmt.executeQuery();
            // carregar clients
            while (userRs.next()) {
                var id = userRs.getLong("user_id");
                var name = userRs.getString("name");
                var username = userRs.getString("username");
                var password = userRs.getString("cipher");
                var address = userRs.getString("address");
                var cpf = userRs.getString("cpf");
                var userType = userRs.getInt("user_type");
                var cellphone = userRs.getString("cellphone");

                accountStmt.setLong(1, id);
                //carregar accounts
                if (userType == 1) {
                    Client client = new Client(id, name, username, address, password, cpf,
                            cellphone, UserType.CLIENT);

                    try (ResultSet accountRs = accountStmt.executeQuery()) {
                        while (accountRs.next()) {
                            var accountNumber = accountRs.getString("account_number");
                            var balance = accountRs.getDouble("balance");
                            var accountLimit = accountRs.getDouble("account_limit");
                            var accountType = accountRs.getInt("account_type");
                            Account account;

                            if (accountType == 1) {
                                account = new CheckingAccount(accountNumber, balance, accountLimit, client);
                            } else {
                                account = new SavingsAccount(accountNumber, balance, accountLimit, client);
                            }
                            client.addAccount(account);
                        }
                    }
                    bank.addClient(client);
                }

            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void removeClient(long userId) {
        bank.deleteClient(userId);
    }
}
