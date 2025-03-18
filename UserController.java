import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class UserController {
    private static Connection conn;

    public UserController() {
        conn = DataBase.getInstance(); 
        createTables();
    }

    // Method to create necessary tables
    public void createTables() {
        String userTable = "CREATE TABLE IF NOT EXISTS users (" +
                "user_id SERIAL PRIMARY KEY, " +
                "name VARCHAR(255) NOT NULL, " +
                "email VARCHAR(255) UNIQUE NOT NULL, " +
                "dob DATE NOT NULL, " +
                "phone_no BIGINT UNIQUE NOT NULL, " +
                "password VARCHAR(255) NOT NULL" +
                ");";

        String employeeTable = "CREATE TABLE IF NOT EXISTS employees (" +
                "emp_id SERIAL PRIMARY KEY, " +
                "user_id INT UNIQUE NOT NULL, " +
                "name VARCHAR(255) NOT NULL, " +
                "department VARCHAR(100) NOT NULL, " +
                "salary DECIMAL(10,2) NOT NULL, " +
                "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE" +
                ");";

        String accountTable = "CREATE TABLE IF NOT EXISTS accounts (" +
                "account_id SERIAL PRIMARY KEY, " +
                "user_id INT NOT NULL, " +
                "account_number VARCHAR(20) UNIQUE NOT NULL, " +
                "account_type VARCHAR(20) NOT NULL CHECK (account_type IN ('SAVINGS', 'CURRENT')), " +
                "balance DECIMAL(12,2) DEFAULT 0.00, " +
                "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE" +
                ");";

        String transactionTable = "CREATE TABLE IF NOT EXISTS transactions (" +
                "transaction_id SERIAL PRIMARY KEY, " +
                "user_id INT NOT NULL, " +
                "account_id INT NOT NULL, " +
                "transaction_type VARCHAR(10) NOT NULL CHECK (transaction_type IN ('DEPOSIT', 'WITHDRAWAL', 'TRANSFER')), " +
                "amount DECIMAL(12,2) NOT NULL, " +
                "transaction_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, " +
                "FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE, " +
                "FOREIGN KEY (account_id) REFERENCES accounts(account_id) ON DELETE CASCADE" +
                ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(userTable);
            stmt.execute(employeeTable);
            stmt.execute(accountTable);  
            stmt.execute(transactionTable);
            System.out.println("✅ Tables created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to register a new user
    public static int registerUser(User user) {
       
        String query = "INSERT INTO users (name, email, dob, phone_no, password) VALUES (?, ?, ?, ?, ?) RETURNING user_id";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail_id());
            pst.setDate(3, java.sql.Date.valueOf(user.getDob()));
            pst.setLong(4, user.getPhoneNo());
            pst.setString(5, user.getPassword());

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int userId = rs.getInt("user_id");
                    System.out.println("✅ User registered successfully!");
                    return userId;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    //✅ register Employee
    public static void registerEmployee(int userId, String department, double salary) {
     String nameQuery = "SELECT name FROM users WHERE user_id = ?";
    String name = null;

    try (PreparedStatement pst = conn.prepareStatement(nameQuery)) {
        pst.setInt(1, userId);

        try (ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                name = rs.getString("name");
            } else {
                System.out.println("❌ No user found with user_id: " + userId);
                return; // If user not found, return early from the method
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return;
    }
    String query = "INSERT INTO employees (user_id,name,department, salary) VALUES (?, ?, ?, ?)";

    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setInt(1, userId);
        pst.setString(2, name);
        pst.setString(3, department);
        pst.setDouble(4, salary);

        int rows = pst.executeUpdate();
        if (rows > 0) {
            System.out.println("✅ Employee registered successfully!");
        } else {
            System.out.println("❌ Employee registration failed!");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    // ✅ Login User
public static int loginUser(String email, String password) {
    ensureConnectionOpen(); // ✅ Ensures connection is open before running queries

    String query = "SELECT user_id, name, password FROM users WHERE email = ?";

    try (PreparedStatement pst = conn.prepareStatement(query)) {
        pst.setString(1, email);
        ResultSet rs = pst.executeQuery();

        if (rs.next()) {
            String storedPassword = rs.getString("password");
            if (password.equals(storedPassword)) {
                System.out.println("✅ Login successful! Welcome, " + rs.getString("name"));
                return rs.getInt("user_id");
            } else {
                System.out.println("❌ Incorrect password.");
            }
        } else {
            System.out.println("❌ No user found with this email.");
        }
    } catch (SQLException e) {
        System.err.println("❌ Database error: " + e.getMessage());
    }
    return -1;
}


    // Method to create an account for the user
    public void createAccount(int userId, AccountType accountType) {
        String query = "INSERT INTO accounts (user_id, account_number, account_type, balance) VALUES (?, ?, ?, ?) RETURNING account_id";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            Account account = new Account(userId, accountType);

            pst.setInt(1, userId);
            pst.setString(2, account.getAccountNumber());
            pst.setString(3, accountType.name());
            pst.setDouble(4, account.getBalance());

            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    int accountId = rs.getInt("account_id");
                    System.out.println("✅ Account created successfully! Account ID: " + accountId + ", Account Number: " + account.getAccountNumber());
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // Ensures the database connection is open before running queries
private static void ensureConnectionOpen() {
    try {
        if (conn == null || conn.isClosed()) {
            System.out.println("🔄 Reconnecting to the database...");
            conn = DataBase.getInstance();  
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
// validation of account number
 private boolean isAccountValid(int userId, String accountNumber) {
        String query = "SELECT account_id FROM accounts WHERE user_id = ? AND account_number = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setString(2, accountNumber);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // If a row is found, account is valid
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Method to deposit money into the account
    public void deposit(int userId, String accountNumber, double amount) { 
        ensureConnectionOpen();  

        if (amount <= 0) {
            System.out.println("❌ Deposit amount must be greater than zero!");
            return;
        }

        if (!isAccountValid(userId, accountNumber)) {
            System.out.println("❌ Invalid account number!");
            return;
        }

        String updateBalanceQuery = "UPDATE accounts SET balance = balance + ? WHERE user_id = ? AND account_number = ?";
        String insertTransactionQuery = "INSERT INTO transactions (user_id, account_id, transaction_type, amount) VALUES (?, (SELECT account_id FROM accounts WHERE account_number = ?), 'DEPOSIT', ?)";

        try (PreparedStatement pst = conn.prepareStatement(updateBalanceQuery)) {
            pst.setDouble(1, amount);
            pst.setInt(2, userId);
            pst.setString(3, accountNumber);
            int updated = pst.executeUpdate();

            if (updated > 0) {
                try (PreparedStatement pstTransaction = conn.prepareStatement(insertTransactionQuery)) {
                    pstTransaction.setInt(1, userId);
                    pstTransaction.setString(2, accountNumber);
                    pstTransaction.setDouble(3, amount);
                    pstTransaction.executeUpdate();
                }
                System.out.println("✅ Deposited ₹" + amount + " successfully!");
            } else {
                System.out.println("❌ Deposit failed!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Method to withdraw money from the account
 public void withdraw(int userId, String accountNumber, double amount) {
        ensureConnectionOpen();  

        if (amount <= 0) {
            System.out.println("❌ Invalid withdrawal amount!");
            return;
        }

        if (!isAccountValid(userId, accountNumber)) {
            System.out.println("❌ Invalid account number!");
            return;
        }

        String checkBalanceQuery = "SELECT balance FROM accounts WHERE user_id = ? AND account_number = ?";
        String updateBalanceQuery = "UPDATE accounts SET balance = balance - ? WHERE user_id = ? AND account_number = ?";
        String insertTransactionQuery = "INSERT INTO transactions (user_id, account_id, transaction_type, amount) VALUES (?, (SELECT account_id FROM accounts WHERE account_number = ?), 'WITHDRAWAL', ?)";

        try {
            conn.setAutoCommit(false); 

            try (PreparedStatement checkStmt = conn.prepareStatement(checkBalanceQuery)) {
                checkStmt.setInt(1, userId);
                checkStmt.setString(2, accountNumber);
                ResultSet rs = checkStmt.executeQuery();

                if (rs.next()) {
                    double balance = rs.getDouble("balance");

                    if (amount <= balance) {
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateBalanceQuery)) {
                            updateStmt.setDouble(1, amount);
                            updateStmt.setInt(2, userId);
                            updateStmt.setString(3, accountNumber);
                            updateStmt.executeUpdate();
                        }

                        try (PreparedStatement pstTransaction = conn.prepareStatement(insertTransactionQuery)) {
                            pstTransaction.setInt(1, userId);
                            pstTransaction.setString(2, accountNumber);
                            pstTransaction.setDouble(3, amount);
                            pstTransaction.executeUpdate();
                        }

                        conn.commit();  
                        System.out.println("✅ Withdrawn ₹" + amount + " successfully!");
                    } else {
                        System.out.println("❌ Insufficient balance!");
                    }
                } else {
                    System.out.println("❌ Account not found!");
                }
            }
        } catch (SQLException e) {
            try {
                conn.rollback();  
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true); 
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    // Method to get account balance
    public double getBalance(int userId) {
        String query = "SELECT balance FROM accounts WHERE user_id = ?";

        try (PreparedStatement pst = conn.prepareStatement(query)) {
            pst.setInt(1, userId);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getDouble("balance");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0.0;
    }

    // Method to get account_id from user_id
  public int getAccountId(int userId) {
    String query = "SELECT account_id FROM accounts WHERE user_id = ?";
    try (PreparedStatement stmt = conn.prepareStatement(query)) {
        stmt.setInt(1, userId);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            return rs.getInt("account_id"); // ✅ Returns the account ID
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return -1; // Returns -1 if no account is found
}

    // Method to get transaction history for the user
    public List<Transaction> getTransactionHistory(int userId) {
        List<Transaction> transactions = new ArrayList<>();

        String query = "SELECT * FROM transactions WHERE user_id = ? ORDER BY transaction_date DESC";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                int transactionId = rs.getInt("transaction_id");
                int accountId = rs.getInt("account_id");
                String typeString = rs.getString("transaction_type");
                TransactionType transactionType = TransactionType.valueOf(typeString.toUpperCase());
                double amount = rs.getDouble("amount");
                Timestamp timestamp = rs.getTimestamp("transaction_date");

                Transaction transaction = new Transaction(transactionId, accountId, transactionType, amount, timestamp.toLocalDateTime());
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }
      

    // Method to close the database connection
    public void closeConnection() {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                System.out.println("🔴 Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

