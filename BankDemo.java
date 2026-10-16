import java.util.ArrayList;
import java.util.List;

public class BankDemo {
    public static void main(String[] args) {
        List<Account> accounts = new ArrayList<>();
        accounts.add(new SavingsAccount("SAV-001", 500.00));
        accounts.add(new CurrentAccount("CUR-001", 200.00, 300.00));
        accounts.add(new SavingsAccount("SAV-002", 150.00));
        accounts.add(new CurrentAccount("CUR-002", 50.00, 100.00));

        System.out.println("=== Deposits (including an invalid one) ===");
        accounts.get(0).deposit(100.00);
        accounts.get(1).deposit(-20.00);

        // Polymorphic loop: only the Account reference is used, no casting
        System.out.println("\n=== Withdrawals through Account references ===");
        double[] amounts = {450.00, 400.00, 100.00, 200.00}; // one amount per account
        for (int i = 0; i < accounts.size(); i++) {
            Account acc = accounts.get(i);
            acc.withdraw(amounts[i]);
        }

        System.out.println("\n=== End of month processing ===");
        for (Account acc : accounts) {
            acc.endOfMonth();
        }

        System.out.println("\n=== Final balances ===");
        for (Account acc : accounts) {
            System.out.printf("%s: %.2f%n", acc.getAccountNumber(), acc.getBalance());
        }

        System.out.println("\n=== Edge cases ===");
        System.out.println("-- Savings withdrawal rejected (below minimum balance) --");
        Account savings = new SavingsAccount("SAV-EDGE", 300.00);
        savings.withdraw(250.00);
        System.out.println("-- Current account goes into overdraft within its limit --");
        Account current = new CurrentAccount("CUR-EDGE", 100.00, 500.00);
        current.withdraw(400.00);
        System.out.println("-- Current withdrawal rejected (beyond overdraft limit) --");
        current.withdraw(300.00);
    }
}
