/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.forms;

import ifmo.staffdepartment.ApplicationActionServlet;
import ifmo.staffdepartment.DAO.ConnectionFailedException;
import ifmo.staffdepartment.DAO.GettingDataFailedException;
import ifmo.staffdepartment.model.Department;
import ifmo.staffdepartment.model.DepartmentHistoryItem;
import ifmo.staffdepartment.model.Employee;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created: 26.10.2007 || 13:36:11
 *
 * @author A.Zainullina
 */
public class DepartmentHistoryForm extends ValidatorForm {
    private List<DepartmentHistoryItem> history;
    private DepartmentHistoryItem newHistoryItem;
    private List<Department> departments;


    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public DepartmentHistoryForm() {
        newHistoryItem = new DepartmentHistoryItem();
        newHistoryItem.setChangedTo(new Department());
        newHistoryItem.setEmployee(new Employee());
    }

    public List<DepartmentHistoryItem> getHistory() {
        return history;
    }

    public void setHistory(List<DepartmentHistoryItem> history) {
        this.history = history;
    }

    public DepartmentHistoryItem getNewHistoryItem() {
        return newHistoryItem;
    }

    public void setNewHistoryItem(DepartmentHistoryItem newHistoryItem) {
        this.newHistoryItem = newHistoryItem;
    }


    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        try {
            Employee employee = (Employee) httpServletRequest.getSession().getAttribute("employee");
            if (employee == null) {
                return;
            }
            if (newHistoryItem == null) {
                newHistoryItem = new DepartmentHistoryItem();
                newHistoryItem.setChangedTo(new Department());
                getNewHistoryItem().setEmployee(employee);
            }
            history = ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartmentHistory(employee.getId());
        } catch (
                GettingDataFailedException e) {
        } catch (
                ConnectionFailedException e) {
        }
    }


    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        ActionErrors errors = super.validate(actionMapping, httpServletRequest);
        try {
            for (DepartmentHistoryItem departmentHistoryItem : history) {
                java.sql.Date.valueOf(departmentHistoryItem.getChangedWhen());
            }
            java.sql.Date.valueOf(newHistoryItem.getChangedWhen());
        } catch (Exception e) {
            ApplicationActionServlet.bindError(errors, ApplicationActionServlet.ERROR_DATE);
        }
        return errors;
    }
}
