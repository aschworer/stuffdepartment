/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.DAO;

import ifmo.staffdepartment.model.Department;
import ifmo.staffdepartment.model.DepartmentHistoryItem;
import ifmo.staffdepartment.model.EducationHistoryItem;

import java.util.List;

/**
 * Created: 24.10.2007 || 16:40:14
 *
 * @author A.Zainullina
 */
public interface DepartmentDAO {

    public abstract List<Department> getDepartments() throws GettingDataFailedException;

    public abstract List<DepartmentHistoryItem> getDepartmentHistory(int employeeID) throws GettingDataFailedException;

    public abstract Integer addDepartmentHistoryItem(DepartmentHistoryItem departmentHistoryItem) throws GettingDataFailedException;

    public abstract void updateDepartmentHistoryList(List<DepartmentHistoryItem> list) throws GettingDataFailedException;

    public abstract Integer addDepartment(Department department) throws GettingDataFailedException;

    public abstract void deleteDepartment(int departmentID) throws GettingDataFailedException;

}
