import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserController userController = new UserController();
        Scanner scanner = new Scanner(System.in);
        AccountView accountView = new AccountView();
        UserView userView = new UserView(); // ✅ Use a single instance

        while (true) {
            System.out.println("\n📌 Welcome to the User Registration System");
            System.out.println("1. Register as Employee");
            System.out.println("2. Register as User");
            System.out.println("3. Login");
            System.out.println("4. Create Account & Check Balance");
            System.out.println("5. View Transaction History");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    EmployeeView employeeView = new EmployeeView();
                    employeeView.employeeRegister();
                    break;
                case 2:
                    userView.register();  // ✅ Reuse the same instance
                    break;
                case 3:
                Login login = new Login();
                  login.login();  // ✅ Reuse the same instance
                    break;
                case 4:
                    accountView.accountMenu();
                    break;
                case 5:
                    TransactionHistoryView.viewTransactionHistory();
                    break;
                case 6:
                    System.out.println("👋 Exiting... Have a great day!");
                    System.out.println("✅ Thank you for using our system!");
                    try {
                        Thread.sleep(1500);  // ✅ Delay exit for better UX
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ Invalid choice. Please select a valid option.");
            }
        }
    }
}

