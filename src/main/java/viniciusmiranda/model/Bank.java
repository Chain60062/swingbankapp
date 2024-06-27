package viniciusmiranda.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Bank {
    private static Bank instance;
    List<Client> clients;
    User loggedInUser;
    boolean isUserLoggedIn;
    String test;
    
    public Bank() {
        clients = new ArrayList<>();
    }

    public static Bank getInstance() {
        if (instance == null) {
            instance = new Bank();
        }
        return instance;
    }

    public void addClient(Client client) {
        clients.add(client);
    }

    public void deleteClient(long clientId) {
        clients.removeIf(u -> u.getId() == clientId);
    }
}
