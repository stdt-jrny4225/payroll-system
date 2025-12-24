// package payroll;

// import java.util.Scanner;

// public class Main {

//     public static void main(String[] args) {

//         Scanner sc = new Scanner(System.in);

//         while (true) {
//             System.out.println("\n===== PAYROLL SYSTEM =====");
//             System.out.println("1. Add Employee");
//             System.out.println("2. View Employees");
//             System.out.println("3. Generate Payroll");
//             System.out.println("4. View Payslips");
//             System.out.println("5. Exit");
//             System.out.print("Choose option: ");

//             int choice = sc.nextInt();
//             sc.nextLine(); // clear buffer

//             switch (choice) {
//                 case 1:
//                     EmployeeDAO.addEmployee();
//                     break;

//                 case 2:
//                     EmployeeDAO.fetchEmployees();
//                     break;

//                 case 3:
//                     PayrollDAO.generatePayroll();
//                     break;

//                 case 4:
//                     PayslipDAO.showPayslips();
//                     break;

//                 case 5:
//                     System.out.println("👋 Exiting Payroll System");
//                     System.exit(0);

//                 default:
//                     System.out.println("❌ Invalid choice");
//             }
//         }
//     }
// }
