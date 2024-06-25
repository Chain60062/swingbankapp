package viniciusmiranda.model;

public abstract class Account {
    protected double balance;
    protected double limit;

    protected abstract double simulateDailyYields();
}
