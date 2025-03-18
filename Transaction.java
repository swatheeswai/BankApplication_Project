import java.time.LocalDateTime;

public class Transaction {
    private int transactionId;
    private int accountId;
    private TransactionType transactionType;
    private double amount;
    private LocalDateTime transactionDate;

    public Transaction(int transactionId, int accountId, TransactionType transactionType, double amount, LocalDateTime transactionDate) {
        this.transactionId = transactionId;
        this.accountId = accountId;
        this.transactionType = transactionType;
        this.amount = amount;
        this.transactionDate = transactionDate;
    }

    public int getTransactionId() {
        return transactionId;
    }

    public int getAccountId() { // ✅ Added this method
        return accountId;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }
}

