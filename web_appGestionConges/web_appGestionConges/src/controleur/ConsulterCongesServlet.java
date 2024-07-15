package controleur;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domaine.CongeEtat;
import domaine.Conges;
import services.CongesSessionRemote;

/**
 * Servlet implementation class ConsulterCongesServlet
 */
@WebServlet("/ConsulterCongesServlet")
public class ConsulterCongesServlet extends HttpServlet {

    @EJB(lookup = "ejb:/ejb_appGestionConges/CR!services.CongesSessionRemote")
    private CongesSessionRemote congesSession;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Conges> allConges = congesSession.getAllConges();
        request.setAttribute("allConges", allConges);

        // Filter based on criteria if needed
        String filterCriteria = request.getParameter("filterCriteria");
        if (filterCriteria != null) {
            switch (filterCriteria) {
                case "byUserId":
                    Long userId = Long.parseLong(request.getParameter("userId"));
                    List<Conges> filteredByUserId = congesSession.getAllCongesByUserId(userId);
                    request.setAttribute("allConges", filteredByUserId);
                    break;
                case "byState":
                    CongeEtat state = CongeEtat.valueOf(request.getParameter("state"));
                    List<Conges> filteredByState = congesSession.getAllCongesByState(state);
                    request.setAttribute("allConges", filteredByState);
                    break;
                // Add more cases for other filter criteria if needed
            }
        }

        // Forward to JSP
        request.getRequestDispatcher("ConsulterConges.jsp").forward(request, response);
    }
}
