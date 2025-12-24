package payroll;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class PayslipFrame extends JFrame {

    DefaultTableModel model;

    public PayslipFrame() {
        setTitle("Payslips");
        setSize(700, 300);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(
            new String[]{"ID","Name","Dept","Gross","Deduction","Net","Date"}, 0
        );

        JTable table = new JTable(model);
        add(new JScrollPane(table));

        loadPayslips();
        setVisible(true);
    }

    private void loadPayslips() {
        model.setRowCount(0);

        String sql = """
            SELECT e.id, e.name, e.department,
                   p.gross_salary, p.deductions, p.net_salary, p.pay_date
            FROM employees e
            JOIN payroll p ON e.id = p.emp_id
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                model.addRow(new Object[]{
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
    }
}
