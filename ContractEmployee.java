package payroll;

class ContractEmployee extends BaseEmployee {

    ContractEmployee(int id, String name, double basicSalary) {
        super(id, name, basicSalary);
    }

    @Override
    double calculateNetSalary() {
        return basicSalary; // no benefits
    }
}
