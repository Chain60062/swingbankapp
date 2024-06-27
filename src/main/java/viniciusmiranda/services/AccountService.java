package viniciusmiranda.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import viniciusmiranda.db.DB;
import viniciusmiranda.model.*;

public class AccountService {
    Bank bank = Bank.getInstance();

    // adiciona conta ao cliente
    public void addAccount(Client client, double limit, boolean isSavings) {
        PreparedStatement st = null;

        try (Connection conn = DB.getConnection()) {
            Account account = isSavings ? new SavingsAccount(client) : new CheckingAccount(client);

            st = prepareInsertAccount(conn, account.getAccountNumber(), limit, client.getId(), isSavings);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                client.addAccount(account);
                bank.addAccount(account);
            } else {
                throw new SQLException("Erro ao inserir conta.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                DB.closeStatement(st);
            } catch (Exception e) {
                System.err.println("Erro ao fechar recursos. Causado por: " + e.getMessage());
            }
        }
    }

    private PreparedStatement prepareInsertAccount(Connection conn, String accountNumber, double limit, long clientId,
            boolean isSavings)
            throws SQLException {
        PreparedStatement st = conn
                .prepareStatement(
                        "INSERT INTO account (account_number, balance, account_limit, account_type, account_holder_id) VALUES (?, ?, ?, ?, ?)");
        st.setString(1, accountNumber);
        st.setInt(2, 0);
        st.setDouble(3, limit);
        st.setShort(4, isSavings ? (short) 2 : (short) 1);
        st.setLong(5, clientId);

        return st;
    }
}
