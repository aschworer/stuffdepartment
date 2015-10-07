package ifmo.staffdepartment.DAO.rdbDAO;

import ifmo.staffdepartment.DAO.*;
import ifmo.staffdepartment.model.*;
import ifmo.staffdepartment.ApplicationActionServlet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class represents implementation of Transfer Object pattern
 * for working with concrete DB entry - Order
 */
public class RdbEmployeeDAO implements EmployeeDAO {
    private Connection conn;
    private PreparedStatement selectAllEmployees;
    private PreparedStatement insertEmployee;
    private PreparedStatement insertShedule;
    private PreparedStatement updateShedule;
    private PreparedStatement selectFromTableByField;
    private PreparedStatement deleteEmployee;
    private PreparedStatement updateEmployee;
    private PreparedStatement selectEmployee;
    private PreparedStatement selectHeiringDate;
    private PreparedStatement selectJobHistory;
    private PreparedStatement selectExtra;
    private PreparedStatement insertJobHistoryItem;
    private PreparedStatement insertExtraItem;



    /**
     * Constructs an instance of <code>RdbEmployeeDAO</code> object with given connection and
     * <code>Statement</code> object for Statement creation
     *
     * @param conn Connection with database
     */
    public RdbEmployeeDAO(Connection conn) throws GettingDataFailedException {
        try {
            selectAllEmployees = conn.prepareStatement(
                    "SELECT p1.id, p1.firstname, p1.lastname, p1.middlename, p1.charachteristics, p1.photo_filename, p1.resume_filename, " +
                            "p1.passport_no, p1.passport_ser, p1.passport_received_when, p1.passport_received_where, p1.passport_received_from, p1.passport_copy_filename, " +
                            "p2.id position_id, p2.name position_name, p3.id department_id, p3.name department_name " +
                            "from employees p1, positions p2, departments p3 where p1.current_position=p2.id and p1.current_department=p3.id " +
                            "group by p1.id order by lastname asc;");

            selectEmployee = conn.prepareStatement("SELECT p1.id, p1.firstname, p1.lastname, p1.middlename, p1.charachteristics, p1.photo_filename, p1.resume_filename,\n" +
                    "p1.passport_no, p1.passport_ser, p1.passport_received_when, p1.passport_received_where, p1.passport_received_from, p1.passport_copy_filename,\n" +
                    "p2.id position_id, p2.name position_name, p3.id department_id, p3.name department_name\n" +
                    "from employees p1, positions p2, departments p3 where p1.current_position=p2.id and p1.current_department=p3.id and p1.id=?;");

            selectHeiringDate = conn.prepareStatement("select changed_date from positions_history where employee=? order by changed_date asc limit 1;");

            selectFromTableByField = conn.prepareStatement("select * from shedules where employee=?;");

            deleteEmployee = conn.prepareStatement("delete from employees where id=?;");

            insertShedule = conn.prepareStatement("INSERT INTO shedules (mondayFlag, tuesdayFlag, wednesdayFlag, " +
                    "thursdayFlag, fridayFlag, saturdayFlag, sundayFlag, employee) VALUES (?,?,?,?,?,?,?,?);");

            updateShedule = conn.prepareStatement("update shedules set mondayFlag=?, tuesdayFlag=?, wednesdayFlag=?, " +
                    "thursdayFlag=?, fridayFlag=?, saturdayFlag=?, sundayFlag=? where employee=?;");

            insertEmployee = conn.prepareStatement(
                    "INSERT INTO employees (firstname, lastname, middlename, passport_no, passport_ser," +
                            " passport_received_when, passport_received_from," +
                            "  passport_received_where, passport_copy_filename," +
                            " charachteristics, current_position, current_department, resume_filename, photo_filename)\n" +
                            "  VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?);");
            updateEmployee = conn.prepareStatement(
                    "update employees set firstname=?, lastname=?, middlename=?, passport_no=?, passport_ser=?," +
                            " passport_received_when=?, passport_received_from=?," +
                            "  passport_received_where=?, passport_copy_filename=?," +
                            " charachteristics=?, current_position=?, current_department=?, resume_filename=?, photo_filename=? where id =?;");

            insertJobHistoryItem = conn.prepareStatement("insert into employee_job_history " +
                    "(employee, company, position, start_date, end_date, notes) values (?,?,?,?,?,?);");

            selectJobHistory = conn.prepareStatement("select * from employee_job_history where employee=?;");

            selectExtra = conn.prepareStatement("select * from extra_information where employee=?;");

            insertExtraItem = conn.prepareStatement("insert into extra_information (employee, document_copy_filename, " +
                    "achievement_name, achievement_description, document_no) values (?, ?, ?, ?, ?);");

        } catch (SQLException e) {
            throw new GettingDataFailedException();
        }
        this.conn = conn;
    }


