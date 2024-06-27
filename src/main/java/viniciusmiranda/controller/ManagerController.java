package viniciusmiranda.controller;

import lombok.NoArgsConstructor;
import viniciusmiranda.model.Client;
import viniciusmiranda.model.UserType;
import viniciusmiranda.services.AccountService;
import viniciusmiranda.services.UserService;

@NoArgsConstructor
public class ManagerController {
    AccountService accountService = new AccountService();
    UserService userService = new UserService();

    public void registerNewClient(String name, String username, String password, String cpf,
            String address, String cellphone) {
                Client client = new Client(0, name, username, address, password, cpf, cellphone, UserType.CLIENT);
                userService.addUser(client);
                
    }

    public void registerNewClientAccount(long clientId) {

    }
}
