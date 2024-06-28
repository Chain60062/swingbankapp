package viniciusmiranda.controller;

import viniciusmiranda.model.Manager;
import viniciusmiranda.model.UserType;
import viniciusmiranda.services.UserService;

public class DirectorController {
    UserService userService = new UserService();

    public void registerNewManager(String name, String username, String password, String address, String cpf, String cellphone) {
        Manager manager = new Manager(null, name, username, address, password, cpf, cellphone, UserType.DIRECTOR, null);
        userService.addUser(manager);
    }

    public void removeManager(long managerId) {

    }
}
