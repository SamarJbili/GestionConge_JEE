<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Consulter Conges</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
    <div class="container mt-5">
        <h1 class="mb-4">Vos congés</h1>
        <table class="table table-striped">
            <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Description</th>
                    <th>Date Début</th>
                    <th>Date Fin</th>
                    <th>État</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${userConges}" var="conge">
                    <tr>
                        <td>${conge.id}</td>
                        <td>${conge.description}</td>
                        <td>${conge.dateDebut}</td>
                        <td>${conge.dateFin}</td>
                        <td>${conge.etat}</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</body>
</html>
