package ifmo.staffdepartment;

import ifmo.staffdepartment.DAO.DAOFactory;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.upload.FormFile;

import javax.servlet.ServletException;
import java.util.Properties;
import java.io.InputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;

public class ApplicationActionServlet extends ActionServlet {
    public static final String SESSIONS_ATRIBUTE = "sessions";
    public static final String VIEW = "View";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/staff";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "welcome";
    public static final String DB_NAME = "staff";
    public static DAOFactory DATA_ACCESSOR;

    private static Logger logger = Logger.getLogger(ApplicationActionServlet.class);
    public static final String STAFF_UPLOADS_DIR = "..\\webapps\\staff_uploads\\";

    public void init() throws ServletException {
        super.init();
        Properties databaseConnectionsProperties = new Properties();
        databaseConnectionsProperties.put("url", DB_URL);
        databaseConnectionsProperties.put("userName", DB_USERNAME);
        databaseConnectionsProperties.put("password", DB_PASSWORD);
        databaseConnectionsProperties.put("dbname", DB_NAME);
        DATA_ACCESSOR = DAOFactory.getDAOFactory(1);
        DATA_ACCESSOR.setProperties(databaseConnectionsProperties);
//      logger.error("Cannot instanciate Action Servlet cause of database access error", e);
        logger.debug("--Servlet .init() invoked--");
    }

    public void destroy() {
        super.destroy();
        logger.debug("--Servlet .destroy() invoked--");
    }

    public static void copyFileToServer(String userID, FormFile fileSrc) throws java.io.IOException {
        String fileName = fileSrc.getFileName();
        InputStream uploadInStream = fileSrc.getInputStream();
        File dir = new File(ApplicationActionServlet.STAFF_UPLOADS_DIR + userID + "\\");
        if (!dir.exists()) {
            dir.mkdirs();
        }
        File file = new File(ApplicationActionServlet.STAFF_UPLOADS_DIR + userID + "\\" + fileName);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream streamOUT;
        try {
            streamOUT = new FileOutputStream(file);
            int c = 0;
            while ((c = uploadInStream.read()) != -1) {
                streamOUT.write(c);
            } // while
            streamOUT.flush();
            streamOUT.close();
        } catch (FileNotFoundException e) {
            logger.error("FILE NOT FOUND AND CANNOT BE CREATED");
        }
    }

    public static void bindError(ActionErrors errors, String error) {
        if (errors == null) {
            errors = new ActionErrors();
        }
        errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionMessage(error));
    }

    public static void bindArgumentError(ActionErrors errors, String errorMessage, String[] arguments) {
        if (errors == null) {
            errors = new ActionErrors();
        }
        errors.add(ActionErrors.GLOBAL_MESSAGE,
                new ActionMessage(errorMessage, arguments));
    }


    public static final String ERROR_MAXLENGTH = "errors.maxlength";
    public static final String ERROR_ALPHA_NUMERIC_EXT = "alpha.numeric.ext";
    public static final String ERROR_ALPHA_NUMERIC = "alpha.numeric";
    public static final String ERROR_RANGE = "errors.range";
    public static final String ERROR_TIME_COMPARISION = "error.time.comparision";
    public static final String ERROR_ILLEGAL_TIME = "error.illegal.time";
    public static final String ERROR_INTEGER = "errors.integer";
    public static final String ERROR_DATA_ACCESS_EXCEPTION = "data.access.exception";
    public static final String ERROR_NAME_ALREADY_EXISTS = "name.already.exists";
    public static final String ERROR_ALREADY_EXISTS = "already.exists";
    public static final String ERROR_PASSWORD_CONFIRM = "errors.password.confirm";
    public static final String ERROR_PCM_APP = "pcm.app.error";
    public static final String ERROR_OPERATION_IN_USE = "operation.in.use.error";
    public static final String ERROR_DIS_CODE_ALREADY_EXISTS = "dis.code.already.exists";
    public static final String ERROR_REQUIRED = "errors.required";
    public static final String ERROR_LONG = "errors.long";
    public static final String ERROR_DATE = "errors.dateformat";
}