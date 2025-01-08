<%@page import="in.co.rays.ctl.FollowUpCtl"%>
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

	<form action="<%=ORSView.FOLLOWUP_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.FollowUpBean"
			scope="request" />

		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> Follow Up Data
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
				<!-- Patient -->
				<tr>
					<th align="left">Patient <span style="color: red">*</span></th>
					<td><input type="text" name="patient"
						placeholder="Enter Patient Name"
						value="<%=DataUtility.getStringData(bean.getPatient())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("patient", request)%></font></td>
				</tr>

				<!-- Doctor -->
				<tr>
					<th align="left">Doctor <span style="color: red">*</span></th>
					<td><input type="text" name="doctor"
						placeholder="Enter Doctor Name"
						value="<%=DataUtility.getStringData(bean.getDoctor())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("doctor", request)%></font></td>
				</tr>

				<!-- Visit Date -->
				<tr>
					<th align="left">Visit Date <span style="color: red">*</span></th>
					<td><input style="width: 97%" type="text" id="sdate"
						name="visitDate" placeholder="Select Visit Date" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("visitDate", request)%></font></td>

				</tr>

				<!-- Fees -->
				<tr>
					<th align="left">Fees <span style="color: red">*</span></th>
					<td><input type="number" name="fees" placeholder="Enter Fees "
						value="<%=(DataUtility.getStringData(bean.getFees()).equals("0") ? "" : bean.getFees())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("fees", request)%></font></td>
				</tr>


				<!-- Submit Buttons -->
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=FollowUpCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=FollowUpCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=FollowUpCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=FollowUpCtl.OP_RESET%>">
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