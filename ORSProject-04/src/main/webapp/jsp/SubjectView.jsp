<%@page import="in.co.rays.ctl.SubjectCtl"%>
<%@page import="java.util.List"%>
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

	<form action="<%=ORSView.SUBJECT_CTL%>" method="post">

		<jsp:useBean id="bean" class="in.co.rays.bean.SubjectBean"
			scope="request" />

		<%
			List courseList = (List) request.getAttribute("courseList");
		%>

		<div align="center">
			<h1>
				<font color="navy"> <%
 	if (bean != null && bean.getId() > 0) {
 %> Update <%
 	} else {
 %> Add <%
 	}
 %> Subject
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
				<!-- Name -->
				<tr>
					<th align="left">Name <span style="color: red">*</span></th>
					<td><input type="text" name="name"
						placeholder="Enter Subject Name"
						value="<%=DataUtility.getStringData(bean.getName())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("name", request)%></font></td>
				</tr>

				<!-- Course -->

				<tr>
					<th align="left">Course <span style="color: red">*</span></th>
					<td><%=HTMLUtility.getList("courseId", DataUtility.getStringData(bean.getCourseId()), courseList)%></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("courseId", request)%></font></td>
				</tr>


				<!-- Description -->
				<tr>
					<th align="left">Description <span style="color: red">*</span></th>
					<td><input type="text" name="description"
						placeholder="Enter Description "
						value="<%=DataUtility.getStringData(bean.getDescription())%>"></td>
					<td style="position: fixed;"><font color="red"><%=ServletUtility.getErrorMessage("description", request)%></font></td>
				</tr>

				<!-- Submit Buttons -->
				<tr>
					<th></th>
					<%
						if (bean != null && bean.getId() > 0) {
					%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=SubjectCtl.OP_UPDATE%>"> <input
						type="submit" name="operation" value="<%=SubjectCtl.OP_CANCEL%>">
						<%
							} else {
						%>
					<td align="left" colspan="2"><input type="submit"
						name="operation" value="<%=SubjectCtl.OP_SAVE%>"> <input
						type="submit" name="operation" value="<%=SubjectCtl.OP_RESET%>">
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