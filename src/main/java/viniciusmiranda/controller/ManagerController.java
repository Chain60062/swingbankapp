package viniciusmiranda.controller;

import java.util.List;
import java.util.stream.Collectors;

import lombok.NoArgsConstructor;
import viniciusmiranda.model.Bank;
import viniciusmiranda.model.Client;
import viniciusmiranda.model.UserType;
import viniciusmiranda.services.AccountService;
import viniciusmiranda.services.UserService;

@NoArgsConstructor
public class ManagerController {
    AccountService accountService = new AccountService();
    UserService userService = new UserService();
    Bank bank = Bank.getInstance();

    public void registerNewClient(String name, String username, String password, String cpf,
            String address, Long managerId, String cellphone) {
        Client client = new Client(null, name, username, address, password, cpf, cellphone, managerId, UserType.CLIENT);
        userService.addUser(client);
    }

    public void registerNewClientAccountByUsername(String username, double limit, boolean isSavings) {
        accountService.addAccount(bank.getClientByUsername(username), limit, isSavings);
    }

    public String[] getClients(long managerId) {
        var allClients = bank.getClients();

        //filtra lista de clientes
        List<Client> filteredClients = allClients.stream()
                .filter(client -> client.getManagerId() == managerId)
                .collect(Collectors.toList());

        // converte para array de usernames
        return filteredClients.stream()
                .map(Client::getUsername)
                .toArray(String[]::new);
    }
}
