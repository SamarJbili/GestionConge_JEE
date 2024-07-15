<%@ page import="domaine.User" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Utilisateur List</title>
    <!-- Include Bootstrap CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        .container {
            margin-top: 50px;
        }
        .card {
            margin: 20px 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Employee List</h2>
        <table class="table">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>prenom</th>
                    <th>Date of Hire</th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<User> employees = (List<User>) session.getAttribute("employees");
                    if (employees != null && !employees.isEmpty()) {
                        for (User employee : employees) {
                %>
                <tr>
                    <td><%= employee.getId() %></td>
                    <td><%= employee.getNom() %></td>
                    <td><%= employee.getPrenom() %></td>
                    <td><%= employee.getDateEmbauchement() %></td>
                </tr>
                <%
                        }
                    } else {
                %>
                <tr>
                    <td colspan="4">No employees found</td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
    </div>
    <!-- Include Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>
