package controleur;

import domaine.Conges;
import domaine.User;
import services.CongesSessionRemote;

import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ConsulterCongeUserServlet")
public class ConsulterCongeUserServlet extends HttpServlet {

    @EJB(lookup = "ejb:/ejb_appGestionConges/CR!services.CongesSessionRemote")
    private CongesSessionRemote congesSession;

    @Override
    public void init() throws ServletException {
        try {
            InitialContext ctx = new InitialContext();
            congesSession = (CongesSessionRemote) ctx.lookup("java:global/ejb_appGestionConges/CR!services.CongesSessionRemote");
        } catch (NamingException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null) {
            try {
                List<Conges> userConges = congesSession.getAllCongesByUserId(user.getId());
                request.setAttribute("userConges", userConges);
                request.getRequestDispatcher("consultercongeuser.jsp").forward(request, response);
            } catch (Exception e) {
                throw new ServletException("Error while retrieving user's congés", e);
            }
        } else {
            response.sendRedirect("login.jsp"); // Redirect to login page if user is not logged in
        }
    }
}
