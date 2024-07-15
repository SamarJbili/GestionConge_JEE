package controleur;

import domaine.User;
import services.UserSessionRemote;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/ViewEmployeesServlet")
public class ViewEmployeesServlet extends HttpServlet {

    @EJB(lookup = "ejb:/ejb_appGestionConges/UR!services.UserSessionRemote")
    private UserSessionRemote userSession;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Context context = new InitialContext();
            userSession = (UserSessionRemote) context.lookup("java:global/ejb_appGestionConges/UR!services.UserSessionRemote");
        } catch (NamingException e) {
            e.printStackTrace();
        }

        List<User> employees = userSession.getAllUsers();
        request.getSession().setAttribute("employees", employees);
        response.sendRedirect("employees.jsp");
    }
}
