import java.util.List;
import java.util.Scanner;

public class TransactionHistoryView {

    public static void displayTransactionHistory(List<Transaction> transactions) {
        if (transactions == null || transactions.isEmpty()) {
            System.out.println("❌ No transactions found!");
            return;
        }

        System.out.println("\n📜 Transaction History:");
        System.out.println("----------------------------------------------------------");
        System.out.printf("| %-5s | %-10s | %-12s | %-10s | %-20s |\n", 
                          "ID", "Account", "Type", "Amount", "Date");
        System.out.println("----------------------------------------------------------");

        for (Transaction t : transactions) {
            System.out.printf("| %-5d | %-10d | %-12s | ₹%-9.2f | %-20s |\n",
                              t.getTransactionId(), t.getAccountId(),  
                              t.getTransactionType(), t.getAmount(), 
                              t.getTransactionDate());
        }

        System.out.println("----------------------------------------------------------");
    }

 public static void viewTransactionHistory() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("👤 Enter User ID to view transactions: ");

    if (!scanner.hasNextInt()) {  // ✅ Prevents Scanner NoSuchElementException
        System.out.println("❌ Invalid input! Please enter a valid User ID.");
        return;
    }

    int userId = scanner.nextInt();
    scanner.nextLine();  // Consume the newline

    UserController userController = new UserController();
    List<Transaction> transactions = userController.getTransactionHistory(userId);

    displayTransactionHistory(transactions);
}

}

