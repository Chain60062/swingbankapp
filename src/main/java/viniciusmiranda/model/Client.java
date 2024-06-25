package viniciusmiranda.model;

import jakarta.persistence.Entity;

@Entity
public class Client extends Person{
    private static final long serialVersionUID = 1L;
    Manager manager;
    
}
