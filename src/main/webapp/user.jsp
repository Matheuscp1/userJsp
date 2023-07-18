<%@page import="userJsp.model.PermissionUser"%>
<%@page import="userJsp.model.UserModel"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="userJsp.dao.DaoUser"%>
<%@page import="userJsp.model.Permission"%>
<%@page import="java.util.List"%>
<% if(session.getAttribute("user") == null){
	response.sendRedirect("index.jsp");
	return;
}; %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Usuário</title>
<link rel="stylesheet" href="./resource/css/style.css">
</head>
	<%
	UserModel userSession = (UserModel) session.getAttribute("user");
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
				<input hidden type="text" name="userId" value="<%out.print(user.getId() == null ? "" : user.getId());%>">
				<label for="user">Usuario:</label> 
				<input required type="text" id="user" value="<%	out.print(user.getUserName()); %>" maxlength="255"
					name="user" placeholder="Nome de usuário"> <br />
				<label for="name">Nome:</label>
				 <input required maxlength="255"
					type="text" id="name" name="name" value="<%	out.print(user.getName()); %>"
					placeholder="Informe seu nome"> <br />
				<label for="email">Email:</label>
				 <input maxlength="255" required type="email" id="email" value="<%	out.print(user.getEmail()); %>"
					name="email" placeholder="Informe seu email"> <br />
				<label for="password">Senha:</label>
				<input maxlength="255" required type="password" id="password" name="password" value="<%	out.print(user.getPassword()); %>"
					placeholder="Informe a senha"> <br />
				<label for="supervisorId">Responsavel:</label>
				<select style="margin-bottom: 5px;" name="supervisorId" id="supervisorId">
					<option value="">Sem responsável</option>
					<% for(UserModel userSelect : usersSelect){ %>
					<option value="<%out.print(userSelect.getId());%>"<%out.print(userSelect.getId() == user.getsupervisorId() ? "selected": ""); %> >
						<% out.print(userSelect.getName()); %>
					</option>
					<%
					  }
					%>
				</select>
				<label for="cpf">CPF:</label>
				<input maxlength="14" required value="<%out.print(user.getCpf()); %>"
					type="text" id="cpf" name="cpf" placeholder="Informe seu CPF">
				<br />
				<label for="checkbox">Status:</label>
				<input  type="checkbox" id="checkbox" name="status" <%	out.print(user.getStatus() ? "checked": ""); %>> <br />
				<label for="name">Permissões:</label>
				<%
					for (Permission permission : permissions) {
						PermissionUser havePermission = new PermissionUser();
						if(user.getId() != null){
							havePermission.setPermissionId(permission.getId());
						}
				%>
				<br>
				<% out.println(permission.getName()); %>
					<input type="checkbox" <% out.println(permission.getId() == 1 ?  "checked onclick='return false'": ""); %>
					<% out.println(user.getPermissions() != null   && user.getPermissions().contains(havePermission)  ?  "checked": ""); %>
					onchange="remove(this)" name="permissions" value="<% out.println(permission.getId());%>"> 
				<%
					}
	
				%>
				<input hidden id="removePermission" name="removePermission">
				<button <% out.print(userSession.getPermissions() != null && userSession.getPermissions().size() > 1 ? "" : "disabled" ); %>  type="submit">Salvar</button>
			</form>
		</div>
	</div>
	
	<script type="text/javascript">
	document.addEventListener("DOMContentLoaded", function() {
		  var cpfInput = document.getElementById("cpf");

		  cpfInput.addEventListener("input", function() {
		    var cpf = cpfInput.value.replace(/\D/g, ""); 

		    if (cpf.length > 3 && cpf.length <= 6) {
		      cpf = cpf.replace(/(\d{3})(\d+)/, "$1.$2"); 
		    } else if (cpf.length > 6 && cpf.length <= 9) {
		      cpf = cpf.replace(/(\d{3})(\d{3})(\d+)/, "$1.$2.$3"); 
		    } else if (cpf.length > 9) {
		      cpf = cpf.replace(/(\d{3})(\d{3})(\d{3})(\d+)/, "$1.$2.$3-$4"); 
		    }

		    cpfInput.value = cpf;
		  });
	
		});
	
	 function remove(t){
		 if(t.checked){
			 document.getElementById('removePermission').value = ''
		 }else{
			 document.getElementById('removePermission').value = t.value
		 }
		
	  }
	</script>
</body>
</html>