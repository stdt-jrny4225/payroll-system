package payroll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PayslipDAO {

    public static void showPayslips() {

        try {
            Connection con = DBConnection.getConnection();

            String sql = """
                SELECT 
                    e.id,
                    e.name,
                    e.department,
                    e.basic_salary,
                    p.gross_salary,
                    p.deductions,
                    p.net_salary,
                    p.pay_date
                FROM employees e
                JOIN payroll p
                ON e.id = p.emp_id
            """;

            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            System.out.println("\n========== PAYSLIP ==========");

            while (rs.next()) {
                System.out.println("Employee ID   : " + rs.getInt("id"));
                System.out.println("Name          : " + rs.getString("name"));
                System.out.println("Department    : " + rs.getString("department"));
                System.out.println("Basic Salary  : " + rs.getDouble("basic_salary"));
                System.out.println("Gross Salary  : " + rs.getDouble("gross_salary"));
                System.out.println("Deductions    : " + rs.getDouble("deductions"));
                System.out.println("Net Salary    : " + rs.getDouble("net_salary"));
                System.out.println("Pay Date      : " + rs.getDate("pay_date"));
                System.out.println("----------------------------------");
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
