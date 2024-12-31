<%@page import="in.co.rays.ctl.MarksheetCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="in.co.rays.util.DataUtility"%>
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

	<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.MarksheetBean"
			scope="request" />
		<%
			List studentList = (List) request.getAttribute("studentList");
		%>

		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> Marksheet
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
				<!-- Roll No -->
				<tr>
					<th align="left">First Name <span style="color: red">*</span></th>
					<td><input type="text" name="rollNo"
						placeholder="Enter Roll NO"
						value="<%=DataUtility.getStringData(bean.getRollNo())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>
				</tr>

				<!-- Student Id -->

				<tr>
					<th align="left">College Id<span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("studentId", DataUtility.getStringData(bean.getStudentId()), studentList)%></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("studentId", request)%></font></td>
				</tr>

				<!-- Physics -->
				<tr>
					<th align="left">Physics<span style="color: red">*</span> :
					</th>
					<td><input type="number" name="physics" maxlength="3"
						placeholder="Enter Physics No"
						value="<%=(DataUtility.getStringData(bean.getPhysics()).equals("0") ? ""
					: DataUtility.getStringData(bean.getPhysics()))%>">
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("physics", request)%></font></td>
				</tr>

				<!-- Chemistry -->

				<tr>
					<th align="left">Chemistry <span style="color: red">*</span></th>
					<td><input type="number" name="chemistry" maxlength="3"
						placeholder="Enter Chemistry No"
						value="<%=(DataUtility.getStringData(bean.getChemistry()).equals("0") ? ""
					: DataUtility.getStringData(bean.getChemistry()))%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("chemistry", request)%></font></td>
				</tr>

				<!-- Maths -->

				<tr>
					<th align="left">Maths <span style="color: red">*</span></th>
					<td><input type="number" name="maths" maxlength="3"
						placeholder="Enter Maths No"
						value="<%=(DataUtility.getStringData(bean.getMaths()).equals("0") ? ""
					: DataUtility.getStringData(bean.getMaths()))%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("maths", request)%></font></td>
				</tr>

				<!-- Submit Buttons -->
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=MarksheetCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=MarksheetCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=MarksheetCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=MarksheetCtl.OP_RESET%>">
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