package payroll;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class ViewEmployeesFrame extends JFrame {

    private JTable table;
    private DefaultTableModel model;

    public ViewEmployeesFrame() {
        setTitle("Employees");
        setSize(500, 300);
        setLocationRelativeTo(null);

        model = new DefaultTableModel(
            new String[]{"ID", "Name", "Department", "Salary"}, 0
        );

        table = new JTable(model);
        add(new JScrollPane(table));

        loadEmployees();
        setVisible(true);
    }

    // 🔥 AUTO REFRESH METHOD
    public void loadEmployees() {
        model.setRowCount(0); // clear old data

        Vector<Vector<Object>> data =
            EmployeeDAO.getAllEmployees();

        for (Vector<Object> row : data) {
            model.addRow(row);
        }
    }
}
