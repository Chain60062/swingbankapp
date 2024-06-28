package viniciusmiranda.controller;

import viniciusmiranda.model.Manager;
import viniciusmiranda.model.UserType;
import viniciusmiranda.services.UserService;

public class DirectorController {
    UserService userService = new UserService();

    public void registerNewManager(String name, String username, String password, String address, String cpf, String cellphone) {
        /*
         * passar manager para o JDBC com userID nulo causa nullPointerException, 0 não
         * representa o userId final deste gerente, que é gerado normalmente pelo bd.
         */
        Manager manager = new Manager((long)0, name, username, address, password, cpf, cellphone, UserType.DIRECTOR, null);
        userService.addUser(manager);
    }

    public void removeManager(long managerId) {

    }
}
