package viniciusmiranda.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import viniciusmiranda.db.DB;

@Getter
@Setter
public class Client extends User {
    public Client(long id, String name, String username, String address, String password, String cpf,
            String cellphone, UserType userType) {
        super(id, name, username, address, password, cpf, cellphone, userType);
    }

    private static final long serialVersionUID = 1L;
    private List<Account> accounts = new ArrayList<>();
    Manager manager;

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public String[] getAccountNumbersArray() {
        String[] array = new String[accounts.size()];

        for (int i = 0; i < array.length; i++) {
            array[i] = accounts.get(i).accountNumber;
        }
        return array;
    }

    public void loadClientAccounts() {
        String accountQuery = "SELECT account_number, balance, account_limit, account_type FROM account WHERE account_holder_id = ?";

        try (Connection conn = DB.getConnection();
                PreparedStatement accountStmt = conn.prepareStatement(accountQuery)) {


            accountStmt.setLong(1, id);
            ResultSet accountRs = accountStmt.executeQuery();
            while (accountRs.next()) {
                var accountNumber = accountRs.getString("account_number");
                var balance = accountRs.getDouble("balance");
                var accountLimit = accountRs.getDouble("account_limit");
                var accountType = accountRs.getInt("account_type");
                Account account;

                if (accountType == 1) {
                    account = new CheckingAccount(accountNumber, balance, accountLimit, this);
                } else {
                    account = new SavingsAccount(accountNumber, balance, accountLimit, this);
                }
                addAccount(account);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
