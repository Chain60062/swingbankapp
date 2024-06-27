package viniciusmiranda.model;

public class SavingsAccount extends Account {
    public SavingsAccount(Client accountHolder){
        super(accountHolder);
    }
    
    public SavingsAccount(String accountNumber, double balance, double limit, Client accountHolder) {
        super(accountNumber, balance, limit, accountHolder);
    }
}
