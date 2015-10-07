package ifmo.staffdepartment.DAO;

/**
 * Throwed when cannot connect to DataBase if driver not found or login failed, etc.
 * Throwed by {@link ifmo.staffdepartment.DAO.rdbDAO.RdbDAOFactory <code>DAO.rdbDAO.RdbDAOFactory</code>} instance
 *
 * @see ifmo.staffdepartment.DAO.rdbDAO.RdbDAOFactory
 */
public class ConnectionFailedException extends Exception {
}
