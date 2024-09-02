public class ATMsystem {

    // Base class Account
    static class Account {
        private String accountNumber;
        private double balance;

        public Account(String accountNumber, double initialBalance) {
            this.accountNumber = accountNumber;
            this.balance = initialBalance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            if (amount > 0) {
                balance += amount;
                System.out.println("Deposited: $" + amount);
            } else {
                System.out.println("Deposit amount must be positive.");
            }
        }

        public void withdraw(double amount) {
            if (amount > 0 && amount <= balance) {
                balance -= amount;
                System.out.println("Withdrew: $" + amount);
            } else {
                System.out.println("Insufficient balance or invalid amount.");
            }
        }

        @Override
        public String toString() {
            return "Account Number: " + accountNumber + "\nBalance: $" + balance;
        }
    }

    // SavingsAccount class that extends Account
    static class SavingsAccount extends Account {
        private double interestRate;

        public SavingsAccount(String accountNumber, double initialBalance, double interestRate) {
            super(accountNumber, initialBalance);
            this.interestRate = interestRate;
        }

        public void addInterest() {
            double interest = getBalance() * (interestRate / 100);
            deposit(interest);
            System.out.println("Interest added: $" + interest);
        }

        @Override
        public String toString() {
            return super.toString() + "\nInterest Rate: " + interestRate + "%";
        }
    }

    // CheckingAccount class that extends Account
    static class CheckingAccount extends Account {
        private double overdraftLimit;

        public CheckingAccount(String accountNumber, double initialBalance, double overdraftLimit) {
            super(accountNumber, initialBalance);
            this.overdraftLimit = overdraftLimit;
        }

        @Override
        public void withdraw(double amount) {
            if (amount > 0 && (getBalance() + overdraftLimit) >= amount) {
                super.withdraw(amount);
            } else {
                System.out.println("Withdrawal amount exceeds limit.");
            }
        }

        @Override
        public String toString() {
            return super.toString() + "\nOverdraft Limit: $" + overdraftLimit;
        }
    }

    // Main class to test the ATM system
    public static void main(String[] args) {
        // Create a savings account
        SavingsAccount savings = new SavingsAccount("SA12345", 5000, 2.5);
        System.out.println("Savings Account Details:");
        System.out.println(savings);
        savings.deposit(200);
        savings.withdraw(100);
        savings.addInterest();
        System.out.println();

        // Create a checking account
        CheckingAccount checking = new CheckingAccount("CA12345", 1000, 500);
        System.out.println("Checking Account Details:");
        System.out.println(checking);
        checking.deposit(300);
        checking.withdraw(1200); // Should be successful due to overdraft limit
        checking.withdraw(100);  // Should be unsuccessful
        System.out.println();
    }
}
