package controleur;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import domaine.Conges;
import domaine.CongeEtat;
import domaine.User;
import services.CongesSessionRemote;

@WebServlet(name = "RequestCongeServlet", urlPatterns = {"/RequestCongeServlet"})
public class RequestCongeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @EJB
    private CongesSessionRemote congesSession;

    @Override
    public void init() throws ServletException {
        try {
            Context context = new InitialContext();
            congesSession = (CongesSessionRemote) context.lookup("ejb:/ejb_appGestionConges/CR!services.CongesSessionRemote");
        } catch (NamingException e) {
            throw new ServletException("Failed to lookup CongesSessionRemote EJB", e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String description = request.getParameter("description");
        String dateDebutStr = request.getParameter("dateDebut");
        String dateFinStr = request.getParameter("dateFin");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDebut = sdf.parse(dateDebutStr);
            Date dateFin = sdf.parse(dateFinStr);

            congesSession.requestNewConge(user.getId(), description, dateDebut, dateFin);
            //congesSession.requestNewConge(1L, "fadit nheb nrawah", new Date(2024, 6, 1), new Date(2024, 6, 15));

            response.sendRedirect("congeRequested.jsp");
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid date format. Please use yyyy-MM-dd.");
            request.getRequestDispatcher("requestConge.jsp").forward(request, response);
        }
    }
}
