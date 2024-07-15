package controleur;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import services.CongesSessionRemote;

@WebServlet("/stopConge")
public class StopCongeServlet extends HttpServlet {
    @EJB(lookup = "ejb:/ejb_appGestionConges/CR!services.CongesSessionRemote")
    private CongesSessionRemote congesSession;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String congeIdStr = request.getParameter("congeId");
        if (congeIdStr != null) {
            Long congeId = Long.parseLong(congeIdStr);
            congesSession.stopOngoingConge(congeId);
        }
        response.sendRedirect(request.getContextPath() + "/ConsulterConges.jsp");
    }
}
