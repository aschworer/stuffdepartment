/*
 * Nakuru. 2007. 
 */
package ifmo.staffdepartment.DAO;

import ifmo.staffdepartment.model.Position;
import ifmo.staffdepartment.model.PositionHistoryItem;

import java.util.List;

/**
 * Created: 24.10.2007 || 16:40:06
 *
 * @author A.Zainullina
 */
public interface PositionDAO {
    
    public abstract List<Position> getPositions() throws GettingDataFailedException;

    public abstract List<Position> getVacancies() throws GettingDataFailedException;

    public abstract List<PositionHistoryItem> getPositionHistory(int employeeID) throws GettingDataFailedException;

    public abstract Integer addPositionHistoryItem(PositionHistoryItem positionHistoryItem) throws GettingDataFailedException;

    public abstract void updatePositionHistoryList(List<PositionHistoryItem> list) throws GettingDataFailedException;

    public abstract Integer addPosition(Position position) throws GettingDataFailedException;

    public abstract void deletePosition(int positionID) throws GettingDataFailedException;

    public abstract void updatePosition(Position position) throws GettingDataFailedException;

}
