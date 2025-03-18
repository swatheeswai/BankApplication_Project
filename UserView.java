import java.util.Scanner;
import java.util.regex.Pattern;

class UserView {
    public int register() {  // 🔹 Now returns an int
        Scanner scan = new Scanner(System.in);

        String name;
        while (true) {
            System.out.println("Enter Your UserName:");
            name = scan.nextLine();
            if (Pattern.matches("^[A-Za-z]{3,}$", name)) {
                break;
            }
            System.out.println("Your Name format is wrong. Try again.");
        }

        String email_id;
        while (true) {
            System.out.println("Enter Your Email:");
            email_id = scan.nextLine();
            if (Pattern.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$", email_id)) {
                break;
            }
            System.out.println("Your Email format is wrong. Try again.");
        }

        String dob;
        while (true) {
            System.out.print("Enter DOB (YYYY-MM-DD): ");
            dob = scan.nextLine();
            if (Pattern.matches("^\\d{4}-\\d{2}-\\d{2}$", dob)) {
                break;
            }
            System.out.println("Your DOB format is wrong. Try again.");
        }

        long phoneNo;
        while (true) {
            System.out.print("Enter Phone Number: ");
            String phoneInput = scan.nextLine();
            if (Pattern.matches("^[6-9]\\d{9}$", phoneInput)) {
                phoneNo = Long.parseLong(phoneInput);
                break;
            }
            System.out.println("Your Phone Number format is wrong. Try again.");
        }

        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = scan.nextLine();
            if (Pattern.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", password)) {
                break;
            }
            System.out.println("Your Password format is wrong. Try again.");
        }

        User user = new User(name, email_id, dob, phoneNo, password);
         return UserController.registerUser(user);
    }

   
}

