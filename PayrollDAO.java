package payroll;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;

public class PayrollDAO {

    public static void generatePayroll() {
        try {
            Connection con = DBConnection.getConnection();

            // 🔥 STEP 1: DELETE old payroll (FIX DUPLICATION)
            String clearSql = "DELETE FROM payroll";
            PreparedStatement clearPs = con.prepareStatement(clearSql);
            clearPs.executeUpdate();

            // 🔥 STEP 2: Fetch employees
            String fetchSql = "SELECT id, basic_salary FROM employees";
            PreparedStatement fetchPs = con.prepareStatement(fetchSql);
            ResultSet rs = fetchPs.executeQuery();

            // 🔥 STEP 3: Insert fresh payroll
            String insertSql = """
                INSERT INTO payroll (emp_id, gross_salary, deductions, net_salary, pay_date)
                VALUES (?,?,?,?,?)
            """;
            PreparedStatement insertPs = con.prepareStatement(insertSql);

            while (rs.next()) {
                int empId = rs.getInt("id");
                double basic = rs.getDouble("basic_salary");

                double hra = basic * 0.20;
                double pf  = basic * 0.12;
                double gross = basic + hra;
                double net   = gross - pf;

                insertPs.setInt(1, empId);
                insertPs.setDouble(2, gross);
                insertPs.setDouble(3, pf);
                insertPs.setDouble(4, net);
                insertPs.setDate(5, java.sql.Date.valueOf(LocalDate.now()));

                insertPs.executeUpdate();
            }

            con.close();
            System.out.println("✅ Payroll generated (old data cleared)");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
