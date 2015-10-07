/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.DAO.rdbDAO;

import ifmo.staffdepartment.DAO.EmployeeEducationDAO;
import ifmo.staffdepartment.DAO.GettingDataFailedException;
import ifmo.staffdepartment.DAO.ConnectionFailedException;
import ifmo.staffdepartment.model.*;

import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Created: 30.10.2007 || 17:38:23
 *
 * @author A.Zainullina
 */
public class RdbEmployeeEducationDAO implements EmployeeEducationDAO {
    private Connection conn;

    private PreparedStatement selectEducation;
    private PreparedStatement selectAllPlaces;
    private PreparedStatement selectAllTypes;
    private PreparedStatement insertEducationHistoryItem;
    private PreparedStatement insertEducationPlace;

    public RdbEmployeeEducationDAO(Connection conn) throws GettingDataFailedException {
        try {
            selectEducation = conn.prepareStatement("select ee.id, et.type, et.id type_id, places.name, places.id place_id, ee.start_date, ee.end_date, ee.document_copy_filename, ee.document_no from employee_education ee, places, education_types et where ee.place=places.id and ee.education_type=et.id and ee.employee=?;");
            selectAllPlaces = conn.prepareStatement("select * from places order by name;");
            selectAllTypes = conn.prepareStatement("select * from education_types order by type;");
            insertEducationHistoryItem = conn.prepareStatement
                    ("INSERT INTO employee_education(employee, place, start_date, end_date, education_type,document_copy_filename, document_no) VALUES (?,?,?,?,?,?,?);");
            insertEducationPlace = conn.prepareStatement
                    ("INSERT INTO places(name) VALUES (?);");

        } catch (SQLException e) {
            throw new GettingDataFailedException();
        }
        this.conn = conn;
    }

    public List<EducationHistoryItem> getEducationHistory(int employeeID) throws GettingDataFailedException {

        ArrayList<EducationHistoryItem> result = new ArrayList<EducationHistoryItem>();
        try {
            selectEducation.setInt(1, employeeID);
            ResultSet rs = selectEducation.executeQuery();
            while (rs.next()) {
                EducationHistoryItem educationHistoryItem = new EducationHistoryItem(rs.getInt("id"));
                educationHistoryItem.setPlace(new EducationPlace());
                educationHistoryItem.setType(new EducationType());
                educationHistoryItem.setEmployee(new Employee());

                educationHistoryItem.setDocumentCopyFilename(rs.getString("document_copy_filename"));
                educationHistoryItem.setDocumentNO(rs.getString("document_no"));

                educationHistoryItem.getEmployee().setId(employeeID);
                educationHistoryItem.setFromDateFormat(rs.getDate("start_date"));
                educationHistoryItem.setTillDateFormat(rs.getDate("end_date"));
                educationHistoryItem.getPlace().setId(rs.getInt("place_id"));
                educationHistoryItem.getPlace().setName(rs.getString("name"));
                educationHistoryItem.getType().setName(rs.getString("type"));
                educationHistoryItem.getType().setId(rs.getInt("type_id"));

                result.add(educationHistoryItem);
            }
            rs.close();
            return result;
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
    }

    public Integer addEducationHistoryItem(EducationHistoryItem educationHistoryItem) throws GettingDataFailedException {
        int id;
        try {
            insertEducationHistoryItem.setInt(1, educationHistoryItem.getEmployee().getId());
            insertEducationHistoryItem.setInt(2, educationHistoryItem.getPlace().getId());
            insertEducationHistoryItem.setDate(3, educationHistoryItem.getFromDateFormat());
            insertEducationHistoryItem.setDate(4, educationHistoryItem.getTillDateFormat());
            insertEducationHistoryItem.setInt(5, educationHistoryItem.getType().getId());
            insertEducationHistoryItem.setString(6, educationHistoryItem.getDocumentCopyFilename());
            insertEducationHistoryItem.setString(7, educationHistoryItem.getDocumentNO());

            insertEducationHistoryItem.executeUpdate();
            ResultSet r = insertEducationHistoryItem.executeQuery("SELECT * FROM " + EducationHistoryItem.TABLE + " ORDER BY id DESC LIMIT 1");
            r.next();
            id = r.getInt("id");
            r.close();
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
        return id;

    }

    public Integer addEducationPlace(EducationPlace educationPlace) throws GettingDataFailedException {
        int id;
        try {
            insertEducationPlace.setString(1, educationPlace.getName());

            insertEducationPlace.executeUpdate();
            ResultSet r = insertEducationPlace.executeQuery("SELECT * FROM " + EducationPlace.TABLE + " ORDER BY id DESC LIMIT 1");
            r.next();
            id = r.getInt("id");
            r.close();
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
        return id;
    }

    public void updateEducationList(List<EducationHistoryItem> list) throws GettingDataFailedException {
        try {
            for (EducationHistoryItem educationHistoryItem : list) {
                insertEducationHistoryItem.executeUpdate
                        ("delete from " + EducationHistoryItem.TABLE + " where id=" + educationHistoryItem.getId() + ";");
                addEducationHistoryItem(educationHistoryItem);
            }
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
    }

    public List<EducationPlace> getEducationPlaces() throws GettingDataFailedException {
        ArrayList<EducationPlace> result = new ArrayList<EducationPlace>();
        try {
            ResultSet rs = selectAllPlaces.executeQuery();
            while (rs.next()) {
                EducationPlace nextPlace = new EducationPlace(rs.getInt("id"));
                nextPlace.setName(rs.getString("name"));
                result.add(nextPlace);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
        return result;
    }

    public List<EducationType> getEducationTypes() throws GettingDataFailedException {
        ArrayList<EducationType> result = new ArrayList<EducationType>();
        try {
            ResultSet rs = selectAllTypes.executeQuery();
            while (rs.next()) {
                EducationType nextType = new EducationType(rs.getInt("id"));
                nextType.setName(rs.getString("type"));
                result.add(nextType);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
        return result;
    }


    public void close() throws ConnectionFailedException {
        try {
//            insertPer.close();
            selectEducation.close();
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