    public Integer saveEmployee(Employee employee) throws GettingDataFailedException {
        int id;
        try {
            if (employee.getId() == 0) {
                insertEmployee.setString(1, employee.getFirstname());
                insertEmployee.setString(2, employee.getLastname());
                insertEmployee.setString(3, employee.getMiddlename());
                insertEmployee.setInt(4, employee.getPassport().getNo());
                insertEmployee.setInt(5, employee.getPassport().getSer());
                insertEmployee.setDate(6, employee.getPassport().getReceivedWhenDateFormat());
                insertEmployee.setString(7, employee.getPassport().getReceivedFrom());
                insertEmployee.setString(8, employee.getPassport().getReceivedWhere());
                insertEmployee.setString(9, employee.getPassport().getPassportCopyFilename());
                insertEmployee.setString(10, employee.getCharacteristics());
                insertEmployee.setInt(11, employee.getCurrentPosition().getId());
                insertEmployee.setInt(12, employee.getCurrentDepartment().getId());
                insertEmployee.setString(13, employee.getResumeCopyFilename());
                insertEmployee.setString(14, employee.getPhotoCopyFilename());

                insertEmployee.executeUpdate();
                ResultSet r = insertEmployee.executeQuery("SELECT * FROM employees ORDER BY id DESC LIMIT 1");
                r.next();
                id = r.getInt("id");
                r.close();

                DepartmentHistoryItem departmentHistoryItem = new DepartmentHistoryItem();
                departmentHistoryItem.setChangedTo(new Department(employee.getCurrentDepartment().getId()));
                departmentHistoryItem.setEmployee(new Employee(id));
                departmentHistoryItem.setChangedWhenDateFormat(new java.sql.Date(System.currentTimeMillis()));
                ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().addDepartmentHistoryItem(departmentHistoryItem);

                PositionHistoryItem positionHistoryItem = new PositionHistoryItem();
                positionHistoryItem.setChangedTo(new Position(employee.getCurrentPosition().getId()));
                positionHistoryItem.setEmployee(new Employee(id));
                positionHistoryItem.setChangedWhenDateFormat(new java.sql.Date(System.currentTimeMillis()));
                ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().addPositionHistoryItem(positionHistoryItem);

            } else {
                id = employee.getId();


                if (!((ApplicationActionServlet.DATA_ACCESSOR.getEmployeeDAO()).getEmployee(id).getCurrentDepartment().getId()
                        == employee.getCurrentDepartment().getId())) {
                    DepartmentHistoryItem departmentHistoryItem = new DepartmentHistoryItem();
                    departmentHistoryItem.setChangedTo(new Department(employee.getCurrentDepartment().getId()));
                    departmentHistoryItem.setEmployee(new Employee(id));
                    departmentHistoryItem.setChangedWhenDateFormat(new java.sql.Date(System.currentTimeMillis()));
                    ApplicationActionServlet.DATA_ACCESSOR.getDepartmentDAO().addDepartmentHistoryItem(departmentHistoryItem);
                }

                if (!(ApplicationActionServlet.DATA_ACCESSOR.getEmployeeDAO().getEmployee(id).getCurrentPosition().getId()
                        == employee.getCurrentPosition().getId())) {
                    PositionHistoryItem positionHistoryItem = new PositionHistoryItem();
                    positionHistoryItem.setChangedTo(new Position(employee.getCurrentPosition().getId()));
                    positionHistoryItem.setEmployee(new Employee(id));
                    positionHistoryItem.setChangedWhenDateFormat(new java.sql.Date(System.currentTimeMillis()));
                    ApplicationActionServlet.DATA_ACCESSOR.getPositionDAO().addPositionHistoryItem(positionHistoryItem);
                }

                updateEmployee.setString(1, employee.getFirstname());
                updateEmployee.setString(2, employee.getLastname());
                updateEmployee.setString(3, employee.getMiddlename());
                updateEmployee.setInt(4, employee.getPassport().getNo());
                updateEmployee.setInt(5, employee.getPassport().getSer());
                updateEmployee.setDate(6, employee.getPassport().getReceivedWhenDateFormat());
                updateEmployee.setString(7, employee.getPassport().getReceivedFrom());
                updateEmployee.setString(8, employee.getPassport().getReceivedWhere());
                updateEmployee.setString(9, employee.getPassport().getPassportCopyFilename());
                updateEmployee.setString(10, employee.getCharacteristics());
                updateEmployee.setInt(11, employee.getCurrentPosition().getId());
                updateEmployee.setInt(12, employee.getCurrentDepartment().getId());
                updateEmployee.setString(13, employee.getResumeCopyFilename());
                updateEmployee.setString(14, employee.getPhotoCopyFilename());
                updateEmployee.setInt(15, id);

                updateEmployee.executeUpdate();
            }
            saveShedule(employee.getShedule(), id);
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        } catch (ConnectionFailedException e) {
            throw new GettingDataFailedException(e);
        }
        return id;
    }

