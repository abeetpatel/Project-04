<%@page import="in.co.rays.ctl.CollegeCtl"%>
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
	<form action="<%=ORSView.COLLEGE_CTL%>" method="post">

		<!-- CollegeBean Object -->
		<jsp:useBean id="bean" class="in.co.rays.bean.CollegeBean"
			scope="request" />
		<div align="center">


			<!-- Heading -->
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %>Update<%
 	} else {
 %>Add<%
 	}
 %>College
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
				type="hidden" name="createdBy"
				value="<%=DataUtility.getStringData(bean.getCreatedBy())%>">
			<input type="hidden" name="modifiedBy"
				value="<%=DataUtility.getStringData(bean.getModifiedBy())%>">
			<input type="hidden" name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<!-- View Fields -->
			<table>

				<!-- Name -->
				<tr>
					<th align="left">College Name <span style="color: red">*</span></th>
					<td><input type="text" name="name"
						placeholder="Enter College Name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>

				<!-- Address -->
				<tr>
					<th align="left">Address <span style="color: red">*</span></th>
					<td><input type="text" name="address"
						placeholder="Enter Address"
						value="<%=DataUtility.getStringData(bean.getAddress())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("address", request)%></font></td>
				</tr>

				<!-- State -->
				<tr>
					<th align="left">State <span style="color: red">*</span></th>
					<td><input type="text" name="state" placeholder="Enter State"
						value="<%=DataUtility.getStringData(bean.getState())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("state", request)%></font></td>
				</tr>

				<!-- City -->
				<tr>
					<th align="left">City <span style="color: red">*</span></th>
					<td><input type="text" name="city" placeholder="Enter City"
						value="<%=DataUtility.getStringData(bean.getCity())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("city", request)%></font></td>
				</tr>

				<!-- Phone No. -->
				<tr>
					<th align="left">Phone No <span style="color: red">*</span></th>
					<td><input type="tel" name="phoneNo"
						placeholder="Enter Phone No."
						value="<%=DataUtility.getStringData(bean.getPhoneNo())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("phoneNo", request)%></font></td>
				</tr>

				<!-- Submit Buttons -->
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=CollegeCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=CollegeCtl.OP_CANCEL%>"></td>
					<%
						} else {
					%>
					<td><input type="submit" name="operation"
						value="<%=CollegeCtl.OP_SAVE%>"><input type="submit"
						name="operation" value="<%=CollegeCtl.OP_RESET%>"></td>
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