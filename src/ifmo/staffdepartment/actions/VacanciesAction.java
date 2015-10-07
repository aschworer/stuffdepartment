/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.actions;

import org.apache.struts.actions.DispatchAction;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import ifmo.staffdepartment.model.Employee;
import ifmo.staffdepartment.forms.SearchForm;
import ifmo.staffdepartment.forms.VacanciesForm;
import ifmo.staffdepartment.ApplicationActionServlet;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created: 25.10.2007 || 17:42:58
 *
 * @author A.Zainullina
 */
public class VacanciesAction extends DispatchAction {

    public ActionForward unspecified(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        VacanciesForm form = (VacanciesForm) actionForm;
        form.setVacancies(ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getVacancies());
        return actionMapping.findForward("View");
    }

    /*public ActionForward submit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        return actionMapping.findForward("View");
    }

    private List<Employee> find(List<Employee> sourseList, String firstname, String lastname, String middlename,
                                String departmentID, String date) {
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
                if (!"".equals(departmentID)) {
                    if (!(Integer.parseInt(departmentID) == employee.getCurrentDepartment().getId())) {
                        i.remove();
                        continue;
                    }
                }
            }
            if (date != null) {
                if (!"".equals(date)) {
                }
            }

        }
        return result;
    }*/
}