    public Integer saveShedule(Shedule shedule, int employeeID) throws GettingDataFailedException {
        int id;
        try {
            Shedule sheduleTest = getShedule(employeeID);
            if (sheduleTest != null) {
                id = sheduleTest.getId();
                updateShedule.setString(1, String.valueOf(shedule.getMondayFlag()));
                updateShedule.setString(2, String.valueOf(shedule.getTuesdayFlag()));
                updateShedule.setString(3, String.valueOf(shedule.getWednesdayFlag()));
                updateShedule.setString(4, String.valueOf(shedule.getThursdayFlag()));
                updateShedule.setString(5, String.valueOf(shedule.getFridayFlag()));
                updateShedule.setString(6, String.valueOf(shedule.getSaturdayFlag()));
                updateShedule.setString(7, String.valueOf(shedule.getSundayFlag()));
                updateShedule.setInt(8, employeeID);

                updateShedule.executeUpdate();
            } else {
                insertShedule.setString(1, String.valueOf(shedule.getMondayFlag()));
                insertShedule.setString(2, String.valueOf(shedule.getTuesdayFlag()));
                insertShedule.setString(3, String.valueOf(shedule.getWednesdayFlag()));
                insertShedule.setString(4, String.valueOf(shedule.getThursdayFlag()));
                insertShedule.setString(5, String.valueOf(shedule.getFridayFlag()));
                insertShedule.setString(6, String.valueOf(shedule.getSaturdayFlag()));
                insertShedule.setString(7, String.valueOf(shedule.getSundayFlag()));
                insertShedule.setInt(8, employeeID);

                insertShedule.executeUpdate();

                ResultSet r = insertShedule.executeQuery("SELECT * FROM shedules ORDER BY id DESC LIMIT 1");
                r.next();
                id = r.getInt("id");
                r.close();
            }
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
        return id;
    }


    public void deleteEmployee(int employeeID) throws GettingDataFailedException {
        try {
            deleteEmployee.setInt(1, employeeID);
            deleteEmployee.executeUpdate();
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
    }

    public Employee getEmployee(int employeeID) throws GettingDataFailedException {
        try {
            selectEmployee.setInt(1, employeeID);
            ResultSet rs = selectEmployee.executeQuery();
            Employee newEmployee = new Employee();
            while (rs.next()) {
                newEmployee.setId(rs.getInt("id"));
                newEmployee.setCurrentDepartment(new Department());
                newEmployee.setCurrentPosition(new Position());
                newEmployee.setPassport(new Passport());

                newEmployee.setFirstname(rs.getString("firstname"));
                newEmployee.setLastname(rs.getString("lastname"));
                newEmployee.setMiddlename(rs.getString("middlename"));
                newEmployee.setCharacteristics(rs.getString("charachteristics"));
                newEmployee.setPhotoCopyFilename(rs.getString("photo_filename"));
                newEmployee.setResumeCopyFilename(rs.getString("resume_filename"));

                newEmployee.getCurrentDepartment().setId(rs.getInt("department_id"));
                newEmployee.getCurrentDepartment().setName(rs.getString("department_name"));
                newEmployee.getCurrentPosition().setId(rs.getInt("position_id"));
                newEmployee.getCurrentPosition().setName(rs.getString("position_name"));

                newEmployee.getPassport().setNo(rs.getInt("passport_no"));
                newEmployee.getPassport().setSer(rs.getInt("passport_ser"));
                newEmployee.getPassport().setReceivedWhere(rs.getString("passport_received_where"));
                newEmployee.getPassport().setReceivedFrom(rs.getString("passport_received_from"));
                newEmployee.getPassport().setReceivedWhenDateFormat(rs.getDate("passport_received_when"));
                newEmployee.getPassport().setPassportCopyFilename(rs.getString("passport_copy_filename"));

                newEmployee.setShedule(getShedule(newEmployee.getId()));

                selectHeiringDate.setInt(1, newEmployee.getId());
                ResultSet rsHeiring = selectHeiringDate.executeQuery();
                while (rsHeiring.next()) {
                    newEmployee.setHeiringDate(rsHeiring.getDate(1));
                }
            }
            rs.close();
            return newEmployee;
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
    }

    /**
     * Method constructs String value with needed information about all employees
     * stored in database (lastname, firstname and advicer)
     *
     * @return String value for needed information
     */
    public List<Employee> getEmployees() throws GettingDataFailedException {
        ArrayList<Employee> result = new ArrayList<Employee>();
        try {
            ResultSet rs = selectAllEmployees.executeQuery();
            while (rs.next()) {
                Employee newEmployee = new Employee(rs.getInt("id"));
                newEmployee.setCurrentDepartment(new Department());
                newEmployee.setCurrentPosition(new Position());
                newEmployee.setPassport(new Passport());

                newEmployee.setFirstname(rs.getString("firstname"));
                newEmployee.setLastname(rs.getString("lastname"));
                newEmployee.setMiddlename(rs.getString("middlename"));
                newEmployee.setCharacteristics(rs.getString("charachteristics"));
                newEmployee.setPhotoCopyFilename(rs.getString("photo_filename"));
                newEmployee.setResumeCopyFilename(rs.getString("resume_filename"));

                newEmployee.getCurrentDepartment().setId(rs.getInt("department_id"));
                newEmployee.getCurrentDepartment().setName(rs.getString("department_name"));
                newEmployee.getCurrentPosition().setId(rs.getInt("position_id"));
                newEmployee.getCurrentPosition().setName(rs.getString("position_name"));

                newEmployee.getPassport().setNo(rs.getInt("passport_no"));
                newEmployee.getPassport().setSer(rs.getInt("passport_ser"));
                newEmployee.getPassport().setReceivedWhere(rs.getString("passport_received_where"));
                newEmployee.getPassport().setReceivedFrom(rs.getString("passport_received_from"));
                newEmployee.getPassport().setReceivedWhenDateFormat(rs.getDate("passport_received_when"));
                newEmployee.getPassport().setPassportCopyFilename(rs.getString("passport_copy_filename"));

                newEmployee.setShedule(getShedule(newEmployee.getId()));

                selectHeiringDate.setInt(1, newEmployee.getId());
                ResultSet rsHeiring = selectHeiringDate.executeQuery();
                while (rsHeiring.next()) {
                    newEmployee.setHeiringDate(rsHeiring.getDate(1));
                }
                result.add(newEmployee);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
        return result;
    }


    public Shedule getShedule
            (
                    int employeeID) throws GettingDataFailedException {
        try {
            /*selectFromTableByField.setString(1, "shedules");
           selectFromTableByField.setString(2, "employee");*/
            selectFromTableByField.setInt(1, employeeID);
            ResultSet rs = selectFromTableByField.executeQuery();
            Shedule newShedule = null;
            while (rs.next()) {
                newShedule = new Shedule();
                newShedule.setId(rs.getInt("id"));
                newShedule.setMondayFlag(Boolean.parseBoolean(rs.getString("mondayFlag")));
                newShedule.setTuesdayFlag(Boolean.parseBoolean(rs.getString("tuesdayFlag")));
                newShedule.setWednesdayFlag(Boolean.parseBoolean(rs.getString("wednesdayFlag")));
                newShedule.setThursdayFlag(Boolean.parseBoolean(rs.getString("thursdayFlag")));
                newShedule.setFridayFlag(Boolean.parseBoolean(rs.getString("fridayFlag")));
                newShedule.setSaturdayFlag(Boolean.parseBoolean(rs.getString("saturdayFlag")));
                newShedule.setSundayFlag(Boolean.parseBoolean(rs.getString("sundayFlag")));
            }
            rs.close();
            return newShedule;
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
    }


    public List<JobHistoryItem> getJobHistory(int employeeID) throws GettingDataFailedException {
        ArrayList<JobHistoryItem> result = new ArrayList<JobHistoryItem>();
        try {
            selectJobHistory.setInt(1, employeeID);
            ResultSet rs = selectJobHistory.executeQuery();
            while (rs.next()) {
                JobHistoryItem nextJobHistoryItem = new JobHistoryItem();
                nextJobHistoryItem.setId(rs.getInt("id"));
                nextJobHistoryItem.setCompany(rs.getString("company"));
                nextJobHistoryItem.setEmployee(new Employee(rs.getInt("employee")));
                nextJobHistoryItem.setNote(rs.getString("notes"));
                nextJobHistoryItem.setPosition(rs.getString("position"));
                nextJobHistoryItem.setFromDateFormat(rs.getDate("start_date"));
                nextJobHistoryItem.setTillDateFormat(rs.getDate("end_date"));
                result.add(nextJobHistoryItem);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
        return result;
    }


    public Integer addJobHistoryItem(JobHistoryItem jobHistoryItem) throws GettingDataFailedException {
        int id;
        try {
            insertJobHistoryItem.setInt(1, jobHistoryItem.getEmployee().getId());
            insertJobHistoryItem.setString(2, jobHistoryItem.getCompany());
            insertJobHistoryItem.setString(3, jobHistoryItem.getPosition());
            insertJobHistoryItem.setDate(4, jobHistoryItem.getFromDateFormat());
            insertJobHistoryItem.setDate(5, jobHistoryItem.getTillDateFormat());
            insertJobHistoryItem.setString(6, jobHistoryItem.getNote());

            insertJobHistoryItem.executeUpdate();
            ResultSet r = insertJobHistoryItem.executeQuery("SELECT * FROM employee_job_history ORDER BY id DESC LIMIT 1");
            r.next();
            id = r.getInt("id");
            r.close();
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
        return id;
    }

    public List<ExtraItem> getExtra(int employeeID) throws GettingDataFailedException {
       ArrayList<ExtraItem> result = new ArrayList<ExtraItem>();
        try {
            selectExtra.setInt(1, employeeID);
            ResultSet rs = selectExtra.executeQuery();
            while (rs.next()) {
                ExtraItem extraItem = new ExtraItem();
                extraItem.setId(rs.getInt("id"));
                extraItem.setDescription(rs.getString("achievement_description"));
                extraItem.setEmployee(new Employee(rs.getInt("employee")));
                extraItem.setName(rs.getString("achievement_name"));
                extraItem.setDocumentCopyFilename(rs.getString("document_copy_filename"));
                extraItem.setDocumentNO(rs.getString("document_no"));
                result.add(extraItem);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
        return result;
    }

    public void updateJobHistoryList(List<JobHistoryItem> list) throws GettingDataFailedException {
        try {
            for (JobHistoryItem jobHistoryItem : list) {
                insertJobHistoryItem.executeUpdate
                        ("delete from " + JobHistoryItem.TABLE + " where id=" + jobHistoryItem.getId()+";");
                addJobHistoryItem(jobHistoryItem);
            }
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
    }


    public void updateExtraHistoryItem(List<ExtraItem> list) throws GettingDataFailedException {
        try {
            for (ExtraItem extraItem : list) {
                insertExtraItem.executeUpdate
                        ("delete from " + ExtraItem.TABLE + " where id=" + extraItem.getId()+";");
                addExtraItem(extraItem);
            }
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }


    }


    public Integer addExtraItem(ExtraItem extraItem) throws GettingDataFailedException {
        int id;
        try {
            insertExtraItem.setInt(1, extraItem.getEmployee().getId());
            insertExtraItem.setString(2, extraItem.getDocumentCopyFilename());
            insertExtraItem.setString(3, extraItem.getName());
            insertExtraItem.setString(4, extraItem.getDescription());
            insertExtraItem.setString(5, extraItem.getDocumentNO());

            insertExtraItem.executeUpdate();
            ResultSet r = insertExtraItem.executeQuery("SELECT * FROM extra_information ORDER BY id DESC LIMIT 1");
            r.next();
            id = r.getInt("id");
            r.close();
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
        return id;
    }

    public void close() throws ConnectionFailedException {
        try {
//            insertPer.close();
            selectAllEmployees.close();
//            selectPerID.close();
            conn.close();
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
    }


    protected void finalize() throws Throwable {
        super.finalize();
        close();
    }
}
