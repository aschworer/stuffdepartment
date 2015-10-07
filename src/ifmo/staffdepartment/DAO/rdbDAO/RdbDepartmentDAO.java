/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.DAO.rdbDAO;

import ifmo.staffdepartment.DAO.GettingDataFailedException;
import ifmo.staffdepartment.DAO.ConnectionFailedException;
import ifmo.staffdepartment.DAO.DepartmentDAO;
import ifmo.staffdepartment.model.*;

import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Connection;

/**
 * Created: 24.10.2007 || 16:41:34
 *
 * @author A.Zainullina
 */
public class RdbDepartmentDAO implements DepartmentDAO {
    private Connection conn;

    private PreparedStatement selectAllDepartments;
    private PreparedStatement selectDepartmentHistory;
    private PreparedStatement insertHistoryItem;
    private PreparedStatement updateEmployeeCurrentDepartment;
    private PreparedStatement insertDepartment;
    private PreparedStatement deleteDepartment;

    public RdbDepartmentDAO(Connection conn) throws GettingDataFailedException {
        try {
            selectAllDepartments = conn.prepareStatement("select * from departments order by name;");
            selectDepartmentHistory = conn.prepareStatement("select dh.id history_id, dh.employee employee_id, dh.changed_to department_id, dh.changed_date changed_date, d.name department_name " +
                    "from departments_history dh, departments d, employees e where dh.employee=? and d.id=dh.changed_to and e.id=dh.employee order by dh.changed_date;");
            insertHistoryItem =
                    conn.prepareStatement("insert into departments_history (employee, changed_to, changed_date) values (?, ?, ?);");
            updateEmployeeCurrentDepartment = conn.prepareStatement("update employees set current_department=? where id=?;");
            insertDepartment = conn.prepareStatement("insert into departments (name) values (?)");
            deleteDepartment = conn.prepareStatement("delete from departments where id = ?");
        } catch (SQLException e) {
            throw new GettingDataFailedException();
        }
        this.conn = conn;
    }


    public List<Department> getDepartments() throws GettingDataFailedException {
        ArrayList<Department> result = new ArrayList<Department>();
        try {
            ResultSet rs = selectAllDepartments.executeQuery();
            while (rs.next()) {
                Department nextDepartment = new Department(rs.getInt("id"));
                nextDepartment.setName(rs.getString("name"));
                result.add(nextDepartment);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
        return result;
    }


    public List<DepartmentHistoryItem> getDepartmentHistory(int employeeID) throws GettingDataFailedException {
        ArrayList<DepartmentHistoryItem> result = new ArrayList<DepartmentHistoryItem>();
        try {
            selectDepartmentHistory.setInt(1, employeeID);
            ResultSet rs = selectDepartmentHistory.executeQuery();
            while (rs.next()) {
                DepartmentHistoryItem nextDepartmentHistoryItem = new DepartmentHistoryItem();
                nextDepartmentHistoryItem.setId(rs.getInt("history_id"));

                Department changedTo = new Department(rs.getInt("department_id"));
                changedTo.setName(rs.getString("department_name"));
//                changedTo.setId(rs.getInt("department_id"));
                nextDepartmentHistoryItem.setChangedTo(changedTo);

                nextDepartmentHistoryItem.setEmployee(new Employee(rs.getInt("employee_id")));

                nextDepartmentHistoryItem.setChangedWhenDateFormat(rs.getDate("changed_date"));
                result.add(nextDepartmentHistoryItem);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
        return result;
    }


    public Integer addDepartmentHistoryItem(DepartmentHistoryItem departmentHistoryItem) throws GettingDataFailedException {
        int id;
        try {
            insertHistoryItem.setInt(1, departmentHistoryItem.getEmployee().getId());
            insertHistoryItem.setInt(2, departmentHistoryItem.getChangedTo().getId());
            insertHistoryItem.setDate(3, departmentHistoryItem.getChangedWhenDateFormat());

            insertHistoryItem.executeUpdate();
            ResultSet r = insertHistoryItem.executeQuery("SELECT * FROM departments_history ORDER BY id DESC LIMIT 1");
            r.next();
            id = r.getInt("id");
            r.close();

            updateEmployeeCurrentDepartment.setInt(1, departmentHistoryItem.getChangedTo().getId());
            updateEmployeeCurrentDepartment.setInt(2, departmentHistoryItem.getEmployee().getId());
            updateEmployeeCurrentDepartment.executeUpdate();
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
        return id;
    }


    public Integer addDepartment(Department department) throws GettingDataFailedException {
        int id;
        try {
            insertDepartment.setString(1, department.getName());

            insertDepartment.executeUpdate();
            ResultSet r = insertDepartment.executeQuery("SELECT * FROM departments ORDER BY id DESC LIMIT 1");
            r.next();
            id = r.getInt("id");
            r.close();

        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
        return id;
    }

    public void updateDepartmentHistoryList(List<DepartmentHistoryItem> list) throws GettingDataFailedException {
        try {
            for (DepartmentHistoryItem departmentHistoryItem: list) {
                insertHistoryItem.executeUpdate
                        ("delete from " + DepartmentHistoryItem.TABLE + " where id=" + departmentHistoryItem.getId()+";");
                addDepartmentHistoryItem(departmentHistoryItem);
            }
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
    }


    public void deleteDepartment(int departmentID) throws GettingDataFailedException {
        try {
            deleteDepartment.setInt(1, departmentID);
            deleteDepartment.executeUpdate();
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
    }

    public void close() throws ConnectionFailedException {
        try {
            selectAllDepartments.close();
            selectDepartmentHistory.close();
            conn.close();
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
    }

    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }
}
