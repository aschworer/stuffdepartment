package ifmo.staffdepartment.DAO.rdbDAO;

import ifmo.staffdepartment.DAO.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * Class represents DAO Factory Pattern for Relational Database wich allows you to use
 * DB "Shop" with given name and login information
 */
public class RdbDAOFactory extends DAOFactory {
    private static Logger logger = Logger.getLogger(RdbDAOFactory.class);
    /**
     * Creates new <code>RdbOrderDAO</code> object with current connection settings
     *
     * @return new <code>RdbOrderDAO</code> object
     */
    /*public OrderDAO getOrderDAO() throws GettingDataFailedException, ConnectionFailedException {
        Connection c = this.createConnection(databaseConnectionProperties);
        return new RdbOrderDAO(c);
    }*/

    /**
     * Creates new <code>RdbEmployeeDAO</code> object with current connection settings
     *
     * @return new <code>RdbEmployeeDAO</code> object
     */
    public EmployeeDAO getEmployeeDAO() throws GettingDataFailedException, ConnectionFailedException {
        Connection c = this.createConnection(databaseConnectionProperties);
        return new RdbEmployeeDAO(c);
    }


    public PositionDAO getPositionDAO() throws GettingDataFailedException, ConnectionFailedException {
        Connection c = this.createConnection(databaseConnectionProperties);
        return new RdbPositionDAO(c);
    }

    public DepartmentDAO getDepartmentDAO() throws GettingDataFailedException, ConnectionFailedException {
        Connection c = this.createConnection(databaseConnectionProperties);
        return new RdbDepartmentDAO(c);
    }


    public EmployeeEducationDAO getEmployeeEducationDAO() throws GettingDataFailedException, ConnectionFailedException {
        Connection c = this.createConnection(databaseConnectionProperties);
        return new RdbEmployeeEducationDAO(c);
    }


    /**
     * Creates new <code>RdbProductDAO</code> object with current connection settings
     *
     * @return new <code>RdbProductDAO</code> object
     */
//    public OrderedProductDAO getOrderProductDAO() throws GettingDataFailedException,ConnectionFailedException{
//        Connection c = this.createConnection(databaseConnectionProperties);
//        return new RdbOrderedProductDAO(c);
//    }

    /**
     * Method establishes connection with database
     */
    private Connection createConnection(Properties props) {

        String url = (String) props.get("url");
        String userName = (String) props.get("userName");
        String password = (String) props.get("password");
        String database = (String) props.get("dbname");

        Connection c = null;
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            Properties properties = new Properties();
            properties.setProperty("user", userName);
            properties.setProperty("password", password);
            properties.setProperty("useUnicode", "true");
            properties.setProperty("characterEncoding", "UTF-8");
            c = DriverManager.getConnection(url, properties);
//            c = DriverManager.getConnection(url, userName, password);
            c.createStatement().execute("use " + database + ";");
        } catch (ClassNotFoundException e) {
            logger.error("Driver not found\n" + e.getMessage());
        }
        catch (InstantiationException e) {
            logger.error("InstallationException\n" + e.getMessage());
        }
        catch (IllegalAccessException e) {
            logger.error("Illegal Access\n" + e.getMessage());
        }
        catch (SQLException e) {
            logger.error("\nSQLException caught\n" + e.getMessage());
        }
        return c;
    }

//    public Connection getConn() {
//        return conn;
//    }
//
//    public void setConn(Connection conn) {
//        this.conn = conn;
//    }

    /*public void closeConnection() throws ConnectionFailedException {
        try {
            conn.close();
        } catch (SQLException e) {
            throw new ConnectionFailedException();
        }
    }*/
}
