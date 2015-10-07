package ifmo.staffdepartment.DAO;

import ifmo.staffdepartment.model.*;

import java.util.Collection;
import java.util.List;

public interface EmployeeDAO {
    public List<Employee> getEmployees() throws GettingDataFailedException;

    public Integer saveEmployee(Employee employee) throws GettingDataFailedException;

    public Integer saveShedule(Shedule shedule, int EmployeeID) throws GettingDataFailedException;

    public Shedule getShedule(int employeeID) throws GettingDataFailedException;

    public void deleteEmployee(int employeeID) throws GettingDataFailedException;

    public Employee getEmployee(int employeeID) throws GettingDataFailedException;

    public List<JobHistoryItem> getJobHistory(int employeeID) throws GettingDataFailedException;

    public Integer addJobHistoryItem(JobHistoryItem jobHistoryItem) throws GettingDataFailedException;

    public void updateJobHistoryList(List<JobHistoryItem> list) throws GettingDataFailedException;

    public void updateExtraHistoryItem(List<ExtraItem> list) throws GettingDataFailedException;

    public List<ExtraItem> getExtra(int employeeID) throws GettingDataFailedException;

    public Integer addExtraItem(ExtraItem extraItem) throws GettingDataFailedException;

}
