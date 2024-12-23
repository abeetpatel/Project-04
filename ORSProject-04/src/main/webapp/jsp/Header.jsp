<%@page import="in.co.rays.ctl.ORSView"%>
<%@page import="in.co.rays.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>

	<%
		if (user != null) {
	%>
	<h2><%="Hi, " + user.getFirstName()%></h2>
	<a href="<%=ORSView.USER_REGISTRATION_CTL%>">Add User</a> |
	<a href="<%=ORSView.WELCOME_CTL%>">Welcome</a> |


	<%
		} else {
	%>
	<h2>Hi, Guest</h2>
	<a href="<%=ORSView.USER_REGISTRATION_CTL%>">SignUp</a> |
	<a href="<%=ORSView.LOGIN_CTL%>">Login</a> |
	<a href="<%=ORSView.WELCOME_CTL%>">Welcome</a>
	<%
		}
	%>



	<hr>

</body>
</html>