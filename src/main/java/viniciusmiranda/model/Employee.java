package viniciusmiranda.model;

import jakarta.persistence.Entity;

@Entity
public class Employee extends Person{
    private static final long serialVersionUID = 1L;
    private String number;
}