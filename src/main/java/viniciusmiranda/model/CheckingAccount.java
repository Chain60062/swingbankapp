package viniciusmiranda.model;

public class CheckingAccount extends Account {
    public CheckingAccount(Client accountHolder){
        super(accountHolder);
    }

    public CheckingAccount(String accountNumber, double balance, double limit, Client accountHolder) {
        super(accountNumber, balance, limit, accountHolder);
    }
}
