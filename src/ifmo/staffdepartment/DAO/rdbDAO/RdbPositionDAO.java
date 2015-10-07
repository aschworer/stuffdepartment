/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.DAO.rdbDAO;

import ifmo.staffdepartment.DAO.PositionDAO;
import ifmo.staffdepartment.DAO.GettingDataFailedException;
import ifmo.staffdepartment.DAO.ConnectionFailedException;
import ifmo.staffdepartment.model.Position;
import ifmo.staffdepartment.model.Employee;
import ifmo.staffdepartment.model.PositionHistoryItem;
import ifmo.staffdepartment.model.Department;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

/**
 * Created: 24.10.2007 || 16:41:25
 *
 * @author A.Zainullina
 */
public class RdbPositionDAO implements PositionDAO {
    private Connection conn;
    private PreparedStatement selectAllPositions;
    private PreparedStatement selectVacancies;

    private PreparedStatement selectPositionHistory;
    private PreparedStatement insertHistoryItem;
    private PreparedStatement updateEmployeeCurrentPosition;
    private PreparedStatement insertPosition;
    private PreparedStatement deletePosition;
    private PreparedStatement updatePosition;


    public RdbPositionDAO(Connection conn) throws GettingDataFailedException {
        try {
            selectAllPositions = conn.prepareStatement("select * from positions order by name;");
            selectVacancies = conn.prepareStatement("select * from positions where id not in (select current_position from employees) order by name;");

            selectPositionHistory = conn.prepareStatement("select dh.id history_id, dh.employee employee_id, dh.changed_to position_id, dh.changed_date changed_date, d.name position_name " +
                    "from positions_history dh, positions d, employees e where dh.employee=? and d.id=dh.changed_to and e.id=dh.employee order by dh.changed_date;");
            insertHistoryItem =
                    conn.prepareStatement("insert into positions_history (employee, changed_to, changed_date) values (?, ?, ?);");
            updateEmployeeCurrentPosition = conn.prepareStatement("update employees set current_position=? where id=?;");

            insertPosition = conn.prepareStatement("insert into positions (name, requirements, conditions) values (?,?,?)");

            deletePosition = conn.prepareStatement("delete from positions where id = ?");

            updatePosition = conn.prepareStatement("update positions set name=?, requirements=?, conditions=? where id=?");

        } catch (SQLException e) {
            throw new GettingDataFailedException();
        }
        this.conn = conn;
    }


    public List<Position> getPositions() throws GettingDataFailedException {
        ArrayList<Position> result = new ArrayList<Position>();
        try {
            ResultSet rs = selectAllPositions.executeQuery();
            while (rs.next()) {
                Position nextPosition = new Position(rs.getInt("id"));
                nextPosition.setConditions(rs.getString("conditions"));
                nextPosition.setName(rs.getString("name"));
                nextPosition.setRequirements(rs.getString("requirements"));
                result.add(nextPosition);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
        return result;
    }

    public List<Position> getVacancies() throws GettingDataFailedException {
        ArrayList<Position> result = new ArrayList<Position>();
        try {
            ResultSet rs = selectVacancies.executeQuery();
            while (rs.next()) {
                Position nextPosition = new Position(rs.getInt("id"));
                nextPosition.setConditions(rs.getString("conditions"));
                nextPosition.setName(rs.getString("name"));
                nextPosition.setRequirements(rs.getString("requirements"));
                result.add(nextPosition);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
        return result;
    }


    public void updatePositionHistoryList(List<PositionHistoryItem> list) throws GettingDataFailedException {
        try {
            for (PositionHistoryItem positionHistoryItem: list) {
                insertHistoryItem.executeUpdate
                        ("delete from " + PositionHistoryItem.TABLE + " where id=" + positionHistoryItem.getId()+";");
                addPositionHistoryItem(positionHistoryItem);
            }
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
    }


    public Integer addPosition(Position position) throws GettingDataFailedException {
        int id;
        try {
            insertPosition.setString(1, position.getName());
            insertPosition.setString(2, position.getRequirements());
            insertPosition.setString(3, position.getConditions());

            insertPosition.executeUpdate();
            ResultSet r = insertPosition.executeQuery("SELECT * FROM positions ORDER BY id DESC LIMIT 1");
            r.next();
            id = r.getInt("id");
            r.close();

        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
        return id;
    }

    public void updatePosition(Position position) throws GettingDataFailedException {
        try {
            updatePosition.setString(1, position.getName());
            updatePosition.setString(2, position.getRequirements());
            updatePosition.setString(3, position.getConditions());
            updatePosition.setInt(4, position.getId());

            updatePosition.executeUpdate();
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
    }

    public List<PositionHistoryItem> getPositionHistory(int employeeID) throws GettingDataFailedException {
        ArrayList<PositionHistoryItem> result = new ArrayList<PositionHistoryItem>();
        try {
            selectPositionHistory.setInt(1, employeeID);
            ResultSet rs = selectPositionHistory.executeQuery();
            while (rs.next()) {
                PositionHistoryItem nextPositionHistoryItem = new PositionHistoryItem();
                nextPositionHistoryItem.setId(rs.getInt("history_id"));

                Position changedTo = new Position(rs.getInt("position_id"));
                changedTo.setName(rs.getString("position_name"));
                nextPositionHistoryItem.setChangedTo(changedTo);

                nextPositionHistoryItem.setEmployee(new Employee(rs.getInt("employee_id")));

                nextPositionHistoryItem.setChangedWhenDateFormat(rs.getDate("changed_date"));
                result.add(nextPositionHistoryItem);
            }
            rs.close();
        } catch (SQLException e) {
            System.err.println("\n---SQLException caught---\n" + e.getMessage());
            throw new GettingDataFailedException();
        }
        return result;
    }

    public void deletePosition(int positionID) throws GettingDataFailedException {
        try {
            deletePosition.setInt(1, positionID);
            deletePosition.executeUpdate();
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
    }

    public Integer addPositionHistoryItem(PositionHistoryItem positionHistoryItem) throws GettingDataFailedException {
        int id;
        try {
            insertHistoryItem.setInt(1, positionHistoryItem.getEmployee().getId());
            insertHistoryItem.setInt(2, positionHistoryItem.getChangedTo().getId());
            insertHistoryItem.setDate(3, positionHistoryItem.getChangedWhenDateFormat());

            insertHistoryItem.executeUpdate();
            ResultSet r = insertHistoryItem.executeQuery("SELECT * FROM positions_history ORDER BY id DESC LIMIT 1");
            r.next();
            id = r.getInt("id");
            r.close();

            updateEmployeeCurrentPosition.setInt(1, positionHistoryItem.getChangedTo().getId());
            updateEmployeeCurrentPosition.setInt(2, positionHistoryItem.getEmployee().getId());
            updateEmployeeCurrentPosition.executeUpdate();
        } catch (SQLException e) {
            throw new GettingDataFailedException(e);
        }
        return id;
    }


    public void close() throws ConnectionFailedException {
        try {
            selectAllPositions.close();
            selectVacancies.close();
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
