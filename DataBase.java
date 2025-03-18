
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBase {
    private static Connection conn;

    private DataBase() {}

    public static Connection getInstance() {
        if (conn == null) {  // 🔹 Ensure only one connection is created
            try {
                String url = "jdbc:postgresql://localhost:5432/postgres";
                String user = "postgres";
                String password = "newpassword";
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("✅ Database Connection Established");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return conn;
    }
}

