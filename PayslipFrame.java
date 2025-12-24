package payroll;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class PayslipFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public PayslipFrame() {
        setTitle("Payslips");
        setSize(900, 400);
        setLocationRelativeTo(null);

        // ✅ Table model
        model = new DefaultTableModel(
                new String[]{"ID", "Name", "Dept", "Gross", "Deduction", "Net", "Date"}, 0
        );

        table = new JTable(model);
        add(new JScrollPane(table), BorderLayout.CENTER);

        loadPayslips();

        setVisible(true);
    }

    private void loadPayslips() {
        model.setRowCount(0); // clear old data

        String sql = """
            SELECT e.id, e.name, e.department,
                   p.gross_salary, p.deductions, p.net_salary, p.pay_date
            FROM employees e
            JOIN payroll p ON e.id = p.emp_id
            ORDER BY p.pay_date DESC
        """;

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {
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
            JOptionPane.showMessageDialog(this,
                    "Error loading payslips\n" + e.getMessage());
            e.printStackTrace();
        }
    }
}
