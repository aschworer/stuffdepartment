/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.forms;

import ifmo.staffdepartment.ApplicationActionServlet;
import ifmo.staffdepartment.DAO.ConnectionFailedException;
import ifmo.staffdepartment.DAO.GettingDataFailedException;
import ifmo.staffdepartment.model.Employee;
import ifmo.staffdepartment.model.ExtraItem;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.upload.FormFile;
import org.apache.struts.validator.ValidatorForm;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created: 19.11.2007 || 13:32:24
 *
 * @author A.Zainullina
 */
public class ExtraInfoForm extends ValidatorForm {
    private List<ExtraItem> history;
    private ExtraItem newHistoryItem;
    private String saveMode;
    private FormFile fileToAdd;


    public FormFile getFileToAdd() {
        return fileToAdd;
    }

    public void setFileToAdd(FormFile fileToAdd) {
        this.fileToAdd = fileToAdd;
    }

    public List<ExtraItem> getHistory() {
        return history;
    }

    public void setHistory(List<ExtraItem> history) {
        this.history = history;
    }

    public ExtraItem getNewHistoryItem() {
        return newHistoryItem;
    }

    public void setNewHistoryItem(ExtraItem newHistoryItem) {
        this.newHistoryItem = newHistoryItem;
    }

    public String getSaveMode() {
        return saveMode;
    }

    public void setSaveMode(String saveMode) {
        this.saveMode = saveMode;
    }


    public void reset(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        try {
            newHistoryItem = new ExtraItem();
            Employee employee = (Employee) httpServletRequest.getSession().getAttribute("employee");
            if (employee == null) {
                return;
            }
            newHistoryItem.setEmployee(employee);

            history = ApplicationActionServlet.DATA_ACCESSOR.getEmployeeDAO().getExtra(employee.getId());
        } catch (GettingDataFailedException e) {
        } catch (ConnectionFailedException e) {
        }

    }

    public ActionErrors validate(ActionMapping actionMapping, HttpServletRequest httpServletRequest) {
        ActionErrors errors = super.validate(actionMapping, httpServletRequest);
        for (ExtraItem newHistoryItem : history) {
            if (newHistoryItem.getDescription().length() == 0) {
                ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_REQUIRED, new String[]{"Description"});
                return errors;
            }

            if (newHistoryItem.getDocumentNO().length() == 0) {
                ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_REQUIRED, new String[]{"Document No"});
                return errors;
            }

            if (newHistoryItem.getName().length() == 0) {
                ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_REQUIRED, new String[]{"Achievement name"});
                return errors;
            }


            try {
                Integer.parseInt(newHistoryItem.getDocumentNO());
            } catch (Exception e) {
                ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_INTEGER, new String[]{"Document No"});
                return errors;
            }
        }
        if (newHistoryItem.getDescription().length() == 0) {
            ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_REQUIRED, new String[]{"Description"});
            return errors;
        }

        if (newHistoryItem.getDocumentNO().length() == 0) {
            ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_REQUIRED, new String[]{"Document No"});
            return errors;
        }

        if (newHistoryItem.getName().length() == 0) {
            ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_REQUIRED, new String[]{"Achievement name"});
            return errors;
        }


        try {
            Integer.parseInt(newHistoryItem.getDocumentNO());
        } catch (Exception e) {
            ApplicationActionServlet.bindArgumentError(errors, ApplicationActionServlet.ERROR_INTEGER, new String[]{"Document No"});
            return errors;
        }
        return errors;
    }
}


