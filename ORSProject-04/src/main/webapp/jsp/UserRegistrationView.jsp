<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="in.co.rays.ctl.UserRegistrationCtl"%>
<%@page import="in.co.rays.util.DataUtility"%>
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

	<%@ include file="Header.jsp"%>

	<%
		ServletUtility.getSuccessMessage(request);
	%>

	<form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">
		<jsp:useBean id="bean" class="in.co.rays.bean.UserBean"
			scope="request" />

		<div align="center">
			<h1>
				<font color="navy">User Registration</font>
			</h1>

			<%
				if (ServletUtility.getSuccessMessage(request) != null) {
			%>
			<span><%=ServletUtility.getSuccessMessage(request)%></span>
			<%
				}
			%>

			<!-- Hidden Fields -->
			<input type="hidden" name="id" value="<%=bean.getId()%>" /> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>" />
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>" /> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>" />
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>" />

			<table>
				<tr>
					<th align="left">First Name<span style="color: red">*</span></th>
					<td><input type="text" name="firstName"
						placeholder="Enter First Name" /></td>
				</tr>
				<tr>
					<th align="left">Last Name<span style="color: red">*</span></th>
					<td><input type="text" name="lastName"
						placeholder="Enter Last Name" /></td>
				</tr>
				<tr>
					<th align="left">Login Id<span style="color: red">*</span></th>
					<td><input type="text" name="login"
						placeholder="Enter Email ID" /></td>
				</tr>
				<tr>
					<th align="left">Password<span style="color: red">*</span></th>
					<td><input type="password" name="password"
						placeholder="Enter Password" /></td>
				</tr>
				<tr>
					<th align="left">Confirm Pass<span style="color: red">*</span></th>
					<td><input type="password" name="confirmPassword"
						placeholder="Confirm Password" /></td>
				</tr>
				<tr>
					<th align="left">DOB<span style="color: red">*</span></th>
					<td><input type="date" name="dob"
						placeholder="Select Date of Birth" /></td>
				</tr>
				<tr>
					<th align="left">Gender<span style="color: red">*</span></th>
					<td><input type="text" name="gender"
						placeholder="Enter Gender" /></td>
				</tr>
				<tr>
					<th align="left">Mobile No<span style="color: red">*</span></th>
					<td><input type="text" name="mobileNo"
						placeholder="Enter Mobile No." /></td>
				</tr>
				<tr>
					<td></td>
					<td colspan="3"><input type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_SIGN_UP%>" /> <input
						type="submit" name="operation"
						value="<%=UserRegistrationCtl.OP_RESET%>" /></td>
				</tr>
			</table>
		</div>
	</form>

	<%@include file="Footer.jsp"%>

</body>
</html>