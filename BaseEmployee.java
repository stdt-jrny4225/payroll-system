package payroll;

abstract class BaseEmployee {

    protected int id;
    protected String name;
    protected double basicSalary;

    BaseEmployee(int id, String name, double basicSalary) {
        this.id = id;
        this.name = name;
        this.basicSalary = basicSalary;
    }

    public void showDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Basic Salary: " + basicSalary);
    }

    // Polymorphic method
    abstract double calculateNetSalary();
}
