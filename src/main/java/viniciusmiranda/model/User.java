package viniciusmiranda.model;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public abstract class User implements Serializable {
    
    private static final long serialVersionUID = 1L;
    public User(Long id, String name, String username, String address, String password, String cpf,
            String cellphone, UserType userType) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.address = address;
        this.password = password;
        this.cpf = cpf;
        this.cellphone = cellphone;
        this.userType = userType;
    }
    protected Long id;
    protected String name;
    protected String username;
    protected String address;
    protected String password;
    protected String cpf;
    protected String cellphone;
    protected UserType userType;
    protected boolean isLoggedIn;
}
