public class CurrentAccount extends Account {
    private final double overdraftLimit;
    private static final double MONTHLY_FEE = 5.00;

    public CurrentAccount(String accountNumber, double initialBalance, double overdraftLimit) {
        super(accountNumber, initialBalance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("[" + accountNumber + "] Withdrawal rejected: amount must be positive.");
        } else if (balance - amount < -overdraftLimit) {
            System.out.printf("[%s] Withdrawal of %.2f rejected: exceeds overdraft limit of %.2f.%n",
                    accountNumber, amount, overdraftLimit);
        } else {
            balance -= amount;
            String note = balance < 0 ? " (account is in overdraft)" : "";
            System.out.printf("[%s] Withdrew %.2f. New balance: %.2f%s%n", accountNumber, amount, balance, note);
        }
    }

    @Override
    public void endOfMonth() {
        balance -= MONTHLY_FEE;
        System.out.printf("[%s] Current end of month: maintenance fee %.2f deducted. New balance: %.2f%n",
                accountNumber, MONTHLY_FEE, balance);
    }
}
