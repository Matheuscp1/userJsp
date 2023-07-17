<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="userJsp.dao.DaoUser" %>
<%@page import="userJsp.model.Permission" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<% List<Permission> permissions = new DaoUser().listAllPermissions();   %>

<% for(Permission permis: permissions) {%>
	
<% out.println(permis.getName()); %>
<% } %>

</body>
</html>