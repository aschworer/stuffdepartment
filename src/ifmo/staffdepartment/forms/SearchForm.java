/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.forms;

import ifmo.staffdepartment.model.Department;
import ifmo.staffdepartment.model.Employee;
import ifmo.staffdepartment.model.PositionHistoryItem;
import ifmo.staffdepartment.ApplicationActionServlet;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created: 25.10.2007 || 16:22:49
 *
 * @author A.Zainullina
 */
public class SearchForm extends ValidatorForm {
    private String lastname;
    private String firstname;
    private String middlename;
    private String departmentID;
    private List<Department> departments;

    private List<Employee> result;

    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Employee> getResult() {
        return result;
    }

    public void setResult(List<Employee> result) {
        this.result = result;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        ActionErrors errors = super.validate(actionMapping, httpServletRequest);
        if (date != null) {
            if (date.length() != 0) {
                try {

                    java.sql.Date.valueOf(date);

                } catch (Exception e) {
                    ApplicationActionServlet.bindError(errors, ApplicationActionServlet.ERROR_DATE);
                }
            }
        }
        return errors;
    }

}
