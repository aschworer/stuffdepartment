/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.forms;

import ifmo.staffdepartment.model.Department;
import ifmo.staffdepartment.model.Employee;
import ifmo.staffdepartment.ApplicationActionServlet;
import ifmo.staffdepartment.DAO.ConnectionFailedException;
import ifmo.staffdepartment.DAO.GettingDataFailedException;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created: 14.11.2007 || 12:55:25
 *
 * @author A.Zainullina
 */
public class EditDepartmentForm extends ValidatorForm {
    private String departmentIDChoosed;
    private List<Department> departments;
    private Department newDepartment;


    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String getDepartmentIDChoosed() {
        return departmentIDChoosed;
    }

    public void setDepartmentIDChoosed(String departmentIDChoosed) {
        this.departmentIDChoosed = departmentIDChoosed;
    }


    public Department getNewDepartment() {
        return newDepartment;
    }

    public void setNewDepartment(Department newDepartment) {
        this.newDepartment = newDepartment;
    }

    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        newDepartment = new Department();
    }


    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        ActionErrors errors = super.validate(actionMapping, httpServletRequest);
        try {
            if ("deleteDepartment".equals(httpServletRequest.getParameter("method"))) {
                for (Employee employee : ApplicationActionServlet.DATA_ACCESSOR.getEmployeeDAO().getEmployees()) {
                    if (employee.getCurrentDepartment().getId() == Integer.parseInt(departmentIDChoosed)) {
                        ApplicationActionServlet.bindArgumentError(errors, "error",
                                new String[]{"Department cannot be deleted cause it is in use by "+ employee.getFullName() +" employee"});
                        return errors;
                    }
                }
            }
            if ("addDepartment".equals(httpServletRequest.getParameter("method"))) {
                if (newDepartment.getName() == null) {
                    ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_REQUIRED,
                            new String[]{"Department name"});
                    return errors;
                } else if (newDepartment.getName().length() == 0) {
                    ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_REQUIRED,
                            new String[]{"Department name"});
                    return errors;
                }

                for (Department department : ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartments()) {
                    if (department.getName().equals(newDepartment.getName())) {
                        ApplicationActionServlet.bindArgumentError(errors, "already.exists",
                                new String[]{"Department with this name"});
                    }
                    return errors;
                }
            }
        } catch (ConnectionFailedException e) {

        } catch (
                GettingDataFailedException e) {
        }
        return errors;
    }
}
