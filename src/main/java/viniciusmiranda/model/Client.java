package viniciusmiranda.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Client extends User {
    public Client(long id, String name, String username, String address, String password, String cpf,
            String cellphone, UserType userType) {
        super(id, name, username, address, password, cpf, cellphone, userType);
    }

    private static final long serialVersionUID = 1L;
    private List<Account> accounts = new ArrayList<>();
    Manager manager;

    public void addAccount(Account account) {
        accounts.add(account);
    }
    public String[] getAccountNumbersArray(){
        String[] array = new String[accounts.size()];
        
        for(int i = 0;i < array.length; i++){
            array[i] = accounts.get(i).accountNumber;
        }
        return array;
    }
}
