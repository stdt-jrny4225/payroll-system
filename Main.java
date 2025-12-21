package payroll;

import java.util.Scanner;

class Main {
    public static void main(String[] args) {

        PayrollService service = new PayrollService();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== PAYROLL SYSTEM =====");
            System.out.println("1. Add Permanent Employee");
            System.out.println("2. Add Contract Employee");
            System.out.println("3. Show All Employees");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Salary: ");
                double salary = sc.nextDouble();

                service.addEmployee(
                    new PermanentEmployee(id, name, salary)
                );

            } else if (choice == 2) {
                System.out.print("Enter ID: ");
                int id = sc.nextInt();
                sc.nextLine();

                System.out.print("Enter Name: ");
                String name = sc.nextLine();

                System.out.print("Enter Salary: ");
                double salary = sc.nextDouble();

                service.addEmployee(
                    new ContractEmployee(id, name, salary)
                );

            } else if (choice == 3) {
                service.showAllEmployees();

            } else if (choice == 4) {
                System.out.println("👋 Exiting...");
                break;

            } else {
                System.out.println("❌ Invalid choice");
            }
        }

        sc.close();
    }
}
