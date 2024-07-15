package controleur;
import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domaine.User;
import services.CongesSessionRemote;
import services.UserSessionRemote;

@WebServlet("/ValidateCongeServlet")
public class ValidateCongeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    @EJB(lookup = "ejb:/ejb_appGestionConges/CR!services.CongesSessionRemote")
    
    private CongesSessionRemote congesSession;
    private UserSessionRemote usersessRemote;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String congeIdStr = request.getParameter("congeId");
        if (congeIdStr != null) {
            Long congeId = Long.parseLong(congeIdStr);
            congesSession.validateConge(congeId);
        }
        response.sendRedirect(request.getContextPath() + "/ConsulterConges.jsp");
    }
}
