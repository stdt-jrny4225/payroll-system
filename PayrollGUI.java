package payroll;

import javax.swing.*;
import java.awt.*;

public class PayrollGUI {

    static ViewEmployeesFrame viewFrame;

    public static void main(String[] args) {

        JFrame frame = new JFrame("Employee Payroll System");
        frame.setSize(300, 300);
        frame.setLayout(new GridLayout(3,1,10,10));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JButton addBtn = new JButton("Add Employee");
        JButton viewBtn = new JButton("View Employees");
        JButton exitBtn = new JButton("Exit");
        JButton payslipBtn = new JButton("View Payslips");
        JButton editBtn = new JButton("Update / Delete");
        JButton pdfBtn = new JButton("Export Payslip PDF");


        frame.add(addBtn);
        frame.add(viewBtn);
        frame.add(exitBtn);
        frame.add(payslipBtn);
        frame.add(editBtn);
        frame.add(exitBtn);
        frame.add(pdfBtn);
        

        addBtn.addActionListener(e ->
            new AddEmployeeForm(viewFrame)
        );

        viewBtn.addActionListener(e -> {
            if (viewFrame == null) {
                viewFrame = new ViewEmployeesFrame();
            } else {
                viewFrame.loadEmployees();
                viewFrame.setVisible(true);
            }
        });

       payslipBtn.addActionListener(e -> new PayslipFrame());
       
        pdfBtn.addActionListener(e -> {

    String input = JOptionPane.showInputDialog(
            frame,
            "Enter Employee ID"
    );

    if (input == null || input.isEmpty()) return;

    int empId = Integer.parseInt(input);

    PayslipPDFExporter.exportPDF(empId);

    JOptionPane.showMessageDialog(
            frame,
            "Payslip PDF Generated"
    );
});


editBtn.addActionListener(e -> new UpdateDeleteEmployeeFrame());

pdfBtn.addActionListener(e -> {
    int id = Integer.parseInt(
        JOptionPane.showInputDialog("Enter Employee ID"));
    PayslipPDFExporter.exportPDF(id);
    JOptionPane.showMessageDialog(null,"PDF Generated");
});


        exitBtn.addActionListener(e -> System.exit(0));

        frame.setVisible(true);
    }
}
