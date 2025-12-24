package payroll;

import javax.swing.*;

public class AddEmployeeForm extends JFrame {

    private JTextField nameField, deptField, salaryField;
    private ViewEmployeesFrame viewFrame;

    public AddEmployeeForm(ViewEmployeesFrame viewFrame) {
        this.viewFrame = viewFrame;

        setTitle("Add Employee");
        setSize(300, 220);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("Name:");
        JLabel l2 = new JLabel("Department:");
        JLabel l3 = new JLabel("Salary:");

        nameField = new JTextField();
        deptField = new JTextField();
        salaryField = new JTextField();

        JButton saveBtn = new JButton("Save");

        l1.setBounds(20, 20, 80, 25);
        l2.setBounds(20, 60, 80, 25);
        l3.setBounds(20, 100, 80, 25);

        nameField.setBounds(110, 20, 150, 25);
        deptField.setBounds(110, 60, 150, 25);
        salaryField.setBounds(110, 100, 150, 25);

        saveBtn.setBounds(90, 140, 100, 30);

        add(l1); add(l2); add(l3);
        add(nameField); add(deptField); add(salaryField);
        add(saveBtn);

        saveBtn.addActionListener(e -> saveEmployee());

        setVisible(true);
    }

    private void saveEmployee() {
        String name = nameField.getText();
        String dept = deptField.getText();
        double salary = Double.parseDouble(salaryField.getText());

        boolean success =
            EmployeeDAO.addEmployee(name, dept, salary);

        if (success) {
            JOptionPane.showMessageDialog(this, "Employee Added");

            if (viewFrame != null) {
                viewFrame.loadEmployees(); // 🔥 AUTO REFRESH
            }
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error Saving Employee");
        }
    }
}
