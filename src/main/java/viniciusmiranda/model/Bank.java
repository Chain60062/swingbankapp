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
    List<Account> accounts;
    User loggedInUser;
    Client loggedInClient;
    boolean isUserLoggedIn;
    String test;
    
    public Bank() {
        clients = new ArrayList<>();
        accounts = new ArrayList<>();
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
    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccountByNumber(String accountNumber) {
        for (var account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }

    public void deleteClient(long clientId) {
        clients.removeIf(u -> u.getId() == clientId);
    }
    
    public String[] getUsernamesArray() {
        String[] array = new String[clients.size()];

        for (int i = 0; i < array.length; i++) {
            array[i] = clients.get(i).username;
        }
        return array;
    }
}
