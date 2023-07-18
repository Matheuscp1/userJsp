<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="userJsp.dao.DaoUser"%>
<%@page import="userJsp.model.Permission"%>
<%@page import="userJsp.model.UserModel"%>
<% 
UserModel userSession = (UserModel) session.getAttribute("user");
if(userSession == null){
	response.sendRedirect("index.jsp");
	return;
}; %>
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
	<div class="link">
		<a  href="<%= request.getContextPath() %>/user.jsp">Novo usuário</a>
		<a  href="UserController?action=logout">Logout</a>
	</div>

	<table class="styled-table">
		<thead>
			<tr>
				<th>Nome</th>
				<th>CPF</th>
				<th>Usuário Responsável</th>
				<th>Status</th>
				<th>Acões</th>
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
					String supervisorName = "Sem responsável";
					if(user.getSupervisorName() != null) supervisorName = user.getSupervisorName();
					out.println(supervisorName);
					%>
				</td>
				<td>
					<%
					String status = "Ativo";
					if(!user.getStatus()) status = "Inativo";
					out.println(status);
					%>
				</td>
				<td>
					<% if(userSession.getPermissions() != null && userSession.getPermissions().size() > 1 && userSession.getId() != user.getId()) { %>
					<a class="link-table" href="UserController?action=delete&userId=<%out.print(user.getId());%>">Deletar</a>
					<% } %>
					<a class="link-table" href="UserController?action=edit&userId=<%out.print(user.getId());%>">
						<% out.print(userSession.getPermissions() != null && userSession.getPermissions().size() > 1 ? "Editar" : "Visualizar"); %>
					</a>
				</td>
			</tr>

			<%
			}
			%>
			<!-- and so on... -->
		</tbody>
	</table>
	<%-- 	<%
		UserModel user = (UserModel) 	session.getAttribute("user");
	out.println(user.getPermissions().get(0).getUserId());
					%> --%>
</body>
</html>