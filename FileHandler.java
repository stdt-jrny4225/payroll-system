package payroll;

import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

class FileHandler {

    private static final String FILE_NAME = "employees.txt";

    // SAVE employees to file
    public static void saveEmployees(ArrayList<BaseEmployee> employees) {

        try (FileWriter writer = new FileWriter(FILE_NAME)) {

            for (BaseEmployee emp : employees) {

                if (emp instanceof PermanentEmployee) {
                    writer.write(
                        "PERMANENT," + emp.id + "," + emp.name + "," + emp.basicSalary + "\n"
                    );
                } 
                else if (emp instanceof ContractEmployee) {
                    writer.write(
                        "CONTRACT," + emp.id + "," + emp.name + "," + emp.basicSalary + "\n"
                    );
                }
            }

        } catch (IOException e) {
            System.out.println("❌ Error saving file");
        }
    }

    // LOAD employees from file
    public static ArrayList<BaseEmployee> loadEmployees() {

        ArrayList<BaseEmployee> employees = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

                String type = data[0];
                int id = Integer.parseInt(data[1]);
                String name = data[2];
                double salary = Double.parseDouble(data[3]);

                if (type.equals("PERMANENT")) {
                    employees.add(new PermanentEmployee(id, name, salary));
                } 
                else if (type.equals("CONTRACT")) {
                    employees.add(new ContractEmployee(id, name, salary));
                }
            }

        } catch (IOException e) {
            System.out.println("⚠️ No previous data found");
        }

        return employees;
    }
}
