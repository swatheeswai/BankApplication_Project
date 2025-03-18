import java.util.Random;

public class Account {
    private int accountId;
    private int userId;
    private String accountNumber;
    private AccountType accountType;
    private double balance;

    // Constructor
    public Account(int userId, AccountType accountType) {
        this.userId = userId;
        this.accountNumber = generateAccountNo();
        this.accountType = accountType;
        this.balance = 0.0;  // Default balance is 0.0
    }

    // Generate random account number
    private String generateAccountNo() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        sb.append(rand.nextInt(9) + 1); // Ensure first digit is not 0
        for (int i = 0; i < 9; i++) {
            sb.append(rand.nextInt(10));
        }
        return sb.toString();
    }

    // Getters and Setters
    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    public int getUserId() {
        return userId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public double getBalance() {
        return balance;
    }

    // Deposit money
    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            System.out.println("✅ Deposited ₹" + amount + " successfully! New Balance: ₹" + this.balance);
        } else {
            throw new IllegalArgumentException("❌ Deposit amount must be greater than zero!");
        }
    }

    // Withdraw money
    public boolean withdraw(double amount) {
        if (amount > 0 && amount <= this.balance) {
            this.balance -= amount;
            System.out.println("✅ Withdrawn ₹" + amount + " successfully! New Balance: ₹" + this.balance);
            return true;
        } else {
            throw new IllegalArgumentException("❌ Insufficient balance or invalid amount!");
        }
    }

    @Override
    public String toString() {
        return "Account ID: " + accountId + ", Account Number: " + accountNumber + ", Type: " + accountType + ", Balance: ₹" + balance;
    }
}

