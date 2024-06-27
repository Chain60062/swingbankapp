package viniciusmiranda.controller;

public class CheckingController {
    public double calculateYield(double initialInvestment, double interest, double profitability,
            int period, boolean perYear) {
        double result = 0;

        if (perYear) {
            result = (interest * (profitability / 100) * period) * initialInvestment;
        } else {
            result = ((interest / 12) * ((profitability / 100) / 12) * period) * initialInvestment;
        }

        return (result * 0.2) + initialInvestment;
    }
}
