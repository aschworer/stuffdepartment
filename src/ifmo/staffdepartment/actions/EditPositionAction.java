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

import ifmo.staffdepartment.forms.EditPositionForm;
import ifmo.staffdepartment.forms.EditPositionForm;
import ifmo.staffdepartment.ApplicationActionServlet;
import ifmo.staffdepartment.model.Position;

import java.util.ArrayList;

/**
 * Created: 14.11.2007 || 14:41:57
 *
 * @author A.Zainullina
 */
public class EditPositionAction extends DispatchAction {
    public ActionForward unspecified(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        EditPositionForm form = (EditPositionForm) actionForm;

        form.setPositions(ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getPositions());

        if (!form.getPositions().isEmpty()) {
            String currentPositionID = String.valueOf(form.getPositions().get(0).getId());
            Position cuPosition = form.getPositions().get(form.getPositions().
                    indexOf(new Position(Integer.parseInt(currentPositionID))));
            form.getCurrentPosition().setId(cuPosition.getId());
            form.getCurrentPosition().setName(cuPosition.getName());
            form.getCurrentPosition().setRequirements(cuPosition.getRequirements());
            form.getCurrentPosition().setConditions(cuPosition.getConditions());
        }
        return actionMapping.findForward("View");
    }

    public ActionForward addPosition(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        EditPositionForm form = (EditPositionForm) actionForm;

        ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().addPosition(form.getNewPosition());

        form.setPositions(ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getPositions());

        /*if (!form.getPositions().isEmpty()) {
            String currentPositionID = String.valueOf(form.getPositions().get(0).getId());
            Position cuPosition = form.getPositions().get(form.getPositions().
                    indexOf(new Position(Integer.parseInt(currentPositionID))));
            form.getCurrentPosition().setId(cuPosition.getId());
            form.getCurrentPosition().setName(cuPosition.getName());
            form.getCurrentPosition().setRequirements(cuPosition.getRequirements());
            form.getCurrentPosition().setConditions(cuPosition.getConditions());
        }*/
        return actionMapping.findForward("View");
    }

    public ActionForward updatePosition(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        EditPositionForm form = (EditPositionForm) actionForm;

        ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().updatePosition(form.getCurrentPosition());

        form.setPositions(ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getPositions());

        if (!form.getPositions().isEmpty()) {
            Position cuPosition = form.getPositions().get(form.getPositions().
                    indexOf(new Position(Integer.parseInt(form.getCurrentPositionID()))));
            form.getCurrentPosition().setId(cuPosition.getId());
            form.getCurrentPosition().setName(cuPosition.getName());
            form.getCurrentPosition().setRequirements(cuPosition.getRequirements());
            form.getCurrentPosition().setConditions(cuPosition.getConditions());
        }
        return actionMapping.findForward("View");
    }

    public ActionForward deletePosition(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        EditPositionForm form = (EditPositionForm) actionForm;

        ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().
                deletePosition(Integer.parseInt(form.getCurrentPositionID()));

        form.setPositions(ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getPositions());


        if (!form.getPositions().isEmpty()) {
            String currentPositionID = String.valueOf(form.getPositions().get(0).getId());
            Position cuPosition = form.getPositions().get(form.getPositions().
                    indexOf(new Position(Integer.parseInt(currentPositionID))));
            form.getCurrentPosition().setId(cuPosition.getId());
            form.getCurrentPosition().setName(cuPosition.getName());
            form.getCurrentPosition().setRequirements(cuPosition.getRequirements());
            form.getCurrentPosition().setConditions(cuPosition.getConditions());
        }
        return actionMapping.findForward("View");
    }

    public ActionForward update(ActionMapping actionMapping, ActionForm actionForm, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
        EditPositionForm form = (EditPositionForm) actionForm;

        form.setPositions(ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getPositions());

        if (!form.getPositions().isEmpty()) {
            Position cuPosition = form.getPositions().get(form.getPositions().
                    indexOf(new Position(Integer.parseInt(form.getCurrentPositionID()))));
            form.getCurrentPosition().setId(cuPosition.getId());
            form.getCurrentPosition().setName(cuPosition.getName());
            form.getCurrentPosition().setRequirements(cuPosition.getRequirements());
            form.getCurrentPosition().setConditions(cuPosition.getConditions());
        }
        return actionMapping.findForward("View");
    }
}