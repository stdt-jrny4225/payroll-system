package payroll;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PayrollGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    public PayrollGUI() {
        setTitle("Employee Payroll System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                exitApp();
            }
        });

        setLayout(new BorderLayout());

        add(createSidebar(), BorderLayout.WEST);
        add(createMainPanel(), BorderLayout.CENTER);

        setVisible(true);
    }

    // ================= SIDEBAR =================
    private JPanel createSidebar() {
        JPanel sidebar = new JPanel();
        sidebar.setBackground(new Color(33, 37, 41));
        sidebar.setPreferredSize(new Dimension(240, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Razz Payroll");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        title.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                cardLayout.show(mainPanel, "WELCOME");
            }
        });

        sidebar.add(title);
        sidebar.add(Box.createVerticalStrut(20));

        sidebar.add(menuButton("Add Employee", () -> new AddEmployeeForm(null)));
        sidebar.add(menuButton("View Employees", () -> cardLayout.show(mainPanel, "EMPLOYEES")));
        sidebar.add(menuButton("Generate Payroll", () -> PayrollService.generatePayrollForAll()));
        sidebar.add(menuButton("View Payslips", () -> cardLayout.show(mainPanel, "PAYSLIPS")));
        sidebar.add(menuButton("Export Payslip PDF", () ->
                {
            String id = JOptionPane.showInputDialog(this,
                    "Enter Employee ID:");
            if (id != null) {
                PayslipPDFExporter.exportPDF(Integer.parseInt(id));
            }
        }));
        sidebar.add(menuButton("Update / Delete", () ->
                 new UpdateDeleteEmployeeFrame()));
        sidebar.add(menuButton("Exit", this::exitApp));

        return sidebar;
    }

    private JButton menuButton(String text, Runnable action) {
        JButton btn = new JButton(text);
        btn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 55));
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 15));
        btn.setBackground(new Color(52, 58, 64));
        btn.setForeground(Color.WHITE);
        btn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));
        btn.setHorizontalAlignment(SwingConstants.LEFT);

        btn.addActionListener(e -> action.run());
        return btn;
    }

    // ================= MAIN PANEL =================
    private JPanel createMainPanel() {
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createWelcomePanel(), "WELCOME");
        mainPanel.add(createEmployeesPanel(), "EMPLOYEES");
        mainPanel.add(createPayslipPanel(), "PAYSLIPS");

        cardLayout.show(mainPanel, "WELCOME");
        return mainPanel;
    }

    // ================= WELCOME =================
    private JPanel createWelcomePanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        JLabel label = new JLabel(
                "<html><center>" +
                "<h1>Welcome to Employee Payroll System</h1>" +
                "<p style='font-size:16px'>Manage employees, payroll & payslips easily</p>" +
                "</center></html>"
        );
        label.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        panel.add(label);
        return panel;
    }

    // ================= EMPLOYEES TABLE =================
    private JPanel createEmployeesPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel heading = new JLabel("Employees List");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        String[] cols = {"ID", "Name", "Department", "Salary"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        EmployeeDAO.fetchEmployeesToTable(model);

        panel.add(heading, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    // ================= PAYSLIPS TABLE =================
    private JPanel createPayslipPanel() {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel heading = new JLabel("Payroll Payslips");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 22));
        heading.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        String[] cols = {"ID", "Name", "Dept", "Gross", "Deduction", "Net", "Date"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable table = new JTable(model);

        PayslipDAO.fetchPayslipsToTable(model);

        panel.add(heading, BorderLayout.NORTH);
        panel.add(new JScrollPane(table), BorderLayout.CENTER);

        return panel;
    }

    // ================= EXIT =================
    private void exitApp() {
        int opt = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to exit?",
                "Exit Confirmation",
                JOptionPane.YES_NO_OPTION
        );
        if (opt == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        SwingUtilities.invokeLater(PayrollGUI::new);
    }
}
