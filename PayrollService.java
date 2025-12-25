package payroll;

import javax.swing.JOptionPane;

public class PayrollService {

    public static void generatePayrollForAll() {
        try {
            PayrollDAO.generatePayroll();
            JOptionPane.showMessageDialog(
                    null,
                    "Payroll generated successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(
                    null,
                    "Error generating payroll:\n" + e.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}
