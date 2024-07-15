<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ page import="java.util.List"%>
<%@ page import="domaine.User"%>
<%@ page import="domaine.Conges"%>
<%@ page import="domaine.CongeEtat"%>
<%@ page session="true"%>



<%
	User user = (User) session.getAttribute("user");
	List<Conges> congesList = (List<Conges>) request.getAttribute("congesList");
	int remainingDays = (int) request.getAttribute("remainingDays");
	String sel = (String) request.getAttribute("sel");
	Integer can = (Integer) request.getAttribute("can");
	int hideDiv = can != null && can == -1 ? -1 : 1;
%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Dashboard</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" />

<link rel="stylesheet" type="text/css" href="css/main.css" />
<link rel="stylesheet" type="text/css" href="css/styles.css" />

</head>

<body class="container">
	<!-- Sidebar -->
	<div class="menu">
		<%@ include file="components/sidebar.jsp"%>
	</div>

	<!-- Content -->
	<div class="content">
		<div class="home_container">
			<%@ include file="components/navbar.jsp"%>
			<div class="content_container">


				<h1 class="text-4xl font-bold">
					Welcome,
					<%=user.getNom()%>
					<%=user.getPrenom()%></h1>


				<%
					String message = (String) request.getAttribute("message");
					if (message != null && !message.isEmpty()) {
				%>
				<h1><%=message%></h1>
				<%
					}
				%>






				<div style="display: <%=hideDiv == -1 ? "none" : "block"%>;">

					<h2 class="text-2xl font-bold mt-8">Leave History</h2>
					<form method="get" action="EmployeeServlet" class="mt-4">
						<label for="stateFilter" class="mr-2">Filter by State:</label> <select
							name="stateFilter" id="stateFilter"
							class="select select-bordered">
							<option name="sel">All</option>
							<option value="sel2">test</option>

							<%
								for (CongeEtat state : CongeEtat.values()) {
							%>
							<option value="<%=state%>" class="option"
								<%if (state.name().equals(sel)) {%> selected <%}%>>
								<%=state%>
							</option>
							<%
								}
							%>

						</select> <label for="yearFilter" class="ml-4 mr-2">Filter by Year:</label>
						<input type="number" name="yearFilter" id="yearFilter"
							class="input input-bordered w-24"> <input type="submit"
							value="Filter" class="btn btn-primary ml-4">
					</form>
					<table class="table-auto mt-4">
						<thead>
							<tr>
								<th class="px-4 py-2">Description</th>
								<th class="px-4 py-2">Start Date</th>
								<th class="px-4 py-2">End Date</th>
								<th class="px-4 py-2">Status</th>
								<th class="px-4 py-2">Action</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (Conges conge : congesList) {
							%>
							<tr>
								<td class="px-4 py-2"><%=conge.getDescription()%></td>
								<td class="px-4 py-2"><%=conge.getDateDebut()%></td>
								<td class="px-4 py-2"><%=conge.getDateFin()%></td>
								<td
									class="px-4 py-2 text-lg <%=conge.getEtat() == CongeEtat.SOLLICITE ? "text-blue-600"
						: conge.getEtat() == CongeEtat.VALIDE ? "text-green-600"
								: conge.getEtat() == CongeEtat.REFUSE ? "text-red-600" : "text-black"%>">
									<%=conge.getEtat()%>
								</td>
								<td class="px-4 py-2">
									<%
										if (conge.getEtat() == CongeEtat.SOLLICITE) {
									%>
									<form method="post" action="EmployeeServlet">
										<input type="hidden" name="action" value="cancel"> <input
											type="hidden" name="congeId" value="<%=conge.getId()%>">
										<input type="submit" value="Cancel"
											class="btn btn-outline btn-xs">
									</form> <%
 	}
 %>
								</td>
							</tr>
							<%
								}
							%>
						</tbody>
					</table>

					<h2 class="text-2xl font-bold mt-8">Request New Leave</h2>
					<form method="post" action="EmployeeServlet" class="mt-4">
						<input type="hidden" name="action" value="request"> <label
							for="description" class="mr-2">Description:</label> <input
							type="text" name="description" id="description" required
							class="input input-bordered w-full"><br> <label
							for="dateDebut" class="mr-2 mt-2">Start Date:</label> <input
							type="date" name="dateDebut" id="dateDebut" required
							class="input input-bordered w-full"><br> <label
							for="dateFin" class="mr-2 mt-2">End Date:</label> <input
							type="date" name="dateFin" id="dateFin" required
							class="input input-bordered w-full"><br> <input
							type="submit" value="Request Leave" class="btn btn-primary mt-2">
					</form>

					<h2 class="text-2xl font-bold mt-8">Remaining Leave Days</h2>
					<p class="mt-4">
						You have
						<%=remainingDays%>
						remaining leave days.
					</p>

				</div>
				
				<div style="display: <%= hideDiv == -1 ? "block" : "none" %>;">
				
				<h2 class="text-2xl font-bold mt-8">Unfortunately, you are not yet eligible to request leave as our policy requires a minimum of one year of employment.</h2>
				</div>

			</div>

		</div>

	</div>



</body>

</html>
