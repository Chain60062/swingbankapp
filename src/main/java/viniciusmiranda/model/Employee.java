package viniciusmiranda.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee extends User {
    private String employeeNumber;

    public Employee(long id, String name, String username, String address, String password, String cpf,
            String cellphone, UserType userType, String employeeNumber) {
        super(id, name, username, address, password, cpf, cellphone, userType);
        this.employeeNumber = employeeNumber;
    }
}