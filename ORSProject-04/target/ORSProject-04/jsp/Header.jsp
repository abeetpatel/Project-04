<%@page import="in.co.rays.ctl.LoginCtl"%>
<%@page import="in.co.rays.ctl.ORSView"%>
<%@page import="in.co.rays.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
<!-- Include jQuery UI -->
<script src="https://code.jquery.com/ui/1.13.2/jquery-ui.min.js"></script>
<!-- Include jQuery UI CSS -->
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
<script src="/ORSProject-04/js/checkbox.js"></script>
<script src="/ORSProject-04/js/datepicker.js"></script>
<script src="/ORSProject-04/js/dateselecter.js"></script>
</head>
<body>

	<%
		UserBean userBean = (UserBean) session.getAttribute("user");
		boolean userLoggedIn = userBean != null;
		String welcomeMsg = "Hi, "
				+ (userLoggedIn ? userBean.getFirstName() + " (" + session.getAttribute("role") + ")" : "Guest");
	%>
	<table>
		<tr>
			<td width="90%"><a style="text-decoration: none;"
				href="<%=ORSView.WELCOME_CTL%>"><b>Welcome</b></a> | <%
				if (userLoggedIn) {
			%> <a style="text-decoration: none;"
				href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><b>Logout</b></a>
				<%
					} else {
				%> <a style="text-decoration: none;" href="<%=ORSView.LOGIN_CTL%>"><b>Login</b></a>
				<%
					}
				%></td>
			<td rowspan="2" align="right"><img
				src="<%=ORSView.APP_CONTEXT%>/img/customLogo.jpg" width="318"
				height="90"></td>
		</tr>
		<tr>
			<td>
				<h3><%=welcomeMsg%></h3>
			</td>
		</tr>
		<%
			if (userLoggedIn) {
		%>
		<tr>
			<td colspan="2"><a href="<%=ORSView.USER_CTL%>">Add User</a> | <a
				href="<%=ORSView.USER_LIST_CTL%>">User List</a> | <a
				href="<%=ORSView.ROLE_CTL%>">Add Role</a> | <a
				href="<%=ORSView.ROLE_LIST_CTL%>">Role List</a> | <a
				href="<%=ORSView.COLLEGE_CTL%>">Add College</a> | <a
				href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</a> | <a
				href="<%=ORSView.STUDENT_CTL%>">Add Student</a> |<a
				href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</a> | <a
				href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</a> | <a
				href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</a> |<br>
				<a href="<%=ORSView.COURSE_CTL%>">Add Course</a> | <a
				href="<%=ORSView.COURSE_LIST_CTL%>">Course List</a> | <a
				href="<%=ORSView.SUBJECT_CTL%>">Add Subject</a> | <a
				href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</a> | <a
				href="<%=ORSView.TIMETABLE_CTL%>">Add Timetable</a> | <a
				href="<%=ORSView.TIMETABLE_LIST_CTL%>">Timetable List</a> | <a
				href="<%=ORSView.FACULTY_CTL%>">Add Faculty</a> | <a
				href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</a> |<br> <a
				href="<%=ORSView.STOCKPURCHASE_CTL%>">Add Stock Purchase</a> | <a
				href="<%=ORSView.STOCKPURCHASE_LIST_CTL%>">Stock Purchase List</a> |
				<a href="<%=ORSView.FOLLOWUP_CTL%>">Add Follow Up Data</a> | <a
				href="<%=ORSView.FOLLOWUP_LIST_CTL%>">Follow Up List</a> | <a
				href="<%=ORSView.PURCHASEORDER_CTL%>">Add Purchase Order</a> | <a
				href="<%=ORSView.PURCHASEORDER_LIST_CTL%>">Purchase Order List</a> |
				<a href="<%=ORSView.CLIENT_CTL%>">Add Client</a> | <a
				href="<%=ORSView.CLIENT_LIST_CTL%>">Client List</a> | <a
				href="<%=ORSView.CUSTOMER_CTL%>">Add Customer</a> | <a
				href="<%=ORSView.CUSTOMER_LIST_CTL%>">Customer List</a> | <a
				href="<%=ORSView.STAFFMEMBER_CTL%>">Add Staff Member</a> | <a
				href="<%=ORSView.STAFFMEMBER_LIST_CTL%>">Staff Member List</a> | <a
				href="<%=ORSView.JAVA_DOC_VIEW%>" target="blank">Java Doc</a></td>


		</tr>
		<%
			}
		%>
	</table>
	<hr>

</body>
</html>