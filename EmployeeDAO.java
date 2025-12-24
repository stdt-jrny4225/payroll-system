package payroll;

import java.sql.*;
import java.util.Vector;

public class EmployeeDAO {

    public static Vector<Vector<Object>> getAllEmployees() {
        Vector<Vector<Object>> data = new Vector<>();

        try (Connection con = DBConnection.getConnection()) {

            String sql = "SELECT * FROM employees";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                row.add(rs.getInt("id"));
                row.add(rs.getString("name"));
                row.add(rs.getString("department"));
                row.add(rs.getDouble("basic_salary"));
                data.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public static boolean addEmployee(
            String name, String dept, double salary) {

        try (Connection con = DBConnection.getConnection()) {

            String sql =
                "INSERT INTO employees(name, department, basic_salary) VALUES (?,?,?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, dept);
            ps.setDouble(3, salary);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public static boolean deleteEmployee(int id) {
    try (Connection con = DBConnection.getConnection()) {
        PreparedStatement ps =
            con.prepareStatement("DELETE FROM employees WHERE id=?");
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        return false;
    }
}

public static boolean updateEmployee(int id, String name, String dept, double sal) {
    try (Connection con = DBConnection.getConnection()) {
        PreparedStatement ps =
            con.prepareStatement(
                "UPDATE employees SET name=?, department=?, basic_salary=? WHERE id=?");
        ps.setString(1, name);
        ps.setString(2, dept);
        ps.setDouble(3, sal);
        ps.setInt(4, id);
        return ps.executeUpdate() > 0;
    } catch (Exception e) {
        return false;
    }
}

}
