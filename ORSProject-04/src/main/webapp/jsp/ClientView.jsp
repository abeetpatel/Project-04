<%@page import="in.co.rays.ctl.ClientCtl"%>
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

	<form action="<%=ORSView.CLIENT_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.ClientBean"
			scope="request" />

		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> Client
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
			<input type="hidden" name="id" value="<%=bean.getId()%>">

			<table>
				<!-- Full Name -->
				<tr>
					<th align="left">Full Name <span style="color: red">*</span></th>
					<td><input type="text" name="fullName"
						placeholder="Enter Full Name"
						value="<%=DataUtility.getStringData(bean.getFullName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("fullName", request)%></font></td>
				</tr>

				<!-- Appointment Date -->
				<tr>
					<th align="left">Appointment Date <span style="color: red">*</span></th>
					<td><input style="width: 97%" type="date"
						name="appointmentDate" placeholder="Enter Appointment Date "
						value="<%=DataUtility.getStringData(bean.getAppointmentDate())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("appointmentDate", request)%></font></td>
				</tr>

				<!-- Phone -->
				<tr>
					<th align="left">Phone <span style="color: red">*</span></th>
					<td><input type="text" name="phone"
						placeholder="Enter Phone No. "
						value="<%=DataUtility.getStringData(bean.getPhone())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("phone", request)%></font></td>
				</tr>

				<!-- Illness -->
				<tr>
					<th align="left">Illness <span style="color: red">*</span></th>
					<td><input type="text" name="illness"
						placeholder="Enter Illness"
						value="<%=DataUtility.getStringData(bean.getIllness())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("illness", request)%></font></td>
				</tr>

				<!-- Submit Buttons -->
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=ClientCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=ClientCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=ClientCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=ClientCtl.OP_RESET%>">
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