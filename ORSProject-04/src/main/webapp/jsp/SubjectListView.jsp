<%@page import="in.co.rays.bean.CourseBean"%>
<%@page import="in.co.rays.model.CourseModel"%>
<%@page import="in.co.rays.bean.SubjectBean"%>
<%@page import="in.co.rays.ctl.SubjectListCtl"%>
<%@page import="in.co.rays.util.HTMLUtility"%>
<%@page import="java.util.Iterator"%>
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

	<div align="center">
		<form action="<%=ORSView.SUBJECT_LIST_CTL%>" method="post">

			<jsp:useBean id="bean" class="in.co.rays.bean.SubjectBean"
				scope="request"></jsp:useBean>

			<div align="center">
				<h1>
					<font color="navy">Subject List</font>
				</h1>
			</div>

			<div align="center" style="height: 15px; margin-bottom: 12px">
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
				<h3>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
			</div>

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;
				int nextPageSize = DataUtility.getInt(request.getAttribute("nextListSize").toString());

				List courseList = (List) request.getAttribute("courseList");

				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Subject Name :</b></label> <input
						type="text" name="name"
						value="<%=ServletUtility.getParameter("name", request)%>"><label><b>Course
								:</b></label> <%=HTMLUtility.getList("courseId", DataUtility.getStringData(bean.getId()), courseList)%>

						<input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=SubjectListCtl.OP_RESET%>"></td>
				</tr>
			</table>

			<br>

			<table border="1%" style="width: 100%">
				<tr style="background-color: lavender; color: black;">
					<th><input type="checkbox" id="selectall"></th>
					<th>S.No.</th>
					<th>Name</th>
					<th>Course Name</th>
					<th>Description</th>
					<th>Edit</th>

				</tr>

				<%
					while (it.hasNext()) {
							bean = (SubjectBean) it.next();
							CourseModel model = new CourseModel();
							CourseBean courseBean = model.finedByPk(bean.getCourseId());
				%>

				<tr align="center">
					<td><input type="checkbox" class="case" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getName()%></td>
					<td><%=bean.getCourseName()%></td>
					<td><%=bean.getDescription()%></td>
					<td><a href="<%=ORSView.SUBJECT_CTL%>?id=<%=bean.getId()%>">Edit</a></td>

				</tr>

				<%
					}
				%>

			</table>

			<br>

			<table style="width: 100%">
				<tr>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_NEW%>"></td>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_DELETE%>"></td>
					<td style="text-align: right;"><input type="submit"
						name="operation" value="<%=SubjectListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<br>
			<table>
				<tr>
					<td align="right"><input type="submit" name="operation"
						value="<%=SubjectListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
				}
			%>
		</form>
	</div>

	<%@include file="Footer.jsp"%>

</body>
</html>