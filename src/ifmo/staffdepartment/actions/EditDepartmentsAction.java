/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.actions;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import ifmo.staffdepartment.model.Department;
import ifmo.staffdepartment.model.Employee;
import ifmo.staffdepartment.model.EducationHistoryItem;
import ifmo.staffdepartment.forms.EducationHistoryForm;
import ifmo.staffdepartment.forms.EditDepartmentForm;
import ifmo.staffdepartment.ApplicationActionServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created: 14.11.2007 || 12:55:16
 *
 * @author A.Zainullina
 */
public class EditDepartmentsAction extends DispatchAction {
    public ActionForward unspecified(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        EditDepartmentForm form = (EditDepartmentForm) actionForm;

        form.setDepartments(ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartments());
        if (!form.getDepartments().isEmpty()){
        form.setDepartmentIDChoosed(String.valueOf(form.getDepartments().get(0).getId()));
        }
        return actionMapping.findForward("View");
    }

    public ActionForward addDepartment(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        EditDepartmentForm form = (EditDepartmentForm) actionForm;

        ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().addDepartment(form.getNewDepartment());

        form.setDepartments(ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartments());
        form.setDepartmentIDChoosed(String.valueOf(form.getDepartments().get(0).getId()));
        return actionMapping.findForward("View");
    }

    public ActionForward deleteDepartment(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        EditDepartmentForm form = (EditDepartmentForm) actionForm;

        ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().
                deleteDepartment(Integer.parseInt(form.getDepartmentIDChoosed()));

        form.setDepartments(ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().getDepartments());
        form.setDepartmentIDChoosed(String.valueOf(form.getDepartments().get(0).getId()));
        return actionMapping.findForward("View");
    }
}
