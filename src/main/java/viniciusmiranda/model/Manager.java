package viniciusmiranda.model;

import java.util.ArrayList;
import java.util.List;

public class Manager extends Employee {
    public Manager(Long id, String name, String username, String address, String password, String cpf,
            String cellphone, UserType userType, String employeeNumber) {
        super(id, name, username, address, password, cpf, cellphone, userType, employeeNumber);
    }

    private static final long serialVersionUID = 1L;
    private List<User> clients = new ArrayList<>();
}
