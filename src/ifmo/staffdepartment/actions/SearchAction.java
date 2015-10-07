/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.actions;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionForm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ifmo.staffdepartment.ApplicationActionServlet;
import ifmo.staffdepartment.model.Employee;
import ifmo.staffdepartment.model.Department;
import ifmo.staffdepartment.forms.SearchForm;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.sql.Date;

/**
 * Created: 25.10.2007 || 16:24:44
 *
 * @author A.Zainullina
 */
public class SearchAction extends DispatchAction {
    public ActionForward unspecified(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        SearchForm form = (SearchForm) actionForm;
        form.setDepartments(ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartments());
         Department emptyDepartment = new Department(0);
        emptyDepartment.setName("Не имеет значения");
        form.getDepartments().add(0,emptyDepartment);
        return actionMapping.findForward("View");
    }

    public ActionForward submit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        SearchForm form = (SearchForm) actionForm;
        form.setDepartments(ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartments());
        Department emptyDepartment = new Department(0);
        emptyDepartment.setName("Не имеет значения");
        form.getDepartments().add(0,emptyDepartment);
        
        Date date = null;
        if (!"".equals(form.getDate())) {
            date = Date.valueOf(form.getDate());
        }

        form.setResult(find(ApplicationActionServlet.DATA_ACCESSOR.getEmployeeDAO().getEmployees(),
                form.getFirstname(), form.getLastname(), form.getMiddlename(), form.getDepartmentID(),
                date));

        return actionMapping.findForward("View");
    }

    private List<Employee> find(List<Employee> sourseList, String firstname, String lastname, String middlename,
                                String departmentID, java.sql.Date date) {
        ArrayList<Employee> result = new ArrayList<Employee>();
        result.addAll(sourseList);


        Iterator i = result.iterator();

        while (i.hasNext()) {
            Employee employee = (Employee) i.next();
            if (lastname != null) {
                if (!"".equals(lastname)) {
                    if (!lastname.equals(employee.getLastname())) {
                        i.remove();
                        continue;
                    }
                }
            }
            if (firstname != null) {
                if (!"".equals(firstname)) {
                    if (!firstname.equals(employee.getFirstname())) {
                        i.remove();
                        continue;
                    }
                }
            }
            if (middlename != null) {
                if (!"".equals(middlename)) {
                    if (!middlename.equals(employee.getMiddlename())) {
                        i.remove();
                        continue;
                    }
                }
            }
            if (departmentID != null) {
                if (!"".equals(departmentID) && !"0".equals(departmentID)) {
                    if (!(Integer.parseInt(departmentID) == employee.getCurrentDepartment().getId())) {
                        i.remove();
                        continue;
                    }
                }
            }

            if (date != null) {
                if (!(date.equals(employee.getHeiringDate()))) {
                    i.remove();
                }
            }
        }
        return result;
    }

}
