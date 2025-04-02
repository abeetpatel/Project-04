<%@page import="in.co.rays.bean.DropdownListBean"%>
<%@page import="in.co.rays.model.RoleModel"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="in.co.rays.ctl.UserCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@include file="Header.jsp"%>

	<form action="<%=ORSView.USER_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.UserBean"
			scope="request" />
		<%
			List roleList = (List) request.getAttribute("roleList");
		%>

		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> User
				</font>
			</h1>

			<!-- Success and Error Messages -->
			<h3>
				<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
			</h3>
			<h3>
				<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
			</h3>

			<!-- Hidden Fields -->
			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<!-- First Name -->
				<tr>
					<th align="left">First Name <span style="color: red">*</span></th>
					<td><input type="text" name="firstName"
						placeholder="Enter First Name"
						value="<%=DataUtility.getStringData(bean.getFirstName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
				</tr>

				<!-- Last Name -->
				<tr>
					<th align="left">Last Name <span style="color: red">*</span></th>
					<td><input type="text" name="lastName"
						placeholder="Enter Last Name"
						value="<%=DataUtility.getStringData(bean.getLastName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>

				<!-- Login Id -->
				<tr>
					<th align="left">Login Id <span style="color: red">*</span></th>
					<td><input type="email" name="login"
						placeholder="Enter Email ID"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>

				<!-- Password -->
				<tr>
					<th align="left">Password <span style="color: red">*</span></th>
					<td><input type="password" name="password"
						placeholder="Enter Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("password", request)%></font></td>
				</tr>

				<!-- Confirm Password -->
				<tr>
					<th align="left">Confirm Pass <span style="color: red">*</span></th>
					<td><input type="password" name="confirmPassword"
						placeholder="Enter Confirm Password"
						value="<%=DataUtility.getStringData(bean.getPassword())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("confirmPassword", request)%></font></td>
				</tr>

				<!-- Date of Birth -->
				<tr>
					<th align="left">DOB<span style="color: red">*</span></th>
					<td><input style="width: 97%" type="text" id="udate" name="dob"
						placeholder="Select Date of Birth" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font></td>

				</tr>

				<!-- Gender -->
				<tr>
					<th align="left">Gender <span style="color: red">*</span></th>
					<td>
						<%
							HashMap<String, String> map = new HashMap<>();
							map.put("male", "Male");
							map.put("female", "Female");
						%> <%=HTMLUtility.getList("gender", bean.getGender(), map)%>
					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("gender", request)%></font></td>
				</tr>

				<!-- Role -->

				<tr>
					<th align="left">Role <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("roleId", DataUtility.getStringData(bean.getRoleId()), roleList)%></td>

					</td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("roleId", request)%></font></td>
				</tr>

				<!-- Mobile No -->
				<tr>
					<th align="left">Mobile No <span style="color: red">*</span></th>
					<td><input type="tel" name="mobileNo"
						placeholder="Enter Maobile No."
						value="<%=DataUtility.getStringData(bean.getMobileNo())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
				</tr>

				<!-- Submit Buttons -->
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=UserCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=UserCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=UserCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=UserCtl.OP_RESET%>">
						<%
							}
						%>
				</tr>
			</table>
		</div>
	</form>

	<%@include file="Footer.jsp"%>
</body>
</html>