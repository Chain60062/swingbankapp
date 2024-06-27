package viniciusmiranda.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import viniciusmiranda.db.DB;
import viniciusmiranda.model.*;

public class LoginController {
    Bank bank = Bank.getInstance();
    public boolean login(String name, String password, UserType userType) {

        try (Connection conn = DB.getConnection()) {
            String sql = "SELECT * FROM person p WHERE name =? AND password =? JOIN account a ON ac.account_holder_id = p.user_id";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, name);
                pstmt.setString(2, password);

                try (ResultSet rs = pstmt.executeQuery()) {
                    loadUser(rs);
                }
            }
        } catch (Exception e) {
            System.out.println("Error during login: " + e.getMessage());
        }
        return true;
    }

    public void loadUser(ResultSet rs) {
        User user;
        Account account;
        try {
            while (rs.next()) {
                long id = rs.getShort("person_id");
                short accountType = rs.getShort("account_type");
                short userType = rs.getShort("user_type");
                String accountNumber = rs.getString("account_number");
                double balance = rs.getDouble("balance");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("cipher");
                String cpf = rs.getString("cpf");
                String address = rs.getString("address");
                String cellphone = rs.getString("cellphone");
                String employeeNumber = rs.getString("employee_number");

                switch (userType) {
                    case 1:
                        Client client = new Client(id, name,
                                username, address, password, cpf,
                                cellphone, UserType.get(userType));

                        if (accountType == 1) {
                            account = new SavingsAccount(client);
                        } else {
                            account = new CheckingAccount(client);
                        }
                        // vincula conta ao cliente e adiciona cliente a lista de usuarios do bank
                        client.addAccount(account);
                        bank.setLoggedInUser(client);
                        break;
                    case 2:
                        user = new Employee(id, name, username, address, password, cpf, cellphone,
                                UserType.get(userType), employeeNumber);
                        bank.setLoggedInUser(user);
                        break;
                    case 3:
                        user = new Manager(id, name, username, address, password, cpf, cellphone,
                                UserType.get(userType), employeeNumber);
                        bank.setLoggedInUser(user);
                        break;
                    case 4:
                        user = new Director(id, name, username, address, password, cpf, cellphone,
                                UserType.get(userType), employeeNumber);
                        bank.setLoggedInUser(user);
                        break;
                    default:
                        throw new RuntimeException("Tipo de conta inválido.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}