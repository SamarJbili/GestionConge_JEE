package controleur;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import domaine.User;
import services.UserSessionRemote;

@WebServlet("/UserWebEJB")
public class UserWebEJB extends HttpServlet {
    @EJB(lookup = "ejb:/ejb_appGestionConges/UR!services.UserSessionRemote")
    private UserSessionRemote userSession;

    private static final long serialVersionUID = 1L;

    public UserWebEJB() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = null;
        try {
            user = userSession.getUserByLogin(login, password); // Use the correct method and parameters
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        if (user != null) {
            System.out.println("User found: " + user.getLogin() + ", Type: " + user.getType());
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);

            // Check user type and redirect accordingly
            if (user.getType().equalsIgnoreCase("ADMIN")) {
                System.out.println("Redirecting to admin home page");
                response.sendRedirect("adminHome.jsp"); // Redirect to admin home page
            } else if (user.getType().equalsIgnoreCase("EMPLOYE")) {
                System.out.println("Redirecting to employee home page");
                response.sendRedirect("EmployeeServlet"); // Redirect to employee home page
            } else {
                System.out.println("Unknown user type: " + user.getType());
                response.sendRedirect("login.jsp?error=unknown_user_type");
            }
        } else {
            // Authentication failed, redirect back to login page with error message
            System.out.println("Authentication failed for login: " + login);
            request.setAttribute("errorMessage", "Invalid username or password.");
            response.sendRedirect("login.jsp?error=authentication_failed");
        }
    }
}
