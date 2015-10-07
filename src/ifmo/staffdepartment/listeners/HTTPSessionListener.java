package ifmo.staffdepartment.listeners;

import ifmo.staffdepartment.ApplicationActionServlet;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.Date;
import java.util.HashMap;

public class HTTPSessionListener implements HttpSessionListener {
    protected HashMap<HttpSession, Date> sessions;

    public HTTPSessionListener() {
        sessions = new HashMap<HttpSession, Date>();
    }

    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
//        httpSessionEvent.getSession().setAttribute(ApplicationActionServlet.HREFS_PARAMETER,
//                ApplicationActionServlet.HREFS);
//        httpSessionEvent.getSession().setAttribute(ApplicationActionServlet.LOCATIONS_PARAMETER,
//                ApplicationActionServlet.LOCATIONS);
        System.out.println("--HTTPSessionListener: new session created. id=" + httpSessionEvent.getSession().getId());
        sessions.put(httpSessionEvent.getSession()/*.getId()*/, new Date(httpSessionEvent.getSession().getCreationTime()));
        if (httpSessionEvent.getSession().getServletContext().getAttribute(ApplicationActionServlet.SESSIONS_ATRIBUTE) == null) {
            httpSessionEvent.getSession().getServletContext().setAttribute(ApplicationActionServlet.SESSIONS_ATRIBUTE, sessions);
        }
    }

    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        sessions.remove(httpSessionEvent.getSession());
    }
}