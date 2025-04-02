<%@page import="in.co.rays.bean.TimetableBean"%>
<%@page import="in.co.rays.model.TimetableModel"%>
<%@page import="in.co.rays.ctl.TimetableListCtl"%>
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
		<form action="<%=ORSView.TIMETABLE_LIST_CTL%>" method="post">

			<jsp:useBean id="bean" class="in.co.rays.bean.TimetableBean"
				scope="request"></jsp:useBean>

			<div align="center">
				<h1>
					<font color="navy">Timetable List</font>
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
				List subjectList = (List) request.getAttribute("subjectList");
				List list = ServletUtility.getList(request);
				Iterator it = list.iterator();
				if (list.size() != 0) {
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Semester :</b></label> <input
						type="text" name="semester"
						value="<%=ServletUtility.getParameter("semester", request)%>">
						<label><b>Course :</b></label> <%=HTMLUtility.getList("courseId", DataUtility.getStringData(bean.getCourseId()), courseList)%><label><b>Subject
								:</b></label> <%=HTMLUtility.getList("subjectId", DataUtility.getStringData(bean.getSubjectId()), subjectList)%><input
						type="submit" name="operation"
						value="<%=TimetableListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=TimetableListCtl.OP_RESET%>"></td>
				</tr>
			</table>

			<br>

			<table border="1%" style="width: 100%">
				<tr style="background-color: lavender; color: black;">
					<th><input type="checkbox" id="selectall"></th>
					<th>S.No.</th>
					<th>Semester</th>
					<th>Description</th>
					<th>Exame Date</th>
					<th>Exam Time</th>
					<th>Course</th>
					<th>Subject</th>
					<th>Edit</th>

				</tr>
				<%
					while (it.hasNext()) {
							bean = (TimetableBean) it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" class="case" name="ids"
						value="<%=bean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=bean.getSemester()%></td>
					<td><%=bean.getDescription()%></td>
					<td><%=bean.getExamDate()%></td>
					<td><%=bean.getExamTime()%></td>
					<td><%=bean.getCourseName()%></td>
					<td><%=bean.getSubjectName()%></td>
					<td><a href="<%=ORSView.TIMETABLE_CTL%>?id=<%=bean.getId()%>">edit</a></td>

				</tr>
				<%
					}
				%>
			</table>

			<br>

			<table style="width: 100%">
				<tr>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=TimetableListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=TimetableListCtl.OP_NEW%>"></td>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=TimetableListCtl.OP_DELETE%>"></td>
					<td style="text-align: right;"><input type="submit"
						name="operation" value="<%=TimetableListCtl.OP_NEXT%>"
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
						value="<%=TimetableListCtl.OP_BACK%>"></td>
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