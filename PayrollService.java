package payroll;

import java.util.ArrayList;

class PayrollService {

    private ArrayList<BaseEmployee> employees;

    PayrollService() {
        employees = FileHandler.loadEmployees();
    }

    public void addEmployee(BaseEmployee emp) {
        employees.add(emp);
        FileHandler.saveEmployees(employees);
        System.out.println("✅ Employee added & saved");
    }

    public void showAllEmployees() {
        for (BaseEmployee emp : employees) {
            emp.showDetails();
            System.out.println("Net Salary: " + emp.calculateNetSalary());
            System.out.println("--------------------");
        }
    }
}
