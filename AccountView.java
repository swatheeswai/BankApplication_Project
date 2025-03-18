import java.util.Scanner;

public class AccountView {
    private Scanner scanner;
    private UserController userController;

    public AccountView() {
        this.scanner = new Scanner(System.in);
        this.userController = new UserController();
    }

    public void createAccount() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.println("Select Account Type: 1. Savings  2. Current");
        int typeChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        AccountType accountType = (typeChoice == 1) ? AccountType.SAVINGS : AccountType.CURRENT;
        userController.createAccount(userId, accountType);
    }

    public void depositMoney() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter User AccountNumber: ");
        String accountNumber = scanner.next();
        System.out.print("Enter amount to deposit: ₹");
        double amount = scanner.nextDouble();

        userController.deposit(userId,accountNumber,amount);
    }

    public void withdrawMoney() {
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        System.out.print("Enter User AccountNumber: ");
        String accountNumber = scanner.next();
        System.out.print("Enter amount to withdraw: ₹");
        double amount = scanner.nextDouble();

        userController.withdraw(userId,accountNumber,amount);
    }

public static void checkBalance() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("👤 Enter User ID: ");
    int userId = scanner.nextInt();
    scanner.nextLine();

    UserController userController = new UserController(); // ✅ Create instance
    double balance = userController.getBalance(userId);   // ✅ Call getBalance()

    if (balance == -1) {
        System.out.println("❌ User ID not found!");
    } else {
        System.out.println("💰 Your current balance is: ₹" + balance);
    }
}




    public void accountMenu() {
        while (true) {
            System.out.println("\n📌 Account Management Menu");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositMoney();
                    break;
                case 3:
                    withdrawMoney();
                    break;
                case 4:
                    checkBalance();
                    break;
                case 5:
                    return; // Go back to main menu
                default:
                    System.out.println("❌ Invalid choice. Please try again.");
            }
        }
    }
}

