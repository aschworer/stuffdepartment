package ifmo.staffdepartment.DAO;

import ifmo.staffdepartment.DAO.rdbDAO.RdbDAOFactory;

import java.util.Properties;

/**
 * Represents DAOFactory pattern which constructs some concrete DAOFactory depending on
 * the way of DATA_ACCESSOR stored
 *
 */
public abstract class DAOFactory {

    protected Properties databaseConnectionProperties;

    /**
     * Static constant for Relation databases
     */
    public static final int REL_DB = 1;
//    public static final int HIBERNATE = 2;

    /**
     * Establishes connection
     *
     * @return
     */
    // public abstract void createConnection(ServletConfig sc) throws ConnectionFailedException;
    public void setProperties(Properties databaseConnectionProperties){
        this.databaseConnectionProperties = databaseConnectionProperties;
    }

    public Properties getDatabaseConnectionProperties() {
        return databaseConnectionProperties;
    }

    /**
     * Creates new EmployeeDAO object
     *
     * @return EmployeeDAO object
     */
    public abstract EmployeeDAO getEmployeeDAO() throws GettingDataFailedException, ConnectionFailedException;

    public abstract PositionDAO getPositionDAO() throws GettingDataFailedException, ConnectionFailedException;

    public abstract DepartmentDAO getDepartmentDAO() throws GettingDataFailedException, ConnectionFailedException;

    public abstract EmployeeEducationDAO getEmployeeEducationDAO() throws GettingDataFailedException, ConnectionFailedException;

    /**
     * Creates new OrderDAO object
     *
     * @return OrderDAO object
     */
//    public abstract OrderDAO getOrderDAO() throws GettingDataFailedException, ConnectionFailedException;

    /**
     * Creates new OrderProductDAO object
     *
     * @return
     */
//    public abstract OrderedProductDAO getOrderProductDAO() throws GettingDataFailedException, ConnectionFailedException;

    /**
     * Method creates concrete DAOFactory choosing optional from constants
     * stores as static fields in class
     *
     * @param factory
     * @return DAOFactory
     */
    public static DAOFactory getDAOFactory(int factory) {
        switch (factory) {
            case REL_DB:
                return new RdbDAOFactory();
//            case HIBERNATE:
//                return new HibernateDAOFactory();
            default:
                return null;
        }
    }
    //  public abstract void closeConnection() throws ConnectionFailedException;

}
