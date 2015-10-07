/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.forms;

import ifmo.staffdepartment.model.Employee;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created: 20.10.2007 || 16:14:57
 *
 * @author A.Zainullina
 */
public class ManageEmployeesForm extends ValidatorForm {

    private String employeeID;

    private String choice;


    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

    private List<Employee> employees;

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }


    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
//        choice = "editEmployee";
    }
}
