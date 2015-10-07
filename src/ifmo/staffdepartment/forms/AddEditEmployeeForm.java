/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.forms;

import ifmo.staffdepartment.model.*;
import ifmo.staffdepartment.ApplicationActionServlet;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created: 20.10.2007 || 16:37:05
 *
 * @author A.Zainullina
 */
public class AddEditEmployeeForm extends ValidatorForm {
    private String passportLink;
    private FormFile passportCopy;
    private FormFile photoCopy;
    private FormFile resumeCopy;
    private Employee employee;
    private Shedule employeeShedule;
    private Shedule shedule;
    private List<Department> departments;
    private List<Position> positions;


    public Shedule getEmployeeShedule() {
        return employeeShedule;
    }

    public void setEmployeeShedule(Shedule employeeShedule) {
        this.employeeShedule = employeeShedule;
    }

    public FormFile getPhotoCopy() {
        return photoCopy;
    }

    public void setPhotoCopy(FormFile photoCopy) {
        this.photoCopy = photoCopy;
    }

    public FormFile getResumeCopy() {
        return resumeCopy;
    }

    public void setResumeCopy(FormFile resumeCopy) {
        this.resumeCopy = resumeCopy;
    }

    public List<Department> getDepartments() {
        return departments;
    }

    public void setDepartments(List<Department> departments) {
        this.departments = departments;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public String getPassportLink() {
        return passportLink;
    }

    public void setPassportLink(String passportLink) {
        this.passportLink = passportLink;
    }

    public AddEditEmployeeForm() {
        employee = new Employee();
        employee.setId(0);

        employee.setPassport(new Passport());
        employee.setCurrentPosition(new Position());
        employee.setCurrentDepartment(new Department());
        employee.setShedule(new Shedule());

        employee.setLastname("");
        employee.setFirstname("");
        employee.setMiddlename("");
        employee.setCharacteristics("");
//        employee.getPassport().setNo();
//        employee.getPassport().setSer(2);
        employee.getPassport().setReceivedFrom("");
        employee.getPassport().setReceivedWhere("");
//        employee.getPassport().setReceivedWhen("1985-11-20");
    }


    public FormFile getPassportCopy() {
        return passportCopy;
    }

    public void setPassportCopy(FormFile passportCopy) {
        this.passportCopy = passportCopy;
    }


    public Shedule getShedule() {
        return shedule;
    }

    public void setShedule(Shedule shedule) {
        this.shedule = shedule;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        ActionErrors errors = super.validate(actionMapping, httpServletRequest);

        if (employee.getFirstname().length() == 0) {
            ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_REQUIRED, new String[]{"Firstname"});
        }

        if (employee.getLastname().length() == 0) {
            ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_REQUIRED, new String[]{"Lastname"});
        }
        if (employee.getMiddlename().length() == 0) {
            ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_REQUIRED, new String[]{"Middlename"});
        }

        if (employee.getPassport().getSer()==0){
            ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_INTEGER, new String[]{"Passport ser"});
        }

        if (employee.getPassport().getNo()==0){
            ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_INTEGER, new String[]{"Passport no"});
        }

        try {
            java.sql.Date.valueOf(employee.getPassport().getReceivedWhen());
        } catch (Exception e) {
            ApplicationActionServlet.bindError(errors, ApplicationActionServlet.ERROR_DATE);
        }
        return errors;
    }
}
