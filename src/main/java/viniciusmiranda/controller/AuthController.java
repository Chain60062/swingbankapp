package viniciusmiranda.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import viniciusmiranda.db.DB;
import viniciusmiranda.model.*;

public class AuthController {
    Bank bank = Bank.getInstance();

    public boolean login(String username, String password) {

        try (Connection conn = DB.getConnection()) {
            String sql = "SELECT * FROM person p WHERE username =? AND cipher =?";

            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, username);
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
                long id = rs.getShort("user_id");
                short userType = rs.getShort("user_type");
                String name = rs.getString("name");
                String username = rs.getString("username");
                String password = rs.getString("cipher");
                String cpf = rs.getString("cpf");
                String address = rs.getString("address");
                String cellphone = rs.getString("cellphone");
                Long managerId = rs.getLong("manager_id");
                String employeeNumber = rs.getString("employee_number");

                switch (userType) {
                    case 1:
                        Client client = new Client(id, name,
                                username, address, password, cpf,
                                cellphone, managerId, UserType.get(userType));
                        client.setLoggedIn(true);
                        bank.setLoggedInClient(client);
                        break;
                    case 2:
                        user = new Employee(id, name, username, address, password, cpf, cellphone,
                                UserType.get(userType), employeeNumber);
                        user.setLoggedIn(true);
                        bank.setLoggedInUser(user);
                        break;
                    case 3:
                        user = new Manager(id, name, username, address, password, cpf, cellphone,
                                UserType.get(userType), employeeNumber);
                        user.setLoggedIn(true);
                        bank.setLoggedInUser(user);
                        break;
                    case 4:
                        user = new Director(id, name, username, address, password, cpf, cellphone,
                                UserType.get(userType), employeeNumber);
                        user.setLoggedIn(true);
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