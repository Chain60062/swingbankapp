package viniciusmiranda.model;

public enum AccountType {
    CLIENT(1),
    EMPLOYEE(2),
    MANAGER(3),
    DIRECTOR(4);

    private int number;
    AccountType(int number){
        this.number = number;
    }
}
