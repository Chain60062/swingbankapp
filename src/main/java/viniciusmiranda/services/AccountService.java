package viniciusmiranda.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import viniciusmiranda.db.DB;
import viniciusmiranda.model.*;

public class AccountService {
    Bank bank = Bank.getInstance();

    // adiciona conta atrelada a um cliente especifico
    public void addAccount(Client client, boolean isSavings) {
        Connection conn = null;
        PreparedStatement st = null;
        ResultSet keys = null;

        try {
            conn = DB.getConnection();

            Account account = isSavings ? new SavingsAccount(client) : new CheckingAccount(client);
            // pega id gerado pelo db na tabela person da ultima pessoa inserida
            keys = st.getGeneratedKeys();
            
            if(keys == null) throw new SQLException("Erro ao inserir e retornar primary key de cliente");
            
            long clientId = (keys.next()) ? keys.getInt(1) : 0;
            // cria conta nova para cliente
            st = prepareInsertAccount(conn, account.getAccountNumber(), clientId, isSavings);

            st.executeUpdate();

            client.addAccount(account);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (keys != null)
                    keys.close();
                DB.closeStatement(st);
                DB.closeConnection(conn);
            } catch (Exception e) {
                System.err.println("Erro ao fechar recursos. Causado por: " + e.getMessage());
            }
        }
    }

    private PreparedStatement prepareInsertAccount(Connection conn, String accountNumber, long personId,
            boolean isSavings)
            throws SQLException {
        try (PreparedStatement st = conn
                .prepareStatement(
                        "INSERT INTO account (balance, account_number, account_type, account_holder_id) VALUES (?, ?, ?, ?)")) {
            st.setInt(1, 0);
            st.setString(2, accountNumber);
            st.setShort(3, isSavings ? (short) 1 : (short) 2);
            st.setLong(4, personId);

            return st;
        }
    }
}
