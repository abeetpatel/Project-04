<%@page import="in.co.rays.ctl.CustomerCtl"%>
<%@page import="java.util.List"%>
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

	<form action="<%=ORSView.CUSTOMER_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.CustomerBean"
			scope="request" />
		<%
			List customerList = (List) request.getAttribute("customerList");
		%>

		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> Customer
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
				<!-- Client Name -->
				<tr>
					<th align="left">Client Name <span style="color: red">*</span></th>
					<td><input type="text" name="clientName"
						placeholder="Enter Client Name"
						value="<%=DataUtility.getStringData(bean.getClientName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("clientName", request)%></font></td>
				</tr>

				<!-- Location -->
				<tr>
					<th align="left">Location <span style="color: red">*</span></th>
					<td><input type="text" name="location"
						placeholder="Enter Location"
						value="<%=DataUtility.getStringData(bean.getLocation())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("location", request)%></font></td>
				</tr>

				<!-- Contact Number -->
				<tr>
					<th align="left">Contact Number <span style="color: red">*</span></th>
					<td><input type="text" name="contactNumber"
						placeholder="Enter Contact Number"
						value="<%=DataUtility.getStringData(bean.getContactNumber())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("contactNumber", request)%></font></td>
				</tr>

				<!-- Importance -->
				<tr>
					<th align="left">Importance <span style="color: red">*</span></th>
					<td><input type="text" name="importance"
						placeholder="Enter Importance"
						value="<%=DataUtility.getStringData(bean.getImportance())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("importance", request)%></font></td>
				</tr>

				<!-- Submit Buttons -->
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=CustomerCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=CustomerCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=CustomerCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=CustomerCtl.OP_RESET%>">
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