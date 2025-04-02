 <%@page import="in.co.rays.util.DataUtility"%>
<%@page import="in.co.rays.ctl.StaffMemberCtl"%>
<%@page import="in.co.rays.util.ServletUtility"%>
<%@page import="java.util.List"%>
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

	<form action="<%=ORSView.STAFFMEMBER_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.StaffMemberBean"
			scope="request" />
		<%
			List staffMemberList = (List) request.getAttribute("staffMemberList");
		%>

		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> Staff Member
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
			<input type="hidden" name="identifier" value="<%=bean.getIdentifier()%>">

			<table>
				<!-- Full Name -->
				<tr>
					<th align="left">Full Name <span style="color: red">*</span></th>
					<td><input type="text" name="fullName"
						placeholder="Enter Full Name"
						value="<%=DataUtility.getStringData(bean.getFullName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("fullName", request)%></font></td>
				</tr>

				<!-- Joining Date -->
				<tr>
					<th align="left">Joining Date <span style="color: red">*</span></th>
					<td><input style="width: 97%" type="text" id="udate" name="joiningDate"
						placeholder="Select Joining Date" /></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("joiningDate", request)%></font></td>

				</tr>

				<!-- Division -->
				<tr>
					<th align="left">Division <span style="color: red">*</span></th>
					<td><input type="text" name="division"
						placeholder="Enter Division"
						value="<%=DataUtility.getStringData(bean.getDivision())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("division", request)%></font></td>
				</tr>
				
				<!-- Previous Employer -->
				<tr>
					<th align="left">Previous Employer <span style="color: red">*</span></th>
					<td><input type="text" name="previousEmployer"
						placeholder="Enter Previous Employer"
						value="<%=DataUtility.getStringData(bean.getPreviousEmployer())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("previousEmployer", request)%></font></td>
				</tr>

				<!-- Submit Buttons -->
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=StaffMemberCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=StaffMemberCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=StaffMemberCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=StaffMemberCtl.OP_RESET%>">
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