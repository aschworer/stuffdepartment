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

import ifmo.staffdepartment.forms.SearchForm;
import ifmo.staffdepartment.forms.DepartmentHistoryForm;
import ifmo.staffdepartment.forms.JobHistoryForm;
import ifmo.staffdepartment.ApplicationActionServlet;
import ifmo.staffdepartment.model.DepartmentHistoryItem;
import ifmo.staffdepartment.model.Employee;

/**
 * Created: 26.10.2007 || 13:40:44
 *
 * @author A.Zainullina
 */
public class DepartmentHistoryAction extends DispatchAction {
    public ActionForward unspecified(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        DepartmentHistoryForm form = (DepartmentHistoryForm) actionForm;

        Employee employee = (Employee) httpServletRequest.getSession().getAttribute("employee");
        if (employee == null) {
            return actionMapping.findForward("ManageEmployees");
        }
        form.getNewHistoryItem().setEmployee(employee);
        form.setHistory(ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartmentHistory(employee.getId()));

        form.setDepartments(ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartments());
        return actionMapping.findForward("View");
    }

    public ActionForward submit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        DepartmentHistoryForm form = (DepartmentHistoryForm) actionForm;
        Employee employee = (Employee) httpServletRequest.getSession().getAttribute("employee");
        if (employee == null) {
            return actionMapping.findForward("ManageEmployees");
        }
        form.getNewHistoryItem().setEmployee(employee);
        form.getNewHistoryItem().initDate();
        ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().addDepartmentHistoryItem(form.getNewHistoryItem());
        form.setHistory(ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartmentHistory(employee.getId()));
        form.setDepartments(ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartments());
        return actionMapping.findForward("View");
    }


    public ActionForward updateList(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        DepartmentHistoryForm form = (DepartmentHistoryForm) actionForm;
        Employee employee = (Employee) httpServletRequest.getSession().getAttribute("employee");
        if (employee == null) {
            return actionMapping.findForward("ManageEmployees");
        }
        ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().updateDepartmentHistoryList(form.getHistory());

        form.setHistory(ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartmentHistory(employee.getId()));
        form.setDepartments(ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartments());
        return actionMapping.findForward("View");
    }

}
