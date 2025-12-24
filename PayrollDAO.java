package payroll;

import java.sql.*;
import java.time.LocalDate;

public class PayrollDAO {

    public static void generatePayrollForAll() {

        String selectSQL =
            "SELECT id, basic_salary FROM employees " +
            "WHERE id NOT IN (SELECT emp_id FROM payroll)";

        String insertSQL =
            "INSERT INTO payroll(emp_id, gross_salary, deductions, net_salary, pay_date) " +
            "VALUES (?, ?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement psSelect = con.prepareStatement(selectSQL);
             PreparedStatement psInsert = con.prepareStatement(insertSQL)) {

            ResultSet rs = psSelect.executeQuery();

            while (rs.next()) {
                int empId = rs.getInt("id");
                double basic = rs.getDouble("basic_salary");

                double gross = basic * 1.2;
                double deduction = gross * 0.1;
                double net = gross - deduction;

                psInsert.setInt(1, empId);
                psInsert.setDouble(2, gross);
                psInsert.setDouble(3, deduction);
                psInsert.setDouble(4, net);
                psInsert.setDate(5, Date.valueOf(LocalDate.now()));

                psInsert.executeUpdate();
            }

            System.out.println("✔ Payroll generated for all pending employees");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
