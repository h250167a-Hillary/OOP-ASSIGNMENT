public class SavingsAccount extends Account {
    private static final double MINIMUM_BALANCE = 100.00;
    private static final double INTEREST_RATE = 0.02; // 2% per month

    public SavingsAccount(String accountNumber, double initialBalance) {
        super(accountNumber, initialBalance);
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("[" + accountNumber + "] Withdrawal rejected: amount must be positive.");
        } else if (balance - amount < MINIMUM_BALANCE) {
            System.out.printf("[%s] Withdrawal of %.2f rejected: balance would fall below the minimum of %.2f.%n",
                    accountNumber, amount, MINIMUM_BALANCE);
        } else {
            balance -= amount;
            System.out.printf("[%s] Withdrew %.2f. New balance: %.2f%n", accountNumber, amount, balance);
        }
    }

    @Override
    public void endOfMonth() {
        double interest = balance * INTEREST_RATE;
        balance += interest;
        System.out.printf("[%s] Savings end of month: interest %.2f added. New balance: %.2f%n",
                accountNumber, interest, balance);
    }
}
