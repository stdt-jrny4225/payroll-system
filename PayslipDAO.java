package payroll;

import java.sql.*;
import java.util.*;

public class PayslipDAO {

    public static List<Object[]> fetchPayslips() {

        List<Object[]> list = new ArrayList<>();

        String sql =
            "SELECT e.id, e.name, e.department, " +
            "p.gross_salary, p.deductions, p.net_salary, p.pay_date " +
            "FROM employees e " +
            "JOIN payroll p ON e.id = p.emp_id";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Object[]{
                    rs.getInt(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getDouble(4),
                    rs.getDouble(5),
                    rs.getDouble(6),
                    rs.getDate(7)
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
