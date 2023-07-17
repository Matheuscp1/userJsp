<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="userJsp.dao.DaoUser"%>
<%@page import="userJsp.model.Permission"%>
<%@page import="userJsp.model.UserModel"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="./resource/css/style.css">
<title>Home</title>
</head>
<body>


	<%
	List<UserModel> users = new DaoUser().listAllUsers();
	%>

	<a class="link" href="<%= request.getContextPath() %>/user.jsp">Novo usuário</a>
	<table class="styled-table">
		<thead>
			<tr>
				<th>Nome</th>
				<th>CPF</th>
				<th>Usuário Responsável</th>
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<%
			for (UserModel user : users) {
			%>
			<tr>
				<td>
					<%
					out.println(user.getName());
					%>
				</td>
				<td>
					<%
					out.println(user.getCpf());
					%>
				</td>
				<td>
					<%
					out.println(user.getSupervisorName());
					%>
				</td>
				<td>
					<%
					String status = "Ativo";
					if(!user.getStatus()) status = "Inativo";
					out.println(status);
					%>
				</td>
			</tr>

			<%
			}
			%>
			<!-- and so on... -->
		</tbody>
	</table>

</body>
</html>