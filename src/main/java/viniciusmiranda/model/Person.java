package viniciusmiranda.model;

import java.io.Serializable;

import jakarta.persistence.MappedSuperclass;
import lombok.NoArgsConstructor;

@MappedSuperclass
@NoArgsConstructor
public abstract class Person implements Serializable {
    private static final long serialVersionUID = 1L;
    protected Long id;
    protected String name;
    protected String address;
    protected String cpf;
    protected String telefone;
}
