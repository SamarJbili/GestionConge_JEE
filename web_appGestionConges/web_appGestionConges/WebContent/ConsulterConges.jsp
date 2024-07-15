<%@ page import="java.util.List" %>
<%@ page import="services.CongesSessionRemote" %>
<%@ page import="domaine.Conges" %>
<%@ page import="domaine.CongeEtat" %>
<%@ page import="javax.naming.InitialContext" %>
<%@ page import="javax.naming.NamingException" %>
<%@ page import="javax.ejb.EJB" %>

<%
    CongesSessionRemote congesSession = null;
    try {
        InitialContext ctx = new InitialContext();
        congesSession = (CongesSessionRemote) ctx.lookup("java:global/ejb_appGestionConges/CR!services.CongesSessionRemote");
    } catch (NamingException e) {
        e.printStackTrace();
    }

    List<Conges> allConges = congesSession.getAllConges();
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulter Conges</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Consulter Conges</h1>
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Description</th>
                    <th>Date Debut</th>
                    <th>Date Fin</th>
                    <th>Etat</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% for (Conges conge : allConges) { %>
                    <tr>
                        <td><%= conge.getId() %></td>
                        <td><%= conge.getDescription() %></td>
                        <td><%= conge.getDateDebut() %></td>
                        <td><%= conge.getDateFin() %></td>
                        <td><%= conge.getEtat() %></td>
                        <td>
                            <div class="btn-group" role="group">
                                <% if (conge.getEtat() == CongeEtat.SOLLICITE) { %>
                                    <form action="ValidateCongeServlet" method="POST">
                                        <input type="hidden" name="congeId" value="<%= conge.getId() %>">
                                        <button type="submit" class="btn btn-success">Validate</button>
                                    </form>
                                    <form action="RefuseCongeServlet" method="POST">
                                        <input type="hidden" name="congeId" value="<%= conge.getId() %>">
                                        <button type="submit" class="btn btn-warning">Refuse</button>
                                    </form>
                                    <form action="RemoveCongeServlet" method="POST">
                                        <input type="hidden" name="congeId" value="<%= conge.getId() %>">
                                        <button type="submit" class="btn btn-danger">Remove</button>
                                    </form>
                                <% } else if (conge.getEtat() == CongeEtat.EN_COURS) { %>
                                    <form action="StopCongeServlet" method="POST">
                                        <input type="hidden" name="congeId" value="<%= conge.getId() %>">
                                        <button type="submit" class="btn btn-secondary">Stop</button>
                                    </form>
                                <% } else if (conge.getEtat() == CongeEtat.FINI || conge.getEtat() == CongeEtat.VALIDE) { %>
                                    <form action="RemoveCongeServlet" method="POST">
                                        <input type="hidden" name="congeId" value="<%= conge.getId() %>">
                                        <button type="submit" class="btn btn-danger">Remove</button>
                                    </form>
                                <% } %>
                            </div>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
</body>
</html>
