package controleur;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import domaine.Conges;
import domaine.CongeEtat;
import services.CongesSessionRemote;

@WebServlet("/CongesWebEJB")
public class CongesWebEJB extends HttpServlet {
    @EJB(lookup = "ejb:/ejb_appGestionConges/CR!services.CongesSessionRemote")
    private CongesSessionRemote congesSession;
    private static final long serialVersionUID = 1L;

    public CongesWebEJB() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println("Accessing Conges EJB Component...<br>");

        // Perform operations with CongesSessionRemote EJB
        List<Conges> conges = congesSession.getAllConges();
        out.println("------List of Conges------<br>");
        for (Conges conge : conges) {
            out.println(conge);
            out.println("<br>");
        }
        out.println("------------------------------<br>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
