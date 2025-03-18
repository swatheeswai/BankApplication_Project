import java.util.Scanner;
class Login extends User{
      public void login() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Enter Email: ");
        String email = scan.nextLine();

        System.out.print("Enter Password: ");
        String password = scan.nextLine();

        int userId = UserController.loginUser(email, password);
        if (userId != -1) {
            System.out.println("✅ Logged in successfully as User ID: " + userId);
        } else {
            System.out.println("❌ Login failed! Incorrect email or password.");
        }
    }
  }
