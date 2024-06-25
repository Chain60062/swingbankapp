package viniciusmiranda.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Account {
    protected double balance;
    protected double limit;

    protected abstract double simulateDailyYields();
}
