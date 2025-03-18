import java.util.Scanner;

class EmployeeView extends UserView {
    public void employeeRegister() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Employee Registration 👨‍🚀️");
        
        int userId = super.register(); // 🔹 Get user_id from UserView
        
        System.out.println("Enter your Department:");
        String department = scan.nextLine();
        
        double salary;
        while (true) {
            System.out.print("Enter your Salary: ");
            String salaryInput = scan.nextLine();
            try {
                salary = Double.parseDouble(salaryInput);
                break;
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid Salary. Try again.");
            }
        }
        
        
        // 🔹 Now pass userId to registerEmployee()
        UserController.registerEmployee(userId, department, salary);
    }
}

