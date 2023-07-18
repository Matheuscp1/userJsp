<%@page import="userJsp.model.UserModel"%>
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
	UserModel user = (UserModel) request.getAttribute("userEdit"); 
	if(user == null){
		user = new UserModel();
		user.setCpf("");
		user.setEmail("");
		user.setName("");
		user.setPassword("");
		user.setUserName("");
	}

	Long idThan;
	if(request.getParameter("userId") == null){
		idThan = 0L;
	}else{
		idThan = (Long) Long.parseLong(request.getParameter("userId"));
	}
	List<UserModel> usersSelect = new DaoUser().listUsersOtherThan(idThan);
	%>
<body>
	<div class="login-page">

		<div class="form">
			<form action="createUser" method="post">
				<label for="user">Usuario:</label> 
				<input required type="text" id="user" value="<%	out.print(user.getCpf()); %>"
					name="user" placeholder="Nome de usuário"> <br />
				<label for="email">Email:</label>
				 <input required type="email" id="email" value="<%	out.print(user.getEmail()); %>"
					name="email" placeholder="Informe seu email"> <br />
				<label for="password">Senha:</label>
				<input required type="password" id="password" name="password" value="<%	out.print(user.getPassword()); %>"
					placeholder="Informe a senha"> <br />
				<label for="supervisorId">Responsavel:</label>
				<select style="margin-bottom: 5px;" name="supervisorId" id="supervisorId">
					<option value="">Sem responsável</option>
					<% for(UserModel userSelect : usersSelect){ %>
					<option value="<%out.print(userSelect.getId()); %> " <%out.print(userSelect.getId() == user.getsupervisorId() ? "selected": ""); %>>
						<% out.print(userSelect.getName()); %>
					</option>
					<%
					  }
					%>
				</select>
				<label for="cpf">CPF:</label>
				<input required value="<%out.print(user.getCpf()); %>"
					type="text" id="cpf" name="cpf" placeholder="Informe seu CPF">
				<br />
				<label for="checkbox">Status:</label>
				<input  type="checkbox" id="checkbox" name="status" <%	out.print(user.getStatus() ? "checked": ""); %>> <br />
				<label for="name">Nome:</label>
				 <input required
					type="text" id="name" name="name" value="<%	out.print(user.getCpf()); %>"
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