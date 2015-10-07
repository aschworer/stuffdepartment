/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.forms;

import ifmo.staffdepartment.ApplicationActionServlet;
import ifmo.staffdepartment.DAO.ConnectionFailedException;
import ifmo.staffdepartment.DAO.GettingDataFailedException;
import ifmo.staffdepartment.model.Employee;
import ifmo.staffdepartment.model.Position;
import ifmo.staffdepartment.model.PositionHistoryItem;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created: 26.10.2007 || 17:06:41
 *
 * @author A.Zainullina
 */
public class PositionHistoryForm extends ValidatorForm {
    private List<PositionHistoryItem> history;
    private PositionHistoryItem newHistoryItem;
    private List<Position> positions;


    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public PositionHistoryForm() {
        newHistoryItem = new PositionHistoryItem();
        newHistoryItem.setChangedTo(new Position());
        newHistoryItem.setEmployee(new Employee());
    }

    public List<PositionHistoryItem> getHistory() {
        return history;
    }

    public void setHistory(List<PositionHistoryItem> history) {
        this.history = history;
    }

    public PositionHistoryItem getNewHistoryItem() {
        return newHistoryItem;
    }

    public void setNewHistoryItem(PositionHistoryItem newHistoryItem) {
        this.newHistoryItem = newHistoryItem;
    }


    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        try {
            Employee employee = (Employee) httpServletRequest.getSession().getAttribute("employee");
            if (employee == null) {
                return;
            }
            if (newHistoryItem == null) {
                newHistoryItem = new PositionHistoryItem();
                newHistoryItem.setChangedTo(new Position());
                newHistoryItem.setEmployee(employee);
            }
            history = ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().getPositionHistory(employee.getId());
        } catch (GettingDataFailedException e) {
        } catch (ConnectionFailedException e) {
        }
    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        ActionErrors errors = super.validate(actionMapping, httpServletRequest);
        try {
            for (PositionHistoryItem positionHistoryItem : history) {
                java.sql.Date.valueOf(positionHistoryItem.getChangedWhen());
            }
            java.sql.Date.valueOf(newHistoryItem.getChangedWhen());
        } catch (Exception e) {
            ApplicationActionServlet.bindError(errors, ApplicationActionServlet.ERROR_DATE);
        }
        return errors;
    }
}