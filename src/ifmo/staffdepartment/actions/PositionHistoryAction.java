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

import ifmo.staffdepartment.forms.DepartmentHistoryForm;
import ifmo.staffdepartment.forms.PositionHistoryForm;
import ifmo.staffdepartment.forms.JobHistoryForm;
import ifmo.staffdepartment.model.Employee;
import ifmo.staffdepartment.ApplicationActionServlet;

/**
 * Created: 26.10.2007 || 17:07:44
 *
 * @author A.Zainullina
 */
public class PositionHistoryAction extends DispatchAction {
    public ActionForward unspecified(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        PositionHistoryForm form = (PositionHistoryForm) actionForm;

        Employee employee = (Employee)httpServletRequest.getSession().getAttribute("employee");
        if (employee==null){
            return actionMapping.findForward("ManageEmployees");
        }
        form.getNewHistoryItem().setEmployee(employee);
        form.setHistory(ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getPositionHistory(employee.getId()));

        form.setPositions(ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getPositions());
        return actionMapping.findForward("View");
    }

    public ActionForward submit(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        PositionHistoryForm form = (PositionHistoryForm) actionForm;
        Employee employee = (Employee)httpServletRequest.getSession().getAttribute("employee");
        if (employee==null){
            return actionMapping.findForward("ManageEmployees");
        }
        form.getNewHistoryItem().setEmployee(employee);
        form.getNewHistoryItem().initDate();
        ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().addPositionHistoryItem(form.getNewHistoryItem());
        form.setHistory(ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getPositionHistory(employee.getId()));
        form.setPositions(ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getPositions());
        return actionMapping.findForward("View");
    }

    public ActionForward updateList(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        PositionHistoryForm form = (PositionHistoryForm) actionForm;
        Employee employee = (Employee) httpServletRequest.getSession().getAttribute("employee");
        if (employee == null) {
            return actionMapping.findForward("ManageEmployees");
        }
        ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().updatePositionHistoryList(form.getHistory());

        form.setHistory(ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getPositionHistory(employee.getId()));
        form.setPositions(ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getPositions());
        return actionMapping.findForward("View");
    }

}
