package payroll;

import javax.swing.*;

public class UpdateDeleteEmployeeFrame extends JFrame {

    JTextField idField, nameField, deptField, salaryField;

    public UpdateDeleteEmployeeFrame() {
        setTitle("Update / Delete Employee");
        setSize(350, 300);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel l1 = new JLabel("ID:");
        JLabel l2 = new JLabel("Name:");
        JLabel l3 = new JLabel("Dept:");
        JLabel l4 = new JLabel("Salary:");

        idField = new JTextField();
        nameField = new JTextField();
        deptField = new JTextField();
        salaryField = new JTextField();

        JButton updateBtn = new JButton("Update");
        JButton deleteBtn = new JButton("Delete");

        l1.setBounds(20,20,80,25);
        l2.setBounds(20,60,80,25);
        l3.setBounds(20,100,80,25);
        l4.setBounds(20,140,80,25);

        idField.setBounds(110,20,150,25);
        nameField.setBounds(110,60,150,25);
        deptField.setBounds(110,100,150,25);
        salaryField.setBounds(110,140,150,25);

        updateBtn.setBounds(50,190,100,30);
        deleteBtn.setBounds(170,190,100,30);

        add(l1);add(l2);add(l3);add(l4);
        add(idField);add(nameField);add(deptField);add(salaryField);
        add(updateBtn);add(deleteBtn);

        updateBtn.addActionListener(e -> update());
        deleteBtn.addActionListener(e -> delete());

        setVisible(true);
    }

    private void update() {
        boolean ok = EmployeeDAO.updateEmployee(
            Integer.parseInt(idField.getText()),
            nameField.getText(),
            deptField.getText(),
            Double.parseDouble(salaryField.getText())
        );
        JOptionPane.showMessageDialog(this, ok?"Updated":"Failed");
    }

    private void delete() {
        boolean ok = EmployeeDAO.deleteEmployee(
            Integer.parseInt(idField.getText())
        );
        JOptionPane.showMessageDialog(this, ok?"Deleted":"Failed");
    }
}
