package viniciusmiranda.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import viniciusmiranda.db.DB;
import viniciusmiranda.model.*;

public class UserService {
    private Bank bank = Bank.getInstance();

    public void addUser(User user) {
        PreparedStatement st = null;
        ResultSet keys = null;

        try (Connection conn = DB.getConnection()) {
            if (user instanceof Client) {
                var client = (Client) user;
                st = prepareInsertClient(conn, client);
            } else {
                var employee = (Employee) user;
                st = prepareInsertEmployee(conn, employee);
            }

            st.executeUpdate();

            // Retrieve generated keys after executing the update
            keys = st.getGeneratedKeys();

            // Perform operations with the retrieved keys
            if (keys.next()) {
                long generatedId = keys.getLong(1);
                user.setId(generatedId); // Assuming User class has a setId method
            }

            // Add the client to the bank if the user is a Client
            if (user instanceof Client) {
                bank.addClient((Client) user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                if (keys != null)
                    keys.close();
                if (st != null)
                    st.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void deleteUser(long userId) {

        try (Connection conn = DB.getConnection();
                PreparedStatement st = conn.prepareStatement("DELETE FROM person WHERE user_id = ?")) {

            st.setLong(1, userId);

            st.executeUpdate();

            bank.deleteClient(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // cliente, funcionario, diretor e gerente estão na mesma tabela e somente
    // funcionarios possuem matricula
    private PreparedStatement prepareInsertEmployee(Connection conn, Employee employee)
            throws SQLException {
        String userInsertSql = "INSERT INTO person (name, username, address, cipher, cpf, cellphone, user_type, employee_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st = conn.prepareStatement(userInsertSql, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, employee.getName());
        st.setString(2, employee.getUsername());
        st.setString(3, employee.getAddress());
        st.setString(4, employee.getPassword());
        st.setString(5, employee.getCpf());
        st.setString(6, employee.getCellphone());
        st.setShort(7, (short) employee.getUserType().getCode());
        st.setString(8, employee.getEmployeeNumber());

        return st;
    }

    private PreparedStatement prepareInsertClient(Connection conn, Client client)
            throws SQLException {
        String clientInsertSql = "INSERT INTO person (name, username, address, cipher, cpf, cellphone, user_type) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement st = conn.prepareStatement(clientInsertSql, Statement.RETURN_GENERATED_KEYS);
        // gerado pelo db
        st.setString(1, client.getName());
        st.setString(2, client.getUsername());
        st.setString(3, client.getAddress());
        st.setString(4, client.getPassword());
        st.setString(5, client.getCpf());
        st.setString(6, client.getCellphone());
        st.setShort(7, (short) client.getUserType().getCode());

        return st;
    }
}
