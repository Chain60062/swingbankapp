package viniciusmiranda.controller;

import viniciusmiranda.model.Manager;
import viniciusmiranda.model.UserType;
import viniciusmiranda.services.UserService;

public class DirectorController {
    UserService userService = new UserService();

    public void registerNewManager(String name, String username, String password, String address, String cpf) {
        Manager manager = new Manager(null, name, username, address, password, cpf, address, UserType.MANAGER, cpf);
        userService.addUser(manager);
    }

    public void removeManager(long managerId) {

    }
}
