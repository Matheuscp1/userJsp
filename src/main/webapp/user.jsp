<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="userJsp.dao.DaoUser"%>
<%@page import="userJsp.model.Permission"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Usuário</title>
<link rel="stylesheet" href="./resource/css/style.css">
</head>
	<%
	List<Permission> permissions = new DaoUser().listAllPermissions();
	%>
<body>
	<div class="login-page">
		<div class="form">
			<form action="createUser" method="post">
				<label for="user">Usuario:</label> 
				<input type="text" id="user"
					name="user" placeholder="Nome de usuário"> <br />
				<label for="email">Email:</label>
				 <input type="email" id="email"
					name="email" placeholder="Informe seu email"> <br />
				<label for="password">Senha:</label>
				<input type="password" id="password" name="password"
					placeholder="Informe a senha"> <br />
				<label for="cpf">CPF:</label>
				<input
					type="password" id="cpf" name="cpf" placeholder="Informe seu CPF">
				<br />
				<label for="password">Status:</label>
				<input type="checkbox" id="checkbox" name="status"> <br />
				<label for="name">Nome:</label>
				 <input
					type="password" id="name" name="name"
					placeholder="Informe seu nome"> <br />
				<label for="name">Permissões:</label>
				<%
					for (Permission permission : permissions) {
				%>
				<br>
				<% out.println(permission.getName()); %>
					<input type="checkbox" <% out.println(permission.getId() == 1 ?  "checked onclick='return false'": ""); %>
					 name="permissions" value="<% out.println(permission.getId());%>"> 
				<%
					}
				%>
				<button type="submit">Salvar</button>
			</form>
		</div>
	</div>
</body>
</html>