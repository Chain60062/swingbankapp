package viniciusmiranda.model;

import lombok.Data;

@Data
public class Director extends Employee {
    public Director(Long id, String name, String username, String address, String password, String cpf,
            String cellphone, UserType userType, String employeeNumber) {
        super(id, name, username, address, password, cpf, cellphone, userType, employeeNumber);
    }

    private static final long serialVersionUID = 1L;
}
