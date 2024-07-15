package controleur;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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
import java.util.concurrent.TimeUnit;


@WebServlet(name = "EmployeeServlet", urlPatterns = {"/EmployeeServlet"})
public class EmployeeServlet extends HttpServlet {
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
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        
Date hireDate = user.getDateEmbauchement();
        
        Date currentDate = new Date(); 

        long differenceInMilliseconds = currentDate.getTime() - hireDate.getTime();

        long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds);
        
        
        int can = differenceInDays < 365 ? -1 : 1; // Set can to -1 if condition is met, otherwise set to 1
        request.setAttribute("can", can);

        String stateFilter = request.getParameter("stateFilter");
        String yearFilter = request.getParameter("yearFilter");
        

        List<Conges> congesList = congesSession.getAllCongesByUserId(user.getId());
        if (stateFilter != null && !stateFilter.isEmpty()) {
            congesList = congesList.stream()
                .filter(c -> c.getEtat().toString().equals(stateFilter))
                .collect(Collectors.toList());
        }
        if (yearFilter != null && !yearFilter.isEmpty()) {
            int year = Integer.parseInt(yearFilter);
            congesList = congesList.stream()
                .filter(c -> c.getDateDebut().getYear() + 1900 == year)
                .collect(Collectors.toList());
        }

        request.setAttribute("sel", stateFilter);
        request.setAttribute("congesList", congesList);
        request.setAttribute("remainingDays", calculateRemainingDays(user));

        request.getRequestDispatcher("dashboard.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            response.sendRedirect("login.jsp");
            return;
        }
        
        
        
        

 
        String action = request.getParameter("action");
        if ("request".equals(action)) {
            handleRequestNewConge(request, response, user);
        } else if ("cancel".equals(action)) {
            handleCancelConge(request, response);
        }
    }

    private void handleRequestNewConge(HttpServletRequest request, HttpServletResponse response, User user)
            throws ServletException, IOException {
        String description = request.getParameter("description");
        String dateDebutStr = request.getParameter("dateDebut");
        String dateFinStr = request.getParameter("dateFin");

        try {
        	
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dateDebut = sdf.parse(dateDebutStr);
            Date dateFin = sdf.parse(dateFinStr);
            
            Date hireDate = user.getDateEmbauchement();
            
            Date currentDate = new Date(); 

            long differenceInMilliseconds = currentDate.getTime() - hireDate.getTime();

            long differenceInDays = TimeUnit.MILLISECONDS.toDays(differenceInMilliseconds);
            
            
            if (differenceInDays < 365) {
            	HttpSession session = request.getSession();
            	session.setAttribute("message", "Your message here");
            	response.sendRedirect("EmployeeServlet");
            } else {
                congesSession.requestNewConge(user.getId(), description, dateDebut, dateFin);
                response.sendRedirect("requestConge.jsp");
            }
            
        } catch (ParseException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid date format. Please use yyyy-MM-dd.");
            request.getRequestDispatcher("dashboard.jsp").forward(request, response);
        }
    }

    private void handleCancelConge(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long congeId = Long.parseLong(request.getParameter("congeId"));
        congesSession.cancelConge(congeId);
        response.sendRedirect("EmployeeServlet");
    }

    private int calculateRemainingDays(User user) {
        int t = 30 -  user.getTotyearLeaveDays();
        return t; 
    }
}
