package viniciusmiranda.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import viniciusmiranda.db.DB;
import viniciusmiranda.model.*;

public class AccountController {
    private Bank bank = Bank.getInstance();

    public double getUpdatedBalance(String accountNumber) {
        var allAccounts = bank.getAccounts();

        return allAccounts.stream()
                .filter(account -> account.getAccountNumber().equals(accountNumber))
                .findFirst()
                .map(Account::getBalance)
                .orElseThrow(() -> new IllegalArgumentException("Conta não encontrada"));
    }

    public Account getAccountByNumber(String accountNumber) {
        return bank.getAccountByNumber(accountNumber);
    }

    // false caso valor invalido
    public boolean withdraw(Account account, double value) throws UnsupportedOperationException {
        if (value > account.getBalance())
            return false;

        updateAccountBalance(account, value, false);
        return true;
    }

    public void deposit(Account account, double value) throws UnsupportedOperationException {
        if (value < 1)
            throw new UnsupportedOperationException("Valor de depósito inválido.");

        updateAccountBalance(account, value, true);
    }

    private void updateAccountBalance(Account account, double value, boolean isDeposit) {
        double updatedBalance = isDeposit ? account.getBalance() + value : account.getBalance() - value;

        Connection conn = null;
        PreparedStatement st = null;
        int rowsAffectedBalance = 0;
        int rowsAffectedTransaction = 0;

        try {
            conn = DB.getConnection();

            st = updateBalanceStatement(conn, updatedBalance, account.getAccountNumber());
            rowsAffectedBalance = st.executeUpdate();

            rowsAffectedTransaction = st.executeUpdate();

            if (rowsAffectedBalance <= 0 || rowsAffectedTransaction <= 0) {
                conn.rollback();
                return;
            }

            account.setBalance(updatedBalance);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                DB.closeStatement(st);
                DB.closeConnection(conn);
            } catch (Exception e) {
                System.err.println("Erro ao fechar recursos. Causado por: " + e.getMessage());
            }
        }
    }

    private PreparedStatement updateBalanceStatement(Connection conn, double updatedBalance, String accountNumber)
            throws SQLException {
        PreparedStatement st = conn.prepareStatement("UPDATE account SET balance = ? WHERE account_number = ?");
        st.setDouble(1, updatedBalance);
        st.setString(2, accountNumber);

        return st;
    }
}
