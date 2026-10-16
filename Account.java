public abstract class Account {
    protected String accountNumber;
    protected double balance;

    public Account(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    // Concrete: rejects non-positive amounts
    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("[" + accountNumber + "] Deposit rejected: amount must be positive.");
            return;
        }
        balance += amount;
        System.out.printf("[%s] Deposited %.2f. New balance: %.2f%n", accountNumber, amount, balance);
    }

    // Concrete
    public double getBalance() {
        return balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    // Abstract: each account type decides its own withdrawal rules
    public abstract void withdraw(double amount);

    // Abstract hook: month-end processing differs per account type
    public abstract void endOfMonth();
}
