package payroll;
import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static final String URL =
        "jdbc:mariadb://localhost:3308/payroll_db";
    private static final String USER = "root";
    private static final String PASSWORD = "raonerazz"; // MariaDB password

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("DB Connection Failed ❌");
            e.printStackTrace();
            return null;
        }
    }
}
