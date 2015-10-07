/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.DAO;

import ifmo.staffdepartment.model.*;

import java.util.List;

/**
 * Created: 30.10.2007 || 17:38:49
 *
 * @author A.Zainullina
 */
public interface EmployeeEducationDAO {

    public abstract List<EducationHistoryItem> getEducationHistory(int employeeID) throws GettingDataFailedException;

    public abstract Integer addEducationHistoryItem(EducationHistoryItem educationHistoryItem) throws GettingDataFailedException;

    public abstract Integer addEducationPlace(EducationPlace educationPlace) throws GettingDataFailedException;

    public abstract List<EducationPlace> getEducationPlaces() throws GettingDataFailedException;

    public abstract List<EducationType> getEducationTypes() throws GettingDataFailedException;

    public abstract void updateEducationList(List<EducationHistoryItem> list) throws GettingDataFailedException;

}
