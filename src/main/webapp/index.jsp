<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="./resource/css/style.css">
</head>
<body>

	<div class="login-page">
		<div class="form">
			<form action="login" method="post">
				<label for="login">Usuario:</label> <input type="text" id="login"
					name="user" placeholder="Informe o login"> <br /> <label
					for="password"> Senha:</label> <input type="password" id="password"
					name="password" placeholder="Informe a senha"> <br />

				<button type="submit" value="Logar">Logar</button>

				<%
				String mensagem = (String) request.getAttribute("message");
				if (mensagem != null) {
				%>
				<span class="error"> <%
					 out.print(mensagem);
				 %>
				</span>
				<%
				}
				%>
			</form>
		</div>

	</div>

	<%-- 		<%@ include file="../common/header.jsp" %> --%>


</body>
</html>