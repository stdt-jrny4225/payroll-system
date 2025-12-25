package payroll;

import java.sql.*;
import java.util.*;
import javax.swing.table.DefaultTableModel;

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

  

// public static void fetchPayslipsToTable(DefaultTableModel model) {
//     model.setRowCount(0);

//     String sql = """
//         SELECT e.id, e.name, e.department,
//                p.gross_salary, p.deductions, p.net_salary, p.pay_date
//         FROM payroll p
//         JOIN employees e ON p.emp_id = e.id
//         ORDER BY p.pay_date DESC
//     """;

//     try (Connection con = DBConnection.getConnection();
//          PreparedStatement ps = con.prepareStatement(sql);
//          ResultSet rs = ps.executeQuery()) {

//         while (rs.next()) {
//             model.addRow(new Object[]{
//                     rs.getInt("id"),
//                     rs.getString("name"),
//                     rs.getString("department"),
//                     rs.getDouble("gross_salary"),
//                     rs.getDouble("deductions"),
//                     rs.getDouble("net_salary"),
//                     rs.getDate("pay_date")
//             });
//         }
//     } catch (Exception e) {
//         e.printStackTrace();
//     }
// }



public static void fetchPayslipsToTable(DefaultTableModel model) {
    model.setRowCount(0);

    String sql = """
        SELECT e.id, e.name, e.department,
               p.gross_salary, p.deductions, p.net_salary, p.pay_date
        FROM payroll p
        JOIN employees e ON p.emp_id = e.id
        ORDER BY p.pay_date DESC
    """;

    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ResultSet rs = ps.executeQuery()) {

        while (rs.next()) {
            model.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("department"),
                    rs.getDouble("gross_salary"),
                    rs.getDouble("deductions"),
                    rs.getDouble("net_salary"),
                    rs.getDate("pay_date")
            });
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}


}
