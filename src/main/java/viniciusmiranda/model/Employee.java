package viniciusmiranda.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Employee extends Person{
    private static final long serialVersionUID = 1L;
    private String number;
}