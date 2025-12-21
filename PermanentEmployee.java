package payroll;

class PermanentEmployee extends BaseEmployee {

    PermanentEmployee(int id, String name, double basicSalary) {
        super(id, name, basicSalary);
    }

    @Override
    double calculateNetSalary() {
        double hra = basicSalary * 0.20;
        double da = basicSalary * 0.10;
        double pf = basicSalary * 0.12;

        return basicSalary + hra + da - pf;
    }
}
