package viniciusmiranda.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Manager extends Employee {
    private static final long serialVersionUID = 1L;
    private List<Person> clients = new ArrayList<>();

    public void registerNewClient(){

    }

    public void registerNewClientAccount(){

    }
}
