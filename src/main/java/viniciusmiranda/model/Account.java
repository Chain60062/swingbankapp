package viniciusmiranda.model;

import java.util.Random;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Account {
    protected String accountNumber;
    protected double balance;
    protected double limit;
    protected Client accountHolder;
    private final Random random = new Random();

    protected Account(Client accountHolder) {
        this.accountHolder = accountHolder;
        this.accountNumber = String.valueOf(100000000 + random.nextInt(900000000));
        balance = 0.0;
        limit = 1000;
    }

    protected Account(String accountNumber, double balance, double limit, Client accountHolder) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.limit = limit;
        this.accountHolder = accountHolder;
    }
    @Override
    public String toString() {
        return accountNumber;
    }
}
